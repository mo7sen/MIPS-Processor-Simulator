package core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class LinePivot extends ImageView
{
	public LinePivot(double x, double y)
	{
		this.setImage(new Image("/src/Images/LinePivot.png"));
		this.setX(x);
		this.setY(y);
	}

	public void bind(LinePivot l)
	{
		Line ln = new Line(this.getX(),this.getY(),l.getX(),l.getY());
	}
}
