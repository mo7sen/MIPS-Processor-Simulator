package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ControlUnit
{
	static StringProperty opCodeIn = new SimpleStringProperty();
	static StringProperty ALUOpOut = new SimpleStringProperty();
	static StringProperty regDst = new SimpleStringProperty();
	static StringProperty branch = new SimpleStringProperty();
	static StringProperty memRead = new SimpleStringProperty();
	static StringProperty memToReg = new SimpleStringProperty();
	static StringProperty memWrite = new SimpleStringProperty();
	static StringProperty ALUSrc = new SimpleStringProperty();
	static StringProperty regWrite = new SimpleStringProperty();
	static StringProperty byteOp = new SimpleStringProperty();
	static StringProperty memToMem = new SimpleStringProperty();
	static StringProperty signed = new SimpleStringProperty();
	static StringProperty jump = new SimpleStringProperty();
	static StringProperty link = new SimpleStringProperty();
	static StringProperty equal = new SimpleStringProperty();
	static StringProperty lui = new SimpleStringProperty();
	static StringProperty halfOp = new SimpleStringProperty();

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

		}
	}
}
