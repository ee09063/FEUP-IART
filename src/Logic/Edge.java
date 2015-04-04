package Logic;

public class Edge {
	protected Node a, b;
	protected double weight;
	
	public Edge(Node a, Node b, double weight){
		this.a = a;
		this.b = b;
		this.weight = weight;
	}
	
	public double getWeight(){
		return this.weight;
	}
	
	public String toString(){
		return a + "<==>" + b;
	}
	
	public Node getNodeA(){return this.a;}
	public Node getNodeB(){return this.b;}
	
}
