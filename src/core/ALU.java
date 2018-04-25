package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ALU
{

	static StringProperty input1 = new SimpleStringProperty(),
			input2 = new SimpleStringProperty(),
			ALUOp = new SimpleStringProperty(),
			output = new SimpleStringProperty(),
			zero = new SimpleStringProperty(),
			arithmetic = new SimpleStringProperty(),
			shiftDirection = new SimpleStringProperty(),
			signed = new SimpleStringProperty(),
			shift = new SimpleStringProperty();

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

	static void execute()
	{

	}
}
