package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ALUControl
{
	static StringProperty controlIn = new SimpleStringProperty("000");
	static StringProperty functIn = new SimpleStringProperty("000000");
	static StringProperty ALUOpOut = new SimpleStringProperty("0000");
	static StringProperty shift = new SimpleStringProperty("0");
	static StringProperty jumpR = new SimpleStringProperty("0");
	static StringProperty div = new SimpleStringProperty("0");
	static StringProperty divMult = new SimpleStringProperty("0");
	static StringProperty lo = new SimpleStringProperty("0");
	static StringProperty mWrite = new SimpleStringProperty("0");
	static StringProperty shiftDir = new SimpleStringProperty("0");
	static StringProperty signed = new SimpleStringProperty("0");
	static StringProperty aShift = new SimpleStringProperty("0");
	static StringProperty mRead = new SimpleStringProperty("0");
	static StringProperty regWrite = new SimpleStringProperty("0");

	public static void execute()
	{
		switch(controlIn.get())
		{
			case "010":
				executeRFormat();
				break;
			case "000"://add
				shift.set("0");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				signed.set("0");
				mRead.set("0");
				regWrite.set("0");
				ALUOpOut.setValue("0010");
				break;
			case "001"://sub
				shift.set("0");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				signed.set("0");
				mRead.set("0");
				regWrite.set("0");
				break;
			case "011"://slt
				shift.set("0");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				signed.set("0");
				mRead.set("0");
				regWrite.set("0");
				break;
			case "100"://and
				shift.set("0");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				signed.set("0");
				mRead.set("0");
				regWrite.set("0");
				break;
			case "101"://or
				shift.set("0");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				signed.set("0");
				mRead.set("0");
				regWrite.set("0");
				break;
			case "111"://xor
				shift.set("0");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				signed.set("0");
				mRead.set("0");
				regWrite.set("0");
				break;
		}
	}

	private static void executeRFormat()
	{
		switch(functIn.get())
		{

		}
	}
}
