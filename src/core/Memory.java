package core;

import java.util.ArrayList;

public class Memory
{
	private static boolean device[][] = new boolean[2621440][32];
	private static ArrayList<Variable> variables = new ArrayList<>();
	private static Pointer instantiationPointer = new Pointer(0);
	public static Data address, readData, writeData, memRead, memWrite;

	private static Variable findVariable(String var)
	{
		for(Variable v : variables)
                {
			if(v.name.equals(var))
			{
				return v;
			}
		}
		return null;
	}

	public static void refresh()
	{

	}

	public static void saveByte(String data, Pointer address)
	{
		for (int i = 0; i < 8; i++)
			device[address.address][(address.offset*8)+i] = (data.charAt(i) == '1');
		address.moveByte(1);
	}

	public static void saveHWord(String data, Pointer address)
	{
		saveByte(data.substring(0,8), address);
		saveByte(data.substring(8,16),address);
	}

	public static void saveWord(String data, Pointer address)
	{
		saveHWord(data.substring(0,16),address);
		saveHWord(data.substring(16,32),address);
	}
	
	public static void saveArrayEmpty(int sizeBytes,  String name) 
	{
		variables.add(new Variable(name, instantiationPointer));
		instantiationPointer.moveByte(sizeBytes);
	} 
	
	
	
		
	

	public static void saveString(String data, String name, boolean nullTerminated)
	{
		char[] datach = data.toCharArray();
		variables.add(new Variable(name, instantiationPointer));

		for(char c : datach)
		{
			if(c != 0)
			{
				saveByte(SignExtend.extendUnsigned(Integer.toBinaryString((int) c), 8), instantiationPointer);
			}
			else
				break;
		}
		if(nullTerminated)
		{
			saveByte("00000000", instantiationPointer);
		}
	}
        
	public static void saveInt(int data, String name)
	{
		variables.add(new Variable(name, instantiationPointer));
		saveWord(SignExtend.extendUnsigned(Integer.toBinaryString(data),32), instantiationPointer);
	}
        
	public static int readInt(String name)
	{
		Pointer address = findVariable(name).address;
		return Integer.parseUnsignedInt(loadWord(address),2);
	}
	public static String readString(String name)
	{
		String res = "";
		char c;
		Pointer address = findVariable(name).address;
		while(true)
		{
			c = (char) Integer.parseInt(loadByte(address),2);
			if((int)c != 0)
			{
				res += c;
				address.moveByte(1);
			}
			else
				break;
		}
		return res;
	}

	public static String loadByte(Pointer address)
	{
		String ret = "";
		for(int i = 0; i < 8; i++)
			ret += (device[address.address][(address.offset * 8) + i])?"1":"0";
		return ret;
	}

	public static String loadHWord(Pointer address)
	{
		String ret = "";
		ret += loadByte(address);
		address.moveByte(1);
		ret += loadByte(address);
		return ret;
	}

	public static String loadWord(Pointer address)
	{
		String ret = "";
		ret += loadHWord(address);
		address.moveByte(1);
		ret += loadHWord(address);
		return ret;
	}
        
	static void showMemory()
	{
		for(int i = 0; i < 2621440; i++)
		{
			for(int j = 0; j<32; j++)
				System.out.print((device[i][j])?"1":"0");
			System.out.println();
		}
	}

	static void randomize()
	{
		for(int i = 0; i< 2621440; i++)
		{
			for(int j = 0; j < 32 ; j++)
			{
				device[i][j] = (Math.random()>0.5)?true:false;
			}
		}
	}
}
class Pointer
{
	int address;
	int offset;
	void moveByte(int bytes)
	{
		if(bytes + offset >= 4)
		{
			address+=1;
			bytes-=4;
			moveByte(bytes);
		}
		else if (bytes + offset < 0)
		{
			address-=1;
			bytes+=4;
			moveByte(bytes);
		}
		else
		{
			offset += bytes;
		}
	}
	Pointer(int address)
	{
		this.address = address;
		this.offset = address%4;
	}
}

class Variable
{
	String name;
	Pointer address;
	Variable(String name, Pointer a)
	{
		this.name = name;
		this.address = new Pointer(a.address + a.offset);
	}
}
