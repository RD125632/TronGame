package Logix.Networking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Logix.DataStreamHandler;
import Logix.StateHandler;

public class Server implements Runnable{
	
	private ServerSocket serversocket;
	private ServerController serverController;
	private DataStreamHandler dataStreamHandler;
	private StateHandler stateHandler;
	public Server(ServerController serverController, StateHandler stateHandler, DataStreamHandler dataStreamHandler) 
	{
		this.stateHandler = stateHandler;
		this.serverController = serverController;
		this.dataStreamHandler = dataStreamHandler;
		this.serverController.setDataSteam(dataStreamHandler);
	}
	
	public void run()
	{
		try {
			this.serversocket = new ServerSocket(8000);
			System.out.println("started server");
			
			Socket socket = serversocket.accept();
			System.out.println("player joined");
			dataStreamHandler.setPlayersJoined(true);
			
			while(true)
			{
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				
				dataStreamHandler = (DataStreamHandler) ois.readObject();
				if(dataStreamHandler.getPlayerJoined())
				{
					stateHandler.setCurrentState("tronState");
					dataStreamHandler.setPlayersJoined(false);
				}
				dataStreamHandler.setStatus("WORKING");
				oos.writeObject(dataStreamHandler);
			}
			
			//Thread thread = new Thread(new ClientThread(socket, serverController));
			//thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

