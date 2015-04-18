package Utilities;

import Graph.Node;

public class Distance {
	
	private final double STEP = 1.4;
	
	public float heuristicCost(Node current, Node target){
		int R = 6371000;
		double phi1 = toRadian(target.getLatitude());
		double phi2 = toRadian(current.getLatitude());
		double deltaPhi = toRadian((current.getLatitude() - target.getLatitude()));
		double deltaLambda = toRadian((current.getLongitude() - target.getLongitude()));
		
		double a = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) +  Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = R * c;
		
		return (float) ((d/STEP)/60.0);
	}
	
	public static double toRadian(double value){
		return value * (Math.PI / 180.0);
	}
}
