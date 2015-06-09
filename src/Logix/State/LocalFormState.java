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

public class LocalFormState extends GameState{
	
	private Font menuFont;
	private GamePanel panel;
	private List<String> string;
	private List<InputField> formList;
	private int selectedIndex;
	private boolean isPopUp;
	
	public LocalFormState(GamePanel p)
	{
		panel = p;
		selectedIndex = 0;
		isPopUp = false;
		
		string = new ArrayList<String>();
		string.add("Start");
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
		formList.add(new InputField("Player 1"));
		formList.add(new InputField("Player 2"));
		formList.add(new InputField("Color 1"));
		formList.add(new InputField("Color 2"));
	}

	@Override
	public void draw(Graphics2D g2) 
	{
		g2.setFont(menuFont.deriveFont(72f));
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		drawMenu(g2);
		
		if(isPopUp)
		{
			drawForm(g2);
		}
	}
	
	
	
	public boolean isPopUp() {
		return isPopUp;
	}

	public void setPopUp(boolean isPopUp) {
		this.isPopUp = isPopUp;
	}

	private void drawForm(Graphics2D g2)
	{
		g2.setPaint(new Color(15,15,15));
		g2.fillRect((panel.getParent().getWidth()/2) - 250, (panel.getParent().getHeight()/2) - 300 , 510, 450);
		g2.setPaint(new Color(143, 213, 223));
		g2.drawRect((panel.getParent().getWidth()/2) - 250, (panel.getParent().getHeight()/2) - 300 , 510, 450);
	
		g2.drawString("Players", (panel.getParent().getWidth()/2) - 115, (panel.getParent().getHeight()/2) - 210);
		
		int yPlus = 220;
		for(int i = 0; i <= formList.size() - 1; i++)
		{
			formList.get(i).setPosition((panel.getParent().getWidth()/2) - 150, (panel.getParent().getHeight()/2) - (yPlus -=75));
			formList.get(i).draw(g2);
		}
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

	public List<InputField> getForm()
	{
		return formList;
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
