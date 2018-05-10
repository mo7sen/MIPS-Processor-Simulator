package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class DMX
{
	StringProperty input = new SimpleStringProperty("00000000000000000000000000000000");
	ArrayList<StringProperty> selectBits = new ArrayList<>();
	ArrayList<StringProperty> outputs = new ArrayList<>();

	public DMX(int n)
	{
		for(int i = 0; i < n; i ++)
		{
			selectBits.add(new SimpleStringProperty("0"));
		}
		for(int i = 0; i < Math.pow(2,n); i++)
		{
			outputs.add(new SimpleStringProperty("00000000000000000000000000000000"));
		}
	}

	void reset()
	{
		for(StringProperty stringProperty:outputs)
			stringProperty.set("00000000000000000000000000000000");
	}

	public void bindInput(StringProperty stringProperty)
	{
		input.bind(stringProperty);
	}

	public void execute()
	{
		String selection = "";
		for(StringProperty stringProperty : selectBits)
			selection += stringProperty.get();
		selection = new StringBuilder(selection).reverse().toString();
		outputs.get(BinaryParser.parseUnsigned(selection)).bind(input);
	}
}
