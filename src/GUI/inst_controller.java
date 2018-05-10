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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.*;
import javafx.event.*;

import javax.swing.*;
import java.io.*;
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
    public MenuItem mnuitm_save;
    public MenuItem mnuitm_addtomem;
    private int txtcount=0;
    static MasterController masterController;


    //menu functionality---------------------------------------------------\
    public void switchscene()
    {
        Main.stage.setScene(Main.excScene);
        Main.stage.setFullScreen(true);
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
        bufferedReader.close();
    }

    public void savecode()
    {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Program");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));

        File file = fc.showSaveDialog(Main.stage.getScene().getWindow());

        if(file != null)
        {
            try {
                FileWriter fw = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fw);
                pw.write(textarea_isntarea.getText());
                pw.close();

            } catch (IOException ex) {
                showpopup("Error!!","saving file interrupted or file not found");
                ex.printStackTrace();
            }
        }




    }

    public void oneStepSwitchScene(ActionEvent actionEvent)
    {
        exc_controller.setSpeed(1);
        switchscene();
    }

    public void addmemPOPUP()
    {
        Stage alertbox = new Stage();
        alertbox.initModality(Modality.APPLICATION_MODAL);
        alertbox.setTitle("Add data to Memory");

        VBox baselayout = new VBox(10);
        baselayout.setAlignment(Pos.CENTER);


        Button btn_add =  new Button("ADD");
        Button btn_done =  new Button("done");
        btn_done.setOnAction(e ->  alertbox.close() );

        HBox lblslayout = new HBox(100);
        lblslayout.setAlignment(Pos.CENTER);

        Label lbl_data = new Label("Data");
        Label lbl_type = new Label("Type");
        Label lbl_address = new Label("Address");

        lblslayout.getChildren().addAll(lbl_data,lbl_type,lbl_address);

        HBox fieldslayout = new HBox(10);
        fieldslayout.setAlignment(Pos.CENTER);

        TextField txtfld_data = new TextField();

        ChoiceBox<String> cb_types = new ChoiceBox<>();
        cb_types.getItems().addAll("int","string","word","halfword","byte");
        cb_types.setValue("int");

        btn_add.setOnAction(e -> getchoice(cb_types));

        TextField txtfld_address = new TextField();

        fieldslayout.getChildren().addAll(txtfld_data,cb_types,txtfld_address,btn_add);

        baselayout.getChildren().addAll(lblslayout,fieldslayout,btn_done);

        Scene ABscene = new Scene(baselayout,500,200);
        alertbox.setScene(ABscene);
        alertbox.showAndWait();


    }

    public void getchoice(ChoiceBox<String> cb)
    {
        String choice = cb.getValue();
    }

    public void showpopup(String title,String messege)
    {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        Label label = new Label(messege);
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

    //menu functionality---------------------------------------------------/
}
