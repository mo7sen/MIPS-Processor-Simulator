package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WordBuilder
{
	static StringProperty offsetIn = new SimpleStringProperty();
	static StringProperty halfIn = new SimpleStringProperty();
	static StringProperty byteIn = new SimpleStringProperty();
	static StringProperty wordIn = new SimpleStringProperty();
	static StringProperty wordOut = new SimpleStringProperty();
	static StringProperty hOp = new SimpleStringProperty();
	static String[] placeReserveByte = {"00000000111111111111111111111111","11111111000000001111111111111111","11111111111111110000000011111111","11111111111111111111111100000000"};
	static String[] placeReserveHalf = {"00000000000000001111111111111111","11111111000000000000000011111111","11111111111111110000000000000000"};
	static void execute()
	{
		int offset = Integer.parseInt(offsetIn.get());
		String reserveValue = null, wordFinal = null, wordReserved = null;
		if(hOp.get().equals("1"))
		{
			reserveValue = placeReserveHalf[offset];
			if(offset == 3)
				return;
		}
		else
		{
			reserveValue = placeReserveByte[offset];
		}
		wordReserved = Integer.toBinaryString(Integer.parseInt(reserveValue,2) & Integer.parseUnsignedInt(halfIn.get(),2));



	}
}
