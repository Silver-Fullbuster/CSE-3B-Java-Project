import java.util.Scanner;

class Player{

	String player1,player2;

	Player(){
		this.player1=player1;
		this.player2=player2;
	}
	public void SetWhite(String player1){		
		this.player1=player1;
	}
	public void SetBlack(String player2){
		this.player2=player2;
	}
} 

class Code{
	public void command(){
		System.out.print(
"\t\t\t\t   _::_          \n" +
"\t\t\t\t _/____\\_	  	  \n" +
"\t\t\t\t \\      /        \n" +
"\t\t\t\t  \\____/         \n" +
"\t\t\t\t  (____)         \n" +
"\t\t\t\t   |  |   		  \n" +
"\t\t\t\t   |__|   		  \n" +
"\t\t\t\t  /    \\         \n" +
"\t\t\t\t (______) 		  \n" +
"\t\t\t\t(________)       \n" +
"\t\t\t\t/________\\       \n");
	
		System.out.println("\"If the king does not lead, how can he expect his subordinates to follow?\"");

	}
}

class Chess {

	public enum Pieces {
		WHITE_KING,
                WHITE_PAWN,
                WHITE_QUEEN, 
                WHITE_ROOK, 
                WHITE_BISHOP, 
                WHITE_KNIGHT, 
                EMPTY,
                BLACK_KING,
                BLACK_PAWN,            
                BLACK_QUEEN, 
                BLACK_ROOK, 
                BLACK_BISHOP, 
                BLACK_KNIGHT, 
	}

	public void printBoard(Pieces[][] chessboard) {
		char letter = 'a';
		System.out.print("");
		for (int l = 0; l < 8; l++) {                                                   //Printing letters
			System.out.printf("\u001B[31m"+"%4s"+"\u001B[0m", letter);
			letter++;
		}
                System.out.println("");                                                       //To fill Pieces from next line
                System.out.println("   -------------------------------");		
		for (int i = 0; i < 8; i++) {
			System.out.print("\u001B[31m"+ (8-i) + "\u001B[0m" + "| ");
			
			for (int j = 0; j < 8; j++) {		
				
				switch (chessboard[i][j]) {
				
				case BLACK_PAWN:
					System.out.print("\u265F | ");
					break;
				case BLACK_ROOK:
					System.out.print("\u265C | ");
					break;
				case BLACK_KNIGHT:
					System.out.print("\u265E | ");
					break;
				case BLACK_BISHOP:
					System.out.print("\u265D | ");
					break;
				case BLACK_QUEEN:
					System.out.print("\u265B | ");
					break;
				case BLACK_KING:
					System.out.print("\u265A | ");
					break;
				case WHITE_PAWN:
					System.out.print("\u2659 | ");
					break;
				case WHITE_ROOK:
					System.out.print("\u2656 | ");
					break;
				case WHITE_KNIGHT:
					System.out.print("\u2658 | ");
					break;
				case WHITE_BISHOP:
					System.out.print("\u2657 | ");
					break;
				case WHITE_QUEEN:
					System.out.print("\u2655 | ");
					break;
				case WHITE_KING:
					System.out.print("\u2654 | ");
					break;
				case EMPTY:
					System.out.print("  | ");
					break;
				}
			}
			System.out.println("");
                        System.out.println("   -------------------------------");
		}
                letter='a';
                System.out.print("");
		for (int l = 0; l < 8; l++) {                                                   //Printing letters
			System.out.printf("\u001B[31m"+"%4s"+"\u001B[0m", letter);
			letter++;
		}
		System.out.println(" ");
	}

	public void fillBoard(Pieces[][] chessboard) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
                            
