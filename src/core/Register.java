package core;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Register
{
	//	public Data readReg1, readReg2, writeReg, writeData, readData1, readData2, regWriteTrig;
	public StringProperty nameProperty, aliasProperty, addressProperty, currentValueProperty, hexaDecimalProperty, decimalProperty;
	public Register(String nameProperty, String aliasProperty, String addressProperty)
	{
		this.nameProperty = new SimpleStringProperty(nameProperty);
		this.aliasProperty = new SimpleStringProperty(aliasProperty);
		this.addressProperty = new SimpleStringProperty(addressProperty);
		currentValueProperty = new SimpleStringProperty("00000000000000000000000000000000");
		hexaDecimalProperty = new SimpleStringProperty();
		decimalProperty = new SimpleStringProperty();
		decimalProperty.bind(Bindings.createStringBinding(() -> String.valueOf(BinaryParser.parseSigned(currentValueProperty.get())), currentValueProperty));
		hexaDecimalProperty.bind(Bindings.createStringBinding(() -> "0x" + Integer.toHexString(BinaryParser.parseSigned(currentValueProperty.get())), currentValueProperty));
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

	public StringProperty hexaDecimalProperty()
	{
		return this.hexaDecimalProperty;
	}

	public StringProperty decimalProperty()
	{
		return this.decimalProperty;
	}
}
