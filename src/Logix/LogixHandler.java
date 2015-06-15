package Logix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Logix.Objects.Player;
import Visual.GamePanel;


public class LogixHandler  implements Serializable
{
	private List<Player> players;
	private GamePanel panel;
	
	private StateHandler statesHandler;
	private EventHandler eventHandler;
	private DataStreamHandler dataStreamHandler;
	
	public LogixHandler(GamePanel f)
	{
		dataStreamHandler = new DataStreamHandler();
		
		panel = f;
		setPlayers(new ArrayList<Player>());
		statesHandler = new StateHandler(panel, dataStreamHandler);
		eventHandler = new EventHandler(statesHandler, dataStreamHandler);
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
