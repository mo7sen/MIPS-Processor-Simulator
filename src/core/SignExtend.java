package core;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SignExtend
{
	StringProperty in, out, signFlag;
	SignExtend()
	{
		in = new SimpleStringProperty();
		out = new SimpleStringProperty();
		signFlag = new SimpleStringProperty();
	}

	void bindIn(StringProperty stringProperty)
	{
		in.bind(stringProperty);
	}

	void bindSignFlag(StringProperty stringProperty)
	{
		signFlag.bind(stringProperty);
	}

	void getOut()
	{
		if(signFlag.get().equals("1"))
		{
			out.set(extendSigned(in.get(), 32));
		}
		else
		{
			out.set(extendUnsigned(in.get(), 32));
		}
	}

	public static String extendSigned(String num, int size)
	{
		while(num.length() < size)
		{
			num = num.charAt(0) + num;
		}
                while(num.length() > size)
                {
                        num = num.substring(1);
                }
		return num;
	}

	public static String extendUnsigned(String num, int size)
	{
		while(num.length() < size)
		{
			num = "0" + num;
		}
                while(num.length() > size)
                {
                        num = num.substring(1);
                }
		return num;
	}
}
