package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WordBreaker
{
	static StringProperty wordIn = new SimpleStringProperty("00000000000000000000000000000000");
	static StringProperty offsetIn = new SimpleStringProperty("00");
	static StringProperty signedFlag = new SimpleStringProperty("0");
	static StringProperty halfOut = new SimpleStringProperty("0000000000000000");
	static StringProperty byteOut = new SimpleStringProperty("00000000");

	static String[] bytePicker = {"11111111000000000000000000000000","00000000111111110000000000000000","00000000000000001111111100000000","00000000000000000000000011111111"},
					halfPicker = {"11111111111111110000000000000000","00000000111111111111111100000000","00000000000000001111111111111111","00000000000000000000000011111111"};

	static void execute()
	{
		int offset = Integer.parseUnsignedInt(offsetIn.get(), 2);

		int b = BinaryParser.parseUnsigned(wordIn.get()) & BinaryParser.parseUnsigned(bytePicker[offset]);
		int h = BinaryParser.parseUnsigned(wordIn.get()) & BinaryParser.parseUnsigned(halfPicker[offset]);

		for(int i = 0; i < 3-offset; i++)
			b = b >>> 8;

		for(int i = 0; i < 2-offset; i++)
			h = h >>> 8;


		byteOut.set(SignExtend.extendUnsigned(Integer.toBinaryString(b), 32));
		halfOut.set(SignExtend.extendUnsigned(Integer.toBinaryString(h), 32));

	}
}
