package Logix.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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
		 catch (UnknownHostException e) 
		 {
			 System.err.println("Host unknown. Cannot establish connection");
		 } 
		 catch (IOException e) 
		 {
			 System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
			 e.printStackTrace();
		 }
	}

	@Override
	public void run() 
	{
		System.err.println("LOOP");
		
	}
}
