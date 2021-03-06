package Logix.Networking;


import java.net.InetAddress;

import Logix.DataStreamHandler;

public class ClientController {
	
	private Thread client;
	private boolean connected = false;
	private InetAddress Inetadres = null;
	private DataStreamHandler dataStreamHandler;
	
	public ClientController(DataStreamHandler dataStreamHandler)
	{
		this.dataStreamHandler = dataStreamHandler;
		this.client = new Thread(new Client(this, dataStreamHandler));
		client.start();
	}
	
	public void setConnected(boolean connected)
	{
		this.connected = connected;
	}
	
	public boolean isConnected()
	{
		return connected;
	}

	public InetAddress getInetadres() {
		return Inetadres;
	}

	public void setInetadres(InetAddress inetAddress) {
		Inetadres = inetAddress;
	}
	
	public void setDataSteam(DataStreamHandler data)
	{
		dataStreamHandler = data;
	}
	
	public DataStreamHandler getDataSteam()
	{
		return dataStreamHandler;
	}
	

}