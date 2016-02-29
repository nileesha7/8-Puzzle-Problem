

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DepthFirstBranchAndBound {
	private PriorityQueue <Node>openList;
	private ArrayList<Node> closedList;
	Comparator<Node> comparator;
	int l, numOfMoves;
	public DepthFirstBranchAndBound(Node startNode){
		numOfMoves=0;
		comparator=new IntegerComparator();
		openList=new PriorityQueue<Node> (11, comparator);
		closedList=new ArrayList<Node>();
		startNode.setManhattanHeuristic();
		openList.add(startNode);
		l=9999;
		search(startNode);
	}
	
	//depth first branch and bound algorithm
	private void search(Node startNode) {
		int c=0;
		while(!openList.isEmpty()){
			c++;
			//System.out.println(c);
			numOfMoves++;
			Node currNode=openList.poll();
			
			//System.out.println("Current Node");
			//currNode.printNode();
			
			closedList.add(currNode);
			if(currNode.nodeAndGoalEqual()){
				l=Math.min(currNode.getfCost(), l);
				printPuzzleSteps(currNode);
				System.out.println("Solution is found");

				//break;
			}else{
				ArrayList <Node>neighbors=currNode.generateNeighbors();
			
				for(Node n:neighbors){
					if(!closedListContainsNeighbor( n)){
						n.setManhattanHeuristic();
						int cost=n.getfCost();
						if(cost<=l){
							//System.out.println("Neighbor Node");
							//n.printNode();
							//System.out.println("Neighbor Cost "+n.getfCost());
							n.setParent(currNode);
							openList.add(n);
						}
					}
				}	
			}
		}
	}
			
	private boolean closedListContainsNeighbor(Node n1) {
	
		int []a1=n1.getArray();
		for(Node n2:closedList){
			int []a2=n2.getArray();
			if(Arrays.equals(a1, a2)){
				return true;
			}
		}
		return false;
	}

	private void printPuzzleSteps(Node currNode) {
		int count=0;
		currNode.printNode();
		while(currNode.getParent()!=null){
			count++;
			currNode=currNode.getParent();
			currNode.printNode();
		}		
		System.out.println("Number of steps for Depth First Branch and Bound: " + count);
		System.out.println("Number of nodes expanded: " + numOfMoves);

	}
}
