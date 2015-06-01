package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import model.thread.FPSThread;
import model.thread.LogicThread;
import view.panel.GamePanel;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private GamePanel panel = new GamePanel();
	
	public MainFrame()
	{
		super("MainFrame");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setVisible(true);
		
		startThreads();
	}
	
	private void startThreads()
	{
		new LogicThread().start();
		new FPSThread(panel).start();
		
	}

}
