package Logix.State;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.List;

import Logix.Objects.Player;
import Visual.GamePanel;

public class ScoreState extends GameState{
	
	private GamePanel panel;
	private int selectedIndex;
	
	public List<Player> playerList;
	
	public ScoreState(GamePanel p)
	{
		panel = p;
		selectedIndex = 0;
	}

	@Override
	public void draw(Graphics2D g2) 
	{
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

		g2.setPaint(new Color(15,15,15));
		g2.fillRect((panel.getParent().getWidth()/2) - 700, (panel.getParent().getHeight()/2) - 500 , 1400, 1000);
		g2.setPaint(new Color(143, 213, 223));
		
		Stroke s = g2.getStroke();
		g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g2.drawRect((panel.getParent().getWidth()/2) - 700, (panel.getParent().getHeight()/2) - 500 , 1400, 1000);
		g2.setStroke(s);
		
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

	}

	
	public void resetGame()
	{
		playerList.get(0).reset();
		playerList.get(1).reset();
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
