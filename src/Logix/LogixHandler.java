package Logix;

import java.util.ArrayList;
import java.util.List;

import Logix.Objects.Player;

public class LogixHandler {

	private List<Player> players;
	
	public LogixHandler()
	{
		setPlayers(new ArrayList<Player>());
	}

	
	
	
	
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	
	
}
