package core;

import java.math.BigInteger;

public class BinaryParser
{
	public static int parseUnsigned(String s)
	{
		return Integer.parseUnsignedInt(s, 2);
	}

	public static int parseSigned(String s)
	{
		int size = s.length(),
				positivePart = Integer.parseInt(s.substring(1, size), 2),
				negativePart = (s.substring(0,1).equals("1"))? Integer.parseUnsignedInt(new BigInteger("2").pow(size - 1).toString(), 10):0;
//		System.out.println(s);
//		System.out.println(Integer.parseUnsignedInt(new BigInteger("2").pow(size - 1).toString(), 10));
		return positivePart - negativePart;
	}
}
