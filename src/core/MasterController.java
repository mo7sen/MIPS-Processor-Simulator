package core;

import GUI.exc_controller;
import GUI.inst_controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Scanner;
import java.util.regex.Pattern;

import static core.ComponentManager.*;


public class MasterController extends Thread
{
	protected static IntegerProperty PC = new SimpleIntegerProperty();
	public static int offsetLines = 0;
	public static Scanner scan = new Scanner(System.in);
	public static StringProperty codeFile = new SimpleStringProperty();
	public static boolean paused = false;

	public static void prepareMips()
	{

		Instruction.initialize("src/core/Instructions");
		System.out.println("Instructions initialized");
		RegisterFile.initialize("src/core/Registers");
		System.out.println("Register initialized");
		Memory.initialize();
		PC.bind(Bindings.createIntegerBinding(() -> Integer.parseInt(inst_controller.pcinit.textProperty().get()), inst_controller.pcinit.textProperty()));
		ComponentManager.provoke();
	}

	@Override
    public void run()
    {
        MasterController.configure();
        MasterController.executeAll();
    }

	public static void configure()
	{
		Assembler.assembleProgram(codeFile.get());
		ComponentManager.flowControlMux.output.set(SignExtend.extendUnsigned(Integer.toBinaryString(PC.get()), 32));
	}

	public static boolean executeStep()
	{
		ProgramCounter.execute();
		if(Integer.parseInt(ProgramCounter.addressOut.get(),2)/4 >= InstructionMemory.inMem.size())
			return false;
		pcIncrementer.execute();
		InstructionMemory.execute();
		if(InstructionMemory.instOut.get().equals("00000000000000000000000000000000"))
		{
			switch (Integer.parseUnsignedInt(RegisterFile.findRegister("$v0").currentValueProperty.get(), 2))
			{
				case 1:
					System.out.print(BinaryParser.parseSigned(RegisterFile.findRegister("$a0").currentValueProperty.get()));
					break;
				case 4:
					System.out.println(Memory.readStringFromAddress(RegisterFile.findRegister("$a0").currentValueProperty.get()));
					break;
				case 5:
					RegisterFile.findRegister("$v0").setValue(SignExtend.extendUnsigned(Integer.toBinaryString(scan.nextInt()),32));
					break;
				case 8:
					Memory.saveString(scan.next(Pattern.compile(".{0," + Integer.parseUnsignedInt(RegisterFile.findRegister("$a1").currentValueProperty.get()) + "}?")), "this is a saved string that I donot want anyone to find please", true);
					break;
				case 10:
					return false;
				case 11:
					System.out.print((char) BinaryParser.parseUnsigned(RegisterFile.findRegister("$a0").currentValueProperty.get()));
					break;
				case 12:
					RegisterFile.findRegister("$v0").setValue(String.valueOf(scan.next().charAt(0)));
					break;
			}
		}
		ControlUnit.execute();
		ALUControl.execute();
		signedFlag.execute();
		writeRegisterMux.execute();
		immediateExtend.execute();
		regWriteFlag.execute();
		RegisterFile.execute();
		shamtMux.execute();
		setHiLo.execute();
		DivMultUnit.execute();
		hiLoReader.execute();
		aluSrcMux.execute();
		ALU.execute();
		eqIdentifier.execute();
		eqFlag.execute();
		equalMux.execute();
		jumpRFlag.execute();
		jumpRMux.execute();
		left2BitShifter.execute();
		branchCalculator.execute();
		flowControlMux.execute();
		Memory.execute();
		WordBreaker.execute();
		memReadDataMux.execute();
		dataOutMux.execute();
		memOrReg.execute();
		WordBuilder.execute();
		memWriteDataMux.execute();
		Memory.execute();
		regWriteDataMux.execute();
		writeRegisterMux.execute();
		RegisterFile.execute();
		return true;
	}

	public static void reset()
	{
		pcIncrementer.reset();
		InstructionMemory.reset();
		ControlUnit.reset();
		ALUControl.reset();
		signedFlag.reset();
		writeRegisterMux.reset();
		immediateExtend.reset();
		regWriteFlag.reset();
		RegisterFile.reset();
		shamtMux.reset();
		setHiLo.reset();
		DivMultUnit.reset();
		hiLoReader.reset();
		aluSrcMux.reset();
		ALU.reset();
		eqIdentifier.reset();
		eqFlag.reset();
		equalMux.reset();
		jumpRFlag.reset();
		jumpRMux.reset();
		left2BitShifter.reset();
		branchCalculator.reset();
		flowControlMux.reset();
		Memory.reset();
		WordBreaker.reset();
		memReadDataMux.reset();
		dataOutMux.reset();
		memOrReg.reset();
		WordBuilder.reset();
		memWriteDataMux.reset();
		regWriteDataMux.reset();
	}

	public static void hardReset()
	{
		reset();
		InstructionMemory.clear();
	}

	public static void executeAll()
	{
		boolean state = true;
		while (state)
		{
			while(paused)
			{
				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}

				try
				{
					state = executeStep();
					int speedOfExecution = GUI.exc_controller.getSpeed();
					if (speedOfExecution != 0)
						Thread.sleep(1000 * (1 / GUI.exc_controller.getSpeed()));
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
		}
	}
}

//.text
//		msg1: .asciiz "Enter n: "
//		msg2: .asciiz "Factorial of n is: "
//		.data
//		la        $a0, msg1
//		li        $v0, 4
//		syscall
//		li        $v0, 5
//		syscall
//		move      $t0, $v0
//		addi      $sp, $sp, -12
//		sw        $t0, 0($sp)
//		sw        $ra, 8($sp)
//		jal       factorial
//		lw        $ra, 8($sp)
//		lw        $s0, 4($sp)
//		addi      $sp, $sp, 12
//		la        $a0, msg2
//		li        $v0, 4
//		syscall
//
//		move      $a0, $s0
//		li        $v0, 1
//		syscall
//
//		li        $v0, 10
//		syscall
//
//		factorial:
//		lw        $t0, 0($sp)
//		beq       $t0, 0, return1
//
//		addi      $t0, $t0, -1
//		addi      $sp, $sp, -12
//		sw        $t0, 0($sp)
//		sw        $ra, 8($sp)
//		jal       factorial
//		lw        $t1, 4($sp)
//		lw        $ra, 8($sp)
//		addiu     $sp, $sp, 12
//		lw        $t0, 0($sp)
//		mul       $t2, $t1, $t0
//		sw        $t2, 4($sp)
//
//		jr        $ra
//
//		return1:
//		li        $t0, 1
//		sw        $t0, 4($sp)
//		jr        $ra
//
