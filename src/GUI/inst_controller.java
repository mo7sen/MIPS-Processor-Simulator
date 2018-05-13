package GUI;

import core.MasterController;
import core.Memory;
import core.Pointer;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import static core.MasterController.codeFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


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
    public TextField startingAddress;
    static MasterController masterController;
    public static TextField pcinit;
    private TextField addMemAddr, addMemData;

    public void switchscene()
    {
        if(exc_controller.consoleview2 != null)
            exc_controller.consoleview2.textArea.setText("");
            
        pcinit = startingAddress;
        new Thread(MasterController::prepareMips).start();
        Main.stage.setScene(Main.excScene);
        Main.stage.setFullScreen(true);
        new MasterController().start();
    }

    @FXML
    public void initialize()
    {
        
        codeFile.bindBidirectional(textarea_isntarea.textProperty());
    }

    public void close()
    {
        System.exit(0);
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
    
    public void helpTheUser()
    {
        textarea_isntarea.appendText("\n\nadd,100000,ArithLog,Register        // rd = rs + rt\n" +
"addu,100001,ArithLog,Register       // rd = rs + rt \"UNSIGNED\"\n" +
"addi,001000,ArithLogI,Immediate     // rd = rs + constant\n" +
"addiu,001001,ArithLogI,Immediate    // rd = rs + constant\n" +
"and,100100,ArithLog,Register        // rd = rs & rt\n" +
"andi,001100,ArithLogI,Immediate     // rd = rs & (unsigned) constant\n" +
"div,011010,DivMult,Register         // hi = rs % rt; lo = rs / rt;\n" +
"divu,011011,DivMult,Register        // hi = rs % rt; lo = rs / rt;\n" +
"mult,011000,DivMult,Register        // hilo = rs * rt\n" +
"multu,011001,DivMult,Register       // hilo = rs * rt\n" +
"nor,100111,ArithLog,Register        // rd = ~(rs | rt)\n" +
"or,100101,ArithLog,Register         // rd = rs | rt\n" +
"ori,001101,ArithLogI,Immediate      // rd = rs | (unsigned) constant\n" +
"lui,001111,LoadI,Immediate          // rd = constant << 16\n" +
"sll,000000,Shift,Register           // rd = rt << amount\n" +
"sra,000011,Shift,Register           // \"SIGNED\" rt >> amount\n" +
"srl,000010,Shift,Register           // rd = \"UNSIGNED\" rt >> amount\n" +
"sub,100010,ArithLog,Register        // rd = rs - rt\n" +
"subu,100011,ArithLog,Register       // rd = rs - rt \"UNSIGNED\"\n" +
"xor,100110,ArithLog,Register        // rd = rs ^ rt\n" +
"xori,001110,ArithLogI,Immediate     // rd = rs ^ (unsigned) constant\n" +
"slt,101010,ArithLog,Register        // rd = (rs < rt)\n" +
"sltu,101001,ArithLog,Register       // rd = (rs < rt)\n" +
"slti,001010,ArithLogI,Immediate     // rd = (rs < constant)\n" +
"sltiu,001011,ArithLogI,Immediate    // rd = (rs < constant)\n" +
"beq,000100,Branch,Immediate         // if (rs == rt) goto label\n" +
"bne,000101,Branch,Immediate         // if (rs != rt) goto label\n" +
"j,000010,Jump,Jump                  // goto label\n" +
"jal,000011,Jump,Jump                // label()\n" +
"jr,001000,JumpR,Register            // (Used w/$31 to translate return)\n" +
"lb,100000,LoadStore,Immediate       // Load Byte\n" +
"lbu,100100,LoadStore,Immediate      // Load Byte \"UNSIGNED\"\n" +
"lh,100001,LoadStore,Immediate       // Load Half-Word\n" +
"lhu,100101,LoadStore,Immediate      // Load Half-Word \"UNSIGNED\"\n" +
"lw,100011,LoadStore,Immediate       // Load Word\n" +
"sb,101000,LoadStore,Immediate       // Save Byte\n" +
"sh,101001,LoadStore,Immediate       // Save Half-Word\n" +
"sw,101011,LoadStore,Immediate       // save Word\n" +
"mfhi,010000,MoveFrom,Register       // rd = hi\n" +
"mflo,010010,MoveFrom,Register       // rd = lo\n" +
"mthi,010001,MoveTo,Register         // hi = rs\n" +
"mtlo,010011,MoveTo,Register         // lo = rs\n" +
"syscall,000000,syscall,Register     // executes a command depending on the value in $v0");
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

        addMemData = new TextField();

        ChoiceBox<String> cb_types = new ChoiceBox<>();
        cb_types.getItems().addAll("Integer","String","Word","HalfWord","Byte");
        cb_types.setValue("int");

        btn_add.setOnAction(e -> getchoice(cb_types));

        addMemAddr = new TextField();

        fieldslayout.getChildren().addAll(addMemData,cb_types,addMemAddr,btn_add);

        baselayout.getChildren().addAll(lblslayout,fieldslayout,btn_done);

        Scene ABscene = new Scene(baselayout,500,200);
        alertbox.setScene(ABscene);
        alertbox.showAndWait();


    }

    public void getchoice(ChoiceBox<String> cb)
    {
        String choice = cb.getValue();
        switch (choice)
        {
            case "Integer":
	            Memory.saveWord(Integer.toBinaryString(Integer.parseInt(addMemData.getText())), new core.Pointer(Integer.parseInt(addMemAddr.getText())));
                break;
            case "String":
            	Memory.saveStringByAddress(addMemData.getText(), new Pointer(Integer.parseInt(addMemAddr.getText())));
                break;
            case "Word":
            	Memory.saveWord(addMemData.getText(), new Pointer(Integer.parseInt(addMemAddr.getText())));
                break;
            case "HalfWord":
            	Memory.saveHWord(addMemData.getText(), new Pointer(Integer.parseInt(addMemAddr.getText())));
                break;
            case "Byte":
            	Memory.saveByte(addMemData.getText(), new Pointer(Integer.parseInt(addMemAddr.getText())));

        }
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
}