				if (i == 0) {                                                   //Bottom most row
					switch (j) {
					case 0:
					case 7:
						chessboard[i][j] = Pieces.BLACK_ROOK;           //Places 2 Rooks at bottom corners
						break;
					case 1:
					case 6:
						chessboard[i][j] = Pieces.BLACK_KNIGHT;         //Places 2 Black Knights
						break;
					case 2:
					case 5:
						chessboard[i][j] = Pieces.BLACK_BISHOP;         //Places 2 Black Bishops
						break;
					case 3:
						chessboard[i][j] = Pieces.BLACK_QUEEN;          //Places 2 Black Queens
						break;
					case 4:
						chessboard[i][j] = Pieces.BLACK_KING;           //Places 2 Black Kings
						break;
					}
				} 
                                else if (i == 1) {                                              //Pawn Row
					chessboard[i][j] = Pieces.BLACK_PAWN;                   //Fills Black Pawns
				}

				else if (i > 1 && i < 6) {                                      //Initial Playing field (Empty)
					chessboard[i][j] = Pieces.EMPTY;                        //Allocates empty spaces
				}

				else if (i == 6) {                                              //Outer Perimeter of White Territory
					chessboard[i][j] = Pieces.WHITE_PAWN;                   //Fills it with White Pawns
				}

