package core;

import javax.rmi.CORBA.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.BitSet;

public class Main
{

    public static void main(String[] args)
    {

//        String code = "fact:\n" +
//                "addi $sp, $sp, 8\n" +
//                "sw $ra, 4($sp)\n" +
//                "sw $a0, 0($sp)\n" +
//                "slti $t0, $a0, 1\n" +
//                "beq $t0, $zero, L1\n" +
//                "addi $v0, $zero, 1\n" +
//                "addi $sp, $sp, 8\n" +
//                "jr $ra # and return\n" +
//                "L1: addi $a0, $a0, 1\n" +
//                "jal fact\n" +
//                "lw $a0, 0($sp)n" +
//                "lw $ra, 4($sp)\n" +
//                "addi $sp, $sp, 8\n" +
//                "mul $v0, $a0, $v0\n" +
//                "jr $ra";
//        Instruction.initialize("src/core/Instructions");
//        Registers.initialize("src/core/Registers");
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
        System.out.println(SignExtend.extendUnsigned(Integer.toBinaryString((int)'c'), 8));
    }
}
