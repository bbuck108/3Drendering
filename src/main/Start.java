package main;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import components.Camera;
import components.PhysicalObject;
import components.Sphere;
import components.Vector;

public class Start {
	public static ArrayList<PhysicalObject> renderList;
	public static Camera camera;
	public static void addToRenderList(PhysicalObject p_1) {
		renderList.add(p_1);
	}
	public static void main(String[] args) {
		renderList = new ArrayList<PhysicalObject>();
		
		//Add new objects here
		camera = new Camera(Vector.createFromRectangular(0, 0, 0), Vector.createFromRectangular(800, 600, 300), Vector.createFromRectangular(0, 0, 0));
		//new RectangularPrism(Vector.createFromRectangular(0, 0, 300), 100, Vector.createFromRectangular(0, 0, 50), 1, Color.RED);
		new Sphere(Vector.createFromRectangular(0, 0, 400), 100, Vector.createFromRectangular(0, 0, 0), 1, Color.YELLOW);
		//
		
		try{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.setFullscreen(true);
			Display.create();
		} catch (LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		Loop.loop();
	}
}
