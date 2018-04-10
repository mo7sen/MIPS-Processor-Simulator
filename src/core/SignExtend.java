package core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SignExtend
{
	Data input, output;
	SignExtend(){}

	public void bindInput(Data input)
	{
		this.input = input;
	}

	public Data getOutput()
	{
		output.write(extendSigned(input.read(),32));
		return output;
	}

	public static String extendSigned(String num, int size)
	{
		while(num.length() < size)
		{
			num = num.charAt(0) + num;
		}
                while(num.length() > size)
                {
                        num = num.substring(1);
                }
		return num;
	}

	public static String extendUnsigned(String num, int size)
	{
		while(num.length() < size)
		{
			num = "0" + num;
		}
                while(num.length() > size)
                {
                        num = num.substring(1);
                }
		return num;
	}
}
