package Thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Logix.LogixHandler;



public class LogixThread extends Thread implements ActionListener {
	
	@SuppressWarnings("unused")
	private Thread thread;
	private Timer timer;
	private LogixHandler control;
	
   public LogixThread(LogixHandler controller)
   {
	   thread = this;
	   timer = new Timer(1000/30, this);
	   control = controller;
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
	   control.getStatesHandler().getCurrentState().update();
   }

}
