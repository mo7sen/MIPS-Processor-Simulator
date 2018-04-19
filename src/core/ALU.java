package core;

public class ALU
{
	static Data input1, input2, ALUOp, output, zero;

	public static void doOp()
	{
		int in1 = Integer.parseInt(input1.read(),2);
		int in2 = Integer.parseInt(input2.read(),2);

		switch (Integer.parseInt(ALUOp.read(),2))
		{
			case 0:     //AND
				output.write(SignExtend.extendUnsigned(Integer.toBinaryString(in1 & in2),32));
				break;
			case 1:     //OR
				output.write(SignExtend.extendUnsigned(Integer.toBinaryString(in1 | in2),32));
				break;
			case 2:     //add
				output.write(SignExtend.extendUnsigned(Integer.toBinaryString(in1 + in2),32));
				break;
			case 6:     //sub
				output.write(SignExtend.extendUnsigned(Integer.toBinaryString(in1 - in2),32));
				break;
			case 7:     //Set less-than
				output.write(SignExtend.extendUnsigned((in1 < in2)?"1":"0",32));
				break;
			case 12:    // NOR
				output.write(SignExtend.extendUnsigned(Integer.toBinaryString(~( in1 | in2 )), 32));
				default:
		}
	}
}
