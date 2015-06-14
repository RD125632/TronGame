package Logix;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Logix.Objects.InputField;
import Logix.State.GameState;
import Logix.State.HostFormState;
import Logix.State.JoinFormState;
import Logix.State.LocalFormState;
import Logix.State.SearchState;
import Logix.State.TronState;

public class EventHandler {

	private StateHandler statesHandler;
	private int menuLevel;
	private List<Integer> acceptedChars;
	private DataStreamHandler dataStreamHandler;
	
	public EventHandler(StateHandler states, DataStreamHandler dataStreamHandler)
	{
		this.dataStreamHandler = dataStreamHandler;
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
				if(state instanceof HostFormState || state instanceof LocalFormState || state instanceof JoinFormState)
				{
    				statesHandler.setCurrentState("menuState");
				}
				else
				{
					System.exit(0);
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
        				statesHandler.setCurrentState("joinFormState");
        				break;
        			case "Host Game" :
        				statesHandler.setCurrentState("hostFormState");
        				break;
        			case "Local Game" :
        				statesHandler.setCurrentState("localFormState");
        				break;
        			case "Start" :
        					LocalFormState localTemp = (LocalFormState)state;
        					localTemp.getForm().get(0).setSelected(true);
        					menuLevel = 1;
        					localTemp.setPopUp(true);
    						break;
        			case "Join" :
        					JoinFormState joinState = (JoinFormState)state;
        					joinState.getForm().get(0).setSelected(true);
        					menuLevel = 1;
        					joinState.setPopUp(true);
        				break;
        			case "Host" :
    						HostFormState hostState = (HostFormState)state;
    						hostState.getForm().get(0).setSelected(true);
    						menuLevel = 1;
    						hostState.setPopUp(true);
    					break;
        			case "Back" :
        					statesHandler.setCurrentState("menuState");
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
				statesHandler.setCurrentState("menuState");
				menuLevel = 0;
				state.resetGame();
				break;
			case KeyEvent.VK_RIGHT:
				state.setPlayerDirection(0,"right");
				break;
	        case KeyEvent.VK_UP:
	        	state.setPlayerDirection(0,"up");
	        	break;
	        case KeyEvent.VK_DOWN:
	        	state.setPlayerDirection(0,"down");
	        	break;
	        case KeyEvent.VK_LEFT:
	        	state.setPlayerDirection(0,"left");
	        	break;
	        	/* ---- LOCAL MULTIPLAYER ----- */
			case KeyEvent.VK_D:
				state.setPlayerDirection(1,"right");
				break;
	        case KeyEvent.VK_W:
	        	state.setPlayerDirection(1,"up");
	        	break;
	        case KeyEvent.VK_S:
	        	state.setPlayerDirection(1,"down");
	        	break;
	        case KeyEvent.VK_A:
	        	state.setPlayerDirection(1,"left");
	        	break;
	        case KeyEvent.VK_SPACE:
	        	state.resetGame();
	        	break;
		}	
	}
	
	public void menuL1(int keyCode, KeyEvent e)
	{		
		if(statesHandler.getCurrentState() instanceof JoinFormState || statesHandler.getCurrentState()instanceof HostFormState || statesHandler.getCurrentState() instanceof LocalFormState)
		{
			if(statesHandler.getCurrentState() instanceof LocalFormState)
			{
				LocalFormState menu = (LocalFormState)statesHandler.getCurrentState();
				if(keyCode == KeyEvent.VK_ENTER)
				{
					statesHandler.setCurrentState("tronState");
					menuLevel = 3;
									
					dataStreamHandler.getPlayers().get(0).setName(menu.getForm().get(0).getText());
					dataStreamHandler.getPlayers().get(1).setName(menu.getForm().get(1).getText());
					
					TronState tron = (TronState)statesHandler.getState("tronState");
				}
				
				else if(keyCode == KeyEvent.VK_ESCAPE)
				{
					menuLevel = 0;
					menu.setPopUp(false);
					
					newSelection(0,menu.getForm(),false);
				
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
					newSelection(0,menu.getForm(),true);
				}
				else if(keyCode == KeyEvent.VK_DOWN)
				{
					newSelection(0,menu.getForm(),false);
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
			else
			if(statesHandler.getCurrentState() instanceof JoinFormState)
			{
				JoinFormState menu = (JoinFormState)statesHandler.getCurrentState();
				if(keyCode == KeyEvent.VK_ENTER)
				{
					
					statesHandler.setCurrentState("searchState");
					
					SearchState giveLast = (SearchState)statesHandler.getCurrentState();
					giveLast.startSearch(menu);
					menu.setPopUp(false);

					dataStreamHandler = giveLast.getDataStream();
					menuLevel = 2;
					
				
					dataStreamHandler.getPlayers().get(1).setName(menu.getForm().get(0).getText());
					
				}
				else if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_ESCAPE)
				{
					menuLevel = 0;
					menu.setPopUp(false);
					newSelection(0,menu.getForm(),false);
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
					newSelection(0,menu.getForm(),true);
				}
				else if(keyCode == KeyEvent.VK_DOWN)
				{
					newSelection(0,menu.getForm(),false);
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
					statesHandler.setCurrentState("searchState");
					
					SearchState giveLast = (SearchState)statesHandler.getCurrentState();
					giveLast.startSearch(menu);
					menu.setPopUp(false);
					dataStreamHandler = giveLast.getDataStream();
					menuLevel = 2;
										
					dataStreamHandler.getPlayers().get(0).setName(menu.getForm().get(0).getText());
					
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
					newSelection(0,menu.getForm(),true);
				}
				else if(keyCode == KeyEvent.VK_DOWN )
				{
					newSelection(0,menu.getForm(),false);
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
			SearchState state = (SearchState) statesHandler.getState("searchState");

			if(keyCode == KeyEvent.VK_ESCAPE)
			{
				statesHandler.setCurrentState("menuState");
			}
			else if(keyCode == KeyEvent.VK_ALT)
			{
				if(state.isConnected() == true)
				{					
					statesHandler.setCurrentState("tronState");
					TronState tron = (TronState) statesHandler.getState("tronState");
				}
			}
		}
	
	private void newSelection(int i, List<InputField> menu, boolean isUP)
	{
		
			if(isUP)
			{
				if(menu.get(i).isSelected())
				{
					if(i > 0)
					{
						menu.get(i).setSelected(false);
						menu.get(i-1).setSelected(true);
						return;
					}
					else
					{
						return;
					}
				}	
				else
				{
					i++;
					newSelection(i,menu,isUP);
				}
			}
			else
			{
				if(i < menu.size()-1)
				{	
					if(menu.get(i).isSelected())
					{
						menu.get(i).setSelected(false);
						menu.get(i+1).setSelected(true);
						return;
					}
					else
					{
						i++;
						newSelection(i,menu,isUP);
					}
				}
				else
				{
					return;
				}
			}
	}
	
	
}
