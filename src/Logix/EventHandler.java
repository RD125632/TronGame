package Logix;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Logix.Objects.InputField;
import Logix.State.GameState;
import Logix.State.HostFormState;
import Logix.State.JoinFormState;
import Logix.State.SearchState;
import Logix.State.TronState;

public class EventHandler {

	private StateHandler statesHandler;
	private int menuLevel;
	private List<Integer> acceptedChars;
	
	public EventHandler(StateHandler states)
	{
		statesHandler = states;
		menuLevel = 0;
		acceptedChars = new ArrayList<Integer>();
		
		//Numbers | Dot | Slash
		for(int i = 46; i < 60; i++)
		{
			acceptedChars.add(i);
		}
		
		// Characters
		for(int i = 65; i < 91; i++)
		{
			acceptedChars.add(i);
		}
		
	}
	
	public void MenuKeyEvent(KeyEvent e)
	{
		int keyCode = e.getKeyCode();

		switch( menuLevel ) 
		{
			case 0:
				menuL0(keyCode);
			break;
			case 1:
				menuL1(keyCode, e);
			break;
			case 2:
				menuL2(keyCode, e);
			break;
			case 3:;
				menuL3(keyCode);
				break;
		}
		
	}
	
	public void menuL0(int keyCode)
	{
		GameState state = statesHandler.getCurrentState();
			
		switch( keyCode ) 
		{ 
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_RIGHT:
				if(state instanceof JoinFormState)
				{
					JoinFormState tempState = (JoinFormState)state;
					tempState.getForm().get(0).setSelected(true);
					menuLevel = 1;
					tempState.setPopUp(true);
				}
				else if(state instanceof HostFormState)
				{
					HostFormState tempState = (HostFormState)state;
					tempState.getForm().get(0).setSelected(true);
					menuLevel = 1;
					tempState.setPopUp(true);
				}
				break;
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
         				statesHandler.setIndex(statesHandler.getIndex() + 2);
        				statesHandler.select(statesHandler.getIndex());
        				break;
        			case "Join" :
        					JoinFormState tempState = (JoinFormState)state;
        					tempState.getForm().get(0).setSelected(true);
        					menuLevel = 1;
        					tempState.setPopUp(true);
        				break;
        			case "Host" :
    						HostFormState tempState1 = (HostFormState)state;
    						tempState1.getForm().get(0).setSelected(true);
    						menuLevel = 1;
    						tempState1.setPopUp(true);
    					break;
        			case "Back" :
        				if(state instanceof HostFormState)
        				{
        					statesHandler.setIndex(statesHandler.getIndex() - 2);
            				statesHandler.select(statesHandler.getIndex());
        				}
        				else
        				{
        					statesHandler.back();
        				}
        				break;
	        			case "Exit" :
	        				System.exit(0);
	        				break;
	    			}
	        	break;
		}	
	}
	
	public void menuL3(int keyCode)
	{
		TronState state = (TronState) statesHandler.getCurrentState();
			
		switch( keyCode ) 
		{ 
			case KeyEvent.VK_ESCAPE:
				statesHandler.setIndex(0);
				statesHandler.select(statesHandler.getIndex());
				menuLevel = 0;
				state.resetGame();
				break;
			case KeyEvent.VK_RIGHT:
				state.setPlayerDirection("right");
				break;
	        case KeyEvent.VK_UP:
	        	state.setPlayerDirection("up");
	        	break;
	        case KeyEvent.VK_DOWN:
	        	state.setPlayerDirection("down");
	        	break;
	        case KeyEvent.VK_LEFT:
	        	state.setPlayerDirection("left");
	        	break;
		}	
	}
	
	public void menuL1(int keyCode, KeyEvent e)
	{		
		if(statesHandler.getCurrentState() instanceof JoinFormState || statesHandler.getCurrentState()instanceof HostFormState)
		{
			
			if(statesHandler.getCurrentState() instanceof JoinFormState)
			{
				JoinFormState menu = (JoinFormState)statesHandler.getCurrentState();
				if(keyCode == KeyEvent.VK_ENTER)
				{
					statesHandler.setIndex(statesHandler.getIndex() + 2);
					statesHandler.select(statesHandler.getIndex());
					//SearchState giveLast = (SearchState)statesHandler.getCurrentState();
					//giveLast.startSearch(menu);
					menuLevel = 3;
					//menu.setPopUp(false);
					/*
					for(int i = 0; i < menu.getForm().size(); i++)
					{
						if(menu.getForm().get(i).isSelected())
						{
							menu.getForm().get(i).setSelected(false);
						}
					}*/
				}
				else if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_ESCAPE)
				{
					menuLevel = 0;
					menu.setPopUp(false);
					for(int i = 0; i < menu.getForm().size(); i++)
					{
						if(menu.getForm().get(i).isSelected())
						{
							menu.getForm().get(i).setSelected(false);
						}
					}
				}
				else if(keyCode == KeyEvent.VK_BACK_SPACE)
				{
					for(InputField item : menu.getForm())
					{
						if(item.isSelected())
						{
							item.backspace();
						}
					}
				}
				else if(keyCode == KeyEvent.VK_UP)
				{
					for(int i = menu.getForm().size() - 1; i > 0; i--)
					{
						if(menu.getForm().get(i).isSelected() && i != 0)
						{
							menu.getForm().get(i-1).setSelected(true);
							menu.getForm().get(i).setSelected(false);
							return;
						}
					}
				}
				else if(keyCode == KeyEvent.VK_DOWN)
				{
					for(int i = 0; i < menu.getForm().size() - 1; i++)
					{
						if(menu.getForm().get(i).isSelected() && i != menu.getForm().size() - 1)
						{
							menu.getForm().get(i).setSelected(false);
							menu.getForm().get(i+1).setSelected(true);
							return;
						}
					}
				}
				else if(acceptedChars.contains(keyCode))
				{
					for(InputField item : menu.getForm())
					{
						if(item.isSelected())
						{
							item.updateText(e.getKeyChar());
						}
					}
				}
				
			}
			else if(statesHandler.getCurrentState() instanceof HostFormState)
			{
				HostFormState menu = (HostFormState)statesHandler.getCurrentState();
				if(keyCode == KeyEvent.VK_ENTER)
				{
					statesHandler.next();
					SearchState giveLast = (SearchState)statesHandler.getCurrentState();
					giveLast.startSearch(menu);
					menu.setPopUp(false);
					menuLevel = 2;
					
					for(int i = 0; i < menu.getForm().size(); i++)
					{
						if(menu.getForm().get(i).isSelected())
						{
							menu.getForm().get(i).setSelected(false);
						}
					}
				}
				else if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_ESCAPE)
				{
					menuLevel = 0;
					menu.setPopUp(true);
					for(int i = 0; i < menu.getForm().size(); i++)
					{
						if(menu.getForm().get(i).isSelected())
						{
							menu.getForm().get(i).setSelected(false);
						}
					}
				}
				else if(keyCode == KeyEvent.VK_BACK_SPACE)
				{
					for(InputField item : menu.getForm())
					{
						if(item.isSelected())
						{
							item.backspace();
						}
					}
				}
				else if(keyCode == KeyEvent.VK_UP)
				{
					for(int i = 0; i < menu.getForm().size() - 1; i++)
					{
						if(menu.getForm().get(i).isSelected() && i != 0)
						{
							menu.getForm().get(i).setSelected(false);
							menu.getForm().get(i-1).setSelected(true);
						}
					}
				}
				else if(keyCode == KeyEvent.VK_DOWN )
				{
					for(int i = 0; i < menu.getForm().size() - 1; i++)
					{
						if(menu.getForm().get(i).isSelected() && i != menu.getForm().size() - 1)
						{
							menu.getForm().get(i).setSelected(false);
							menu.getForm().get(i+1).setSelected(true);
						}
					}
				}
				else if(acceptedChars.contains(keyCode))
				{
					for(InputField item : menu.getForm())
					{
						if(item.isSelected())
						{
							item.updateText(e.getKeyChar());
						}
					}
				}	
			}
		}
	}
		
	public void menuL2(int keyCode, KeyEvent e)
		{		
			if(keyCode == KeyEvent.VK_ESCAPE)
			{
				SearchState state =(SearchState)statesHandler.getCurrentState();
				
				if(state.getLastState() instanceof HostFormState)
				{
					menuLevel = 0;
					statesHandler.back();
				}
				else
				{
					menuLevel = 0;
					statesHandler.setIndex(statesHandler.getIndex() - 2);
					statesHandler.select(statesHandler.getIndex());
				}
			}
			
			
		}
	
	
}
