
package chess;

import java.util.Scanner;

public class God {                                                                  
    	// MAIN METHOD
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Chess.Pieces[][] chessboard = new Chess.Pieces[8][8]; // creates chessboard
		String move = "";
                Chess chess = new Chess();
                //       System.out.println("Enter side: (w/b)");
                //     String sidecolour=sc.next();
                //             selectSide(chessboard, sidecolour);
                //will rotate side before fillChessboard fills everything (or can add that functionality in fillChessboard)
                chess.printSquares();
                chess.fillBoard(chessboard);
                //	fillBoard(chessboard);
                // Introduce Functionality for entering difficulty: will take game repositories from championship matches and simulate them
		System.out.println("\nInput the moves in Standard Algebraic Notation (example: a2 to a3 for leftmost white pawn)”:\n");
                // Introduce Functionality to re-assign valid moves (for creative game modes)
                // Introduce hints functionality
		while(true){                            //condition to keep playing
			chess.printBoard(chessboard);
			move = sc.nextLine();
			
			if (move.equals("exit")){
				System.exit(1);
			}
			
			move(chessboard, move);
						
		}
		
	}
        
}
