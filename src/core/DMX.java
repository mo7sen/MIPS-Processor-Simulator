package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class DMX
{
	StringProperty input = new SimpleStringProperty();
	ArrayList<StringProperty> selectBits = new ArrayList<>();
	ArrayList<StringProperty> outputs = new ArrayList<>();

	public DMX(int n)
	{
		for(int i = 0; i < n; i ++)
		{
			selectBits.add(new SimpleStringProperty());
		}
		for(int i = 0; i < Math.pow(2,n); i++)
		{
			outputs.add(new SimpleStringProperty());
		}
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
		for(StringProperty stringProperty : outputs)
			stringProperty.set(null);
		selection = new StringBuilder(selection).reverse().toString();
		outputs.get(Integer.parseInt(selection, 2)).bind(input);
	}
}
