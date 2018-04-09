package core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SignExtend extends ComponentVisual
{
	SignExtend()
	{
		//this.setImage(new Image());
		this.setLinesIn(1);
		this.setLinesOut(1);
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
