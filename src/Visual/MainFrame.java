package Visual;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private GamePanel panel = new GamePanel();
	
	public MainFrame()
	{
		super("MainFrame");
		
		ImageIcon image = new ImageIcon(System.getProperty("user.dir") + "/Resource/Image/icon.jpg");
		
		setIconImage(image.getImage());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setVisible(true);
		
	}

}
