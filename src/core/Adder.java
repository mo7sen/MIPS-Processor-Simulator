package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adder
{
	StringProperty in1, in2, out;
	Adder()
	{
		in1 = new SimpleStringProperty();
		in2 = new SimpleStringProperty();
		out = new SimpleStringProperty();
	}

	void bindInputs(StringProperty in1, StringProperty in2)
	{
		this.in1.bind(in1);
		this.in2.bind(in2);
	}

	void getOut()
	{
		out.set(SignExtend.extendUnsigned(Integer.toBinaryString(Integer.parseUnsignedInt(in1.get(),2) + Integer.parseUnsignedInt(in2.get(), 2)),32));
	}
}
