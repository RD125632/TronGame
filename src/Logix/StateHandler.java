package Logix;

import java.util.ArrayList;
import Logix.State.*;

public class StateHandler 
{
	private ArrayList<GameState> gameStates = new ArrayList<GameState>();
	private GameState currentState;
	private int index;
	
	public StateHandler()
	{
		initializeStates();
		index = 0;
		currentState = gameStates.get(index);
	}
	
	public void initializeStates() { 
		gameStates.clear();
		gameStates.add(new MenuState());
		gameStates.add(new PlayState());
	}
	
	public void select(int i)  { currentState = gameStates.get(i); }
	public void next(){ index++; currentState = gameStates.get(index); }
	public void back(){ index--; currentState = gameStates.get(index); }
	
	public GameState getCurrentState(){
		return currentState;
	}
}
