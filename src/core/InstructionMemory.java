package core;

import java.util.ArrayList;

public class InstructionMemory
{
	static Data input, output;
	public static ArrayList<String> inMem = new ArrayList<>();

	public static void getOutput()
	{
		output = new Data(read(Integer.parseInt(input.read())));
	}
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
