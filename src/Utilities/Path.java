package Utilities;

import java.util.ArrayList;

import Graph.Node;

public class Path {
	private ArrayList<Node> path = new ArrayList<Node>();
	
	public Path(){
		
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
	}
	
	public boolean contains(Node n){
		for(Node node : path){
			if(node.getName().equals(n.getName()))
				return true;
		}
		return false;
	}
}
