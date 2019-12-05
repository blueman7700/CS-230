import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Experiment {

	// to store current position 
    Long currentFrame; 
    Clip clip; 
      
    // current status of clip 
    String status; 
      
    AudioInputStream audioInputStream; 
    static String filePath;
	
    public Experiment(){
    	    	
        try {
        	// create AudioInputStream object 
			audioInputStream =  
			        AudioSystem.getAudioInputStream(new File("src/Files/ignoreMe.wav").getAbsoluteFile());
			// create clip reference 
			clip = AudioSystem.getClip(); 
			// open audioInputStream to the clip 
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY); 
			clip.start(); 
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    }
}
