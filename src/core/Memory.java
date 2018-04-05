package core;

import java.util.ArrayList;

public class Memory
{
	private static boolean device[][] = new boolean[2621440][32];
	private static ArrayList<Variable> variables = new ArrayList<>();
	private static ArrayList<Integer> usedMemory = new ArrayList<>();
	private static Pointer instantiationPointer = new Pointer(0);

	public static void saveByte(String data, Pointer address)
	{
		for (int i = 0; i < 8; i++)
			device[address.address][(address.offset*8)+i] = (data.charAt(i) == '1');
	}

	public static void saveHWord(String data, Pointer address)
	{
		saveByte(data.substring(0,8), address);
		address.moveByte(1);
		saveByte(data.substring(8,16),address);
	}

	public static void saveWord(String data, Pointer address)
	{
		saveHWord(data.substring(0,16),address);
		address.moveByte(1);
		saveHWord(data.substring(16,32),address);
	}

	public static String loadByte(Pointer address)
	{
		String ret = "";
		for(int i = 0; i < 8; i++)
			ret += (device[address.address][(address.offset * 8) + i])?"1":"0";
		return ret;
	}

	static class Pointer
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
		String name, type;
		int address;
	}











	void initializeMemory() {}



















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
