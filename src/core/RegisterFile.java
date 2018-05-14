package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class RegisterFile
{
	public static final ObservableList<Register> registers = FXCollections.observableArrayList();

	static StringProperty readReg1 = new SimpleStringProperty("00000");
	static StringProperty readReg2 = new SimpleStringProperty("00000");
	static StringProperty writeReg = new SimpleStringProperty("00000");
	static StringProperty writeData = new SimpleStringProperty("00000000000000000000000000000000");
	static StringProperty readData1 = new SimpleStringProperty("00000000000000000000000000000000");
	static StringProperty readData2 = new SimpleStringProperty("00000000000000000000000000000000");
	static StringProperty regWrite = new SimpleStringProperty("0");

	static void execute()
	{
		readData1.set(findRegister(readReg1.get()).currentValueProperty.getValue());
		readData2.set(findRegister(readReg2.get()).currentValueProperty.getValue());

		if(regWrite.get().equals("1"))
		{
			findRegister(writeReg.get()).setValue(writeData.get());
		}
	}

	static Register findRegister(String s)
	{
		s = s.trim();
		for (Register r: registers)
			if(r.nameProperty.get().equals(s) || r.aliasProperty.get().equals(s) || r.addressProperty.get().equals(s))
				return r;
		return null;

	}

	public static void showAll()
	{
		for (Register r: registers)
		{
			System.out.println(r.nameProperty.get() + ": " + r.currentValueProperty.get());
		}
	}

	public static void showAllHex()
	{
		for (Register r: registers)
		{
			System.out.println(r.nameProperty.get() + ": " + Integer.toHexString(BinaryParser.parseUnsigned(r.currentValueProperty.get())));
		}
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
			findRegister("$sp").setValue(SignExtend.extendUnsigned(Integer.toBinaryString(2621439 * 4),32));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}


