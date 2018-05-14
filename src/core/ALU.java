package core;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ALU
{

	static StringProperty input1 = new SimpleStringProperty("00000000000000000000000000000000"),
			input2 = new SimpleStringProperty("00000000000000000000000000000000"),
			ALUOp = new SimpleStringProperty("000"),
			output = new SimpleStringProperty("00000000000000000000000000000000"),
			zero = new SimpleStringProperty("0"),
			arithmetic = new SimpleStringProperty("0"),
			shiftDirection = new SimpleStringProperty("0"),
			signed = new SimpleStringProperty("0"),
			shift = new SimpleStringProperty("0");

	static void bindInput1(StringProperty stringProperty)
	{
		input1.bind(stringProperty);
	}
	static void bindInput2(StringProperty stringProperty)
	{
		input2.bind(stringProperty);
	}
	static void bindALUOp(StringProperty stringProperty)
	{
		ALUOp.bind(stringProperty);
	}
	static void bindArithmetic(StringProperty stringProperty)
	{
		arithmetic.bind(stringProperty);
	}
	static void bindShiftDirection(StringProperty stringProperty)
	{
		shiftDirection.bind(stringProperty);
	}
	static void bindSigned(StringProperty stringProperty)
	{
		signed.bind(stringProperty);
	}
	static void bindShift(StringProperty stringProperty)
	{
		shift.bind(stringProperty);
	}

	static void reset()
	{
		input1 = new SimpleStringProperty("00000000000000000000000000000000");
				input2 = new SimpleStringProperty("00000000000000000000000000000000");
				ALUOp = new SimpleStringProperty("000");
				output = new SimpleStringProperty("00000000000000000000000000000000");
				zero = new SimpleStringProperty("0");
				arithmetic = new SimpleStringProperty("0");
				shiftDirection = new SimpleStringProperty("0");
				signed = new SimpleStringProperty("0");
				shift = new SimpleStringProperty("0");
	}

	static void execute()
	{
		int in1, in2, semiResult = 0;
		if(signed.get().equals("1"))
		{
			in1 = BinaryParser.parseSigned(input1.get());
			in2 = BinaryParser.parseSigned(input2.get());
		}
		else
		{
			in1 = BinaryParser.parseUnsigned(input1.get());
			in2 = BinaryParser.parseUnsigned(input2.get());
		}
		switch (BinaryParser.parseUnsigned(ALUOp.get()))
		{
			case 0://and    0000
				semiResult = in1 & in2;
				break;
			case 1://or     0001
				semiResult = in1 | in2;
				break;
			case 2://add    0010
				semiResult = in1 + in2;
				break;
			case 6://sub    0110
				semiResult = in1 - in2;
				break;
			case 7://slt    0111
				semiResult = (in1<in2)?1:0;
				break;
			case 12://nor   1100
				semiResult = ~(in1|in2);
				break;
			case 13://xor   1101
				semiResult = in1 ^ in2;
				break;
			default:
				semiResult = in1;
		}
		if(BinaryParser.parseUnsigned(shift.get()) != 0)
		{
			int shamt = BinaryParser.parseUnsigned(shift.get());
			if (shiftDirection.get().equals("1")) //Checks for shift-left
				semiResult = semiResult << shamt;
			else
				if (arithmetic.get().equals("1"))
					semiResult = semiResult >> shamt;
				else
					semiResult = semiResult >>> shamt;
		}

		output.set(SignExtend.extendUnsigned(Integer.toBinaryString(semiResult), 32));
		zero.set((semiResult == 0)?"1":"0");
	}
}
