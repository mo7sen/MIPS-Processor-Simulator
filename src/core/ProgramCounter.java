package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProgramCounter
{
	static StringProperty addressIn = new SimpleStringProperty("00000000000000000000000000000000");
	static StringProperty addressOut = new SimpleStringProperty("00000000000000000000000000000000");

	public static void execute()
	{
		addressOut.setValue(addressIn.get());
	}
}
