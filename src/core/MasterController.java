package core;

import static core.ComponentManager.*;


public class MasterController
{
	private static int PC = 0;
	public static int offsetLines = 0;
	public static String codeFile = ".text\n" +
			" " +
			".data\n" +
			"main: addi $s0, $zero, 1\n" +
			"loop:slti $s1, $s0, 10\n" +
			"beq $s1, 0, exit\n" +
			"addi $s0, $s0, 1\n" +
			"j loop\n" +
			"exit: addi $t0, $zero, 365";
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
	}

	public static boolean executeStep()
	{
		ProgramCounter.execute();
		if(Integer.parseInt(ProgramCounter.addressOut.get(),2)/4 >= InstructionMemory.inMem.size())
			return false;
		pcIncrementer.execute();
		InstructionMemory.execute();
		writeRegisterMux.execute();
		ControlUnit.execute();
		ALUControl.execute();
		immediateExtend.execute();
		signedFlag.execute();
		regWriteFlag.execute();
		RegisterFile.execute();
		setHiLo.execute();
		DivMultUnit.execute();
		hiLoReader.execute();
		aluSrcMux.execute();
		shamtMux.execute();
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

	public static void executeAll()
	{
			boolean state = true;
			while(state)
				state = executeStep();
	}

	public static void reset()
	{

	}

//	private static void execute(Data codeLine)
//	{
//	}
}
