import java.util.Scanner;

public class God {

	public static void main(HighScore scores) {

		Code Geass = new Code();
		Geass.command();

		Scanner sc = new Scanner(System.in);
		Chess.Pieces[][] chessboard = new Chess.Pieces[8][8]; // creates chessboard

		String move;
		Chess chess = new Chess();

		chess.fillBoard(chessboard);        // fills chessboard with pieces

		//Player Entries
		Player player = new Player();
		System.out.println("\nEnter player 1 name (white): ");
		String p1 = sc.next();
		player.SetWhite(p1);
		System.out.println("ELO rating of " + p1 + " = 1200");
		System.out.println("Enter player 2 name (black): ");
		String p2 = sc.next();
		player.SetBlack(p2);
		System.out.println("ELO rating of " + p2 + " = 1200");

		final long startTime = System.currentTimeMillis();  //Timer Starts

		System.out.println("\nEnter 'exit' to forfeit in mid-game");

		System.out.println("\nInput the moves in Standard Algebraic Notation (example: a2 to a3 for leftmost white pawn)”\n");
/*
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
*/

/*
		try {
			chess.wait();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
*/
		//condition to keep playing
		sc.nextLine();
		while (true) {
			chess.printBoard(chessboard);
			move = sc.nextLine();

			if (move.equals("exit")) {
				break;
			}

			chess.move(chessboard, move);

		}
		float elo = (1200f + 32f * (0.5f));

		final long endTime = System.currentTimeMillis();   //Timer Ends
		System.out.println(p1 + " Wins!");
		System.out.println("Update ELO of player " + p1 + " : " + elo);
		System.out.println("Time taken to complete: " + (endTime - startTime) / 1000 + " second(s)");


		//	ChessScore score=new ChessScore(p1,(endTime-startTime)/1000);
		scores.addScore(new ChessScore(p1, (float) (endTime - startTime) / 1000, elo)); //pass updated elo

	}

}