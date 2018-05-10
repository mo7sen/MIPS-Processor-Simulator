package core;

public class Pointer
{
	int address;
	int offset;
	void moveByte(int bytes)
	{
		if(bytes + offset >= 4)
		{
			address+=1;
			bytes-=4;
			moveByte(bytes);
		}
		else if (bytes + offset < 0)
		{
			address-=1;
			bytes+=4;
			moveByte(bytes);
		}
		else
		{
			offset += bytes;
		}
	}
	public Pointer(int address)
	{
		this.address = address/4;
		this.offset = address%4;
	}
	public String toString()
	{
		return SignExtend.extendUnsigned(Integer.toBinaryString((address * 4) + offset), 32);
	}
	public static Pointer fromBString(String b)
	{
		return new Pointer(BinaryParser.parseUnsigned(b));
	}

}
