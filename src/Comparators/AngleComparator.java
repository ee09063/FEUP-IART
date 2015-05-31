package Comparators;

import java.util.Comparator;

import Graph.Node;

public class AngleComparator implements Comparator<Node> {

    @Override
    public int compare(Node n1, Node n2) {
		double f = n1.getA();
		double of = n2.getA();;
		
		if(f == 0) return -1;
		else if(of == 0) return 1;
		
		if (f < of) {
			return -1;
		} else if (f > of) {
			return 1;
		} else {
			return 0;
		}
    }

}