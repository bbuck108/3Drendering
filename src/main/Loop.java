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
			
			
			//
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
            // render OpenGL here
			Start.camera.render();
			System.out.println("Done rendering");
             
			//Update display
            Display.update();
            Start.x.editTo(Start.x.rotateBy(Vector.createFromSpherical(1, Math.PI/4, Math.PI/4)));
        }
         
        Display.destroy();
	}
}
