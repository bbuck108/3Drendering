package texturing;

import java.awt.Image;
import java.util.ArrayList;

/** Texture for a physical object
 * @author Connor Lehmacher*/
public class Texture {
	private ArrayList<Image> subTextures = new ArrayList<Image>();
	
	public Texture(String key) {
		if(key.equals("Sphere")) {
			//Full
			subTextures.add(null);
		}
		if(key.equals("RectangularPrism")) {
			// + X
			subTextures.add(null);

		}
		else {
			System.err.println("Unsupported Key");
		}
	}
	
	public void defineImage(Image i, int id) {

	}
}
