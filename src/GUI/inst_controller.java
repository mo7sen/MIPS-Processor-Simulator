package GUI;

import core.MasterController;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.*;
import javafx.event.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Desktop;

import static core.MasterController.codeFile;


public class inst_controller {

    @FXML
    public Button btn_run, 
            oneStepBtn;
    public MenuItem mnuitm_close;
    public MenuItem mnuitm_excview;
    public MenuItem mnuitm_import;
    public TextArea textarea_isntarea;
    public MenuItem mnuitm_abtdevs;
    public MenuItem mnuitm_abtprg;
    private int txtcount=0;
    static MasterController masterController;

    //menu functionality---------------------------------------------------\
    public void switchscene()
    {
        Main.stage.setScene(Main.excScene);
//        Main.stage.setFullScreen(true);
	    masterController.start();
    }

    @FXML
    public void initialize()
    {
	    masterController = new MasterController();
        new Thread(MasterController::prepareMips).start();
        codeFile.bindBidirectional(textarea_isntarea.textProperty());
    }

    public void close()
    {
        Main.stage.close();
    }

    public void showalertbox1()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("About the Developers");
        Label label = new Label("MIPS Processor Simulator\n\nDeveloped by:\nRobear Wagih Nabih Selwans\nYoussef Ossama Eid\nMohamed Alaa\nAhmed Tawfeek");
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

    public void showalertbox2()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("About the Program");
        Label label = new Label("About :\n\nMips simulator\nProject Computer Architecture CSE-116\nSubmitted to:\n" +
                                        "DR. Cheriff Salama\n" +
                                        "Eng. Ahmed Fathi\n" +
                                        "Semester: Spring 2018");
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

    public void importfile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import instruction txt file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));

        File file = fileChooser.showOpenDialog(Main.stage.getScene().getWindow());


        FileReader fileReader = new FileReader(new File(String.valueOf(file)));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null)
        {
            textarea_isntarea.appendText(line + "\n");
        }
    }

    public void oneStepSwitchScene(ActionEvent actionEvent)
    {
        exc_controller.setSpeed(1);
        switchscene();
    }


    //menu functionality---------------------------------------------------/


    //txt area functionality-----------------------------------------------/

    /*public void handleenterkey(KeyEvent e)
    {
        if(e.getCode()==KeyCode.ENTER)
        {
            ++txtcount;
            textarea_isntarea.setText(textarea_isntarea.getText()++txtcount+"\t");
        }
    }*/

    //txt area functionality-----------------------------------------------\
}
