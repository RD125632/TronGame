package Logix.Networking;


import java.net.Socket;

public class ClientTask implements Runnable {
       
	private final Socket clientSocket;

    public ClientTask(Socket clientSocket) {
          this.clientSocket = clientSocket;
    }

    @Override
    public void run() 
    {
    	System.out.println("Got a client !");

        // Do whatever required to process the client's request

	}
	  	
}
