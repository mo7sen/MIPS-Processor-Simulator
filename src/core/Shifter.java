package core;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Shifter
{

	SimpleStringProperty in, out;

	Shifter()
	{
		in = new SimpleStringProperty();
		out = new SimpleStringProperty();
	}

	void bindIn(StringProperty stringProperty)
	{
		in.bind(stringProperty);
	}

	void getOut()
	{
		out.set(Integer.toBinaryString(Integer.parseInt(in.get(),2) << 2));
	}
}
