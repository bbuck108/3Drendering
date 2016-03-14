package components;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import main.Start;

public class Camera {
	Vector point;
	Vector screen;
	Vector motion;
	Vector lightSource;
	
	public Camera(Vector p, Vector s, Vector v) {
		point = p;
		screen = s;
		motion = v;
		lightSource = Vector.createFromRectangular(1000, 0, 0);
	}
	
	public void render() {
		for(int i = 0; i < screen.x; i++) {
			for(int j = 0; j < screen.y; j++) {
				double x = i - screen.x/2;
				double y = j - screen.y/2;
				Color color = Color.BLACK;
				
				Vector angle = Vector.createFromRectangular(x, y, screen.z);
				double theta = angle.theta();
				double phi = angle.phi();
				Ray ray = new Ray(point, theta, phi);
				
				for(PhysicalObject object: Start.renderList) {
					Shape shape = object.getShape();
					double distance = shape.isIntersecting(ray);
					
					if(distance != -1){
						Vector intersection = Vector.createFromSpherical(distance, theta, phi).plus(point);
						double shade = Math.cos(shape.getSurfaceNormal(intersection).angleWith((new Segment(intersection, lightSource)).direction()));
						if(shade < 0){shade = 0;}
						shade = (0.1 + 0.9*shade);
						color = new Color((int)(object.getColor().getRed()*shade),(int)(object.getColor().getGreen()*shade),(int)(object.getColor().getBlue()*shade));
					}
				}
				
				// set the color of the quad (R,G,B,A)
				GL11.glColor3f((color.getRed()/255.0f), color.getGreen()/255.0f, color.getBlue()/255.0f);
				
				// draw point
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(i,j);
				GL11.glVertex2d(i+1,j);
				GL11.glVertex2d(i+1,j+1);
				GL11.glVertex2d(i,j+1);
				GL11.glEnd();
			}
		}
	}
}
