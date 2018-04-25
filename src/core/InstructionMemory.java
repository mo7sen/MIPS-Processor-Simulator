package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class InstructionMemory
{
	static StringProperty addressIn = new SimpleStringProperty();
	static StringProperty instOut = new SimpleStringProperty();

	static void bindIn(StringProperty stringProperty)
	{
		addressIn.bind(stringProperty);
	}

	static void getOut()
	{
		instOut.set(read(Integer.parseInt(addressIn.get(), 2)));
	}
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
