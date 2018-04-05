package core;

import java.util.ArrayList;

public class InstructionMemory
{
	private static ArrayList<String> inMem = new ArrayList<>();
	public void add(String ins)
	{
		inMem.add(ins);
	}
	public String read(int address)
	{
		return inMem.get(address/4);
	}
}
