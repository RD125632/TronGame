package Logix;

import Logix.State.GameState;
import Logix.State.HostFormState;
import Logix.State.JoinFormState;
import Logix.State.LocalFormState;
import Logix.State.MenuState;
import Logix.State.SearchState;
import Logix.State.TronState;
import Visual.GamePanel;

public class StateHandler
{
	private GameState currentState;
	private int index;
	private GamePanel panel;
	private GameState menuState;
	private GameState joinFormState;
	private GameState hostFormState;
	private GameState localFormState;
	private GameState searchState;
	private GameState tronState;
	private boolean isClient;
	
	public StateHandler(GamePanel f, DataStreamHandler dataStreamHandler)
	{
		panel = f;
		initializeStates(dataStreamHandler);
		index = 0;
		currentState = menuState;
	}
	
	public boolean isClient()
	{
		return isClient;
	}
	
	public void setIsClient(boolean b)
	{
		isClient = b;
	}
	
	public void initializeStates(DataStreamHandler dataStreamHandler) { 
		menuState = new MenuState(panel);
		joinFormState = new JoinFormState(panel);
		hostFormState = new HostFormState(panel);
		localFormState = new LocalFormState(panel);
		searchState = new SearchState(panel, this, dataStreamHandler);
		tronState = new TronState(panel, dataStreamHandler);
	}
	public int getIndex()
	{
		return index;
	}
	
	public void setIndex(int newIndex)
	{
		index = newIndex;
	}
	
	
	public GameState getCurrentState()
	{
		return currentState;
	}
	
	public void setCurrentState(String state)
	{
		switch(state)
		{
		case "menuState":
			currentState = menuState;
			break;
		case "joinFormState":
			currentState = joinFormState;
			break;
		case "hostFormState":
			currentState = hostFormState;
			break;
		case "localFormState":
			currentState = localFormState;
			break;
		case "searchState":
			currentState = searchState;
			break;
		case "tronState":
			currentState = tronState;
			break;
		default:
			break;
		}
	}
	
	public GameState getState(String state)
	{
		switch(state)
		{
		case "menuState":
			return menuState;
		case "joinFormState":
			return joinFormState;
		case "hostFormState":
			return hostFormState;
		case "localFormState":
			return localFormState;
		case "searchState":
			return searchState;
		case "tronState":
			return tronState;
		default:
			return null;
		}
	}
}
