package core;

public class ComponentManager
{

	static OrGate signedFlag = new OrGate();
	static NotGate eqFlag = new NotGate();
	static AndGate eqIdentifier = new AndGate();

	public static void initialize()
	{
		eqFlag.bindIn(eqIdentifier.out);
	}

}
