package Logix.Objects;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private Color tailColor;
	private List<Point2D> tail;
	
	public Player(String name, Color color)
	{
		this.setName(name);
		this.setTailColor(color);
		this.tail = new ArrayList<Point2D>();
	}
	
	public void start()
	{
		tail.add(new Point2D.Double(5,5));
	}

	public void reset()
	{
		tail.clear();
	}
	
	public void addTail(Point2D position)
	{
		tail.add(position);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getTailColor() {
		return tailColor;
	}

	public void setTailColor(Color tailColor) {
		this.tailColor = tailColor;
	}
	
}
