package core;

public class MasterController
{
	private int PC;
	public static String codeFile = null;

	public static void initiate()
	{
		Instruction.initialize("src/core/Instructions");
		System.out.println("Instructions initialized");
		Registers.initialize("src/core/Registers");
		System.out.println("Registers initialized");

		Assembler.assembleProgram(codeFile);

		for(String codeLine : InstructionMemory.inMem)
		{
			execute(new Data(codeLine));
		}
	}

	private static void execute(Data codeLine)
	{

	}
}
