package Thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Logix.StateHandler;



public class LogixThread extends Thread implements ActionListener {
	
	@SuppressWarnings("unused")
	private Thread thread;
	private Timer timer;
	private StateHandler states;
	
   public LogixThread(StateHandler states)
   {
	   thread = this;
	   timer = new Timer(1000/20, this);
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
	   states.getCurrentState().update();
   }

}
