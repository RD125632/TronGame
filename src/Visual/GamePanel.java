package Visual;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Logix.KeyHandler;
import Logix.StateHandler;
import Thread.FPSThread;
import Thread.LogixThread;

public class GamePanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	private StateHandler states;
	private KeyHandler key;
	
	public GamePanel()
	{
		super();
		setFocusable(true);
		startThreads();
		key = new KeyHandler();
	}

	private void startThreads()
	{
		new LogixThread(states).start();
		new FPSThread(this).start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		states.getCurrentState().draw(g2);
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		key.Event(e);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
