package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Logic.Edge;
import Logic.Graph;
import Logic.Node;

public class myPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Graph g_to_draw = null;
	
	protected static int MAP_SIZE = 600; 
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		if(myPanel.g_to_draw != null){
			drawGraph(g2, g_to_draw);
		}
		else{
			g2.drawString("LOAD A GRAPH", 50, 50);
		}
	}

	
	public void drawCenteredCircle(Graphics2D g, int x, int y, int r){
		x = x-(r);
		y = y-(r);
		
		g.setColor(Color.WHITE);
		g.fillOval(x,y,r*2,r*2);
	}
	
	public void drawConnectingLine(Graphics2D g, int x1, int y1, int x2, int y2){
		g.drawLine(x1, y1, x2, y2);
	}
	
	public void drawGraph(Graphics2D g, Graph gr){
		for(Edge e : gr.getEdges()){
			drawConnectingLine(g,
							   (e.getNodeA().getX()-1)*100+50,
							   (e.getNodeA().getY()-1)*100+50,
							   (e.getNodeB().getX()-1)*100+50,
							   (e.getNodeB().getY()-1)*100+50);
		}
		for(Node n : gr.getNodes()){
			drawCenteredCircle(g, (n.getX()-1)*100+50,
								  (n.getY()-1)*100+50, 25);
		}
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(MAP_SIZE, MAP_SIZE);
	}
	
}
