

import java.util.Comparator;

public class IntegerComparator implements Comparator<Node> {

	@Override
	public int compare(Node x, Node y) {
		Integer intx=x.getfCost();
		Integer inty=y.getfCost();
	
		return intx.compareTo(inty);
		
	}

}
