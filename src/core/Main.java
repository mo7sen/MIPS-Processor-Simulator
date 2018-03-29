package core;

public class Main
{

    public static void main(String[] args)
    {
        Memory.saveWord("10011100100111001001110010011100", 32);
        System.out.println(Memory.loadWord(32));
        Memory.randomize();
        Memory.showMemory();
    }
}
