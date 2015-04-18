package Utilities;

import Graph.Node;

public class Cost {
	/*
	 * FOR NOW IT'S ONLY A SIMPLE FLOAT
	 */
	private float cost;
	
	public Cost(float cost){
		this.cost=cost;
	}
	
	public Cost(Node nodeA, Node nodeB) {
		Distance distance = new Distance();
		this.cost = distance.heuristicCost(nodeA, nodeB);
	}

	public float getCost(){
		return this.cost;
	}
	
	public void setCost(float cost){
		this.cost = cost;
	}
}
