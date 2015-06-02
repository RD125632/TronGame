package Logix;

import java.awt.event.KeyEvent;

public class KeyHandler {

	public KeyHandler()
	{
		
	}
	
	public void Event(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	            // handle up 
	            break;
	        case KeyEvent.VK_DOWN:
	            // handle down 
	            break;
	        case KeyEvent.VK_LEFT:
	            // handle left
	            break;
	        case KeyEvent.VK_RIGHT :
	            // handle right
	            break;
	        case KeyEvent.VK_ESCAPE :
	        	// handle ESC
	        	break;
	     }
	}
	
}
