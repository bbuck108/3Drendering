package components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import org.lwjgl.opengl.GL11;

/** A collection of data and methods used to render the scene.
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class Camera{
	Vector point;
	Vector screen;
	Vector motion;
	Vector lightSource;
	Rotation rotation;
	
	public Camera(Vector p, Vector s, Vector v) {
		point = p;
		screen = s;
		motion = v;
		rotation = new Rotation(0,0,0);
		lightSource = Vector.createFromRectangular(1000, 0, 0);
	}
	
	public void render() {
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		ArrayList<PixelRender> pixelGroup = new ArrayList<PixelRender>();
		for(int i = 0; i < screen.x; i++) {
			for(int j = 0; j < screen.y; j++) {
				pixelGroup.add(new PixelRender(i,j,this));
				pool.execute(pixelGroup.get(pixelGroup.size()-1));
			}
		}
		pixelGroup.forEach(t_1 -> {
			double[] data = t_1.join();
			int i = (int)data[0];
			int j = (int)data[1];
			Color color = new Color((int) data[2]);
				
			// set the color of the quad (R,G,B,A)
			GL11.glColor3f((color.getRed()/255.0f), color.getGreen()/255.0f, color.getBlue()/255.0f);
			
			// draw point
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2d(i,j);
			GL11.glVertex2d(i+1,j);
			GL11.glVertex2d(i+1,j+1);
			GL11.glVertex2d(i,j+1);
			GL11.glEnd();
		});
	}
}
