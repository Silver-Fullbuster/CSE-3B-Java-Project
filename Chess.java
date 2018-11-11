package chess;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

public class Chess {
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
	//Printing a simple, boring, non-fancy Chessboard
        
	public static void printBoard(Pieces[][] chessboard) {
		char letter = 'a';
		System.out.print(" ");
		for (int l = 0; l < 8; l++) {                                                   //Printing letters
			System.out.printf("%2s", letter);
			letter++;
		}
		System.out.println("\r");                                                       //To fill Pieces from next line
		
		for (int i = 0; i < 8; i++) {
			System.out.print(8-i + " ");
			
			for (int j = 0; j < 8; j++) {		
				
				switch (chessboard[i][j]) {
				
				case BLACK_PAWN:
					System.out.print("\u265F ");
					break;
				case BLACK_ROOK:
					System.out.print("\u265C ");
					break;
				case BLACK_KNIGHT:
					System.out.print("\u265E ");
					break;
				case BLACK_BISHOP:
					System.out.print("\u265D ");
					break;
				case BLACK_QUEEN:
					System.out.print("\u265B ");
					break;
				case BLACK_KING:
					System.out.print("\u265A ");
					break;
				case WHITE_PAWN:
					System.out.print("\u2659 ");
					break;
				case WHITE_ROOK:
					System.out.print("\u2656 ");
					break;
				case WHITE_KNIGHT:
					System.out.print("\u2658 ");
					break;
				case WHITE_BISHOP:
					System.out.print("\u2657 ");
					break;
				case WHITE_QUEEN:
					System.out.print("\u2655 ");
					break;
				case WHITE_KING:
					System.out.print("\u2654 ");
					break;
				case EMPTY:
					System.out.print(" ");
					break;
				}
			}
			System.out.println("\r");
		}
	}

        public static void fillChessboard(Pieces[][] chessboard) {
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
}
