package Graph;

import java.util.ArrayList;

import Utilities.Cost;
import Utilities.Pair;

public class Node implements Comparable<Node>{
	
	private String name;
	private int time_cost;
	private int importance;
	protected boolean visited;
	protected boolean enjoyed;
	protected Node parent;
	private ArrayList<Pair<Node,Cost>> neighbours = new ArrayList<Pair<Node,Cost>>();
	
	protected int x;
	protected int y;
	
	private float heuristic;
	private float cost;
	
	public Node(String name, int time_cost, int importance, int x, int y){
		this.name = name;
		this.time_cost = time_cost;
		this.importance = importance;
		this.x = x;
		this.y = y;
		this.parent = null;
		this.cost=0;
		this.heuristic=0;
	}
	
	public boolean isVisited(){
		return this.visited;
	}
	
	public void visit(){
		this.visited = true;
	}
	
	public void unvisit(){
		this.visited = false;
	}
	
	public boolean isEnjoyed(){
		return this.enjoyed;
	}
	
	public void enjoy(){
		this.enjoyed = true;
	}
	
	public int getTimeCost(){
		return this.time_cost;
	}
	
	public void setCost(float neighborCost){
		this.cost=neighborCost;
	}
	
	public void setHeuristic(float heuristic){
		this.heuristic=heuristic;
	}
	
	public int getImportance(){
		return this.importance;
	}
	
	public String toString(){
		return this.name;
	}

	public void setParent(Node parent){
		this.parent = parent;
	}
	
	public Node getParent(){
		return this.parent;
	}
	
	public String getName(){
		return this.name;
	}

	public void addNeighbour(Node n, Cost c){
		this.neighbours.add(new Pair<Node,Cost>(n,c));
	}
	
	public ArrayList<Pair<Node,Cost>> getNeighbourList(){
		return this.neighbours;
	}
	
	public void setNeighbourList(ArrayList<Pair<Node,Cost>> list){
		this.neighbours = list;
	}
	
	public int compareFValue(Object o) {
		Node n = (Node) o;
		
		float f = heuristic + cost;
		float of = n.heuristic + n.cost;
		
		if (f < of) {
			return -1;
		} else if (f > of) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public int getX(){return this.x;}
	
	public int getY(){return this.y;}

	public float getFValue(){
		return this.heuristic + this.cost;
	}
	
	@Override
	public int compareTo(Node n) {
		if(this.name.equals(n.getName())){
			return 1;
		}
		return 0;
	}
}
