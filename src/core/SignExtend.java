package core;

public class SignExtend
{
	static Data input, output;
	SignExtend(){}

	public static Data getOutput()
	{
		output.write(extendSigned(input.read(),32));
		return output;
	}

	public static String extendSigned(String num, int size)
	{
		while(num.length() < size)
		{
			num = num.charAt(0) + num;
		}
                while(num.length() > size)
                {
                        num = num.substring(1);
                }
		return num;
	}

	public static String extendUnsigned(String num, int size)
	{
		while(num.length() < size)
		{
			num = "0" + num;
		}
                while(num.length() > size)
                {
                        num = num.substring(1);
                }
		return num;
	}
}
