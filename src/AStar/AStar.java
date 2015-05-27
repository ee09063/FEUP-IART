package AStar;

import java.util.ArrayList;
import java.util.Collections;

import Graph.Graph;
import Graph.Node;
import Utilities.Angle;
import Utilities.Distance;
import Utilities.NodeComparator;
import Utilities.Path;

public class AStar{
	private ArrayList<Node> closed = new ArrayList<Node>();
	private ArrayList<Node> open = new ArrayList<Node>();
	@SuppressWarnings("unused")
	private Path path;
	private static Graph graph;
	private Node start;
	
	private int TIME_LIMIT;
	
	public AStar(Graph g, Node start, int timeLimit) {
		graph = g;
		this.start = start;
		this.TIME_LIMIT = timeLimit;
		
		new Angle().calculateAngles(start, graph.getNodes());
	}
	
	public Path runAStar(Node target){
		graph.clearNodes();
		closed.clear();
		open.clear();
		open.add(start);
		Node current = null;
		
		while(open.size() != 0){
			current = open.get(0);
			/*System.out.println("-----------------------------------");
			System.out.println("I AM " + current.getName() + " IMPORTANCE: " + current.getG() +
					           " TIME: " + current.getTotalTimeSpent() + " + " + Distance.timeToTarget(current, target) + " = " +
					           (current.getTotalTimeSpent() + Distance.timeToTarget(current, target)));*/
			
			open.remove(current);
			closed.add(current);

			ArrayList<Node> neighbors = gatherValidNeighbors(current, target);
			
			if(neighbors.size() > 0){
				for(Node neighbor : neighbors){
					boolean neighborIsBetter = false;
					
					if(closed.contains(neighbor)){
						continue;
					}
					
					float neighborCost = neighborCost(neighbor, current); // current cost
					
					if(!open.contains(neighbor)){
						open.add(neighbor);
						neighborIsBetter = true;
					} else if(neighborCost < neighbor.getG()){
						neighborIsBetter = true;
						//System.out.println("WILL UPDATE " + neighbor.getName());
					} else {
						neighborIsBetter = false;
						//System.out.println("WILL NOT UPDATE " + neighbor.getName());
					}
					if(neighborIsBetter){
						neighbor.setParent(current);
						neighbor.setG(neighborCost);
						float futureCost = futureCost(neighbor, target);
						neighbor.setH(futureCost);
						neighbor.setTotalTimeSpent(neighbor.getParent().getTotalTimeSpent() +
								                   neighbor.getVisitingTime() +
								                   Distance.timeToTarget(current, neighbor));
						/*System.out.println("TOTAL TIME OF " + neighbor.getName() + ": " + neighbor.getTotalTimeSpent() + " + " + Distance.timeToTarget(neighbor, target) +
								           " = " + (neighbor.getTotalTimeSpent() + Distance.timeToTarget(neighbor, target)));*/
					}
				}
			} else if(neighbors.size() == 0){
				target.setParent(current);
				//System.out.println("NO MORE VALID NODES, BUILDING PATH");
				return reconstructPath(target, graph, target);
			}
			Collections.sort(open, new NodeComparator(current));
			//System.out.println("-----------------------------------");
		}
		target.setParent(current);
		//System.out.println("OPEN LIST EMPTY");
		return reconstructPath(target, graph, target);
	}
	
	ArrayList<Node> gatherValidNeighbors(Node current, Node target){
		ArrayList<Node> list = new ArrayList<Node>();
		for(Node n : graph.getNodes()){
			if(!closed.contains(n) && !n.Visited()){
				float pathCost = current.getTotalTimeSpent() + n.getVisitingTime() +
						Distance.timeToTarget(n, target) + Distance.timeToTarget(current, n);
				if(pathCost < TIME_LIMIT){
					list.add(n);
				} else {
					//System.out.println(n.getName() + " IS NOT A VALID NEIGHBOR. TIME: " + pathCost);
				}
			}
		}
		return list;
	}
	
	private Path reconstructPath(Node node, Graph graph, Node target){
		Path path = new Path();
		boolean var = true;
		while(var){
			path.prepend(node);
			node.setVisited(true);
			node = node.getParent();
			if(node.equals(target)){
				path.prepend(target);
				var = false;
			}
		}
		this.path = path;
		return path;
	}
	
	private float neighborCost(Node neighbor, Node current){
		float neighborCost = neighbor.getImportance() + current.getG();
		return neighborCost;
	}
	
	private float futureCost(Node current, Node target){
		ArrayList<Node> neighbors = gatherValidNeighbors(current, target);
		float highestImportance = 0;
		for(Node n : neighbors){
			if(n.getImportance() < highestImportance){
				highestImportance = n.getImportance();
			}
		}
		return highestImportance;
	}
	
	public static void clear(){
		for(Node n : graph.getNodes()){
			n.setPaintNode(false);
			n.setParent(null);
			n.setVisited(false);
		}
	}
	
	protected Node getFirstInOpen() {
		return (Node) open.get(0);
	}
	
	protected void addToOpen(Node node) {
		open.add(node);
	}
	
	protected boolean inOpenList(Node node) {
		return open.contains(node);
	}
	
	protected void removeFromOpen(Node node) {
		open.remove(node);
	}
	
	protected void addToClosed(Node node) {
		closed.add(node);
	}
	
	protected boolean inClosedList(Node node) {
		return closed.contains(node);
	}
	
	protected void removeFromClosed(Node node) {
		closed.remove(node);
	}
}