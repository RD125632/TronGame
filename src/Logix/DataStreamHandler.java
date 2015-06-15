package Logix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Logix.Objects.Player;
import Logix.State.TronState;

public class DataStreamHandler implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String status = null;
	private List<Player> playerList;
	private boolean playersJoined = false;
	private TronState tron;
	private int menuLevel;
	private int id;
	
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
	
	public void setTronState(TronState tron)
	{
		this.tron = tron;
	}
	
	public void setPlayerDirection(String direction)
	{
		System.out.println("OMGASH");
		switch(direction) 
		{ 
			case "right":
				tron.setPlayerDirection(id,"right");
				break;
	        case "up":
	        	tron.setPlayerDirection(id,"up");
	        	break;
	        case "down":
	        	tron.setPlayerDirection(id,"down");
	        	break;
	        case "left":
	        	tron.setPlayerDirection(id,"left");
	        	break;
		}	
	}
	public void setId(int id)
	{
		this.id = id;
	}
}
