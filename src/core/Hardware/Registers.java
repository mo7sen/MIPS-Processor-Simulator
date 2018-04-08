package core.Hardware;

import java.io.*;
import java.util.ArrayList;

public class Registers
{
	static ArrayList<Registers> registers = new ArrayList<>();
	String name;
	String alias;


	String address;
	int currentValue;
	public Registers(String name, String alias, String address) {
		this.name = name;
		this.alias = alias;
		this.address = address;
		currentValue = 0;
	}



	public static Registers findRegister(String s) {
		for (Registers register: registers)
			if(register.name.equals(s))
				return register;
		for (Registers r: registers)
			if(r.alias.equals(s))
				return r;
		return null;

	}


	public static void initialize(String file) {
		try {
			FileReader fileReader = new FileReader(new File(file));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while((line = bufferedReader.readLine())!= null) {
				String[] parts = line.split(",");
				registers.add(new Registers(parts[0], parts[1], parts[2]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}



