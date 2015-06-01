package Thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;



public class FPSThread extends Thread implements ActionListener {
	
	@SuppressWarnings("unused")
	private Thread thread;
	private Timer timer;
	private JPanel gamePanel;
	
   public FPSThread(JPanel panel)
   {
	   thread = this;
	   timer = new Timer(1000/60, this);
	   gamePanel = panel;
   }
   
   @Override
   public void run() 
   {
	   while(true)
	   {
		   timer.start();
	   }
   }
   
   @Override
   public void actionPerformed(ActionEvent arg0) 
   {
		gamePanel.repaint();
   }

}
