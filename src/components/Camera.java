package components;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import main.Start;

public class Camera {
	Vector point;
	Vector screen;
	Vector motion;
	double maxDistance;
	Vector lightSource;
	
	public Camera(Vector p, Vector s, Vector v) {
		point = p;
		screen = s;
		motion = v;
		maxDistance = 150;
		lightSource = Vector.createFromRectangular(400, 400, 400);
	}
	
	public void render() {
		for(int i = 0; i < screen.x; i++) {
			switch(i){
				case 200:
					//System.out.println("25%");
					break;
				case 400:
					//System.out.println("50%");
					break;
				case 600:
					//System.out.println("75%");
					break;
				case 799:
					//System.out.println("99%");
			}
			for(int j = 0; j < screen.y; j++) {
				double x = i - screen.x/2;
				double y = j - screen.y/2;
				Color color = Color.BLUE;
				
				Vector angle = Vector.createFromRectangular(x, y, screen.z);
				double theta = angle.theta();
				double phi = angle.phi();
				Ray ray = new Ray(point, theta, phi);
				//double r = angle.norm();
				
				/*for(int k = 0; k < maxDistance; k++) {
					Vector check = Vector.createFromSpherical(k+r, theta, phi).plus(point);
					for(PhysicalObject object: Start.renderList) {
						if(object.isIntersecting(check)) {
							color = object.getColor();
							break;
						}
					}
				}*/
				for(PhysicalObject object: Start.renderList) {
					double distance = object.isIntersecting(ray);
					if(distance != -1){
						//System.out.println(distance);
						Vector intersection = Vector.createFromSpherical(distance, theta, phi);
						double shade = (new Segment(intersection, lightSource)).direction().toUnit().dot(object.getSurfaceNormal(intersection).toUnit());
						if(shade < 0){shade = 0;}
						shade = (0.2 + 0.8*shade);
						color = new Color((int)(object.getColor().getRed()*shade),(int)(object.getColor().getGreen()*shade),(int)(object.getColor().getBlue()*shade));
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
