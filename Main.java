import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		HighScore scores = new HighScore();
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n" +
					"1. Chess\n" +
					"2. Hangman\n" +
					"3. Tic Tac Toe\n" +
					"4. High Scores\n" +
					"5. Exit\n" +
					"\n" +
					"Enter choice: ");
			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
				scanner.nextLine();
				choice = 50;
			}
			switch (choice) {
				case 1:
					God.main();
					break;
				case 2:
					Hangman.launch();
					break;
				case 3:
					TicTacToe.launch();
					break;
				case 4:
					scores.displayScoreList();
				case 5:
					break;
				default:
					System.out.println("Unknown Choice! Please try again!");
			}
		} while (choice != 5);
	}
}