package Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Utilities.Cost;
import Utilities.Pair;

public class Graph {
	protected ArrayList<Node> nodes = new ArrayList<Node>();
	protected ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public ArrayList<Node> getNodes(){return this.nodes;}
	public ArrayList<Edge> getEdges(){return this.edges;}
	
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
	
	public ArrayList<Pair<Node,Cost>> getAdj(Node n){
		ArrayList<Pair<Node,Cost>> adjs = new ArrayList<Pair<Node,Cost>>();
		for(int i = 0; i < edges.size(); i++){
			Edge e = edges.get(i);
			if(e.getNodeA() == n)
				adjs.add(new Pair<Node,Cost>(e.getNodeB(), e.getCost()));
			else if(e.getNodeB() == n)
				adjs.add(new Pair<Node,Cost>(e.getNodeA(), e.getCost()));
		}
		return adjs;
	}
	
	public void contructNeighbours(){
		for(Node n : this.nodes){
			n.setNeighbourList(getAdj(n));
		}
	}
	
	public Graph(File file) throws FileNotFoundException, IOException{
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			for(String line; (line = reader.readLine()) != null; ){
				System.out.println(line);
				String[] info = line.split(Pattern.quote("|"));
				if(info.length == 5){
					/*
					 * NODE -> NAME, TIME, IMPORTANCE, X, Y
					 */
					Node n = new Node(info[0].trim(),
									  Integer.parseInt(info[1].trim()),
									  Integer.parseInt(info[2].trim()),
									  Integer.parseInt(info[3].trim()),
									  Integer.parseInt(info[4].trim()));
					this.nodes.add(n);
					/*
					 * EDGE -> NODE A, NODE B, COST
					 */
				} else if(info.length == 3){
					String nodeNameA = info[0];
					String nodeNameB = info[1];
					Node nodeA = this.getNode(nodeNameA.trim());
					Node nodeB = this.getNode(nodeNameB.trim());
					if(nodeA == null || nodeB == null){
						System.err.println("Error parsing Edge : " + line);
					} else {
						float w = Float.parseFloat(info[2].trim());
						Edge e = new Edge(nodeA, nodeB, new Cost(w));
						this.edges.add(e);
					}
				} else {
					System.err.println("Error parsing line : " + line);
				}
			}
			/*
			 * BUILD THE NEIGHBORS
			 */
			this.contructNeighbours();
			System.out.println("Finished importing Graph. Node Size : " + this.nodes.size() + " Edge Size : " + this.edges.size());
		}
	}
}
