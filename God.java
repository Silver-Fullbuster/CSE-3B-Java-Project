import java.util.Scanner;


public class God{	

	public static void main(HighScore scores) {

		Code Geass=new Code();
		Geass.command();

		Scanner sc = new Scanner(System.in);
		Chess.Pieces[][] chessboard = new Chess.Pieces[8][8]; // creates chessboard

		String move = "";
                Chess chess = new Chess();
		
		chess.fillBoard(chessboard);         // fills chessboard with pieces

		//Player Entries
		Player player=new Player();
		System.out.println("\nEnter player 1 name (white): ");
		String p1=sc.next();
		player.SetWhite(p1);
                System.out.println("ELO rating of "+p1+" = 1200");
		System.out.println("Enter player 2 name (black): ");
		String p2=sc.next();
		player.SetBlack(p2);
                System.out.println("ELO rating of "+p2+" = 1200");

		final long startTime = System.currentTimeMillis();  //Timer Starts


		System.out.println("\nInput the moves in Standard Algebraic Notation (example: a2 to a3 for leftmost white pawn)”\n");

		//condition to keep playing		
		while(true){        
			chess.printBoard(chessboard);
			move = sc.nextLine();
			
			if (move.equals("exit")){
				break;
			}
			
			chess.move(chessboard, move);						
		
        }
		
		final long endTime = System.currentTimeMillis();   //Timer Ends
		System.out.println("Time taken to complete: " + (endTime - startTime)/1000+ "seconds");

		String winner=p1;
                int elo=0; //write formula
	//	ChessScore score=new ChessScore(p1,(endTime-startTime)/1000);
                scores.addScore(new ChessScore(p1,(float)(endTime-startTime)/1000,elo)); //pass updated elo
	
	}

}