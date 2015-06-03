package Thread;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class MusicThread implements Runnable{
	
   public MusicThread()
   {
	   
   }
   
   @Override
   public void run() 
   {
	   try 
		{
		   File file = new File(System.getProperty("user.dir") + "/Resource/Music/music.wav");
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.start();
		} 
		catch (Exception e) 
		{
		   System.err.println(e.getMessage());
		}
   }
   
}
