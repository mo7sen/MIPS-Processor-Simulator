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

	static void reset()
	{
		controlIn = new SimpleStringProperty("000");
		functIn = new SimpleStringProperty("000000");
		ALUOpOut = new SimpleStringProperty("0000");
		shift = new SimpleStringProperty("0");
		jumpR = new SimpleStringProperty("0");
		div = new SimpleStringProperty("0");
		divMult = new SimpleStringProperty("0");
		lo = new SimpleStringProperty("0");
		mWrite = new SimpleStringProperty("0");
		shiftDir = new SimpleStringProperty("0");
		signed = new SimpleStringProperty("0");
		aShift = new SimpleStringProperty("0");
		mRead = new SimpleStringProperty("0");
		regWrite = new SimpleStringProperty("0");
	}
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
				shiftDir.set("1");
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
				ALUOpOut.setValue("0110");
				break;
			case "011"://slt
				shift.set("0");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				signed.set("0");
				mRead.set("0");
				regWrite.set("0");
				ALUOpOut.setValue("0111");
				break;
			case "100"://and
				shift.set("0");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				signed.set("0");
				mRead.set("0");
				regWrite.set("0");
				ALUOpOut.setValue("0000");
				break;
			case "101"://or
				shift.set("0");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				signed.set("0");
				mRead.set("0");
				regWrite.set("0");
				ALUOpOut.setValue("0001");
				break;
			case "111"://xor
				shift.set("0");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				signed.set("0");
				mRead.set("0");
				regWrite.set("0");
				ALUOpOut.setValue("1101");
				break;
			case "110":
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				mRead.set("0");
				ALUOpOut.set("1111");
				shiftDir.set("1");
		}
	}

	private static void executeRFormat()
	{
		switch(functIn.get())
		{
			case "100000":
				shift.set("1");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				shiftDir.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				ALUOpOut.setValue("0010");
				break;
			case "100111":
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				divMult.set("0");
				shiftDir.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				ALUOpOut.setValue("1100");
				break;
			case "001000":
				jumpR.set("1");
				mWrite.set("0");
				divMult.set("0");
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
				divMult.set("0");
				ALUOpOut.setValue("0111");
				break;
			case "100010":
				shift.set("1");
				jumpR.set("0");
				divMult.set("0");
				mWrite.set("0");
				shiftDir.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				ALUOpOut.setValue("0110");
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
				divMult.set("0");
				ALUOpOut.setValue("1111");
				break;
			case "100100"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				ALUOpOut.setValue("0000");
				divMult.set("0");
				break;
			case "100101"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				ALUOpOut.setValue("0001");
				divMult.set("0");
				break;
			case "101001"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("0");
				mRead.set("0");
				regWrite.set("1");
				ALUOpOut.setValue("0111");
				divMult.set("0");
				break;
			case "100001"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("0");
				mRead.set("0");
				regWrite.set("1");
				ALUOpOut.setValue("0010");
				divMult.set("0");
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
				signed.set("1");
				aShift.set("1");
				shiftDir.set("0");
				mRead.set("0");
				regWrite.set("1");
				divMult.set("0");
				ALUOpOut.setValue("1111");
				break;
			case "100011"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("0");
				mRead.set("0");
				regWrite.set("1");
				ALUOpOut.setValue("0110");
				divMult.set("0");
				break;
			case "100110"	:
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shift.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				divMult.set("0");
				ALUOpOut.setValue("1101");
				break;
			case "010000"	:
				jumpR.set("0");
				lo.set("0");
				mWrite.set("0");
				mRead.set("1");
				regWrite.set("1");
				divMult.set("0");
				break;
			case "010010"	:
				jumpR.set("0");
				lo.set("1");
				mWrite.set("0");
				mRead.set("1");
				divMult.set("0");
				regWrite.set("1");
				break;
			case "000000":
				shift.set("1");
				jumpR.set("0");
				mWrite.set("0");
				shiftDir.set("1");
				signed.set("1");
				mRead.set("0");
				regWrite.set("1");
				ALUOpOut.setValue("1111");
				divMult.set("0");
				break;
			case "010001":
				jumpR.set("0");
				divMult.set("0");
				lo.set("0");
				mWrite.set("1");
				divMult.set("0");
				regWrite.set("0");
				break;
			case "010011":
				jumpR.set("0");
				divMult.set("0");
				lo.set("1");
				mWrite.set("1");
				regWrite.set("0");
		}

	}
}
