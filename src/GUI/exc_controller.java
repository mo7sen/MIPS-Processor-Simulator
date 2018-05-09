package GUI;

import core.InstructionLine;
import core.MasterController;
import core.Register;
import core.RegisterFile;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import jp.uphy.javafx.console.ConsoleView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class exc_controller {



    @FXML
    public MenuBar mnubar_menu;
    public MenuItem mnuitm_instview ;
    public MenuItem mnuitm_save;
    public ConsoleView consoleView;
    public TableView table_registers,
            table_inst;
    public Slider slider_speed;
    public TextField sliderValue;
    public Button pauseXresumeBtn;
    static TextField placeHolder;
    public static boolean paused = false;
	MasterController masterController;

	//menu functionality---------------------------------------------------\
    public void switchinstscene()
    {
        Main.stage.setScene(Main.instScene);
        Main.stage.setFullScreen(false);
        RegisterFile.showAll();
        MasterController.reset();

    }

    public void close()
    {
        Main.stage.close();
    }

    public void showalertbox1()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("about developers");
        Label label = new Label("developed by:\n\n\nRobear Wagih Selwans\n\nYoussef Ossma Eid\n\nMohamed Alaa\n\nAhmed Tawfeek");
        label.setFont(Font.font("serif",14));
        Button close = new Button("close");
        close.setOnAction(e -> window.close());

        VBox layout = new VBox(40);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,300,300);
        window.setScene(scene);
        window.showAndWait();
    }

    public void initialize()
    {
    	masterController = inst_controller.masterController;
        System.setIn(consoleView.getIn());
        System.setOut(consoleView.getOut());
        System.setErr(consoleView.getOut());
        MasterController.scan = new Scanner(consoleView.getIn());
        table_registers.setItems(RegisterFile.registers);
        table_inst.setItems(InstructionLine.instructionLines);
        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(sliderValue.textProperty(),slider_speed.valueProperty(),converter);
        slider_speed.setMajorTickUnit(1);
        slider_speed.setMinorTickCount(0);
        slider_speed.setSnapToTicks(true);
        placeHolder = sliderValue;
    }

    public void showalertbox2()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("about Program");
        Label label = new Label("About program :\n\nMips simulator\nProject Computer Architecture CSE-116\nSubmitted to: DR. Cherif Salama\n Semester : spring 2018");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(Font.font("serif",14));
        Button close = new Button("close");
        close.setOnAction(e -> window.close());

        VBox layout = new VBox(40);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,300,300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static int getSpeed()
    {
        return (int) Double.parseDouble(placeHolder.getText());
    }

    public static void setSpeed(int n)
    {
        placeHolder.setText(String.valueOf(n));
    }

	public void pauseXresumeAction(ActionEvent actionEvent)
	{
		if(MasterController.paused)
		{
			MasterController.paused = false;
			pauseXresumeBtn.setText("Pause");
		}
		else
		{
			MasterController.paused = true;
			pauseXresumeBtn.setText("Resume");
		}
	}


	//menu functionality---------------------------------------------------/



}
