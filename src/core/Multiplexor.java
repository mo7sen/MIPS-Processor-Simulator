package core;

public class Multiplexor
{
	int selectionBits, inputBits;
	Data[] boundInput;
	Data selection = new Data();
	Multiplexor(int selectionBits)
	{
		this.selectionBits = selectionBits;
		this.inputBits = (int) Math.pow(2, selectionBits);
		boundInput = new Data[inputBits];
	}

	public void bindInput(Data[] otherOutput)
	{
		this.boundInput = otherOutput;
	}

	public void bindSelection(Data selBit)
	{
		selection = selBit;
	}

	public Data getOutput()
	{
		return new Data(boundInput[Integer.parseInt(selection.read(),2)].read());
	}
}