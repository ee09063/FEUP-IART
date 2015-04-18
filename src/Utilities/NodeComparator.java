package Utilities;

import java.util.Comparator;

import Graph.Node;

public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node n1, Node n2) {
		float f = n1.getFValue();
		float of = n2.getFValue();
		
		if (f < of) {
			return -1;
		} else if (f > of) {
			return 1;
		} else {
			return 0;
		}
    }

}