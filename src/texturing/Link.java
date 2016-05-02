package texturing;

/** Important part of Texture
 * defines the relationship between two edges of SubTextures
 * @author Connor Lehmacher */
public class Link {
	/** which SubTexture the link is to */
	int end;
	
	public Link(int e) {
		end = e;
	}
	
	public Link() {}
}
