package Logix.State;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public abstract class GameState {
	
	public GameState() {}
	public abstract void draw(Graphics2D g2);
	public abstract void setFrame(JFrame f);
	public abstract void update();
	public abstract void init();
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	public String toString(){
		return this.getClass().getSimpleName();
	}
}
