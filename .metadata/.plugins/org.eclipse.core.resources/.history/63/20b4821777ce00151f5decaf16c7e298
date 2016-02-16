package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class SelectionGUI extends JFrame{
	boolean server;
	boolean done;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8353624308759738454L;
	
	public SelectionGUI(){
		ButtonGroup group = new ButtonGroup();
		SelectionGUI window = this;
		
		JLabel lblChoose = new JLabel("Choose:");
		getContentPane().add(lblChoose, BorderLayout.NORTH);
		
		JRadioButton rdbtnServer = new JRadioButton("Server");
		getContentPane().add(rdbtnServer, BorderLayout.WEST);
		group.add(rdbtnServer);
		rdbtnServer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				server = true;
				
			}
			
		});
		
		JRadioButton rdbtnClient = new JRadioButton("Client");
		getContentPane().add(rdbtnClient, BorderLayout.EAST);
		group.add(rdbtnClient);
		rdbtnClient.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				server = false;
			}
			
		});
		
		JButton btnSelect = new JButton("Select");
		getContentPane().add(btnSelect, BorderLayout.SOUTH);
		btnSelect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				done = true;
				window.dispose();
				
			}
			
		});
		setSize(240,240);
		setVisible(true);
		
	}

	public boolean isServer() {
		// TODO Auto-generated method stub
		while(!done){
			//System.out.println("This makes the program work for some reason....");
		}
		return server;
	}

}
