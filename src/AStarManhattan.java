

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarManhattan {
	PriorityQueue <Node>openList; //This sorts the nodes in terms of the fcost
	ArrayList <Node> closedList; //stores all expanded nodes
	Comparator<Node> comparator;
	int numOfMoves;
	public AStarManhattan(Node startNode){
		numOfMoves=0;
		comparator=new IntegerComparator();
		openList=new PriorityQueue<Node> (11, comparator);
		closedList=new ArrayList<Node>();
		startNode.setManhattanHeuristic();
		startNode.setgValue(0);
		search(startNode);
	}
	private void search(Node startNode) {
		openList.add(startNode);
		while(true){
			numOfMoves++;
			Node currNode=openList.remove();
			if(currNode.nodeAndGoalEqual()){
				printPuzzleSteps(currNode);
				break;
			}
			ArrayList <Node>neighbors=currNode.generateNeighbors();
		
			closedList.add(currNode);
			for(Node neighbor: neighbors){
				neighbor.setManhattanHeuristic();
				neighbor.setgValue(currNode.getgValue()+1);
				neighbor.setParent(currNode);
				
				//case 1
				if(closedList.contains(neighbor)){
					Node closedListNeighbor=closedList.get(closedList.indexOf(neighbor));

					if(neighbor.getgValue()<closedListNeighbor.getgValue()){
					closedListNeighbor.setgValue(neighbor.getgValue());
					openList.offer(closedListNeighbor);
					closedList.remove(closedList.indexOf(closedListNeighbor));
					}
				}
				else{
					//case 3: add neighbor to the open list and set its g value
					openList.offer(neighbor);
				}
			}
		
		}
	
	}
	private void printPuzzleSteps(Node currNode) {
		int count=0;
		currNode.printNode();
		while(currNode.getParent()!=null){
			count++;
			currNode=currNode.getParent();
			currNode.printNode();
		}		
		System.out.println("Number of steps for A star Manhattan Heuristic:" + count);
		System.out.println("Number of nodes expanded: " + numOfMoves);

	}
	
}
