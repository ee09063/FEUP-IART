package AStar;

import java.util.ArrayList;
import java.util.Collections;

import Graph.Graph;
import Graph.Node;
import Utilities.Cost;
import Utilities.NodeComparator;
import Utilities.Pair;
import Utilities.Path;

public class AStarPathFinder{
	private ArrayList<Node> closed = new ArrayList<Node>();
	//private SortedList open = new SortedList();
	private ArrayList<Node> open = new ArrayList<Node>();
	@SuppressWarnings("unused")
	private Path path;
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
			Node current = open.get(0);
			/*
			 * CHECK IF TARGET
			 */
			
			System.out.println("I AM " + current.getName());
			
			if(current == target){
				System.out.println("TARGET FOUND");
				return reconstructPath(current, graph);
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
				System.out.println("I AM " + current.getName() + " COST OF " + neighbor.getName() + " : " + neighborCost);
				//neighbor.setCost(neighborCost);
				
				if(!open.contains(neighbor)){
					open.add(neighbor);
					System.out.println("ADDED " + neighbor.getName() + " TO OPEN LIST");
					neighborIsBetter = true;
				} else if(neighborCost < neighbor.getFValue()){
					neighborIsBetter = true;
					System.out.println("I AM " + current.getName() + " WILL UPDATE " + neighbor.getName());
				} else {
					neighborIsBetter = false;
					System.out.println("I AM " + current.getName() + " WILL NOT UPDATE " + neighbor.getName());
				}
				if(neighborIsBetter){
					neighbor.setParent(current);
					neighbor.setCost(neighborCost);
					//neighbor.setHeuristic(heuristic);
				}
			}
			Collections.sort(open, new NodeComparator());
		}
		return null;
	}
	
	private Path reconstructPath(Node node, Graph graph){
		Path path = new Path(graph);
		while(!(node == null)){
			path.prepend(node);
			node = node.getParent();
		}
		this.path = path;
		path.paintEdges();
		return path;
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