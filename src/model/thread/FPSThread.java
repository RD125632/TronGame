package model.thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;



public class FPSThread extends Thread implements ActionListener {
	
	@SuppressWarnings("unused")
	private Thread thread;
	private Timer timer;
	int i;
	
   public FPSThread()
   {
	   thread = this;
	   timer = new Timer(1000/60, this);
	   i = 0;  
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
		  i++;
	      System.out.println(i);	
   }

}
