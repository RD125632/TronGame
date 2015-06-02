package Logix;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Logix.State.MenuState;

public class EventHandler {

	private StateHandler statesHandler;
	public EventHandler(StateHandler states)
	{
		statesHandler = states;
	}
	
	public void MenuKeyEvent(KeyEvent e)
	{
		int keyCode = e.getKeyCode();

		if(statesHandler.getCurrentState() instanceof MenuState)
		{
			MenuState menu = (MenuState)statesHandler.getCurrentState();
			
			switch( keyCode ) 
			{ 
	        	case KeyEvent.VK_UP:
	        		menu.selectBack();	
	            break;
	        case KeyEvent.VK_DOWN:
	        		menu.selectNext();	
	            break;
	        case KeyEvent.VK_ENTER :
	        		String item = menu.selectItem();
	        		switch( item ) 
	    			{ 
	        			case "Start" : 
	        				statesHandler.next();
	        				break;
	        			case "Options" :
	        				System.out.println("OPTIONS");
	        				break;
	        			case "Exit" :
	        				System.exit(0);
	        				break;
	    			}
	        	break;
			}
			
		}
	}
	
	public void MenuMouseEvent(MouseEvent e)
	{
		
	}
	
}
