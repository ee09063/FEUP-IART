package Utilities;

import java.util.ArrayList;

import Graph.Edge;
import Graph.Graph;
import Graph.Node;

public class Path {
	private ArrayList<Node> path = new ArrayList<Node>();
	private Graph graph;
	
	public Path(Graph graph){
		this.graph = graph;
	}
	
	public int getLength(){
		return this.path.size();
	}
	
	public Node getNode(int index){
		return path.get(index);
	}
	
	public void append(Node n){
		path.add(n);
	}
	
	public void prepend(Node n){
		path.add(0, n);
		/*
		 * 
		 */
		n.setPaintNode(true);
	}
	
	public void paintEdges(){
		for(Edge e : this.graph.getEdges()){
			Node a = e.getNodeA();
			Node b = e.getNodeB();
			if(a.getPaintNode() && b.getPaintNode()){
				if(a.getParent()!=null){
					if(a.getParent().equals(b)){
						e.setPaint(true);
					}
				}
				if(b.getParent()!=null){
					if(b.getParent().equals(a)){
						e.setPaint(true);
					}
				}
			}
		}
	}
	
	public boolean contains(Node n){
		for(Node node : path){
			if(node.getName().equals(n.getName()))
				return true;
		}
		return false;
	}
	
	public String toString(){
		String str = "";
		for(Node n : path){
			str += n.getName() + "->";
		}
		return str;
	}
}
