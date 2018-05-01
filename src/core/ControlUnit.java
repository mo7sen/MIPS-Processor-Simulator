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

			case "001000":
				regDst.setValue("0");
				branch.setValue("0");
				memToReg.setValue("0");
				memWrite.setValue("0");
				ALUSrc.setValue("1");
				regWrite.setValue("1");
				memToMem.setValue("0");
				signed.setValue("1");
				ALUOpOut.setValue("000");
				jump.setValue("0");
				link.setValue("0");
				equal.setValue("1");
				lui.setValue("0");
				break;
		}
	}
}
