package core;

import com.sun.org.apache.xpath.internal.operations.And;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.math.BigInteger;


public class Main
{
    static StringProperty s1, s0;

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

        StringProperty stot = new SimpleStringProperty();
        s1 = new SimpleStringProperty();

        s0 = new SimpleStringProperty("0");
        stot.bind(s0);
        s1.bind(s0);
        System.out.println(stot.get());
        System.out.println(s1.get());
        s0.set("1");
        System.out.println(stot.get());
        System.out.println(s1.get());
    }
}
