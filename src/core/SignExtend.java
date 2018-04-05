package core;

import javafx.scene.image.ImageView;

public class SignExtend extends ComponentVisual
{
	public static String extend(String num)
	{
		while(num.length() < 32)
		{
			num = num.charAt(0) + num;
		}
		return num;
	}
}
