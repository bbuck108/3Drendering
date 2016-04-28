package render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import org.lwjgl.opengl.GL11;

import component.Ray;
import component.Rotation;
import component.Vector;

/** A collection of data and methods used to render the scene.
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class Camera{
	Vector point;
	Vector screen;
	Vector motion;
	Rotation rotation;
	
	public Camera(Vector p, Vector s, Vector v) {
		point = p;
		screen = s;
		motion = v;
		rotation = new Rotation(0,0,0);
	}
	
	public void render() {
		ForkJoinPool pool = new ForkJoinPool(2*Runtime.getRuntime().availableProcessors());
		ArrayList<RayTrace> pixelGroup = new ArrayList<RayTrace>();
		for(int i = 0; i < screen.x; i++) {
			for(int j = 0; j < screen.y; j++) {
				double x = i - screen.x/2;
				double y = j - screen.y/2;
				
				Vector angle = Vector.createFromRectangular(x, y, screen.z);
				angle = angle.rotateBy2(rotation);
				double theta = angle.theta();
				double phi = angle.phi();
				Ray ray = new Ray(point, theta, phi);
				
				pixelGroup.add(new RayTrace(i,j,ray));
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
