package GUI;

import core.RegisterFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public class Main extends Application
{
    public static Stage stage;
    public static Desktop desktop = Desktop.getDesktop();
    public static javafx.geometry.Rectangle2D screensize = Screen.getPrimary().getVisualBounds();
    public static Scene instScene, excScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("inst_scene.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("exc_scene.fxml"));
//        System.setIn(exc_controller.getIn());
//        System.setOut(exc_controller.getOut());
//        System.setErr(exc_controller.getOut());
        primaryStage.setTitle("MIPS simulator");
        primaryStage.centerOnScreen();
        instScene = new Scene(root, 1000, 500);
        excScene = new Scene(root2, 2000, 1000);
        primaryStage.setScene(instScene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}


//    li $v0, 5
//        syscall
//        move $s1, $v0
//        addi $a0, $s1, 10
//        li $v0, 1
//        syscall