package Logix.State;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import Logix.DataStreamHandler;
import Logix.Objects.Player;
import Visual.GamePanel;

public class TronState extends GameState{
	
	private GamePanel panel;
	private Font menuFont;
	private Player winner;
	private boolean isFinished;	
	private DataStreamHandler dataStream;
	
	public TronState(GamePanel p)
	{
		panel = p;
		isFinished = false;
		menuFont = panel.getMenuFont();
	}


	@Override
	public void draw(Graphics2D g2) 
	{
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g2.setFont(new Font("Arial", Font.ITALIC, 40));
		g2.setPaint(dataStream.getPlayers().get(0).getTailColor());
		g2.drawString(dataStream.getPlayers().get(0).getName(), 50, 100);
		
		g2.setPaint(dataStream.getPlayers().get(1).getTailColor());
		g2.drawString(dataStream.getPlayers().get(1).getName(), 50, 200);
		
		g2.setPaint(new Color(15,15,15));
		g2.fillRect((panel.getParent().getWidth()/2) - 700, (panel.getParent().getHeight()/2) - 500 , 1400, 1000);
		g2.setPaint(new Color(143, 213, 223));
		
		
		Stroke s = g2.getStroke();
		g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g2.drawRect((panel.getParent().getWidth()/2) - 700, (panel.getParent().getHeight()/2) - 500 , 1400, 1000);
		g2.setStroke(s);
		
		AffineTransform tx = new AffineTransform();
		tx.translate((panel.getParent().getWidth()/2), (panel.getParent().getHeight()/2));
		
		g2.setTransform(tx);
		
		if(isFinished)
		{
			drawFinished(g2);
		}
		else
		{
			for(Player p : dataStream.getPlayers())
			{
				drawPlayer(g2, p);
			}
		}
	}

	public void drawPlayer(Graphics2D g2, Player player)
	{

		g2.setPaint(player.getTailColor());
		g2.fillRect((int)player.getPosition().getX(), (int)player.getPosition().getY(), 10, 10);

		
		for(Point2D point : player.getTail())
		{
			g2.fillRect((int)point.getX() + 2, (int)point.getY() + 2, 6, 6);
	
		}
	}
	
	public void drawFinished(Graphics2D g2)
	{
		Font temp = g2.getFont();
		FontMetrics met;
		
		g2.setFont(menuFont.deriveFont(150f));
		met = g2.getFontMetrics();
		g2.setPaint(new Color(143, 213, 223));
		g2.drawString("Winner", -(met.stringWidth("Winner")/2), -300);
		
		g2.setFont(temp);
		met = g2.getFontMetrics();
		g2.setPaint(winner.getTailColor());
		g2.drawString(winner.getName(), -(met.stringWidth(winner.getName())/2), 0);
			
		g2.setFont(temp.deriveFont(22f));
		met = g2.getFontMetrics();
		g2.setPaint(Color.white);
		String retryString = "Press Space to Retry";
		g2.drawString(retryString, -(met.stringWidth(retryString)/2), 200);
	}
	
	
	public String getPlayerDirection(Player player)
	{
		return player.getCurrentDirection();
	}
	
	public void setPlayerDirection(int playerID, String newDirection)
	{
		if(newDirection != getPlayerDirection(dataStream.getPlayers().get(playerID)))
		{
			dataStream.getPlayers().get(playerID).setCurrentDirection(newDirection);
		}
	}
		
	@Override
	public void update() 
	{
		if(!isFinished)
		{
			playerUpdate(0);
			playerUpdate(1);
		}
		else
		{
	
		}
	}

	public void playerUpdate(int index)
	{
		dataStream.getPlayers().get(index).addTail(dataStream.getPlayers().get(index).getPosition());
		
		switch(dataStream.getPlayers().get(index).getCurrentDirection())
		{
			case "right":
				if(dataStream.getPlayers().get(index).getPosition().getX() < 690)
				{
					dataStream.getPlayers().get(index).setPosition(new Point2D.Double(dataStream.getPlayers().get(index).getPosition().getX() + 6, dataStream.getPlayers().get(index).getPosition().getY()));
				}
			break;
			case "up":
				if(dataStream.getPlayers().get(index).getPosition().getY() > -494)
				{
					dataStream.getPlayers().get(index).setPosition(new Point2D.Double(dataStream.getPlayers().get(index).getPosition().getX(), dataStream.getPlayers().get(index).getPosition().getY() - 6));
				}
			break;
			case "left":
				if(dataStream.getPlayers().get(index).getPosition().getX() > -696)
				{
					dataStream.getPlayers().get(index).setPosition(new Point2D.Double(dataStream.getPlayers().get(index).getPosition().getX() - 6, dataStream.getPlayers().get(index).getPosition().getY()));	
				}
			break;
			case "down":
				if(dataStream.getPlayers().get(index).getPosition().getY() < 486)
				{
					dataStream.getPlayers().get(index).setPosition(new Point2D.Double(dataStream.getPlayers().get(index).getPosition().getX(), dataStream.getPlayers().get(index).getPosition().getY() + 6));
				}
			break;
		}
		
		for(int i = 0; i < dataStream.getPlayers().size(); i++)
		{
			if(dataStream.getPlayers().get(i).getTail().contains(dataStream.getPlayers().get(index).getPosition()))
			{
				isFinished(true);
				if(index == 1)
				{
					winner = dataStream.getPlayers().get(0);
				}
				else
				{
					winner = dataStream.getPlayers().get(1);
				}
				
			}
		}
		
		
	}
	
	public void setDataStream(DataStreamHandler data)
	{
		dataStream = data;
	}
	
	public void isFinished(boolean gameFinished)
	{
		isFinished = gameFinished;
	}
	
	public void resetGame()
	{
		dataStream.getPlayers().get(0).reset();
		dataStream.getPlayers().get(1).reset();
		isFinished = false;
	}
	
	public void setPlayers(String p1, String p2)
	{
		dataStream.getPlayers().get(0).setName(p1);
		dataStream.getPlayers().get(1).setName(p2);
	}
	
	public void setPlayers(String p, int id)
	{
		dataStream.getPlayers().get(id).setName(p);
	}	
	
	@Override
	public void init() {
		
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

}
