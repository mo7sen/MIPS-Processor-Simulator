package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WordBreaker
{
	static StringProperty wordIn = new SimpleStringProperty();
	static StringProperty offsetIn = new SimpleStringProperty();
	static StringProperty signedFlag = new SimpleStringProperty();
	static StringProperty halfOut = new SimpleStringProperty();
	static StringProperty byteOut = new SimpleStringProperty();

	static String[] bytePicker = {"11111111000000000000000000000000","00000000111111110000000000000000","00000000000000001111111100000000","00000000000000000000000011111111"},
					halfPicker = {"11111111111111110000000000000000","00000000111111111111111100000000","00000000000000001111111111111111","00000000000000000000000011111111"};

	static void execute()
	{
		int offset = Integer.parseUnsignedInt(offsetIn.get(), 2);
		if(signedFlag.get().equals("1"))
		{
			byteOut.set(SignExtend.extendSigned(Integer.toBinaryString(Integer.parseInt(wordIn.get()) & Integer.parseInt(bytePicker[offset])), 32));
			halfOut.set(SignExtend.extendSigned(Integer.toBinaryString(Integer.parseInt(wordIn.get()) & Integer.parseInt(halfPicker[offset])), 32));
		}
		else
		{
			byteOut.set(SignExtend.extendUnsigned(Integer.toBinaryString(Integer.parseInt(wordIn.get()) & Integer.parseInt(bytePicker[offset])), 32));
			halfOut.set(SignExtend.extendUnsigned(Integer.toBinaryString(Integer.parseInt(wordIn.get()) & Integer.parseInt(halfPicker[offset])), 32));
		}
	}
}
