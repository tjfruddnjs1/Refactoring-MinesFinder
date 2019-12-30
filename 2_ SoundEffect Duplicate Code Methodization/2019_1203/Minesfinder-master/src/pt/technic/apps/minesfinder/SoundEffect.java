package pt.technic.apps.minesfinder;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	
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
	
	public static void effectplay(String file) {
		try {
			soundplay(file);
		}catch(Exception e) {
          e.printStackTrace();
		}
	}
	
	public static void effectplay_1(String file) {
		try {
			soundplay(file);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e) {
          e.printStackTrace();
		}
	}
}
