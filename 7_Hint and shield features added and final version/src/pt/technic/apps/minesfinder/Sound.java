package pt.technic.apps.minesfinder;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
	
	public static Clip clip;
	
	public static void soundplay(String file) throws java.lang.Exception {

	           File fp = new File(file);
	           String basepath = fp.getAbsolutePath();
	           File music = new File(basepath);
	     	  AudioInputStream ais = AudioSystem.getAudioInputStream(music);
	           clip = AudioSystem.getClip();
	           clip.open(ais);
	           clip.start();
	}
	
	public static void pressButtonSound(String file) {
		try {
			soundplay(file);
		}catch(Exception e) {
          e.printStackTrace();
		}
	}
	
	public static void backgroundSound(String file) {
		try {
			soundplay(file);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e) {
          e.printStackTrace();
		}
	}
}


