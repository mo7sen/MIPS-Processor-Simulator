package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.util.ArrayList;

public class RegisterFile
{
	static ArrayList<Register> registers = new ArrayList<>();

	static StringProperty readReg1 = new SimpleStringProperty();
	static StringProperty readReg2 = new SimpleStringProperty();
	static StringProperty writeReg = new SimpleStringProperty();
	static StringProperty writeData = new SimpleStringProperty();
	static StringProperty readData1 = new SimpleStringProperty();
	static StringProperty readData2 = new SimpleStringProperty();
	static StringProperty regWrite = new SimpleStringProperty();

	static void execute()
	{
		readData1.set(findRegister(readReg1.get()).currentValue);
		readData2.set(findRegister(readReg2.get()).currentValue);

		if(regWrite.equals("1"))
			findRegister(writeReg.get()).setValue(writeData.get());
	}

	static Register findRegister(String s)
	{
		for (Register r: registers)
			if(r.name.equals(s) || r.alias.equals(s) || r.address.equals(s))
				return r;
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
				String[] parts = line.split(",");
				registers.add(new Register(parts[0], parts[1], parts[2]));
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
class Register
{
	//	public Data readReg1, readReg2, writeReg, writeData, readData1, readData2, regWriteTrig;
	String name, alias, address, currentValue;
	public Register(String name, String alias, String address)
	{
		this.name = name;
		this.alias = alias;
		this.address = address;
		if(name.equals("$sp"))
			currentValue = Integer.toBinaryString(2621439 * 4);
		else
			currentValue = Integer.toBinaryString(0);
	}

	public void setValue(String newValue)
	{
		this.currentValue = newValue;
	}
}


