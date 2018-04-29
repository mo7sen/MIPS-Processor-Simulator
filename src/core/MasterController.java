package core;

public class MasterController
{
	private static int PC = 0;
	public static int offsetLines = 0;
	public static String codeFile = "sub $t5, $t1, $t7";

	public static void prepareMips()
	{
		ComponentManager.provoke();
	}

	public static void run()
	{
		Assembler.assembleProgram(codeFile);
	}

	public static void execute()
	{

	}

	public static void reset()
	{

	}

//	private static void execute(Data codeLine)
//	{
//	}
}
