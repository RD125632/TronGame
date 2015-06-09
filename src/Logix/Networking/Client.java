package Logix.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client implements Runnable
{

	// IO streams
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	private String serverIP;

	public Client(String ip) 
	{
		serverIP = ip;
	}

	public void serverConnect()
	{
		 try 
		 {
			 Socket socket = new Socket(serverIP, 8000);
             System.out.println("Waiting for clients to connect...");
             
             toServer = new DataOutputStream(socket.getOutputStream());
             fromServer = new DataInputStream(socket.getInputStream());
             
             // Thread thread = new Thread(this); theard.start();
         } 
		 catch (IOException e) 
		 {
             System.err.println("Unable to process client request");
             e.printStackTrace();
         }
	}

	@Override
	public void run() 
	{
		System.err.println("LOOP");
		
	}
}
