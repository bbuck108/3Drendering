package texturing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/** Texture for a physical object
 * @author Connor Lehmacher*/
public class Texture {
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public Texture(String key) {
		if(key.equals("Sphere")) {
			//Full
			images.add(null);
		}
		if(key.equals("RectangularPrism")) {
			for(int i = 0; i < 6; i++) {
				images.add(null);
			}
		}
		else {
			System.err.println("Unsupported Key");
		}
	}
	
	/** Sets a particular image to be blank*/
	public void defineDefaultImage(int index) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("image/Blank.jpg"));
		}
		catch (IOException e) {}
		images.set(index, img);
	}
	
	/** Fills the texture with all white images*/
	public void fillWithBlanks() {
		int end = images.size();
		for(int i = 0; i < end; i++) {
			defineDefaultImage(i);
		}
	}
	
	public void defineImage(int index, BufferedImage i) {
		images.set(index, i);
	}
}
