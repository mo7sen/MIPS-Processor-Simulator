package core;

import java.util.Scanner;
import java.util.regex.Pattern;

import static core.ComponentManager.*;


public class MasterController
{
	private static int PC = 0;
	public static int offsetLines = 0;
	static Scanner scan = new Scanner(System.in);
	public static String codeFile =
			".text\n" +
					"msg1: .asciiz \"Enter n: \"\n" +
					"msg2: .asciiz \"Factorial of n is: \"\n" +
					".data\n" +
					"    la        $a0, msg1\n" +
					"    li        $v0, 4\n" +
					"    syscall\n" +
					"    li        $v0, 5\n" +
					"    syscall\n" +
					"    move      $t0, $v0\n" +
					"    addi      $sp, $sp, -12 \n" +
					"    sw        $t0, 0($sp)   \n" +
					"    sw        $ra, 8($sp) \n" +
					"    jal       factorial\n" +
					"    lw        $ra, 8($sp) \n" +
					"    lw        $s0, 4($sp)  \n" +
					"    addi      $sp, $sp, 12 \n" +
					"    la        $a0, msg2\n" +
					"    li        $v0, 4\n" +
					"    syscall\n" +
					"    move      $a0, $s0\n" +
					"    li        $v0, 1\n" +
					"    syscall\n" +
					"    li        $v0, 10\n" +
					"    syscall\n" +
					"factorial:" +
					"    lw        $t0, 0($sp)\n" +
					"    beq       $t0, 0, return1\n" +
					"    addi      $t0, $t0, -1\n" +
					"    addi      $sp, $sp, -12\n" +
					"    sw        $t0, 0($sp)\n" +
					"    sw        $ra, 8($sp)\n" +
					"    jal       factorial\n" +
					"    lw        $ra, 8($sp)\n" +
					"    lw        $t1, 4($sp)\n" +
					"    lw        $t0, 12($sp)\n" +
					"    mul       $t2, $t1, $t0\n" +
					"    sw        $t2, 16($sp)\n" +
					"    addiu     $sp, $sp, 12\n" +
					"    jr        $ra\n" +
					"return1:li        $t0, 1\n"+
	"sw        $t0, 4($sp)\n"+
	"jr        $ra";

//			pricing questions from the sheet
//

	public static void prepareMips()
	{
		Instruction.initialize("src/core/Instructions");
		System.out.println("Instructions initialized");
		RegisterFile.initialize("src/core/Registers");
		System.out.println("Register initialized");
		Memory.initialize();

		ComponentManager.provoke();
	}

	public static void run()
	{
		Assembler.assembleProgram(codeFile);
		ComponentManager.flowControlMux.output.set(SignExtend.extendUnsigned(Integer.toBinaryString(PC), 32));
//		System.out.println(Integer.parseUnsignedInt(Assembler.findLabel("print int").address,2) / 4);
//		InstructionMemory.showMe();
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
			switch (Integer.parseUnsignedInt(RegisterFile.findRegister("$v0").currentValue, 2))
			{
				case 1:
					System.out.println(BinaryParser.parseSigned(RegisterFile.findRegister("$a0").currentValue));
					break;
				case 4:
					System.out.println(Memory.readStringFromAddress(RegisterFile.findRegister("$a0").currentValue));
					break;
				case 5:
					RegisterFile.findRegister("$v0").setValue(Integer.toBinaryString(scan.nextInt()));
					break;
				case 8:
					Memory.saveString(scan.next(Pattern.compile(".{0," + Integer.parseUnsignedInt(RegisterFile.findRegister("$a1").currentValue) + "}?")), "this is a saved string that I donot want anyone to find please", true);
					break;
				case 10:
					return false;
				case 11:
					System.out.println((char) BinaryParser.parseUnsigned(RegisterFile.findRegister("$a0").currentValue));
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
//		System.out.println(Memory.readStringFromAddress(Memory.findVariable("newString").address.toString()));
//		System.out.println(Memory.findVariable("string").address);
//		System.out.println(Memory.instantiationPointer.toString());
//		System.out.println(Assembler.codeLines.get(Integer.parseInt(ProgramCounter.addressOut.get(), 2) / 4).trim());
//		System.out.println(ALU.output.get());
//		System.out.println(InstructionMemory.instOut.get());
//		System.out.println(Memory.addressIn.get());
//		System.out.println(Integer.parseUnsignedInt(ProgramCounter.addressOut.get(),2) / 4);
//		System.out.println(RegisterFile.findRegister("$a0").currentValue);
//		System.out.println(Assembler.findLabel("done").address);

		return true;
	}

	public static void executeAll()
	{
		boolean state = true;
		while(state)
			state = executeStep();
	}
}
