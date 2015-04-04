package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;

public class Graph {
	protected Vector<Node> nodes = new Vector<Node>();
	protected Vector<Edge> edges = new Vector<Edge>();
	
	public Vector<Node> getNodes(){return this.nodes;}
	public Vector<Edge> getEdges(){return this.edges;}
	
	public Node getNode(String nodeName){
		for(Node n : this.nodes){
			if(n.toString().equals(nodeName)){
				return n;
			}
		}
		System.out.println("Cannot find Node " + nodeName);
		System.exit(-1);
		return null;
	}
	
	public Vector<Node> getAdj(Node n){
		Vector<Node> adjs = new Vector<Node>();
		for(int i = 0; i < edges.size(); i++){
			Edge e = edges.elementAt(i);
			if(e.a == n)
				adjs.add(e.b);
			else if(e.b == n)
				adjs.add(e.a);
		}
		return adjs;
	}
	
	public Graph(File file) throws FileNotFoundException, IOException{
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			for(String line; (line = reader.readLine()) != null; ){
				System.out.println(line);
				/*CAN BE A NODE OR AN EDGE*/
				String[] info = line.split(Pattern.quote("|"));
				if(info.length == 5){// Node
					/*
					 * NAME, TIME, IMPORTANCE, X, Y
					 */
					Node n = new Node(info[0].trim(),
									  Integer.parseInt(info[1].trim()),
									  Integer.parseInt(info[2].trim()),
									  Integer.parseInt(info[3].trim()),
									  Integer.parseInt(info[4].trim()));
					this.nodes.add(n);
				} else if(info.length == 3){// Edge
					String nodeNameA = info[0];
					String nodeNameB = info[1];
					Node nodeA = this.getNode(nodeNameA.trim());
					Node nodeB = this.getNode(nodeNameB.trim());
					if(nodeA == null || nodeB == null){//one or both not found
						System.out.println("Error parsing Edge : " + line);
					} else { //nodes found, register edge
						int w = Integer.parseInt(info[2].trim());
						Edge e = new Edge(nodeA, nodeB, w);
						this.edges.add(e);
					}
				} else {
					System.out.println("Error parsing line : " + line);
				}
			}
			System.out.println("Finished importing Graph. Node Size : " + this.nodes.size() + " Edge Size : " + this.edges.size());
		}
	}
}
