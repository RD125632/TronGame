package Logix;

import java.awt.event.KeyEvent;

import Logix.State.GameState;
import Logix.State.JoinFormState;

public class EventHandler {

	private StateHandler statesHandler;
	public EventHandler(StateHandler states)
	{
		statesHandler = states;
	}
	
	public void MenuKeyEvent(KeyEvent e)
	{
		int keyCode = e.getKeyCode();

		if(statesHandler.getIndex() < 3)
		{
			GameState state = statesHandler.getCurrentState();
			
			switch( keyCode ) 
			{ 
	        	case KeyEvent.VK_UP:
	        		state.selectBack();	
	            break;
	        case KeyEvent.VK_DOWN:
	        	state.selectNext();	
	            break;
	        case KeyEvent.VK_ENTER :
	        		String item = state.selectItem();
	        		switch( item ) 
	    			{ 
        			case "Join Game" :
        				statesHandler.next();
        				break;
        			case "Host Game" :
        				statesHandler.select(statesHandler.getIndex() + 2);
        				break;
        			case "Back" :
        				statesHandler.back();
        				break;
	        			case "Exit" :
	        				System.exit(0);
	        				break;
	    			}
	        	break;
			}	
		}
	}
	
	public void InputFieldEvent(KeyEvent e)
	{
		if(statesHandler.getCurrentState() instanceof JoinFormState)
		{
			JoinFormState menu = (JoinFormState)statesHandler.getCurrentState();
			
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
			{
				menu.getNameBox().backspace();
			}
			else if(e.getKeyCode() >= 65 && e.getKeyCode() <= 90)
			{
				menu.getNameBox().updateText(e.getKeyChar());
			}
			
		
		}
			
	}
	
}
