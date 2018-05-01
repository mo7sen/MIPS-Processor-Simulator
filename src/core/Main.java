package core;

public class Main
{
    public static void main(String[] args)
    {
        MasterController.prepareMips();
        MasterController.run();
        System.out.println(InstructionMemory.read(0));
    }
}
