package Logix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Logix.Objects.Player;

public class DataStreamHandler implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String status = null;
	private List<Player> playerList;
	private boolean playersJoined = false;
	
	public DataStreamHandler()
	{
		playerList = new ArrayList<Player>();
		playerList.add(new Player("p1", 0));
		playerList.add(new Player("p2", 1));
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	
	
	public void setPlayer(Player player)
	{
		playerList.set(player.getID(), player);
	}
	
	public List<Player> getPlayers()
	{
		return playerList;
	}
	
	public void setPlayersJoined(Boolean bool)
	{
		playersJoined = bool;
	}
	
	public boolean getPlayerJoined()
	{
		return playersJoined;
	}
	
	public String getPlayerDirection(Player player)
	{
		return player.getCurrentDirection();
	}
	
	public void setPlayerDirection(int playerID, String newDirection)
	{
		if(newDirection != getPlayerDirection(getPlayers().get(playerID)))
		{
			getPlayers().get(playerID).setCurrentDirection(newDirection);
		}
	}
		
	
}
