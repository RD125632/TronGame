package Logix.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

		// IO streams
	  	private DataOutputStream toServer;
	  	private DataInputStream fromServer;

	  	public Client() 
	  	{
	  		try 
	  		{
	  			// Create a socket to connect to the server
	  			Socket socket = new Socket("localhost", 8000);
	      
	  			// Create an input stream to receive data from the server
	  			fromServer = new DataInputStream(socket.getInputStream());

	  			// Create an output stream to send data to the server
	  			toServer = new DataOutputStream(socket.getOutputStream());
	  		}
	  		catch (IOException e) 
	  		{
	  			e.printStackTrace();
	  		}
	  }

	  	/*
	  	public void writeToServer()
	  	{
	  		try 
	  		{
		        // Send the radius to the server
		        toServer.writeDouble();
		        toServer.flush();

		    }
		    catch (IOException e) 
	  		{
		    	e.printStackTrace();
		    }
		}*/
}
