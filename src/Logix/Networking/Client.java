package Logix.Networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Logix.DataStreamHandler;
import Logix.StateHandler;

public class Client implements Runnable{

	private Socket socket;
	private ClientController clientController;
	private DataStreamHandler dataStreamHandler;
	private StateHandler state;
	
	public Client(ClientController clientController, DataStreamHandler dataStreamHandler)
	{
		this.state = state;
		this.clientController = clientController;
		this.dataStreamHandler = dataStreamHandler;
		this.dataStreamHandler.setStatus("Initialized");
		
		System.out.println("The client status is: " + dataStreamHandler.getStatus());
		
		this.clientController.setDataSteam(dataStreamHandler);
	}

	@Override
	public void run() {
		try {
			this.socket = new Socket("localhost", 8000);
			clientController.setInetadres(socket.getInetAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("connected to server");
		dataStreamHandler.setPlayersJoined(true);
		
		try 
		{			
			while(true)
			{
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				
				oos.writeObject(dataStreamHandler);
				dataStreamHandler = (DataStreamHandler) ois.readObject();
				
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
}
