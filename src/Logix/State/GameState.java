package Logix.State;

import java.awt.Graphics2D;


public abstract class GameState
{
	public GameState() {}
	public abstract void draw(Graphics2D g2);
	public abstract void update();
	public abstract void init();
	public abstract String selectItem();
	public abstract void selectNext();
	public abstract void selectBack();
	
	public String toString(){
		return this.getClass().getSimpleName();
	}
}
