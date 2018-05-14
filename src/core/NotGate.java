package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NotGate
{
	StringProperty in = new SimpleStringProperty("0"),
	out = new SimpleStringProperty("0");

	public void bindIn(StringProperty stringProperty)
	{
		in.bind(stringProperty);
	}

	public void execute()
	{
		out.set(Integer.toBinaryString(~(Integer.parseInt(in.get()))));
	}
}
