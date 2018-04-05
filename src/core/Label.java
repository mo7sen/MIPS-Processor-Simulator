package core;

public class Label
{
	String name;
	String address;
	public Label(String name, String address)
	{
		this.name = name;
		while(address.length() < 32)
			address = "0" + address;
		this.address = address;
	}
	public String getName()
	{
		return this.name;
	}
	public String getAddress()
	{
		return this.address;
	}
}
