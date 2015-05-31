package Utilities;

import java.util.ArrayList;

import Graph.Node;

public class Angle {

	private double angle(Node anchor, Node target){
		double deltaY = target.getY() - anchor.getY();
		double deltaX = anchor.getX() - target.getX();
		
		return Math.atan2(deltaY,deltaX) * (180.0 / Math.PI);
	}
	
	public void calculateAngles(Node anchor, ArrayList<Node> nodes){
		for(Node n : nodes){
			if(!n.getName().equals(anchor.getName())){
				n.setA(this.angle(anchor,n));
			}
		}
	}
}
