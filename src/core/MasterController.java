package core;

public class MasterController
{
	private static int PC = 0;
	public static int offsetLines = 0;
	public static String codeFile = "sub $t5, $t1, $t7";

	public static void initiate()
	{
		Instruction.initialize("src/core/Instructions");
		System.out.println("Instructions initialized");
		Registers.initialize("src/core/Registers");
		System.out.println("Registers initialized");
		Memory.initialize();

		Assembler.assembleProgram(codeFile);

		while(PC < InstructionMemory.inMem.size() * 4)
		{
			execute(new Data(InstructionMemory.inMem.get(PC/4 + offsetLines)));
			PC += 4;
		}
	}

	private static void execute(Data codeLine)
	{
		System.out.println(codeLine.read());
	}
}
