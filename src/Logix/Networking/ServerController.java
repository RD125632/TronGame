package Logix.Networking;

import java.util.ArrayList;
import java.util.List;

import Logix.DataStreamHandler;
import Logix.Objects.Player;

public class ServerController {
	
	private Thread server;
	private List<Player> players;
	private DataStreamHandler dataStream;

	public ServerController()
	{
		this.players = new ArrayList<>();
		this.server = new Thread(new Server(this));
		server.start();
	}
	
	public void setDataSteam(DataStreamHandler data)
	{
		dataStream = data;
	}
	
	public DataStreamHandler getDataSteam()
	{
		return dataStream;
	}
	
	public List<Player> getPlayers()
	{
		return players;
	}
	
	public void addPlayer(String name)
	{
		int n = players.size();
		players.add(new Player(name, n));
	}
}
