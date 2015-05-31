package Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;



import Database.LatLongLoader;

public class Graph {
	
	public static double DISPLACE_X = 0.13;
	public static double DISPLACE_Y = 0.13;
	public static double MOUSE_DISP_X = 0.0;
	public static double MOUSE_DISP_Y = 0.0;
	public static double HEIGHT;
	public static double WIDTH;
	protected ArrayList<Node> nodes = new ArrayList<Node>();
	
	public ArrayList<Node> getNodes(){return this.nodes;}
	
	private double displaceX;
	private double displaceY;
	
	public Graph(File file, double width, double height) throws FileNotFoundException, IOException{
		
		WIDTH = width;
		HEIGHT = height;
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			for(String line; (line = reader.readLine()) != null; ){
				String[] info = line.split(Pattern.quote("|"));
				if(info.length == 4){
					Node n = new Node(info[0].trim(),
							         (info[1].trim()),
							          Integer.parseInt(info[2].trim()), //time
							          Integer.parseInt(info[3].trim())); //importance
					this.nodes.add(n);
				} else {
					System.err.println("Error parsing line : " + line);
				}
			}
			LatLongLoader lll = new LatLongLoader();
			lll.getLatLong(this);
			System.out.println("Finished importing Graph. Node Size : " + this.nodes.size());
		}
	}

	public void displaceNodes(){
		for(Node n : nodes){
			n.setX(((n.getLatLongX() - displaceX) * WIDTH * DISPLACE_X) + WIDTH/2.0 + MOUSE_DISP_X);
			n.setY(((n.getLatLongY() - displaceY) * HEIGHT * DISPLACE_Y) + HEIGHT/2.0 + MOUSE_DISP_Y);
		}
	}
	
	public void clearNodes(){
		for(Node n : nodes){
			if(!n.Visited()){
				n.setG(0);
				n.setH(0);
				n.setTotalTimeSpent(0);
			}
		}
	}
	
	public Node getNode(String nodeName){
		for(Node n : this.nodes){
			if(n.toString().equals(nodeName)){
				return n;
			}
		}
		System.err.println("Cannot find Node " + nodeName);
		System.exit(-1);
		return null;
	}
	
	
	public double getDisplaceY() {
		return displaceY;
	}
	public void setDisplaceY(double displaceY) {
		this.displaceY = displaceY;
	}
	public double getDisplaceX() {
		return displaceX;
	}
	public void setDisplaceX(double displaceX) {
		this.displaceX = displaceX;
	}
}
