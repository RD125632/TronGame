package Logix;

import java.io.Serializable;

public class DataStreamHandler implements Serializable
{
	private String status = null;
	
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getStatus()
	{
		return status;
	}
}
