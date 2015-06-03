package Visual;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Logix.LogixHandler;
import Thread.FPSThread;
import Thread.LogixThread;
import Thread.MusicThread;

public class GamePanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	private LogixHandler control;
	private BufferedImage image;
	
	public GamePanel()
	{
		super();


		control = new LogixHandler(this);
		startThreads();
		
		try {
			image = ImageIO.read(new File(System.getProperty("user.dir") + "/Resource/Image/bg.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
	}
	
	private void startThreads()
	{
		new LogixThread(control).start();
		new FPSThread(this).start();
		new MusicThread().run();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(control.getStatesHandler().getIndex() < 3)
		{
			g2.drawImage(image, null, 0, 0);			
		}
		
		control.getStatesHandler().getCurrentState().draw(g2);
	}

	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		control.getEventHandler().MenuKeyEvent(e);
		control.getEventHandler().InputFieldEvent(e);
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
