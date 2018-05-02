package core;

import static core.ComponentManager.*;


public class MasterController
{
	private static int PC = 0;
	public static int offsetLines = 0;
	public static String codeFile = ".text\n" +
			"hello: .asciiz \"hello world\" \n" +
			"mo7senString: .asciiz \"this is mo7sen\" \n" +
			".data\n" +
			"la $t2, hello \n" +
			"la $t3, mo7senString";

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

	public static void executeStep()
	{
		ProgramCounter.execute();
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
	}

	public static void executeAll()
	{
		for(String s : InstructionMemory.inMem)
			executeStep();
	}

	public static void reset()
	{

	}

//	private static void execute(Data codeLine)
//	{
//	}
}