				else if (i == 7) {                                              //Top-most Row
					switch (j) {
						case 0:
						case 7:
							chessboard[i][j] = Pieces.WHITE_ROOK;   
							break;
						case 1:
						case 6:
							chessboard[i][j] = Pieces.WHITE_KNIGHT; 
							break;
						case 2:
						case 5:
							chessboard[i][j] = Pieces.WHITE_BISHOP;
							break;
						case 3:
							chessboard[i][j] = Pieces.WHITE_QUEEN;
							break;
						case 4:
							chessboard[i][j] = Pieces.WHITE_KING;
							break;
					}
				}
			}

		}

	}

	//Printing Error messages instead of Exception Handling because it's all under an infinite While Loop

	public void move(Pieces[][] chessboard, String move) {
		String[] components = move.split(" ");					//Breaks Standard notation into each character
		
		if (components.length > 3){								//For incorrect notation
			System.err.println("\nPlease provide valid move!\n");
		}
		else if (components[0].length() != 2 || components[2].length() != 2){     //For incomplete notation
			System.err.println("\nPlease provide valid move!\n");
		}
		else if (components[0].charAt(0) < 'a' || components[0].charAt(0) > 'h' || components[0].charAt(1) < '1' || components[0].charAt(1) > '8' ){
			System.err.println("\nPlease provide valid move!\n");
		}
		else if (components[2].charAt(0) < 'a' || components[2].charAt(0) > 'h' || components[2].charAt(1) < '1' || components[2].charAt(1) > '8' ){
			System.err.println("\nPlease provide valid move!\n");
		}
                else{
                // make the move: replace original position with Pieces.
                    int col = components[0].charAt(0) - 97;
                    int row = Math.abs(components[0].charAt(1) - 49-7);
		
                    //and place the piece into the new position
                    int nCol = components[2].charAt(0) - 97;
                    int nRow = Math.abs(components[2].charAt(1) - 49-7);
		
                    System.out.println(col+ " " + row + " " + nCol + " " + nRow);
                    if (isValid(chessboard, row, col, nRow, nCol)){						//Valid moves
                      	chessboard[nRow][+nCol] = chessboard[row][+col];
                        chessboard[row][+col] = Pieces.EMPTY;
                    }
                    else
						System.err.println("Illegal Move");  //AHA

                }
	}

	
	public static Boolean isValid(Pieces[][] chessboard, int row, int col, int nRow, int nCol) {

		switch (chessboard[row][col]) {
			
			case BLACK_PAWN:
				if (chessboard[nRow][nCol] != Pieces.EMPTY && (nCol == col + 1 || nCol == col - 1) ){
					return true;
				}
				else if ( chessboard[nRow][nCol] != Pieces.EMPTY ){
					return false;
				}
				else if ( row + 1 == nRow || (row == 1 && row + 2 == nRow)){
					return true;
				}
				break;

			case WHITE_PAWN:
				if (chessboard[nRow][nCol] != Pieces.EMPTY && (nCol == col + 1 || nCol == col - 1) ){
					return true;
				}
				else if ( chessboard[nRow][nCol] != Pieces.EMPTY ){
					return false;
				}
				else if ( row - 1 == nRow || (row == 6 && row - 2 == nRow)){
					return true;
				}
				break;

			case BLACK_ROOK:
				if ((chessboard[nRow][nCol] == Pieces.WHITE_ROOK ||
					 chessboard[nRow][nCol] == Pieces.WHITE_KNIGHT ||
					 chessboard[nRow][nCol] == Pieces.WHITE_BISHOP ||
					 chessboard[nRow][nCol] == Pieces.WHITE_QUEEN ||
					 chessboard[nRow][nCol] == Pieces.WHITE_KING || 
               	                  chessboard[nRow][nCol] == Pieces.EMPTY )
					 && (nCol == col || nRow == row) ){
					return true;
					}
				break;

			case WHITE_ROOK:
				if ((chessboard[nRow][nCol] == Pieces.BLACK_ROOK ||
					 chessboard[nRow][nCol] == Pieces.BLACK_KNIGHT ||
					 chessboard[nRow][nCol] == Pieces.BLACK_BISHOP ||
					 chessboard[nRow][nCol] == Pieces.BLACK_QUEEN ||
					 chessboard[nRow][nCol] == Pieces.BLACK_KING || 
    	             chessboard[nRow][nCol] == Pieces.EMPTY) // ||
				//	 chessboard[nRow][nCol] == Pieces.BLACK_PAWN)
					 && (nCol == col || nRow == row) ){
						return true;
				}
				break;

			case BLACK_KNIGHT:
			case WHITE_KNIGHT:
				break;

			case BLACK_BISHOP:
			case WHITE_BISHOP:
				break;

			case BLACK_QUEEN:
				if ((chessboard[nRow][nCol] == Pieces.WHITE_ROOK ||
				 chessboard[nRow][nCol] == Pieces.WHITE_KNIGHT ||
				 chessboard[nRow][nCol] == Pieces.WHITE_BISHOP ||
				 chessboard[nRow][nCol] == Pieces.WHITE_QUEEN ||
				 chessboard[nRow][nCol] == Pieces.WHITE_KING || 
	                         chessboard[nRow][nCol] == Pieces.EMPTY)
				 && (nCol == col || nRow == row || nCol == col-1 || 
	                             nRow == row+1 || nRow == row || nRow == row-1)){
					return true;
				}
				break;

			case WHITE_QUEEN:
				break;

			case BLACK_KING:
				if ((chessboard[nRow][nCol] == Pieces.WHITE_ROOK ||
				 chessboard[nRow][nCol] == Pieces.WHITE_KNIGHT ||
				 chessboard[nRow][nCol] == Pieces.WHITE_BISHOP ||
				 chessboard[nRow][nCol] == Pieces.WHITE_QUEEN ||
				 chessboard[nRow][nCol] == Pieces.WHITE_KING || 
   	                     chessboard[nRow][nCol] == Pieces.EMPTY)
				 && (( nCol == col+1 || nCol == col || nCol == col-1 ) && ( nRow == row+1 || nRow == row || nRow == row-1 ))){
					return true;
				}
				break;

			case WHITE_KING:
				if ((chessboard[nRow][nCol] == Pieces.BLACK_ROOK ||
				 chessboard[nRow][nCol] == Pieces.BLACK_KNIGHT ||
				 chessboard[nRow][nCol] == Pieces.BLACK_BISHOP ||
				 chessboard[nRow][nCol] == Pieces.BLACK_QUEEN ||
				 chessboard[nRow][nCol] == Pieces.BLACK_KING || 
                                 chessboard[nRow][nCol] == Pieces.EMPTY)
				 && (( nCol == col+1 || 
                                       nCol == col || 
                                       nCol == col-1 ) 
                                                       && 
                                                         ( nRow == row+1 || 
                                                           nRow == row || 
                                                           nRow == row-1 ))){
                                                                return true;
                                                           }
				break;	

			case EMPTY:
				return false;		
		}

        return false;
	
	}            
	
}

public class God{	

	public static void main(String[] args) {

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
		player.SetWhite(sc.next());
		System.out.println("Enter player 2 name (black): ");
		player.SetBlack(sc.next());

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
	
	}

}
