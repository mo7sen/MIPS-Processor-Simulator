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
			reserveValue = placeReserveHalf[offset];
			if(offset == 3)
				return;
		}
		else
			reserveValue = placeReserveByte[offset];
		wordReserved = Integer.toBinaryString(Integer.parseUnsignedInt(reserveValue,2) & Integer.parseUnsignedInt(wordIn.get(),2));
		if(hOp.get().equals("1"))
			wordOut.set(Integer.toBinaryString((Integer.parseInt(halfIn.get(),2) << ((3 - offset) * 8)) | Integer.parseInt(wordReserved)));

		else
			wordOut.set(Integer.toBinaryString((Integer.parseUnsignedInt(byteIn.get(),2) << ((3 - offset) * 8)) | Integer.parseUnsignedInt(wordReserved)));
	}
}