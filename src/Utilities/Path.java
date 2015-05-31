package Utilities;

import java.util.ArrayList;

import Graph.Node;

public class Path {
	public ArrayList<Node> path = new ArrayList<Node>();
	
	public double totalTime;
	
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
	
	public void setTotalTime(double step){
		Node last = getNode(path.size()-2);
		Node hotel = getNode(0);
		Distance dist = new Distance(step);
		totalTime = last.getTotalTimeSpent() + dist.timeToTarget(last, hotel);
	}
	
}
