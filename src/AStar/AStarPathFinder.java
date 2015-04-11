package AStar;

import java.util.ArrayList;

import Graph.Graph;
import Graph.Node;
import Utilities.Cost;
import Utilities.Pair;
import Utilities.Path;
import Utilities.SortedList;

public class AStarPathFinder{
	private ArrayList<Node> closed = new ArrayList<Node>();
	private SortedList open = new SortedList();
	@SuppressWarnings("unused")
	private Path path;
	@SuppressWarnings("unused")
	private Graph graph;
	private Node start;
	
	public AStarPathFinder(Graph g, Node start) {
		this.graph = g;
		this.start = start;
	}
	
	public Path runAStar(Node target){
		
		System.out.println("STARTING NODE: " + this.start.getName());
		System.out.println("TARGET NODE: " + target.getName());
		
		closed.clear();
		open.clear();
		open.add(this.start);
		
		while(open.size() != 0){
			Node current = (Node)open.first();
			/*
			 * CHECK IF TARGET
			 */
			if(current == target){
				System.out.println("TARGET FOUND");
				return reconstructPath(current);
			}
		
			open.remove(current);
			closed.add(current);
			
			for(Pair<Node,Cost> pair : current.getNeighbourList()){
				
				Node neighbor = pair.getFirst();
				Cost edge = pair.getSecond();
				boolean neighborIsBetter;
				
				if(closed.contains(neighbor)){
					continue;
				}
				/*
				 * CALCULATE HOW LONG THE PATH IS
				 */
				float neighborCost = neighbor.getTimeCost()
										+ edge.getCost()
										+ current.getFValue();
				//neighbor.setCost(neighborCost);
				
				if(!open.contains(neighbor)){
					open.add(neighbor);
					neighborIsBetter = true;
				} else if(neighborCost < neighbor.getFValue()){
					neighborIsBetter = true;
				} else {
					neighborIsBetter = false;
				}
				if(neighborIsBetter){
					neighbor.setParent(current);
					neighbor.setCost(neighborCost);
					//neighbor.setHeuristic(heuristic);
				}
			}
			
		}
		return null;
	}
	
	private Path reconstructPath(Node node){
		Path path = new Path();
		while(!(node == null)){
			path.prepend(node);
			node = node.getParent();
		}
		this.path = path;
		return path;
	}
	
	protected Node getFirstInOpen() {
		return (Node) open.first();
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