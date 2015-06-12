package Logix.Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	
	private ServerSocket serversocket;
	private ServerController serverController;
	
	public Server(ServerController serverController) {
		this.serverController = serverController;
	}

	public void run()
	{
		try {
			this.serversocket = new ServerSocket(8000);
			System.out.println("started server");
			
			Socket socket = serversocket.accept();
			System.out.println("player joined");
			
			Thread thread = new Thread(new ClientThread(socket, serverController));
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

