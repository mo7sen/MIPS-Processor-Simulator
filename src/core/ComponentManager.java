package core;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;

public class ComponentManager
{

	static OrGate       signedFlag = new OrGate(),
						jumpRFlag = new OrGate(),
						regWriteFlag = new OrGate();
	static NotGate      eqFlag = new NotGate();
	static AndGate      eqIdentifier = new AndGate();
	static Shifter      left2BitShifter = new Shifter();
	static SignExtend   immediateExtend = new SignExtend();
	static Adder        pcIncrementer = new Adder(),
						branchCalculator = new Adder();
	static DMX          setHiLo = new DMX(1),
						memOrReg = new DMX(1);
	static MUX          hiLoReader = new MUX(1),
						aluSrcMux = new MUX(1),
						jumpRMux = new MUX(1),
						equalMux = new MUX(1),
						flowControlMux = new MUX(2),
						writeRegisterMux = new MUX(2),
						regWriteDataMux = new MUX(1),
						shamtMux = new MUX(2),
						memWriteDataMux = new MUX(1),
						memReadDataMux = new MUX(2),
						dataOutMux = new MUX(2);



	public static void provoke()
	{

		signedFlag.bindInputs(ControlUnit.signed, ALUControl.signed);
		jumpRFlag.bindInputs(ALUControl.jumpR, ControlUnit.jump);
		regWriteFlag.bindInputs(ALUControl.regWrite, ControlUnit.regWrite);

		eqFlag.bindIn(eqIdentifier.out);

		eqIdentifier.bindInputs(ControlUnit.branch, ALU.zero);

		left2BitShifter.bindIn(immediateExtend.out);

		immediateExtend.in.bind(Bindings.createStringBinding(() -> InstructionMemory.instOut.get().substring(16), InstructionMemory.instOut));
		immediateExtend.bindSignFlag(signedFlag.out);

		pcIncrementer.bindInputs(ProgramCounter.addressOut, new SimpleStringProperty(Integer.toBinaryString(4)));

		branchCalculator.bindInputs(left2BitShifter.out, pcIncrementer.out);

		setHiLo.bindInput(RegisterFile.readData1);
		setHiLo.outputs.get(0).bind(ALUControl.lo);

		memOrReg.bindInput(dataOutMux.output);
		memOrReg.outputs.get(0).bind(ControlUnit.memToMem);

		hiLoReader.bindInputs(DivMultUnit.outHi, DivMultUnit.outLo);
		hiLoReader.selectBits.get(0).bind(ALUControl.lo);

		aluSrcMux.bindInputs(RegisterFile.readData2, immediateExtend.out);
		aluSrcMux.selectBits.get(0).bind(ControlUnit.ALUSrc);

		jumpRMux.inputs.get(0).bind(Bindings.createStringBinding(() -> pcIncrementer.out.get().substring(0, 4) + InstructionMemory.instOut.get().substring(6) + "00", pcIncrementer.out, InstructionMemory.instOut));
		jumpRMux.inputs.get(1).bind(RegisterFile.readData1);
		jumpRMux.selectBits.get(0).bind(ALUControl.jumpR);

		equalMux.bindInputs(eqFlag.out, eqIdentifier.out);
		equalMux.selectBits.get(0).bind(ControlUnit.equal);

		flowControlMux.bindInputs(pcIncrementer.out, branchCalculator.out, jumpRMux.output, jumpRMux.output);
		flowControlMux.selectBits.get(0).bind(jumpRFlag.out);
		flowControlMux.selectBits.get(1).bind(equalMux.output);

		writeRegisterMux.inputs.get(0).bind(Bindings.createStringBinding(() -> InstructionMemory.instOut.get().substring(11, 16), InstructionMemory.instOut));
		writeRegisterMux.inputs.get(1).bind(Bindings.createStringBinding(() -> InstructionMemory.instOut.get().substring(16, 21), InstructionMemory.instOut));
		writeRegisterMux.inputs.get(2).set("11111");
		writeRegisterMux.inputs.get(3).set("11111");
		writeRegisterMux.selectBits.get(0).bind(ControlUnit.link);
		writeRegisterMux.selectBits.get(1).bind(ControlUnit.regDst);

		regWriteDataMux.bindInputs(memOrReg.outputs.get(0), pcIncrementer.out);
		regWriteDataMux.selectBits.get(0).bind(ControlUnit.link);

		shamtMux.inputs.get(0).set("00000");
		shamtMux.inputs.get(1).bind(Bindings.createStringBinding(() -> InstructionMemory.instOut.get().substring(21,26), InstructionMemory.instOut));
		shamtMux.inputs.get(2).set("10000");
		shamtMux.inputs.get(3).set("10000");
		shamtMux.selectBits.get(0).bind(ControlUnit.lui);
		shamtMux.selectBits.get(1).bind(ALUControl.shift);

		memWriteDataMux.bindInputs(RegisterFile.readData2, WordBuilder.wordOut);
		memWriteDataMux.selectBits.get(0).bind(ControlUnit.memToMem);

		memReadDataMux.bindInputs(Memory.dataOut, WordBreaker.halfOut, WordBreaker.byteOut, WordBreaker.byteOut);
		memReadDataMux.selectBits.get(0).bind(ControlUnit.byteOp);
		memReadDataMux.selectBits.get(1).bind(ControlUnit.halfOp);

		dataOutMux.bindInputs(ALU.output, memReadDataMux.output, hiLoReader.output, hiLoReader.output);
		dataOutMux.selectBits.get(0).bind(ALUControl.mRead);
		dataOutMux.selectBits.get(1).bind(ControlUnit.memToReg);

		ProgramCounter.addressIn.bind(flowControlMux.output);

		ALU.arithmetic.bind(ALUControl.aShift);
		ALU.shift.bind(shamtMux.output);
		ALU.shiftDirection.bind(ALUControl.shiftDir);
		ALU.signed.bind(signedFlag.out);
		ALU.ALUOp.bind(ALUControl.ALUOpOut);
		ALU.input1.bind(RegisterFile.readData1);
		ALU.input2.bind(aluSrcMux.output);

		ALUControl.functIn.bind(Bindings.createStringBinding(() ->InstructionMemory.instOut.get().substring(26), InstructionMemory.instOut));
		ALUControl.controlIn.bind(ControlUnit.ALUOpOut);

		ControlUnit.opCodeIn.bind(Bindings.createStringBinding(()-> InstructionMemory.instOut.get().substring(0,6), InstructionMemory.instOut));

		DivMultUnit.signed.bind(signedFlag.out);
		DivMultUnit.inHi.bind(setHiLo.outputs.get(0));
		DivMultUnit.inLo.bind(setHiLo.outputs.get(1));
		DivMultUnit.input1.bind(RegisterFile.readData1);
		DivMultUnit.input2.bind(RegisterFile.readData2);
		DivMultUnit.active.bind(ALUControl.divMult);
		DivMultUnit.div.bind(ALUControl.div);
		DivMultUnit.write.bind(ALUControl.mWrite);

		InstructionMemory.addressIn.bind(ProgramCounter.addressOut);

		Memory.memWriteFlag.bind(ControlUnit.memWrite);
		Memory.memReadFlag.bind(ControlUnit.memRead);
		Memory.dataIn.bind(memWriteDataMux.output);
		Memory.addressIn.bind(Bindings.createStringBinding(()->ALU.output.get().substring(0,30) + "00",ALU.output));

		RegisterFile.regWrite.bind(regWriteFlag.out);
		RegisterFile.readReg1.bind(Bindings.createStringBinding(()->InstructionMemory.instOut.get().substring(6,11),InstructionMemory.instOut));
		RegisterFile.readReg2.bind(Bindings.createStringBinding(()->InstructionMemory.instOut.get().substring(11,16),InstructionMemory.instOut));
		RegisterFile.writeReg.bind(writeRegisterMux.output);
		RegisterFile.writeData.bind(regWriteDataMux.output);

		WordBreaker.signedFlag.bind(signedFlag.out);
		WordBreaker.wordIn.bind(Memory.dataOut);
		WordBreaker.offsetIn.bind(Bindings.createStringBinding(()->ALU.output.get().substring(30),ALU.output));

		WordBuilder.halfIn.bind(RegisterFile.readData2);
		WordBuilder.byteIn.bind(RegisterFile.readData2);
		WordBuilder.offsetIn.bind(Bindings.createStringBinding(()->ALU.output.get().substring(30), ALU.output));
		WordBuilder.hOp.bind(ControlUnit.halfOp);
		WordBuilder.wordIn.bind(memOrReg.outputs.get(1));
	}

}
//00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
//31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 09 08 07 06 05 04 03 02 01 00

//Connections:
//
//signedFlag                //Done
//jumpRFlag                 //Done
//regWriteFlag              //Done
//eqFlag                    //Done
//eqIdentifier              //Done
//left2BitShifter           //Done
//immediateExtend           //Done
//pcIncrementer             //Done
//branchCalculator          //Done
//setHiLo                   //Done
//memOrReg                  //Done
//hiLoReader                //Done
//aluSrcMux                 //Done
//jumpRMux                  //Done
//equalMux                  //Done
//flowControlMux            //Done
//writeRegisterMux          //Done
//regWriteDataMux           //Done
//shamtMux                  //Done
//memWriteDataMux           //Done
//memReadDataMux            //Done
//dataOutMux                //Done
//ProgramCounter            //Done
//ALU                       //Done
//ALU Control               //Done
//ControlUnit               //Done
//DivMultUnit               //Done
//InstructionMemory         //Done
//Memory                    //Done
//RegisterFile              //Done
//WordBreaker               //Done
//WordBuilder               //Done
