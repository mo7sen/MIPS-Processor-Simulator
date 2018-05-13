package GUI;

import core.InstructionLine;
import core.MasterController;
import core.RegisterFile;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import jp.uphy.javafx.console.ConsoleView;
import java.util.Scanner;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class exc_controller {

    static ConsoleView consoleview2;

    @FXML
    public MenuBar mnubar_menu;
    public MenuItem mnuitm_instview ;
    public ConsoleView consoleView;
    public TableView table_registers,
            table_inst;
    public Slider slider_speed;
    public TextField sliderValue;
    public Button pauseXresumeBtn;
    static TextField placeHolder;

    public static boolean paused = false;

	//menu functionality---------------------------------------------------\
    public void switchinstscene()
    {
        Main.stage.setScene(Main.instScene);
        MasterController.hardReset();
    }

    public void close()
    {
        System.exit(0);
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
        consoleview2 = consoleView;
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

        
        public void helpTheUser()
        {
            System.out.print(
                    "add,100000,ArithLog,Register        // rd = rs + rt\n" +
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
}
