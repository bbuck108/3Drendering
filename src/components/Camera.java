package components;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import main.Start;

public class Camera {
	Vector point;
	Vector screen;
	Vector motion;
	double maxDistance;
	
	public Camera(Vector p, Vector s, Vector v) {
		point = p;
		screen = s;
		motion = v;
		maxDistance = 150;
	}
	
	public void render() {
		for(int i = 0; i < screen.getX(); i++) {
			switch(i){
				case 200:
					System.out.println("25%");
					break;
				case 400:
					System.out.println("50%");
					break;
				case 600:
					System.out.println("75%");
					break;
				case 799:
					System.out.println("99%");
			}
			for(int j = 0; j < screen.getY(); j++) {
				double x = i - screen.getX()/2;
				double y = j - screen.getY()/2;
				Color color = Color.BLUE;
				
				Vector angle = Vector.createFromRectangular(x, y, screen.getZ());
				double theta = angle.theta();
				double phi = angle.phi();
				double r = angle.norm();
				
				for(int k = 0; k < maxDistance; k++) {
					Vector check = Vector.createFromSpherical(k+r, theta, phi).addWith(point);
					for(PhysicalObject object: Start.renderList) {
						if(object.isIntersecting(check)) {
							color = object.getColor();
							break;
						}
					}
				}
				
				// set the color of the quad (R,G,B,A)
				GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getTransparency());
				//GL11.glColor3d(0.0d, 0.0d, 255.0d);
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
