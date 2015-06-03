package Logix.Networking;

import java.io.*;
import java.net.*;

public class Server{
	
	private ServerSocket serverSocket;
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	private Client client;
	
	public Server() 
	{
	}
	
	public void start()
	{
		Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                	serverSocket = new ServerSocket(8000);
                    System.out.println("Waiting for clients to connect...");
                    while (true) {
                        client.setClientSocket(serverSocket.accept());
                    }
                } catch (IOException e) {
                    System.err.println("Unable to process client request");
                    e.printStackTrace();
                }
            }
        };
        
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
	}

}
