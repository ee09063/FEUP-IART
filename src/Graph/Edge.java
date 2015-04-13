package Graph;

import Utilities.Cost;

public class Edge {
	private Node a, b;
	private Cost cost;
	private boolean paint;
	
	public Edge(Node a, Node b, Cost cost){
		this.a = a;
		this.b = b;
		this.cost = cost;
		this.paint = false;
	}
	
	public Cost getCost(){
		return this.cost;
	}
	
	public String toString(){
		return a + "<==>" + b;
	}
	
	public Node getNodeA(){return this.a;}
	public Node getNodeB(){return this.b;}
	
	public boolean paint(){
		return this.paint;
	}
	
	public void setPaint(boolean paint){
		this.paint = paint;
	}
}
