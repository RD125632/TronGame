package Logix.Networking;

import java.io.*;
import java.net.*;

public class Server implements Runnable{
	 
	
	public Server() 
	{

	}

	@Override
	public void run() {

			try {
			    ServerSocket serverSocket = new ServerSocket(8000);

			    System.out.println("Waiting for clients...");
			    Socket client = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	
		
	}

}
