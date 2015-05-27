package Utilities;

import java.util.ArrayList;

import Graph.Node;

public class Path {
	public ArrayList<Node> path = new ArrayList<Node>();
	
	public Path(){
		
	}
	
	public Path(ArrayList<Node> nodes){
		this.path = nodes;
	}
	
	public int getLength(){
		return this.path.size();
	}
	
	public Node getNode(int index){
		return path.get(index);
	}
	
	public void removeNode(int index){
		path.remove(index);
	}
	
	public void removeNode(Node node){
		path.remove(node);
	}
	
	public void append(Node n){
		path.add(n);
	}
	
	public void prepend(Node n){
		path.add(0, n);
		n.setPaintNode(true);
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
			str += n.getName() + "\n";
		}
		return str;
	}
	
	public Node closestNode(Node origin, ArrayList<Node> path){
		//System.out.println(path);
		Node closest = null;
		float minDistance = Float.MAX_VALUE;
		
		for(int i = 0; i < path.size()-1; i++){
			float dist = Distance.timeToTarget(origin, path.get(i));
			if(dist < minDistance){
				minDistance = dist;
				closest = path.get(i);
			}
		}
		if(closest == null){
			return path.get(0);
		}
		return closest;
	}
}
