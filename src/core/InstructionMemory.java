package core;

import java.util.ArrayList;

public class InstructionMemory
{
	public static ArrayList<String> inMem = new ArrayList<>();
	public static void add(String ins)
	{
		inMem.add(ins);
	}
	public static String read(int address)
	{
		return inMem.get(address/4);
	}
	public static void reset()
	{
		inMem.clear();
	}
}
