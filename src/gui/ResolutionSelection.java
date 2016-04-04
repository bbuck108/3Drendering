package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.lwjgl.opengl.DisplayMode;

import main.Start;

public class ResolutionSelection {
	protected DisplayMode dm;
	boolean go = false;

	public ResolutionSelection(){
		JFrame dialog = new JFrame("Select resolution.");
		dialog.setSize(400, 400);
		dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		
		for (int i=0;i<Start.modes.length;i++) {
		    DisplayMode current = Start.modes[i];
		    JButton jb = new JButton(Integer.toString(i));
		    jb.addActionListener(new ActionListener(){

		    	@Override
				public void actionPerformed(ActionEvent arg0) {
		    		if(arg0.getSource() instanceof JButton){
						dm = Start.modes[Integer.parseInt(((JButton)(arg0.getSource())).getText())];
						go = true;	
						dialog.dispose();
		    		}
				}
			    	
			});
			panel.add(jb);
			panel.add(new JLabel(current.getWidth() + "x" + current.getHeight() + "x" +
			                     current.getBitsPerPixel() + " " + current.getFrequency() + "Hz"));
		}
		dialog.add(panel);
		dialog.setVisible(true);
	}
	
	public DisplayMode getDisplayMode(){
		while(!go){System.out.print("");}
		return dm;
	}
}
