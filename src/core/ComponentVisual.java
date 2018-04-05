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
}
