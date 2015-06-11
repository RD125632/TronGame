package Logix.Objects;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name, direction;
	private Color tailColor;
	private List<Point2D> tail;
	private Point2D position; 
	
	public Player(String name, Color color, int playerNumber)
	{
		this.setName(name);
		this.setTailColor(color);
		this.tail = new ArrayList<Point2D>();
		
		if(playerNumber == 1)
		{
			this.position = new Point2D.Double(-100,-100);
			this.direction = "up";
		}
		else if(playerNumber == 2)
		{
			this.position = new Point2D.Double(100,100);
			this.direction = "down";
		}
		
		
		
	}
	
	public List<Point2D> getTail() {
		return tail;
	}
	public void setTail(List<Point2D> tail) {
		this.tail = tail;
	}
	public Point2D getPosition() {
		return position;
	}
	public void setPosition(Point2D position) {
		this.position = position;
	}


	
	public void start()
	{
		tail.add(new Point2D.Double(5,5));
	}

	public void reset()
	{
		tail.clear();
		position = new Point2D.Double(0,0);
		direction = "up";
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

	public String getCurrentDirection() {
		return direction;
	}

	public void setCurrentDirection(String newDirection) {
		direction = newDirection;
	}
	
}
