import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		HighScore scores = new HighScore();
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. Chess\n" +
				"2. Hangman\n" +
				"3. Tic Tac Toe\n" +
				"4. Exit\n" +
				"\n" +
				"Enter choice: ");
		int choice = scanner.nextInt();
		switch(choice) {
			case 1:
				God.main(scores);
				break;
			case 2:
				Hangman.launch(scores);
				break;
			case 3:
				TicTacToe.launch(scores);
			case 4:
				break;
			default:
				System.out.println("Unknown Choice! Please try again!\n");
		}
	}
}