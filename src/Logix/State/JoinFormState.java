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

import Logix.Objects.InputField;
import Visual.GamePanel;

public class JoinFormState extends GameState{
	
	private Font menuFont;
	private GamePanel panel;
	private List<String> string;
	private List<InputField> formList;
	private int selectedIndex;
	
	public JoinFormState(GamePanel p)
	{
		panel = p;
		selectedIndex = 0;
		
		string = new ArrayList<String>();
		string.add("Join");
		string.add("Back");
		
		try 
		{
			String path = System.getProperty("user.dir") + "/Resource/JKAbode-Demo.ttf";
			menuFont = Font.createFont(Font.TRUETYPE_FONT, new File(path));
		} 
		catch (FontFormatException | IOException e) 
		{
			e.printStackTrace();
		}
		
		formList = new ArrayList<InputField>();
		formList.add(new InputField("NAME"));
		formList.add(new InputField("HOST ADRESS"));
		formList.add(new InputField("PORT NUMBER"));
	}

	@Override
	public void draw(Graphics2D g2) 
	{
		g2.setFont(menuFont.deriveFont(72f));
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		drawMenu(g2);
		
		drawForm(g2);
	}
	
	private void drawForm(Graphics2D g2)
	{
		int yPlus = 300;
		for(int i = 0; i <= formList.size() - 1; i++)
		{
			formList.get(i).setPosition((panel.getParent().getWidth()/2) - (300 / 2), (panel.getParent().getHeight()/2) - (yPlus -=75));
			formList.get(i).draw(g2);
		}
	}
	
	private void drawMenu(Graphics2D g2)
	{
		for(int i = 0; i <= string.size() - 1 ; i++)
		{
		
			if(selectedIndex == i)
			{
				g2.setPaint(new Color(143, 213, 223));
			}
			else
			{
				g2.setPaint(Color.white);
			}
			
			g2.drawString(string.get(i), 150, (panel.getParent().getHeight() / 2) + i * 100);
			
		}
		
	}


	public InputField getNameBox()
	{
		return formList.get(0);
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
