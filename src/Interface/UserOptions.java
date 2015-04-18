package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import AStar.AStarPathFinder;
import Graph.Graph;
import Utilities.Path;

public class UserOptions extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

		protected JButton b1, b2, b3;

    	JTextArea walking_speed;
    	JTextArea hotel;
    	
    	JTextArea node1;
    	JTextArea node2;
    	
    	static Graph graph;
    	
		public UserOptions() {
		
			Border border_1 = BorderFactory.createLineBorder(Color.RED, 1);
			Border border_2 = BorderFactory.createLineBorder(Color.RED, 2);
			
			this.setBorder(border_2);
			
			JLabel hotel_label = new JLabel("Hotel");
			hotel = new JTextArea();
			hotel.setColumns(7);
			hotel_label.setLabelFor(hotel);
			add(hotel_label);
			hotel.setBorder(border_1);
			add(hotel);
			
			/*JLabel space = new JLabel("   ");
			add(space);*/
			
			JLabel walking_speed_label = new JLabel("Walking Speed");
			walking_speed = new JTextArea();
			walking_speed.setColumns(5);
			walking_speed_label.setLabelFor(walking_speed);
			add(walking_speed_label);
			walking_speed.setBorder(border_1);
			add(walking_speed);
			JLabel unit = new JLabel("m/s");
			add(unit);
			
			/*
			 * FOR TESTING PURPOSES ONLY
			 */
			JLabel node1_label = new JLabel("Origin");
			node1 = new JTextArea();
			node1.setColumns(7);
			node1_label.setLabelFor(node1);
			add(node1_label);
			node1.setBorder(border_1);
			add(node1);
			
			/*add(space);*/
			
			JLabel node2_label = new JLabel("Destination");
			node2 = new JTextArea();
			node2.setColumns(7);
			node2_label.setLabelFor(node2);
			add(node2_label);
			node2.setBorder(border_1);
			add(node2);
			
			b1 = new JButton("RUN");
			b1.setVerticalTextPosition(AbstractButton.CENTER);
			b1.setHorizontalTextPosition(AbstractButton.LEADING);
			b1.setActionCommand("run");
			b1.addActionListener(this);
			add(b1);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if("run".equals(e.getActionCommand())){
				if(graph != null){
					System.out.println("RUNNING");
					AStarPathFinder pathFinder = new AStarPathFinder(graph, graph.getNode(node1.getText()));
					Path path = pathFinder.runAStar(graph.getNode(node2.getText()));
					System.out.println(path);
				} else System.err.println("NO MAP LOADED.");
			}
		}
		
		public static void setGraph(Graph graphToRun){
			graph = graphToRun;
		}
    }