package Logix.State;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import Logix.Objects.Player;
import Visual.GamePanel;

public class TronState extends GameState{
	
	private GamePanel panel;
	private int selectedIndex;
	
	public Player player1, player2;
	
	public TronState(GamePanel p)
	{
		panel = p;
		selectedIndex = 0;

		player1 = new Player("player1", Color.red);
		player2 = new Player("player2", Color.GREEN);
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	@Override
	public void draw(Graphics2D g2) 
	{
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

		g2.setPaint(new Color(15,15,15));
		g2.fillRect((panel.getParent().getWidth()/2) - 700, (panel.getParent().getHeight()/2) - 500 , 1400, 1000);
		g2.setPaint(new Color(143, 213, 223));
		g2.drawRect((panel.getParent().getWidth()/2) - 700, (panel.getParent().getHeight()/2) - 500 , 1400, 1000);
	
		AffineTransform tx = new AffineTransform();
		tx.translate((panel.getParent().getWidth()/2), (panel.getParent().getHeight()/2));
		
		g2.setTransform(tx);
		drawPlayer1(g2);
		drawPlayer2(g2);
	}

	public void drawPlayer1(Graphics2D g2)
	{

		g2.setPaint(player1.getTailColor());
		g2.drawRect((int)player1.getPosition().getX(), (int)player1.getPosition().getY(), 8, 8);

		for(Point2D point : player1.getTail())
		{
			g2.fillRect((int)point.getX() + 2, (int)point.getY() + 2, 6, 6);
	
		}
	}
	
	public void drawPlayer2(Graphics2D g2)
	{
		g2.setPaint(player2.getTailColor());
		g2.drawRect((int)player2.getPosition().getX(), (int)player2.getPosition().getY(), 8, 8);

		for(Point2D point : player2.getTail())
		{
			g2.fillRect((int)point.getX() + 2, (int)point.getY() + 2, 6, 6);
	
		}
		
	}
	
	
	public String getPlayerDirection(Player player)
	{
		return player1.getCurrentDirection();
	}
	
	public void setPlayerDirection(String newDirection)
	{
		player1.setCurrentDirection(newDirection);
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
		playerUpdate(player1);
		playerUpdate(player2);
	}

	public void playerUpdate(Player player)
	{
		player.addTail(player.getPosition());
		
		switch(player.getCurrentDirection())
		{
			case "right":
				if(player.getPosition().getX() < 690)
				{
					player.setPosition(new Point2D.Double(player.getPosition().getX() + 6, player.getPosition().getY()));
				}
			break;
			case "up":
				if(player.getPosition().getY() > -494)
				{
					player.setPosition(new Point2D.Double(player.getPosition().getX(), player.getPosition().getY() - 6));
				}
			break;
			case "left":
				if(player.getPosition().getX() > -696)
				{
					player.setPosition(new Point2D.Double(player.getPosition().getX() - 6, player.getPosition().getY()));	
				}
			break;
			case "down":
				if(player.getPosition().getY() < 486)
				{
					player.setPosition(new Point2D.Double(player.getPosition().getX(), player.getPosition().getY() + 6));
				}
			break;
		}
	}
	
	public void resetGame()
	{
		player1.reset();
		player2.reset();
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
