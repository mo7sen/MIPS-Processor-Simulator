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
		{ case "100000":
			shift.set("1");
			jumpR.set("0");
			mWrite.set("0");
			shiftDir.set("1");
			signed.set("1");
			mRead.set("0");
			regWrite.set("1");
			break;
			case "10011":
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shiftDir.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				break;
			case "001000":
				jumpR.set("1");
				mWrite.set("0");
				regWrite.set("0");
				break;
			case "101010":
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shiftDir.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				break;
			case "100010":
			shift.set("1");
			jumpR.set("0");
			mWrite.set("0");
			shiftDir.set("1");
			signed.set("1");
			mRead.set("0");
			regWrite.set("1");
				break;
			case "011000":
				jumpR.set("0");
				div.set("0");
				divMult.set("1");
				mWrite.set("0");
				signed.set("1");
				regWrite.set("0");
				break;
			case "000010":
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shiftDir.set("0");
				signed.set("1");
				aShift.set("0");
				mRead.set("0");
				regWrite.set("1");
				break;
			case "100100"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				break;
			case "100101"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				break;
			case "101001"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("0");
				mRead.set("0");
				regWrite.set("1");
				break;
			case "100001"	:
				shift.set("1");
				jumpR.set("0");
				div.set("");
				divMult.set("");
				lo.set("");
				mWrite.set("0");
				shift.set("1");
				signed.set("0");
				aShift.set("");
				mRead.set("0");
				regWrite.set("1");
				break;
			case "011010"	:
				jumpR.set("0");
				div.set("1");
				divMult.set("1");
				mWrite.set("0");
				signed.set("1");
				regWrite.set("0");
				break;
			case "011011"	:
				jumpR.set("0");
				div.set("1");
				divMult.set("1");
				mWrite.set("0");
				signed.set("0");
				regWrite.set("0");
				break;
			case "011001"	:
				jumpR.set("0");
				div.set("0");
				divMult.set("1");
				mWrite.set("0");
				signed.set("0");
				regWrite.set("0");
				break;
			case "000011"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("0");
				signed.set("1");
				aShift.set("1");
				mRead.set("0");
				regWrite.set("1");
				break;
			case "100011"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("0");
				mRead.set("0");
				regWrite.set("1");
				break;
			case "100110"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				break;
			case "010000"	:
				jumpR.set("0");
				divMult.set("");
				lo.set("0");
				mWrite.set("0");
				mRead.set("1");
				regWrite.set("0");
				break;
			case "010010"	:
				jumpR.set("0");
				lo.set("1");
				mWrite.set("0");
				mRead.set("1");
				regWrite.set("0");
				break;
			case "0000":
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shiftDir.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
		}

	}
}
