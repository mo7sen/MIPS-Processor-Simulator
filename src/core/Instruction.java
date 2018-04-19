package core;

import java.io.*;
import java.util.ArrayList;

public class Instruction
{
	private static ArrayList<Instruction> instructions = new ArrayList<>();
	String name;
	Syntax syn;
	Encoding enc;
	String opc;
	public Instruction(String name, Syntax syn, Encoding enc, String opc)
	{
		this.name = name;
		this.syn = syn;
		this.enc = enc;
		this.opc = opc;
	}
	public String toString()
	{
		return "\nName: " + name + "\nOpCode/FunctionCode: " + opc + "\nSyntax: " + syn + "\nEncoding: " + enc + "\n-----------------------------";
	}

	public static Instruction searchInstruction(String s)
	{
		for(Instruction i : instructions)
			if(s.equals(i.name))
				return i;
		return null;
	}
	public static void initialize(String file)
	{
		try
		{
			FileReader fileReader = new FileReader(new File(file));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while((line = bufferedReader.readLine())!= null)
			{
				String[] parts = line.split("//")[0].trim().split(",");
				instructions.add(new Instruction(parts[0], Syntax.valueOf(parts[2]), Encoding.valueOf(parts[3]), parts[1]));
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String getSyntaxAsString(Instruction i)
	{
		switch (i.enc.toString())
		{
			case "Register":
				if(i.syn != Syntax.Shift)
					return "000000ssssstttttddddd00000ffffff";
				else
					return "000000ssssstttttdddddaaaaaffffff";

			case "Immediate":
				return "oooooossssstttttiiiiiiiiiiiiiiii";
			case "Jump":
				return "ooooooiiiiiiiiiiiiiiiiiiiiiiiiii";
				default:
					return null;
		}
	}

	public static void showAll()
	{
		System.out.println(instructions.toString());
	}
}
