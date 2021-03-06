package Logix.State;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import Logix.DataStreamHandler;
import Logix.StateHandler;
import Logix.Networking.ClientController;
import Logix.Networking.ServerController;
import Logix.Objects.Player;
import Visual.GamePanel;

public class SearchState extends GameState
{
	private GameState lastState;
	private GamePanel panel;
	private int rotateCount = 0;
	private int outRadius = 50;
	private int inRadius = 10;
	private DataStreamHandler dataStream;
	private boolean isConnected;
	private StateHandler stateHandler;
	private DataStreamHandler dataStreamHandler;
	
	public SearchState(GamePanel p, StateHandler stateHandler, DataStreamHandler dataStreamHandler)
	{
		this.dataStreamHandler = dataStreamHandler;
		this.stateHandler = stateHandler;
		panel = p;
	}
 
	public DataStreamHandler getDataStream()
	{
		return dataStream;
	}
	
	public void startSearch(GameState state)
	{
		if(state instanceof HostFormState)
		{
			setLastState(state);
			ServerController s = new ServerController(stateHandler, dataStreamHandler);
			stateHandler.setIsClient(false);
			dataStream = s.getDataSteam();
		}
		else if(state instanceof JoinFormState)
		{
			setLastState(state);
			ClientController c = new ClientController(dataStreamHandler);
			stateHandler.setIsClient(true);
			dataStream = c.getDataSteam();
		}
		
	}
	
	@Override
	public void draw(Graphics2D g2) {

		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawWaitAnimation(g2);
		
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g2.setPaint(Color.LIGHT_GRAY);
		g2.setFont(new Font("Arial", Font.ITALIC, 40));
		g2.drawString("Searching for Player 2", 150, panel.getParent().getHeight() / 2);
		
	}

	public void drawWaitAnimation(Graphics2D g2)
	{
		AffineTransform normal = new AffineTransform();
		normal = g2.getTransform();
		
		AffineTransform tx = new AffineTransform();
		tx.translate(panel.getWidth() / 2 - 300, panel.getHeight() / 2 - 15);
		tx.rotate(Math.toRadians(rotateCount));
		
		//set Rotate
		g2.setTransform(tx);
		
		Shape outerCircle = new Ellipse2D.Double(- outRadius / 2, - outRadius / 2, outRadius, outRadius);
		Shape innerCircle = new Ellipse2D.Double(- inRadius / 2 - outRadius / 2, - inRadius / 2, inRadius, inRadius);
		
		g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
		g2.setPaint(new Color(143, 213, 223));
		g2.draw(outerCircle);
		
		g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
		g2.setPaint(Color.WHITE);
		g2.fill(innerCircle);
		
		//reset Transform
		g2.setTransform(normal);
				
		
		if(rotateCount == 360)
		{
			rotateCount = 0;
		}
		else
		{
			rotateCount += 5;
		}
	}
	
	@Override
	public void update() 
	{
		
		if(dataStream != null)
		{
			if(dataStream.getStatus() == "Initialized")
			{
					isConnected = true;
					dataStream.setPlayer(new Player(((JoinFormState) lastState).getForm().get(0).getText(), 1));
					stateHandler.setCurrentState("tronState");
			}
			else if(dataStream.getStatus() == "WORKING")
			{
					dataStream.setPlayer(new Player(((HostFormState) lastState).getForm().get(0).getText(), 0));
					stateHandler.setCurrentState("tronState");
					
			}
		}
		
	}

	public boolean isConnected()
	{
		return isConnected;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String selectItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectNext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectBack() {
		// TODO Auto-generated method stub
		
	}

	public GameState getLastState() {
		return lastState;
	}

	public void setLastState(GameState lastState) {
		this.lastState = lastState;
	}
}
