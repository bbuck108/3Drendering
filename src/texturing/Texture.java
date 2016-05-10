package texturing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
		BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		images.set(index, img);
	}
	
	/** Fills the texture with all black images*/
	public void fillWithBlanks() {
		int end = images.size();
		for(int i = 0; i < end; i++) {
			defineDefaultImage(i);
		}
	}
	
	public void defineImage(int index, BufferedImage i) {
		images.set(index, i);
	}
	
	/** Fills the Texture with the same one image */
	public void fillWithImages(BufferedImage bi) {
		int end = images.size();
		for(int i = 0; i < end; i++) {
			defineImage(i, bi);
		}
	}
	
	/** Makes the texture be solid one color */
	public void defineFullColor(int index, Color c) {
		BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(c);
		g.drawRect(0, 0, 100, 100);
		fillWithImages(img);
	}
	
	public BufferedImage getImage(int index) {
		return images.get(index);
	}
}
