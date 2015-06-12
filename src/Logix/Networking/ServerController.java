package Logix.Networking;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Logix.Objects.Player;

public class ServerController {
	
	private Thread server;
	private List<Player> players;

	public ServerController()
	{
		this.players = new ArrayList<>();
		this.server = new Thread(new Server(this));
		server.start();
	}
	
	
	public List<Player> getPlayers()
	{
		return players;
	}
	
	public void addPlayer(String name)
	{
		int n = players.size();
		players.add(new Player(name, Color.red, n));
	}
}
