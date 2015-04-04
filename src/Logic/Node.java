package Logic;

public class Node implements Comparable<Node> {
	private String name;
	private int time_cost;
	private int importance;
	protected boolean visited;
	protected boolean enjoyed;
	
	protected int x;
	protected int y;
	
	public Node(String name, int time_cost, int importance, int x, int y){
		this.name = name;
		this.time_cost = time_cost;
		this.importance = importance;
		this.x = x;
		this.y = y;
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
	
	public int getCost(){
		return this.time_cost;
	}
	
	public int getImportance(){
		return this.importance;
	}
	
	public String toString(){
		return this.name;
	}

	@Override
	public int compareTo(Node o) {
		String a = this.toString();
		String b = o.toString();
		return a.compareTo(b);
	}
	
	public int getX(){return this.x;}
	
	public int getY(){return this.y;}
}
