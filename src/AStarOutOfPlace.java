
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
public class AStarOutOfPlace{
	
	PriorityQueue <Node>openList; //This sorts the nodes in terms of the fcost
	ArrayList <Node> closedList; //stores all expanded nodes
	Comparator<Node> comparator;
	int numOfMoves;
	AStarOutOfPlace(Node startNode){
		numOfMoves=0;
		comparator=new IntegerComparator();
		openList=new PriorityQueue<Node> (11, comparator);
		closedList=new ArrayList<Node>();
		startNode.setOutOfPlaceHeuristic();
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
				neighbor.setOutOfPlaceHeuristic();
				neighbor.setgValue(currNode.getgValue()+1);
				neighbor.setParent(currNode);
				
				//case 1
				if(listContainsNeighbor(closedList,neighbor)){
	
					Node closedListNeighbor=closedList.get(closedList.indexOf(neighbor));
					if(neighbor.getgValue()<closedListNeighbor.getgValue()){
					closedListNeighbor.setgValue(neighbor.getgValue());
					openList.offer(closedListNeighbor);
					closedList.remove(closedList.indexOf(closedListNeighbor));
					}
				}
				//case 2
				else if(openList.contains(neighbor)){
					int openListListGVal=getopenListGValue(neighbor);
					if(neighbor.getgValue()<openListListGVal){
						
					}
				}else{
				
					//case 3: add neighbor to the open list and set its g value
					openList.offer(neighbor);
				}
			}
		
		}
	
	}
	private int getopenListGValue(Node neighbor) {
		// TODO Auto-generated method stub
		return 0;
	}

	private boolean listContainsNeighbor(ArrayList<Node> closedList2, Node neighbor) {
		// TODO Auto-generated method stub
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
		System.out.println("Number of steps for A star out of place:" + count);
		System.out.println("Number of nodes expanded: " + numOfMoves);
	}
	
}
