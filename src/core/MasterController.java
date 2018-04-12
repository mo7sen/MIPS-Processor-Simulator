package core;

public class MasterController
{
	private static int PC = 0;
	public static int offsetLines = 0;
	public static String codeFile = "fact:\n" +
                "addi $sp, $sp, 8\n" +
                "sw $ra, 4($sp)\n" +
                "sw $a0, 0($sp)\n" +
                "slti $t0, $a0, 1\n" +
                "beq $t0, $zero, L1\n" +
                "addi $v0, $zero, 1\n" +
                "addi $sp, $sp, 8\n" +
                "jr $ra\n" +
                "L1: addi $a0, $a0, 1\n" +
                "jal fact\n" +
                "lw $a0, 0($sp)\n" +
                "lw $ra, 4($sp)\n" +
                "addi $sp, $sp, 8\n" +
                "mult $v0, $a0, $v0\n" +
                "jr $ra";

	public static void initiate()
	{
		Instruction.initialize("src/core/Instructions");
		System.out.println("Instructions initialized");
		Registers.initialize("src/core/Registers");
		System.out.println("Registers initialized");

		Assembler.assembleProgram(codeFile);

		while(PC < InstructionMemory.inMem.size() * 4)
		{
			execute(new Data(InstructionMemory.inMem.get(PC/4 + offsetLines)));
			PC += 4;
		}
	}

	private static void execute(Data codeLine)
	{

	}
}
