package Logix.Networking;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	
	public Server() {
        
		try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started at " + new Date() + '\n');

            // Listen for a connection request
            Socket socket = serverSocket.accept();
            InetAddress adres = socket.getInetAddress();

            // Create data input and output streams
            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

            while (true) {
                // Receive radius from the client
                double radius = inputFromClient.readDouble();

                // Compute area
                double area = radius * radius * Math.PI;

                // Send area back to the client
                outputToClient.writeDouble(area);
            }
        }
        catch(IOException e) {
           e.printStackTrace();
        }
    }
}
