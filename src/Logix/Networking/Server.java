package Logix.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{	
	private DataInputStream fromClient1;
	private DataInputStream fromClient2;
	private DataOutputStream toClient1;
	private DataOutputStream toClient2;
	
	public Server() 
	{
		start();
	}
	
	public void start()
	{
	    try 
	    {
	    	ServerSocket serverSocket = new ServerSocket(8000);
	        System.out.println("Waiting for clients to connect...");
	        while (true) 
	        {
	        	Socket client1 = serverSocket.accept();
	        	Socket client2 = serverSocket.accept();
	        }
	    } 
	    catch (IOException e) 
	    {
	        System.err.println("Unable to process client request");
	        e.printStackTrace();
	    }
	}
}
