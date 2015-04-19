package AStar;

import java.util.ArrayList;
import java.util.Collections;

import Graph.Edge;
import Graph.Graph;
import Graph.Node;
import Utilities.Cost;
import Utilities.Distance;
import Utilities.NodeComparator;
import Utilities.Pair;
import Utilities.Path;

public class AStarPathFinder{
	private ArrayList<Node> closed = new ArrayList<Node>();
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
		
		clear();
		
		System.out.println("STARTING NODE: " + start.getName());
		System.out.println("TARGET NODE: " + target.getName());
		
		closed.clear();
		open.clear();
		open.add(start);
		
		while(open.size() != 0){
			Node current = open.get(0);
			
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
				
				float neighborCost = neighborCost(neighbor, edge, current);
				System.out.println("G() COST OF " + neighbor.getName() + " : " + neighborCost +  " vs. " + neighbor.getG());
				
				if(!open.contains(neighbor)){
					open.add(neighbor);
					System.out.println("ADDED " + neighbor.getName() + " TO OPEN LIST");
					neighborIsBetter = true;
				} else if(neighborCost < neighbor.getG()){
					neighborIsBetter = true;
					System.out.println("WILL UPDATE " + neighbor.getName());
				} else {
					neighborIsBetter = false;
					System.out.println("WILL NOT UPDATE " + neighbor.getName());
				}
				if(neighborIsBetter){
					neighbor.setParent(current);
					neighbor.setG(neighborCost);
					//float heuristicCost = heuristicCost(neighbor, target);
					//neighbor.setH(heuristicCost);
					//System.out.println("THE HEURISTIC OF " + neighbor.getName() + " IS " + heuristicCost);
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
	
	private float neighborCost(Node neighbor, Cost edge, Node current){
		float neighborCost = neighbor.getTimeCost() + edge.getCost() + current.getG();
		return neighborCost;
	}
	
	private float heuristicCost(Node current, Node target){
		Distance distance = new Distance();
		return distance.heuristicCost(current, target);
	}
	
	private void clear(){
		for(Node n : graph.getNodes()){
			n.setPaintNode(false);
			n.setParent(null);
		}
		for(Edge e : graph.getEdges()){
			e.setPaint(false);
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