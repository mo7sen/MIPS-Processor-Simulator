package core.Hardware;

import java.util.ArrayList;

public class InstructionMemory
{
	private static ArrayList<String> instructionMemory = new ArrayList<>();
	public static void add(String ins){
		instructionMemory.add(ins);
	}
	public static String read(int address) {
		return instructionMemory.get(address/4);
	}
}
