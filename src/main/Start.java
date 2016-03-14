package main;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.json.JSONArray;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import components.Camera;
import components.PhysicalObject;
import components.RectangularPrism;
import components.Sphere;
import components.Vector;
import file.ParseFile;

public class Start {
	public static ArrayList<PhysicalObject> renderList;
	public static Camera camera;
	static RectangularPrism x;
	static File input;
	
	public static void addToRenderList(PhysicalObject p_1) {
		renderList.add(p_1);
	}
	
	public static void main(String[] args) {
		renderList = new ArrayList<PhysicalObject>();
		
		JFileChooser window = new JFileChooser();
		window.showOpenDialog(window);
		input = window.getSelectedFile();
		JSONArray set = new JSONArray(ParseFile.parseFile(input.getPath()));
		for(int i = 0; i<set.length();i++){
			System.out.println(0);
			new PhysicalObject(set.getJSONObject(i));
		}
		
		//
		camera = new Camera(Vector.createFromRectangular(0, 0, 0), Vector.createFromRectangular(800, 600, 600), Vector.createFromRectangular(0, 0, 0));
		//
		
		
		//Builds the screen
		try{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.setFullscreen(true);
			Display.create();
		} catch (LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		
		//I have no idea what this does
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		//Begins the game loop
		loop();
	}
	
	public static void loop() {
		while (!Display.isCloseRequested()) {
			//Move
			for(PhysicalObject t_1: renderList){
				t_1.move();
			}
			
			
			// I have no idea what this does...
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
            // render OpenGL here
			camera.render();
			//System.out.println("Done rendering");
             
			//Update display
            Display.update();
        }
         
        Display.destroy();
	}
}
