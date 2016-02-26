package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import components.RectangularPrism;
import util.Axis;
import util.Position;

public class Loop {
	public static void loop() {
		while (!Display.isCloseRequested()) {
			//Move
			for(RectangularPrism t_1: Start.renderList){
				t_1.translateBy(t_1.getMotion());
			}
			for(RectangularPrism t_1: Start.renderList){
				System.out.println("x: "+t_1.getSide(Position.FRONT, Axis.X).getStart().getZ());
				System.out.println("y: "+t_1.getSide(Position.FRONT, Axis.Y).getStart().getZ());
				System.out.println("z: "+t_1.getSide(Position.FRONT, Axis.Z).getStart().getZ());
			}
			
			//
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
            // render OpenGL here
			//Start.camera.render();
			//System.out.println("Done rendering");
             
			//Update display
            Display.update();
        }
         
        Display.destroy();
	}
}
