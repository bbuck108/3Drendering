package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import components.PhysicalObject;
import components.Vector;

public class Loop {
	public static void loop() {
		while (!Display.isCloseRequested()) {
			//Move
			for(PhysicalObject t_1: Start.renderList){
				t_1.move();
			}
			
			
			// I have no idea what this does...
			// When this is causing a problem I'm sorry
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
            // render OpenGL here
			Start.camera.render();
			System.out.println("Done rendering");
             
			//Update display
            Display.update();
        }
         
        Display.destroy();
	}
}
