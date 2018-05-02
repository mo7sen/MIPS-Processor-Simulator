package core;

public class Main
{
    public static void main(String[] args)
    {
        MasterController.prepareMips();
        MasterController.run();
        MasterController.executeAll();
//        System.out.println(InstructionMemory.instOut.get());

//        System.out.println(InstructionMemory.instOut.get());
//        System.out.println(InstructionMemory.inMem.get(0));
//        System.out.println(InstructionMemory.inMem.get(1));
//        System.out.println(InstructionMemory.inMem.get(2));

//        MasterController.executeStep();
//        System.out.println(RegisterFile.writeReg.get());
//        MasterController.executeStep();
        System.out.println(RegisterFile.findRegister("$s0").currentValue);
        System.out.println(RegisterFile.findRegister("$t0").currentValue);
//        System.out.println(RegisterFile.findRegister("$t5").currentValue);
//        System.out.println(ProgramCounter.addressIn.get());
    }
}
