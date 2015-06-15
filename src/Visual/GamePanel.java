package Visual;

import java.awt.Font;
import java.awt.FontFormatException;
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
	private  BufferedImage image;
	private Font menuFont;
	
	public GamePanel()
	{
		super();

		try {
			image = ImageIO.read(new File(System.getProperty("user.dir") + "/Resource/Image/bg.jpeg"));
			String path = System.getProperty("user.dir") + "/Resource/JKAbode-Demo.ttf";
			menuFont = Font.createFont(Font.TRUETYPE_FONT, new File(path));
		}
		catch (FontFormatException | IOException e) 
		{
			e.printStackTrace();
		}
		
		control = new LogixHandler(this);
		startThreads();
		
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
	}
	
	public Font getMenuFont()
	{
		return menuFont;
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
		
		g2.drawImage(image, null, 0, 0);			
		
		control.getStatesHandler().getCurrentState().draw(g2);
	}

	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		control.getEventHandler().MenuKeyEvent(e);
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
