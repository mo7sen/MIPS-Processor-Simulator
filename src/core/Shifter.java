package core;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Shifter
{

	SimpleStringProperty in, out;

	Shifter()
	{
		in = new SimpleStringProperty("00000000000000000000000000000000");
		out = new SimpleStringProperty("00000000000000000000000000000000");
	}

	void bindIn(StringProperty stringProperty)
	{
		in.bind(stringProperty);
	}

	void execute()
	{
		out.set(Integer.toBinaryString(BinaryParser.parseUnsigned(in.get()) << 2));
	}
}
