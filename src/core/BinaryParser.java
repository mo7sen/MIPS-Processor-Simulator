package core;

public class BinaryParser
{
	public static int parseUnsigned(String s)
	{
		int result = 0;
		for(int i = s.length() - 1; i >= 0; i--)
			if(s.charAt(i) == '1')
				result += Math.pow(2, s.length() - 1 - i);

		return result;
	}

	public static int parseSigned(String s)
	{
		int result = 0;
		for(int i = s.length() - 1; i > 0; i--)
			if(s.charAt(i) == '1')
				result += Math.pow(2, s.length() - 1 - i);
		if(s.charAt(0) == '1')
			result -= Math.pow(2, s.length() - 1);

		return result;
	}
}
