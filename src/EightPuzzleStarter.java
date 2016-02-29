import java.util.Scanner;

public class EightPuzzleStarter {

	public static void main(String[] args) {
		int option;
		long startTime, endTime;
		int[] puzzleBoard=new int[9];
		int i=-1;
		for(String s: args){
			i++;
			puzzleBoard[i]=Integer.parseInt(s);
		}
		
		System.out.println("What algorithm do you want to implement? Type ");
		System.out.println("1 - A Star with out of place heuristic ");
		System.out.println("2 - A Star with Manhattan heuristic ");
		System.out.println("3 - Iterative Deepening A star ");
		System.out.println("4 - Depth First Branch And Bound ");
		System.out.print("Choose a number: ");
		Scanner sc=new Scanner(System.in);
		option =sc.nextInt();
		if(option!=1 && option!=2 && option!=3 && option!=4){
			System.out.println("Invalid number!!!");
			return;
		}
		if(option==1){
			startTime = System.nanoTime();
			System.out.println("Solution with out of place heuristic:");
			AStarOutOfPlace solver1= new AStarOutOfPlace(new Node(puzzleBoard));
			endTime = System.nanoTime();

			System.out.println("Took "+(endTime - startTime) + " ns"); 
		}
		if(option==2){
			startTime = System.nanoTime();
			System.out.println("Solution with Manhattan heuristic:");
			AStarManhattan solver2= new AStarManhattan(new Node(puzzleBoard));
			endTime = System.nanoTime();
			System.out.println("Took "+(endTime - startTime) + " ns"); 

		}
		if(option==3){
			startTime = System.nanoTime();
			System.out.println("Solution for Iterative Deepening Search using Manhattan Heuristic:");
			IterativeDeepeningSearch solver4= new IterativeDeepeningSearch(new Node(puzzleBoard));
			endTime = System.nanoTime();

			System.out.println("Took "+(endTime - startTime) + " ns"); 
		}
		if(option==4){
			startTime = System.nanoTime();
			System.out.println("Solution for Depth First Branch and bound using Manhattan Heuristic:");
			DepthFirstBranchAndBound solver3= new DepthFirstBranchAndBound(new Node(puzzleBoard));
			endTime = System.nanoTime();

			System.out.println("Took "+(endTime - startTime) + " ns"); 
		}
	
	}

}
