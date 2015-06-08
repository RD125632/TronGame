package Logix;

import java.util.ArrayList;

import Logix.State.*;
import Visual.GamePanel;

public class StateHandler 
{
	private ArrayList<GameState> gameStates = new ArrayList<GameState>();
	private GameState currentState;
	private int index;
	private GamePanel panel;
	
	public StateHandler(GamePanel f)
	{
		panel = f;
		initializeStates();
		index = 0;
		currentState = gameStates.get(index);
	}
	
	public void initializeStates() { 
		gameStates.clear();
		gameStates.add(new MenuState(panel));
		gameStates.add(new JoinFormState(panel));
		gameStates.add(new HostFormState(panel));
		gameStates.add(new LocalFormState(panel));
		//gameStates.add(new SearchState(panel));
		gameStates.add(new TronState(panel));
	}
	
	public void select(int i)  { currentState = gameStates.get(i); }
	public void next(){ index++; currentState = gameStates.get(index); }
	public void back(){ index--; currentState = gameStates.get(index); }
	
	public int getIndex()
	{
		return index;
	}
	
	public void setIndex(int newIndex)
	{
		index = newIndex;
	}
	
	
	public GameState getCurrentState(){
		return currentState;
	}
}
