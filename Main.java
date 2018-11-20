import games.tictactoe.TicTacToe;
import games.chess.God;
import games.hangman.Hangman;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. Chess\n" +
				"2. Hangman\n" +
				"3. Tic Tac Toe\n" +
				"4. Exit\n" +
				"\n"
				"Enter choice: ");
		int choice = scanner.nextInt();
		switch(choice) {
			case 1:
				God.main();
				break;
			case 2:
				Hangman.launch();
				break;
			case 3:
				TicTacToe.launch();
			case 4:
				break;
			default:
				System.out.println("Unknown Choice! Please try again!\n");
		}
	}
}