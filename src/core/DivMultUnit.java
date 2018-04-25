package core;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DivMultUnit
{
	static StringProperty input1 = new SimpleStringProperty();
	static StringProperty input2 = new SimpleStringProperty();
	static StringProperty inHi = new SimpleStringProperty();
	static StringProperty inLo = new SimpleStringProperty();
	static StringProperty write = new SimpleStringProperty();
	static StringProperty div = new SimpleStringProperty();
	static StringProperty outHi = new SimpleStringProperty();
	static StringProperty outLo = new SimpleStringProperty();
	static StringProperty active = new SimpleStringProperty();
	static StringProperty signed = new SimpleStringProperty();
	static StringProperty hi = new SimpleStringProperty();
	static StringProperty lo = new SimpleStringProperty();
	static void execute()
	{
		if(write.get().equals("1"))
		{
			hi.set(inHi.get());
			lo.set(inLo.get());
		}

		if(active.get().equals("1"))
		{
			if(div.get().equals("1"))
			{
				if(signed.get().equals("1"))
				{
					hi = Integer.toBinaryString(Integer.parseInt(input1, 2)Integer.parseInt(input2, 2));
					lo = Integer.toBinaryString(Integer.parseInt(input1, 2)Integer.parseInt(input2, 2));
				}
				else
				{
					hi = Integer.toBinaryString(Integer.parseUnsignedInt(input1, 2)Integer.parseUnsignedInt(input2, 2));
					lo = Integer.toBinaryString(Integer.parseUnsignedInt(input1, 2)Integer.parseUnsignedInt(input2, 2));
				}
			}
			else
			{

			}
		}
		if(!outHi.isBound())
			outHi.bind(hi);
		if(!outLo.isBound())
			outLo.bind(lo);
	}
}
