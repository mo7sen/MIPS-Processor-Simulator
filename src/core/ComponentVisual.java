package core;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ComponentVisual extends ImageView
{
	private ArrayList<LinePivot> lines = new ArrayList<>();
	public ComponentVisual()
	{

	}
	public void setLinesIn(int n)
	{
		for(int i = 1; i <= n; i ++)
		{
			lines.add(new LinePivot(this.getX(),this.getY()+(this.getFitHeight() * (i/(n+1)))));
		}
	}
	public void setLinesOut(int n)
	{
		for(int i = 1; i <= n; i ++)
		{
			lines.add(new LinePivot(this.getX() + this.getFitWidth(),this.getY()+(this.getFitHeight() * (i/(n+1)))));
		}
	}
	public void setLinesSub(int n)
	{
		for(int i = 1; i <= n; i ++)
		{
			lines.add(new LinePivot(this.getX()+(this.getFitWidth() * (i/(n+1))),this.getY()+this.getFitHeight()));
		}
	}
	public void setLinesSup(int n)
	{
		for(int i = 1; i <= n; i ++)
		{
			lines.add(new LinePivot(this.getX()+(this.getFitWidth() * (i/(n+1))),this.getY()));
		}
	}
}
