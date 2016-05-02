package texturing;

import java.awt.Image;

/** A (potentially) deformed square with (potential) curvature
 * based on an image
 * @author Connor Lehmacher*/
public class SubTexture {
	/** Central Image must be a square*/
	Image image;
	/** Links on the right edge of the square*/
	Link linkR;
	/** Links on the top edge of the square*/
	Link linkT;
	/** Links on the left edge of the square*/
	Link linkL;
	/** Links on the bottom edge of the square*/
	Link linkB;
	/** an id for the texture which contains the SubTextures
	 * try to make id order +X, +Y, +Z, -X, -Y, -Z
	 * subdivide these then from closer to +Y of +X
	 * then subdivide these them from the most +Z of +X
	 * repeat along the other directions */
	int id;
	
	public SubTexture(Image i, Link lr, Link lt, Link ll, Link lb, int id) {
		image = i;
		linkR = lr;
		linkT = lt;
		linkL = ll;
		linkB = lb;
		this.id = id;
	}
}

