package Visual;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Logix.LogixHandler;
import Thread.FPSThread;
import Thread.LogixThread;

public class GamePanel extends JPanel implements KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private LogixHandler control;
	
	public GamePanel()
	{
		super();


		control = new LogixHandler();
		startThreads();
		
		setFocusable(true);
		addKeyListener(this);
		requestFocusInWindow();
	}

	private void startThreads()
	{
		new LogixThread(control).start();
		new FPSThread(this).start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		control.getStatesHandler().getCurrentState().setFrame((JFrame) SwingUtilities.getWindowAncestor(this));
		control.getStatesHandler().getCurrentState().draw(g2);
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		control.getEventHandler().MenuKeyEvent(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		control.getEventHandler().MenuMouseEvent(e);
		
	}
	
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
