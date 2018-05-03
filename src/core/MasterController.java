package core;

import static core.ComponentManager.*;


public class MasterController
{
	private static int PC = 0;
	public static int offsetLines = 0;
	public static String codeFile = null;
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
		if(InstructionMemory.instOut.get().equals("00000000000000000000000000000000"))
			switch (Integer.parseUnsignedInt(RegisterFile.findRegister("$v0").currentValue, 2))
			{
				case 1:
					System.out.println(Integer.parseUnsignedInt(RegisterFile.findRegister("$a0").currentValue, 2));
					break;
				case 4:


			}
		ControlUnit.execute();
		ALUControl.execute();
		writeRegisterMux.execute();
		immediateExtend.execute();
		signedFlag.execute();
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

	public static void executeAll()
	{
			boolean state = true;
			while(state)
				state = executeStep();
	}

	public static void reset()
	{

	}

}
