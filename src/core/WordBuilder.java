package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WordBuilder
{
	static StringProperty offsetIn = new SimpleStringProperty("00");
	static StringProperty halfIn = new SimpleStringProperty("0000000000000000");
	static StringProperty byteIn = new SimpleStringProperty("00000000");
	static StringProperty wordIn = new SimpleStringProperty("00000000000000000000000000000000");
	static StringProperty wordOut = new SimpleStringProperty("00000000000000000000000000000000");
	static StringProperty hOp = new SimpleStringProperty("0");
	static String[] placeReserveByte = {"00000000111111111111111111111111","11111111000000001111111111111111","11111111111111110000000011111111","11111111111111111111111100000000"};
	static String[] placeReserveHalf = {"00000000000000001111111111111111","11111111000000000000000011111111","11111111111111110000000000000000"};
	static void execute()
	{
		int offset = Integer.parseUnsignedInt(offsetIn.get(),2);
		String reserveValue = null, wordReserved = null;
		if(hOp.get().equals("1"))
		{
			if(offset == 3)
				return;
			reserveValue = placeReserveHalf[offset];
		}
		else
			reserveValue = placeReserveByte[offset];

		wordReserved = SignExtend.extendUnsigned(Integer.toBinaryString(BinaryParser.parseUnsigned(reserveValue) & BinaryParser.parseUnsigned(wordIn.get())), 32);

		if(hOp.get().equals("1"))
			wordOut.set(SignExtend.extendUnsigned(Integer.toBinaryString((BinaryParser.parseUnsigned(halfIn.get()) << ((2 - offset) * 8)) | BinaryParser.parseUnsigned(wordReserved)),32));
		else
			wordOut.set(SignExtend.extendUnsigned(Integer.toBinaryString((BinaryParser.parseUnsigned(byteIn.get()) << ((3 - offset) * 8)) | BinaryParser.parseUnsigned(wordReserved)),32));
	}
}