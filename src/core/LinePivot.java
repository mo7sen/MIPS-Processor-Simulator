package core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LinePivot extends ImageView
{
	public LinePivot(double x, double y)
	{
		this.setImage(new Image("/src/Images/LinePivot.png"));
		this.setX(x);
		this.setY(y);
	}
}
