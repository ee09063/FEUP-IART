package Utilities;

import java.util.Comparator;

import Graph.Node;

public class NodeComparator implements Comparator<Node> {

	
	private Node source;
	
	public NodeComparator(Node n){
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
    	double d1 = Distance.distanceToTarget(source, n1);
    	double d2 = Distance.distanceToTarget(source, n2);
    	
    	/*System.out.println(source.getName());
    	System.out.println(n1.getName() + " " + d1);
    	System.out.println(n2.getName() + " " + d2 + "\n");*/
    	
    	if (d1 < d2) {
			return -1;
		} else if (d1 > d2) {
			return 1;
		} else {
			return 0;
		}
    }
    
    private int compareImp(Node n1, Node n2){
    	int d1 = -n1.getImportance();
    	int d2 = -n2.getImportance();
    	
    	if (d1 < d2) {
			return -1;
		} else if (d1 > d2) {
			return 1;
		} else {
			return compareDist(n1,n2);
		}
    }

}