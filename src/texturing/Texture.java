package texturing;

import java.awt.Image;
import java.util.ArrayList;

/** Texture for a physical object
 * @author Connor Lehmacher*/
public class Texture {
	private ArrayList<SubTexture> subTextures = new ArrayList<SubTexture>();
	
	public Texture(String key) {
		if(key.equals("Sphere")) {
			//Top
			subTextures.add(new SubTexture(null, new Link(1), new Link(1), new Link(1), new Link(1), 0));
			//Bottom
			subTextures.add(new SubTexture(null, new Link(0), new Link(0), new Link(0), new Link(0), 1));
		}
		if(key.equals("RectangularPrism")) {
			// + X
			subTextures.add(new SubTexture(null, new Link(1), new Link(2), new Link(4), new Link(5), 0));
			// + Y
			subTextures.add(new SubTexture(null, new Link(), new Link(), new Link(), new Link(), 1));
			// + Z
			subTextures.add(new SubTexture(null, new Link(), new Link(), new Link(), new Link(), 2));
			// - X
			subTextures.add(new SubTexture(null, new Link(), new Link(), new Link(), new Link(), 3));
			// - Y
			subTextures.add(new SubTexture(null, new Link(), new Link(), new Link(), new Link(), 4));
			// - Z
			subTextures.add(new SubTexture(null, new Link(), new Link(), new Link(), new Link(), 5));
		}
		else {
			System.err.println("Unsupported Key");
		}
	}
	
	public void defineImage(Image i, int id) {
		SubTexture newSubTexture = subTextures.get(id);
		newSubTexture.image = i;
		subTextures.set(id, newSubTexture);
	}
}
