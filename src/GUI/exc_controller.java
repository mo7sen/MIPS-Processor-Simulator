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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class exc_controller {



    @FXML
    public MenuBar mnubar_menu;
    public MenuItem mnuitm_instview ;
    public MenuItem mnuitm_save;
    public TextArea txtarea_console;
    public TableView table_registers,
            table_inst;
    public Slider slider_speed;
    public TextField sliderValue;
    static TextField placeHolder;

    //menu functionality---------------------------------------------------\
    public void switchinstscene()
    {
//        Main.stage.show();
//
//        if(Main.stage.getHeight()==Main.screensize.getHeight()&&Main.stage.getWidth()==Main.screensize.getWidth())
//        {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("inst_scene.fxml"));
//                Scene scene = new Scene(loader.load());
//                Main.stage.setX(Main.stage.getX() + Main.stage.getWidth());
//                Main.stage.setY(Main.stage.getY() + Main.stage.getHeight());
//                Main.stage.setHeight(Main.screensize.getHeight());
//                Main.stage.setWidth(Main.screensize.getWidth());
//                Main.stage.setScene(scene);
//            }catch (IOException io){
//                io.printStackTrace();
//            }
//            Main.stage.show();
//        }
//
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("inst_scene.fxml"));
//            Scene scene = new Scene(loader.load());
//            Main.stage.setScene(scene);
//        }catch (IOException io){
//            io.printStackTrace();
//        }
        Main.stage.setScene(Main.instScene);
        Main.stage.setFullScreen(false);
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
//        col_reg.setCellValueFactory(new PropertyValueFactory<>("name"));
//        col_data.setCellValueFactory(new PropertyValueFactory<>("currentValue"));
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



    //menu functionality---------------------------------------------------/



}
