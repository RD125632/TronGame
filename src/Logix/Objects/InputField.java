package Logix.Objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class InputField {

	private String text;
	private int x, y, width, height;
	
	public InputField(String s)
	{
		this.text = s;

		this.width = 300;
		this.height = 35;
    }
	

    public void draw(Graphics2D g2)
	{       	
        g2.setPaint(new Color(40,40,40));
        g2.fillRect(x, y, width, height);
        
        g2.setPaint(new Color(143, 213, 223));
        g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
        g2.drawRect(x, y, width, height);

        g2.setFont(new Font("Arial", Font.ITALIC, 20));
        g2.drawString(text, x+8, y+(height - 10)); 
    }   
    
    public void setPosition(int x, int y)
    {
		this.x = x;
		this.y = y;
    }
    
    public void updateText(Character chars)
    {
    	text += chars;
    }
    
    public void backspace()
    {
    	text = text.substring(0, text.length() - 1);
    	       
    }
    
    public String getText()
    {
    	return text;
    }
}
