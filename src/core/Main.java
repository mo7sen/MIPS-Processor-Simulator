package core;

import com.sun.org.apache.xpath.internal.operations.And;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


public class Main
{

    public static void main(String[] args)
    {

        //MasterController.initiate();
//        String code = "fact:\n" +
//                "addi $sp, $sp, 8\n" +
//                "sw $ra, 4($sp)\n" +
//                "sw $a0, 0($sp)\n" +
//                "slti $t0, $a0, 1\n" +
//                "beq $t0, $zero, L1\n" +
//                "addi $v0, $zero, 1\n" +
//                "addi $sp, $sp, 8\n" +
//                "jr $ra\n" +
//                "L1: addi $a0, $a0, 1\n" +
//                "jal fact\n" +
//                "lw $a0, 0($sp)\n" +
//                "lw $ra, 4($sp)\n" +
//                "addi $sp, $sp, 8\n" +
//                "mult $v0, $a0, $v0\n" +
//                "jr $ra";
       // String code = "addi $a0, $a0, 1";

//        Assembler.assembleProgram(code);
//        System.out.println(Integer.toBinaryString(-8));
//        for(int i = 0; i<Assembler.labels.size(); i++)
//        {
//            System.out.println(Assembler.labels.get(i).name);
//        }
//        Instruction.showAll();
//        String trial = "hello bitch";
//        System.out.println(trial.substring(0,5));
//        System.out.println(trial.substring(5,11));
        StringProperty str = new SimpleStringProperty("hello world this is mo7sen");
        StringProperty str2 = new SimpleStringProperty();
        str2.bind(Bindings.createStringBinding(() -> str.get().substring(0,8), str));
        System.out.println(str2.get());
        str.set("1100110011001100");
        System.out.println(str2.get());
    }
}
