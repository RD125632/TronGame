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
	private int id = 1;
	
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
		
		try 
		{
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
			dataStreamHandler.setPlayersJoined(true);
			oos.writeObject(dataStreamHandler);
					
			while(true)
			{
				dataStreamHandler = (DataStreamHandler) ois.readObject();
				dataStreamHandler.setId(id);
				oos.writeObject(dataStreamHandler);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
}
