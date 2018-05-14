package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ControlUnit
{
	static StringProperty opCodeIn = new SimpleStringProperty("000000");
	static StringProperty ALUOpOut = new SimpleStringProperty("000");
	static StringProperty regDst = new SimpleStringProperty("0");
	static StringProperty branch = new SimpleStringProperty("0");
	static StringProperty memRead = new SimpleStringProperty("0");
	static StringProperty memToReg = new SimpleStringProperty("0");
	static StringProperty memWrite = new SimpleStringProperty("0");
	static StringProperty ALUSrc = new SimpleStringProperty("0");
	static StringProperty regWrite = new SimpleStringProperty("0");
	static StringProperty byteOp = new SimpleStringProperty("0");
	static StringProperty memToMem = new SimpleStringProperty("0");
	static StringProperty signed = new SimpleStringProperty("0");
	static StringProperty jump = new SimpleStringProperty("0");
	static StringProperty link = new SimpleStringProperty("0");
	static StringProperty equal = new SimpleStringProperty("0");
	static StringProperty lui = new SimpleStringProperty("0");
	static StringProperty halfOp = new SimpleStringProperty("0");

	static void reset()
	{
		opCodeIn = new SimpleStringProperty("000000");
		ALUOpOut = new SimpleStringProperty("000");
		regDst = new SimpleStringProperty("0");
		branch = new SimpleStringProperty("0");
		memRead = new SimpleStringProperty("0");
		memToReg = new SimpleStringProperty("0");
		memWrite = new SimpleStringProperty("0");
		ALUSrc = new SimpleStringProperty("0");
		regWrite = new SimpleStringProperty("0");
		byteOp = new SimpleStringProperty("0");
		memToMem = new SimpleStringProperty("0");
		signed = new SimpleStringProperty("0");
		jump = new SimpleStringProperty("0");
		link = new SimpleStringProperty("0");
		equal = new SimpleStringProperty("0");
		lui = new SimpleStringProperty("0");
		halfOp = new SimpleStringProperty("0");
	}

	public static void execute()
	{
		switch (opCodeIn.get())
		{
			case "000000":
				ALUOpOut.set("010");
				regDst.set("1");
				branch.set("0");
				memToReg.set("0");
				memWrite.set("0");
				ALUSrc.set("0");
				regWrite.set("0");
				memToMem.set("0");
				signed.set("0");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				break;

			case "001000"://
				regDst.set("0");
				branch.set("0");
				memToReg.set("0");
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				memToMem.set("0");
				signed.set("1");
				ALUOpOut.set("000");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				break;

			case "100011"://
				regDst.set("0");
				branch.set("0");
				memRead.set("1");
				memToReg.set("1");
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				byteOp.set("0");
				memToMem.set("0");
				signed.set("1");
				ALUOpOut.set("000");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				halfOp.set("0");
				break;

			case "101011"://
				branch.set("0");
				memWrite.set("1");
				ALUSrc.set("1");
				regWrite.set("0");
				memToMem.set("0");
				signed.set("1");
				ALUOpOut.set("000");
				jump.set("0");
				equal.set("1");
				lui.set("0");
				break;

			case "100000":
				regDst.set("0");
				branch.set("0");
				memRead.set("1");
				memToReg.set("1");
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				byteOp.set("1");
				memToMem.set("0");
				signed.set("1");
				ALUOpOut.set("000");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				halfOp.set("0");
				break;

			case "100100"://
				regDst.set("0");
				branch.set("0");
				memRead.set("1");
				memToReg.set("1");
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				byteOp.set("1");
				memToMem.set("0");
				signed.set("0");
				ALUOpOut.set("000");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				halfOp.set("0");
				break;

			case "101000"://
				branch.set("0");
				memRead.set("1");
				memWrite.set("1");
				ALUSrc.set("1");
				regWrite.set("0");
				byteOp.set("1");
				memToMem.set("1");
				signed.set("1");
				ALUOpOut.set("000");
				jump.set("0");
				equal.set("1");
				lui.set("0");
				halfOp.set("0");
				break;

			case "000100"://
				branch.set("1");
				memWrite.set("0");
				ALUSrc.set("0");
				regWrite.set("0");
				signed.set("1");
				ALUOpOut.set("001");
				jump.set("0");
				equal.set("1");
				lui.set("0");
				break;

			case "000010"://
				memWrite.set("0");
				regWrite.set("0");
				jump.set("1");
				equal.set("1");
				break;

			case "000011"://
				memWrite.set("0");
				regWrite.set("1");
				memToMem.set("0");
				jump.set("1");
				link.set("1");
				equal.set("1");
				break;

			case "001010":	//			//15
				regDst.set("0");
				branch.set("0");
				memToReg.set("0");
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				memToMem.set("0");
				signed.set("1");
				ALUOpOut.set("011");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				break;


			case "100001"://
				regDst.set("0");
				branch.set("0");
				memRead.set("1");
				memToReg.set("1");	//18
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				byteOp.set("0");
				memToMem.set("0");
				signed.set("1");
				ALUOpOut.set("000");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				halfOp.set("1");
				break;
			case "100101"://
				regDst.set("0");
				branch.set("0");
				memRead.set("1");
				memToReg.set("1");	//19
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				byteOp.set("0");
				memToMem.set("0");
				signed.set("0");
				ALUOpOut.set("000");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				halfOp.set("1");
				break;
			case "101001"://
				branch.set("0");
				memRead.set("1");
				memWrite.set("1");
				ALUSrc.set("1");		//20
				regWrite.set("0");
				byteOp.set("0");
				memToMem.set("1");
				signed.set("1");
				ALUOpOut.set("000");
				jump.set("0");
				equal.set("1");
				lui.set("0");
				halfOp.set("1");
				break;
			case "001111"://
				regDst.set("0");
				branch.set("0");
				memToReg.set("0");
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				memToMem.set("0");
				signed.set("1");
				ALUOpOut.set("000");		//21
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("1");
				break;
			case "001100"://
				regDst.set("0");
				branch.set("0");
				memToReg.set("0");   //24
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				memToMem.set("0");
				signed.set("1");
				ALUOpOut.set("100");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				break;
			case "001101"://
				regDst.set("0");
				branch.set("0");
				memToReg.set("0");
				memWrite.set("0");
				ALUSrc.set("1");		//26
				regWrite.set("1");
				memToMem.set("0");
				signed.set("1");
				ALUOpOut.set("101");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				break;
			case "000101"://
				branch.set("1");
				memWrite.set("0");	//27
				ALUSrc.set("0");
				regWrite.set("0");
				signed.set("1");
				ALUOpOut.set("001");
				jump.set("0");
				equal.set("0");
				lui.set("0");
				break;
			case "001011"://
				regDst.set("0");
				branch.set("0");
				memToReg.set("0");
				memWrite.set("0");
				ALUSrc.set("1");		//29
				regWrite.set("1");
				memToMem.set("0");
				signed.set("0");
				ALUOpOut.set("011");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				break;
			case "001001"://
				regDst.set("0");
				branch.set("0");
				memToReg.set("0");
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				memToMem.set("0");
				signed.set("0");
				ALUOpOut.set("000");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				break;
			case "001110"://
				regDst.set("0");
				branch.set("0");
				memToReg.set("0");
				memWrite.set("0");
				ALUSrc.set("1");
				regWrite.set("1");
				memToMem.set("0");
				signed.set("1");
				ALUOpOut.set("111");
				jump.set("0");
				link.set("0");
				equal.set("1");
				lui.set("0");
				break;
		}
	}
}
