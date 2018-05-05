package core;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AndGate
{
	StringProperty  in1 = new SimpleStringProperty("0"),
					in2 = new SimpleStringProperty("0"),
					out = new SimpleStringProperty("0");

	public void bindInputs(StringProperty stringProperty1, StringProperty stringProperty2)
	{
		in1.bind(stringProperty1);
		in2.bind(stringProperty2);
	}

	public void bindInput1(StringProperty stringProperty)
	{
		in1.bind(stringProperty);
	}

	public void bindInput2(StringProperty stringProperty)
	{
		in2.bind(stringProperty);
	}

	public void execute()
	{
		out.set(Integer.toBinaryString(BinaryParser.parseUnsigned(in1.get()) & BinaryParser.parseUnsigned(in2.get())));
	}
	void reset()
	{
		in1 = new SimpleStringProperty("0");
				in2 = new SimpleStringProperty("0");
				out = new SimpleStringProperty("0");
	}
}
