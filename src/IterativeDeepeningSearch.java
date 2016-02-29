

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class IterativeDeepeningSearch {
	
	private PriorityQueue <Node>openList;
	private ArrayList<Node> closedList;
	Comparator<Node> comparator;
	int l, numOfMoves;
	public IterativeDeepeningSearch (Node startNode){
		numOfMoves=0;
		comparator=new IntegerComparator();
		openList=new PriorityQueue<Node> (11, comparator);
		closedList=new ArrayList<Node>();
		startNode.setManhattanHeuristic();
		openList.add(startNode);
		l=startNode.getfCost(); //The best cost is initially the start cost
		search(startNode);
	}
	private void search(Node startNode) {
		while(true){
			//System.out.println(openList.size());
			numOfMoves++;
			Node currNode=openList.remove();
			closedList.add(currNode);
			if(currNode.nodeAndGoalEqual()){
				printPuzzleSteps(currNode);
				break;
			}else{
				ArrayList <Node>neighbors=currNode.generateNeighbors();
				int minCost=9999;
				for(Node n:neighbors){
					if(!closedListContainsNeighbor(n)){
						n.setManhattanHeuristic();
						int cost=n.getfCost();
							if(cost<minCost) minCost=cost;
							n.setParent(currNode);
							openList.add(n);
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
		System.out.println("Number of steps for Iterative Deepening A*: " + count);
		System.out.println("Number of nodes expanded: " + numOfMoves);

	}
}
