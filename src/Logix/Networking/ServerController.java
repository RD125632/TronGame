package Logix.Networking;

import java.util.ArrayList;
import java.util.List;

import Logix.DataStreamHandler;
import Logix.StateHandler;
import Logix.Objects.Player;

public class ServerController {
	
	private Thread server;
	private List<Player> players;
	private DataStreamHandler dataStreamHandler;

	public ServerController(StateHandler stateHandler, DataStreamHandler dataStreamHandler)
	{
		this.dataStreamHandler = dataStreamHandler;
		this.players = new ArrayList<>();
		this.server = new Thread(new Server(this, stateHandler, dataStreamHandler));
		server.start();
	}
	
	public void setDataSteam(DataStreamHandler data)
	{
		dataStreamHandler = data;
	}
	
	public DataStreamHandler getDataSteam()
	{
		return dataStreamHandler;
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
