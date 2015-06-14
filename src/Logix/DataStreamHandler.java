package Logix;

import java.io.Serializable;
import java.util.List;

import Logix.Objects.Player;

public class DataStreamHandler implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private String status = null;
	private List<Player> playerList;
	
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
}
