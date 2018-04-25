package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NotGate
{
	StringProperty in = new SimpleStringProperty(),
	out = new SimpleStringProperty();

	public void bindIn(StringProperty stringProperty)
	{
		in.bind(stringProperty);
	}

	public void execute()
	{
		out.set(Integer.toBinaryString(~(Integer.parseInt(in.get()))));
	}
}
