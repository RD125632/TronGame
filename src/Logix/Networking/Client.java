package Logix.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

	private Socket clientSocket;
	// IO streams
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	private String serverIP;

	public Client(String ip) 
	{
		serverIP = ip;
	}

	public void start()
	{
		 try {
         	clientSocket = new Socket(serverIP, 8000);
             System.out.println("Waiting for clients to connect...");
         } catch (IOException e) {
             System.err.println("Unable to process client request");
             e.printStackTrace();
         }
		
		Runnable clientTask = new Runnable() {
            @Override
            public void run() {
            	 System.err.println("LOOP");
            }
        };
        
        Thread clientThread = new Thread(clientTask);
        clientThread.start();
	}
	
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	


	  	
	  	
}
