package core;

public class Memory
{
	private static boolean device[][] = new boolean[2621440][32];

	static String loadWord(int address)
	{
		address /= 4;
		String loaded = "";
		for ( boolean b : device[address])
			if (b)
				loaded += "1";
			else
				loaded += "0";
		return loaded;
	}

	static void saveWord(String data, int address)
	{
		address /= 4;
		for(int i = 0; i < 32; i++)
		{
			device[address][i] = data.charAt(i) == '1';
		}
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
