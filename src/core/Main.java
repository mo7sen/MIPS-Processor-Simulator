package core;

public class Main
{
    public static void main(String[] args)
    {
//        System.out.println(6%4);
        MasterController.prepareMips();
        MasterController.run();
        MasterController.executeAll();
//System.out.println(Integer.toBinaryString((65535<<16) | 65524));

//        System.out.println(InstructionMemory.instOut.get());

//        InstructionMemory.showMe();
//        System.out.println(ALU.input1.get());
//        System.out.println(ALU.input2.get());
//        System.out.println(BinaryParser.parseSigned(ALU.input2.get()));
//        System.out.println(ALU.output.get());
//        System.out.println(ALU.ALUOp.get());
//        System.out.println(InstructionMemory.inMem.get(0));
//        System.out.println(InstructionMemory.inMem.get(1));
//        System.out.println(InstructionMemory.inMem.get(2));
System.out.println(Memory.loadWord(new Pointer(0)));
//        MasterController.executeStep();
//        System.out.println(RegisterFile.writeReg.get());
//        MasterController.executeStep();
//        System.out.println(DivMultUnit.lo.get());
//        System.out.println(RegisterFile.findRegister("$zero").currentValue);
//        System.out.println(RegisterFile.findRegister("$t1").currentValue);
//        System.out.println();
//        for(int i = 0; i < Assembler.codeLines.size();i++)
//        		System.out.println(Assembler.codeLines.get(i).trim());
//        System.out.println(BinaryParser.parseUnsigned("10"));
//        System.out.println(RegisterFile.findRegister("$v0").currentValue);
//        System.out.println((int) Math.pow(2,31));
//        System.out.println((char) 98);
//        System.out.println(ProgramCounter.addressIn.get());
    }
}
