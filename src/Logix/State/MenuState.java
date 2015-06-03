package Logix.State;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Visual.GamePanel;


public class MenuState extends GameState {
	
	private Font menuFont;
	private GamePanel panel;
	private List<String> string;
	private int selectedIndex;
	
	
	public MenuState(GamePanel f)
	{	
		panel = f;
		string = new ArrayList<String>();
		string.add("Join Game");
		string.add("Host Game");
		string.add("Exit");
		
		selectedIndex = 0;
		
		try 
		{
			String path = System.getProperty("user.dir") + "/Resource/JKAbode-Demo.ttf";
			menuFont = Font.createFont(Font.TRUETYPE_FONT, new File(path));
		} 
		catch (FontFormatException | IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics2D g2) 
	{
		
		g2.setFont(menuFont.deriveFont(72f));
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		drawMenu(g2);
				
	}

	private void drawMenu(Graphics2D g2)
	{
		for(int i = 0; i <= string.size() - 1 ; i++)
		{
			if(selectedIndex == i)
			{
				g2.setPaint(Color.white);
			}
			else
			{
				g2.setPaint(new Color(143, 213, 223));
			}
			g2.drawString(string.get(i), 150, (panel.getParent().getHeight() / 2) + i * 100);
			
		}
		
	}
		
	
	public String selectItem()
	{
		return string.get(selectedIndex);
	}
	
	public void selectNext()
	{
		if(selectedIndex != string.size() - 1)
		{
			selectedIndex++;
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
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}


}
