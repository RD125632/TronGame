package Logix;

import java.util.ArrayList;
import java.util.List;

import Logix.Objects.Player;
import Visual.GamePanel;


public class LogixHandler {

	private List<Player> players;
	private GamePanel panel;
	
	private StateHandler statesHandler;
	private EventHandler eventHandler;
	
	public LogixHandler(GamePanel f)
	{
		panel = f;
		setPlayers(new ArrayList<Player>());
		statesHandler = new StateHandler(panel);
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


	public GamePanel getFrame() {
		return panel;
	}	
}
