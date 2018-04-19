package core;

public class Shifter
{
	static Data input, output;
	static void getOutput()
	{
		output.write(Integer.toBinaryString(Integer.parseInt(input.read(),2) << 2));
	}
}
