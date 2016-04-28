package texturing;

/** Important part of Texture
 * defines the relationship between two edges of SubTextures
 * @author Connor Lehmacher */
public class Link {
	/** which SubTexture the link is to */
	int endSubTexture;
	/** which side on the SubTexture the link is to */
	int endSide;
	
	public Link(int est, int es) {
		endSubTexture = est;
		endSide = es;
	}
}
