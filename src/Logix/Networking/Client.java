package Logix.Networking;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {

	
	public Client()
	{
		
	    
	}

	@Override
	public void run() {

			try {
		    	Socket client = new Socket(InetAddress.getLocalHost(),8000);
		    	 System.out.println("CONNECTED");
		    } catch (UnknownHostException e) {
		        System.err.println("Host unknown. Cannot establish connection");
		    } catch (IOException e) {
		        System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
		        e.printStackTrace();
		    }
	

		
	}
	
}
