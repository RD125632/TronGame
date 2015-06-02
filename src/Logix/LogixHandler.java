package Logix;

import java.util.ArrayList;
import java.util.List;

import Logix.Objects.Player;

public class LogixHandler {

	private List<Player> players;
	
	private StateHandler statesHandler;
	private EventHandler eventHandler;
	
	public LogixHandler()
	{
		setPlayers(new ArrayList<Player>());
		statesHandler = new StateHandler();
		eventHandler = new EventHandler(statesHandler);
	}

	
	
	
	
	
	public List<Player> getPlayers() {
		return players;
	}
	public StateHandler getStatesHandler() {
		return statesHandler;
	}
	public EventHandler getEventHandler() {
		return eventHandler;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}	
	
	
}
