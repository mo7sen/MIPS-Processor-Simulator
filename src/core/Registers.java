package core;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Registers
{
	public Data readReg1, readReg2, writeReg, writeData, readData1, readData2, regWriteTrig;
	static ArrayList<Registers> registers = new ArrayList<>();
	String name, alias, address;
	int currentValue;
	public Registers(String name, String alias, String address)
	{
		this.name = name;
		this.alias = alias;
		this.address = address;
		if(name.equals("$sp"))
			currentValue = 2621439 * 4;
			else
				currentValue = 0;
	}

	public void setValue(int newValue)
	{
		this.currentValue = newValue;
	}

	static void refresh()
	{

	}

	static Registers findRegister(String s)
	{
		for (Registers r: registers)
			if(r.name.equals(s))
				return r;
		for (Registers r: registers)
			if(r.alias.equals(s))
				return r;
		return null;

	}

	static void setRegisterValue(String registerName, int newValue)
	{
		for (Registers r: registers)
			if(r.name.equals(registerName))
				r.setValue(newValue);
		for (Registers r: registers)
			if(r.alias.equals(registerName))
				r.setValue(newValue);
	}


	public static void initialize(String file)          //Reading all the Registers from a file with the data needed in it
	{
		try
		{
			FileReader fileReader = new FileReader(new File(file));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while((line = bufferedReader.readLine())!= null)
			{
				String[] parts = line.split(",");
				registers.add(new Registers(parts[0], parts[1], parts[2]));
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



