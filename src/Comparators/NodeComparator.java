package Comparators;

import java.util.Comparator;

import Utilities.Distance;
import Graph.Node;

public class NodeComparator implements Comparator<Node> {

	
	private Node source;
	private double step;
	
	public NodeComparator(Node n, double step){
		this.source = n;
	}
	
    @Override
    public int compare(Node n1, Node n2) {
		float f = n1.getF();
		float of = n2.getF();
		
		if (f < of) {
			return -1;
		} else if (f > of) {
			return 1;
		} else {
			int n = compareImp(n1, n2);
			return n;
		}
    }
    
    private int compareDist(Node n1, Node n2){
    	Distance dist = new Distance(step);
    	double d1 = dist.distanceToTarget(source, n1);
    	double d2 = dist.distanceToTarget(source, n2);
    	
    	if (d1 < d2) {
			return -1;
		} else if (d1 > d2) {
			return 1;
		} else {
			return 0;
		}
    }
    
    private int compareImp(Node n1, Node n2){
    	int i1 = n1.getImportance();
    	int i2 = n2.getImportance();
    	
    	if (i1 < i2) {
			return -1;
		} else if (i1 > i2) {
			return 1;
		} else {
			return compareDist(n1,n2);
		}
    }

}