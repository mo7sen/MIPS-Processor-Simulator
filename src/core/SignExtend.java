package core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SignExtend extends ComponentVisual
{
	SignExtend()
	{
		this.setImage(new Image());
		this.setLinesIn(1);
		this.setLinesOut(1);
	}
	public static String extend(String num)
	{
		while(num.length() < 32)
		{
			num = num.charAt(0) + num;
		}
		return num;
	}
}
