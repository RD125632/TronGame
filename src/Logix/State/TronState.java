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
import java.util.ArrayList;
import java.util.List;

import Logix.Objects.Player;
import Visual.GamePanel;

public class TronState extends GameState{
	
	private GamePanel panel;
	private Font menuFont;
	private int selectedIndex;
	private Player winner;
	private boolean isFinished;
	private List<Player> playerList;	
	public TronState(GamePanel p)
	{
		panel = p;
		selectedIndex = 0;
		playerList = new ArrayList<Player>();
		isFinished = false;
		menuFont = panel.getMenuFont();
	}


	
	public Player getPlayer1() {
		return playerList.get(0);
	}

	public Player getPlayer2() {
		return playerList.get(1);
	}

	@Override
	public void draw(Graphics2D g2) 
	{
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g2.setFont(new Font("Arial", Font.ITALIC, 40));
		g2.setPaint(playerList.get(0).getTailColor());
		g2.drawString(playerList.get(0).getName(), 50, 100);
		
		g2.setPaint(playerList.get(1).getTailColor());
		g2.drawString(playerList.get(1).getName(), 50, 200);
		
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
			for(Player p : playerList)
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
		if(newDirection != getPlayerDirection(playerList.get(playerID)))
		{
			playerList.get(playerID).setCurrentDirection(newDirection);
		}
	}
	
	public void selectBack()
	{
		if(selectedIndex != 0)
		{
			selectedIndex--;
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
		playerList.get(index).addTail(playerList.get(index).getPosition());
		
		switch(playerList.get(index).getCurrentDirection())
		{
			case "right":
				if(playerList.get(index).getPosition().getX() < 690)
				{
					playerList.get(index).setPosition(new Point2D.Double(playerList.get(index).getPosition().getX() + 6, playerList.get(index).getPosition().getY()));
				}
			break;
			case "up":
				if(playerList.get(index).getPosition().getY() > -494)
				{
					playerList.get(index).setPosition(new Point2D.Double(playerList.get(index).getPosition().getX(), playerList.get(index).getPosition().getY() - 6));
				}
			break;
			case "left":
				if(playerList.get(index).getPosition().getX() > -696)
				{
					playerList.get(index).setPosition(new Point2D.Double(playerList.get(index).getPosition().getX() - 6, playerList.get(index).getPosition().getY()));	
				}
			break;
			case "down":
				if(playerList.get(index).getPosition().getY() < 486)
				{
					playerList.get(index).setPosition(new Point2D.Double(playerList.get(index).getPosition().getX(), playerList.get(index).getPosition().getY() + 6));
				}
			break;
		}
		
		for(int i = 0; i < playerList.size(); i++)
		{
			if(playerList.get(i).getTail().contains(playerList.get(index).getPosition()))
			{
				isFinished(true);
				if(index == 1)
				{
					winner = playerList.get(0);
				}
				else
				{
					winner = playerList.get(1);
				}
				
			}
		}
		
		
	}
	
	public void isFinished(boolean gameFinished)
	{
		isFinished = gameFinished;
	}
	
	public void resetGame()
	{
		playerList.get(0).reset();
		playerList.get(1).reset();
		isFinished = false;
	}
	
	public void setPlayers(String p1, String p2)
	{
		playerList.add(new Player(p1, Color.red,  1));
		playerList.add(new Player(p2, Color.green, 2));
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

}
