

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	
	int []nodeArray;
	private int hCost, gCost,fCost;
	private int outOfPlaceHeuristic=0;
	private int manhattanHeuristic=0;
	private int []GOAL=new int []{1, 2, 3, 8, -1,4,7,6,5};
	private Node parent;
	
	public Node(int[] newBoard) {
		nodeArray=newBoard;
		hCost=0;
		gCost=0;
		fCost=0;
		parent=null;
	}
	
	public void setManhattanHeuristic() {

		for(int index1=1; index1<=9; index1++){
			int val1=GOAL[index1-1];
			if(val1!=-1){
				int x1=index1/3;
				int y1=(index1%3)-1;
				if(index1%3==0){
					x1--;
					y1=2;
				}
				for(int index2=1; index2<=9; index2++){
					if(val1==nodeArray[index2-1]){
						int x2=index2/3;
						int y2=(index2%3)-1;
						if(index2%3==0){
							x2--;
							y2=2;
						}
							manhattanHeuristic+=Math.abs(x1-x2)+Math.abs(y1-y2);
							//System.out.println(val1+": "+manhattanHeuristic);

						
							break;
					}
				}
			}
			}
		
		hCost=manhattanHeuristic;
		fCost=gCost+hCost;
	}


	

	public void setOutOfPlaceHeuristic() {
		for(int i=0; i<9; i++){
			if(GOAL[i]!=nodeArray[i] && nodeArray[i]!=-1){
				outOfPlaceHeuristic++;
			}	
		}
		hCost=outOfPlaceHeuristic;
	}
	public void setgValue(int gVal){
		gCost=gVal;
		fCost=gCost+hCost;
	}
	public int getgValue(){
		return gCost;
	}
	public int getfCost(){
		return fCost;
	}
	
	public void setParent(Node node){
		parent=node;
	}
	
	public boolean nodeAndGoalEqual() {
		if(Arrays.equals(nodeArray, GOAL)){
			return true;
		}
		return false;
	}
	public ArrayList<Node> generateNeighbors() {
		ArrayList<Node> neighbors=new ArrayList<Node>();
		//get blank space
		int blankIndex=getBlankSpace();
		//slide down
		if(blankIndex!=0 && blankIndex!=1 && blankIndex!=2){
			slideAndStore(blankIndex-3, blankIndex, neighbors);
		}
		//slide up
		if(blankIndex!=6 && blankIndex!=7 && blankIndex!=8){
			slideAndStore(blankIndex+3, blankIndex, neighbors);
		}
		//slide left
		if(blankIndex!=2 && blankIndex!=5 && blankIndex!=8){
			slideAndStore(blankIndex+1, blankIndex, neighbors);
		}
		
		//slide right
		if(blankIndex!=0 && blankIndex!=3 && blankIndex!=6){
			slideAndStore(blankIndex-1, blankIndex, neighbors);
		}
	
		return neighbors;
	}
	private int getBlankSpace() {
		for(int i=0; i<9; i++){
			if(nodeArray[i]==-1){
				return i;
			}
		}
		return 0;
	}
	private void slideAndStore(int i, int j, ArrayList<Node> neighbors) {
		int [] newBoard=copyBoard(nodeArray);
		int temp=newBoard[i];
		newBoard[i]=nodeArray[j];
		newBoard[j]=temp;
		neighbors.add(new Node(newBoard));
	}
	private int[] copyBoard(int[] nodeArray2) {
		int []newB=new int[9];
		for(int i=0; i<9; i++){
			newB[i]=nodeArray[i];
		}
		return newB;
	}


	public void updatefValue() {
		fCost=gCost+hCost;
	}

	public int gethCost() {
		// TODO Auto-generated method stub
		return hCost;
	}

	public void printNode() {
		int index=-1;
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				index++;
				System.out.print(nodeArray[index]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public Node getParent() {
		return parent;
	}

	public int[] getArray() {
		return nodeArray;
	}
	
		
	
}
