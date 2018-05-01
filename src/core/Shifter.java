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
		out.set(Integer.toBinaryString(Integer.parseInt(in.get(),2) << 2));
	}
}
