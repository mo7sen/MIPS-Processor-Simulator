package core;

public class Data
{
	private String underlyingString = null;

	Data(String s)
	{
		write(s);
	}

	Data(){}

	public void write(String s)
	{
		underlyingString = s;
	}

	public String read()
	{
		return underlyingString;
	}
}