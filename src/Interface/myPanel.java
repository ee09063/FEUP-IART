package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Graph.Edge;
import Graph.Graph;
import Graph.Node;

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

	
	public void drawCenteredCircle(Graphics2D g, int x, int y, int r, Color color){
		x = x-(r);
		y = y-(r);
		
		g.setColor(color);
		g.fillOval(x,y,r*2,r*2);
	}
	
	public void drawOutlineCircle(Graphics2D g, int x, int y, int r, Color color){
		x = x-(r);
		y = y-(r);
		
		g.setColor(color);
		g.fillOval(x,y,r*2,r*2);
	}
	
	public void drawConnectingLine(Graphics2D g, int x1, int y1, int x2, int y2, Color color){
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}
	
	public void drawGraph(Graphics2D g, Graph gr){
		for(Edge e : gr.getEdges()){
			if(e.paint()){
				drawEdge(g, e, Color.GREEN);
			} else if(!e.paint()){
				drawEdge(g, e, Color.BLACK);
			}
		}
		for(Node n : gr.getNodes()){
			if(n.getPaintNode()){
				drawNode(g, n, Color.GREEN, Color.WHITE, Color.BLACK);
			} else if(!n.getPaintNode()){
				drawNode(g, n, Color.BLACK, Color.WHITE, Color.BLACK);
			}
		}
	}
	
	public void drawNode(Graphics2D g, Node n, Color outline, Color center, Color text){
		drawOutlineCircle(g, (n.getX()-1)*100+50,
				 (n.getY()-1)*100+50, 28, outline);
		drawCenteredCircle(g, (n.getX()-1)*100+50,
						  (n.getY()-1)*100+50, 25, center);
		drawCenteredText(g, (n.getX()-1)*100+50,
						(n.getY()-1)*100+50,
						10,
						n.getName(), text);
	}
	
	public void drawEdge(Graphics2D g, Edge e, Color color){
		drawConnectingLine(g,
				   (e.getNodeA().getX()-1)*100+50,
				   (e.getNodeA().getY()-1)*100+50,
				   (e.getNodeB().getX()-1)*100+50,
				   (e.getNodeB().getY()-1)*100+50, color);
	}
	
	public void drawCenteredText(Graphics g, int x, int y, float size, String text, Color color) {
		// Create a new font with the desired size
		Font newFont = g.getFont().deriveFont(size);
		g.setFont(newFont);
		g.setColor(color);
		// Find the size of string s in font f in the current Graphics context g.
		FontMetrics fm = g.getFontMetrics();
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(text, g);

		int textHeight = (int) (rect.getHeight());
		int textWidth = (int) (rect.getWidth());

		// Find the top left and right corner
		int cornerX = x - (textWidth / 2);
		int cornerY = y - (textHeight / 2) + fm.getAscent();

		g.drawString(text, cornerX, cornerY);  // Draw the string.
		}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(MAP_SIZE, MAP_SIZE);
	}
	
}
