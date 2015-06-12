package Logix.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Logix.DataStreamHandler;

public class Client implements Runnable{

	private Socket socket;
	private ClientController clientController;
	private DataInputStream in;
	private DataOutputStream out;
	private DataStreamHandler dataStreamHandler;
	
	public Client(ClientController clientController)
	{
		this.clientController = clientController;
		dataStreamHandler = new DataStreamHandler();
		dataStreamHandler.setStatus("Initialized");
		System.out.println("The client status is: " + dataStreamHandler.getStatus());
		
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
			this.out = new DataOutputStream(socket.getOutputStream());
			this.in = new DataInputStream(socket.getInputStream());
			
			while(true)
			{
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				
				oos.writeObject(dataStreamHandler);
				dataStreamHandler = (DataStreamHandler) ois.readObject();
				
				System.out.println("The client status is: " + dataStreamHandler.getStatus());
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
}
