package components;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import main.Start;

public class Camera {
	Vector point;
	Vector screen;
	Vector motion;
	double maxDistance;
	
	public Camera(Vector p, Vector s, Vector v){
		point = p;
		screen = s;
		motion = v;
		maxDistance = 1;
	}
	public void render(){
		for(int i = 0; i < screen.getX(); i++){
			for(int j = 0; j < screen.getY(); j++){
				double x = i - screen.getX()/2;
				double y = j - screen.getY()/2;
				Color color = Color.BLUE;
				
				Vector angle = Vector.createFromRectangular(x, y, screen.getZ());
				double theta = angle.theta();
				double phi = angle.phi();
				
				int k = 0;
				while(k < maxDistance){
					Vector check = Vector.createFromSpherical(k, theta, phi).addWith(point);
					for(RectangularPrism object: Start.renderList){
						if(object.isPointInside(check)){
							color = object.getColor();
							break;
						}
					}
					k++;
				}
				
				// set the color of the quad (R,G,B,A)
				GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getTransparency());
				
				// draw point
				GL11.glBegin(GL11.GL_POINT);
				GL11.glVertex2d(i,j);
				GL11.glEnd();
			}
		}
	}
}
