package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ALUControl
{
	static StringProperty controlIn = new SimpleStringProperty();
	static StringProperty functIn = new SimpleStringProperty();
	static StringProperty ALUOpOut = new SimpleStringProperty();
	static StringProperty shift = new SimpleStringProperty();
	static StringProperty jumpR = new SimpleStringProperty();
	static StringProperty div = new SimpleStringProperty();
	static StringProperty divMult = new SimpleStringProperty();
	static StringProperty lo = new SimpleStringProperty();
	static StringProperty mWrite = new SimpleStringProperty();
	static StringProperty shiftDir = new SimpleStringProperty();
	static StringProperty signed = new SimpleStringProperty();
	static StringProperty aShift = new SimpleStringProperty();
	static StringProperty mRead = new SimpleStringProperty();
	static StringProperty regWrite = new SimpleStringProperty();

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
