package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Register
{
	//	public Data readReg1, readReg2, writeReg, writeData, readData1, readData2, regWriteTrig;
	public StringProperty nameProperty, aliasProperty, addressProperty, currentValueProperty;
	public Register(String nameProperty, String aliasProperty, String addressProperty)
	{
		this.nameProperty = new SimpleStringProperty(nameProperty);
		this.aliasProperty = new SimpleStringProperty(aliasProperty);
		this.addressProperty = new SimpleStringProperty(addressProperty);
		currentValueProperty = new SimpleStringProperty("00000000000000000000000000000000");
	}

	public StringProperty nameProperty()
	{
		return this.nameProperty;
	}

	public StringProperty currentValueProperty()
	{
		return this.currentValueProperty;
	}
	public void setValue(String newValue)
	{
		this.currentValueProperty.set(newValue);
	}
}
