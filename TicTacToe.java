import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class TicTacToe {
	private char OXChoice;
	private boolean firstTurn;
	private char board[][];
	/**
	 * @see TicTacToe#gameEnded()
	 **/
	private char winnerChar = ' ';
	private static Scanner scanner;
	private int totalTurns;

	private enum MULTIPLAYER {SERVER, CLIENT}

	static {
		scanner = new Scanner(System.in);
	}

	private TicTacToe() {
		board = new char[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = ' ';
	}

	public static void launch() {
		TicTacToeHighScore scores = new TicTacToeHighScore();

		int choice;
		do {
			System.out.println("\t\tTIC TAC TOE\n" +
					"\n" +
					"1. Play\n" +
					"2. High Scores\n" +
					"3. Exit\n" +
					"4. Host (Plays first)\n" +
					"5. Join (Plays second)\n" +
					"\n" +
					"Enter choice: ");
			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
				scanner.nextLine();
				choice = 0;
			}
			switch (choice) {
				case 1:
					play(scores);
					return;
				case 2:
					scores.displayScoreList();
					break;
				case 3:
					return;
				case 4:
					playMultiplayer(scores, MULTIPLAYER.SERVER);
					break;
				case 5:
					playMultiplayer(scores, MULTIPLAYER.CLIENT);
					break;
				default:
					System.out.println("Unknown Choice! Please try again!");
			}
		} while (true);
	}

	private static void playMultiplayer(TicTacToeHighScore scores, MULTIPLAYER mode) {
		TicTacToe game;
		boolean won;
		switch (mode) {
			case SERVER:
				Server server = Server.prep();
				if (server == null) return;
				game = new TicTacToe();
				game.OXChoice = 'O';
				game.firstTurn = true;
				try {
					final long startTime = System.currentTimeMillis();
					won = game.startGameMultiplayer(server);
					final long stopTime = System.currentTimeMillis();
					final long elapsedTime = (stopTime - startTime);
					System.out.println("\nGame finished in " + elapsedTime / (float) 1000 + " seconds\n");
					server.write(String.valueOf(elapsedTime));
					server.write(String.valueOf(game.totalTurns));
					String name;
					if (won) {
						System.out.println("Enter your name: ");
						name = scanner.next();
					} else name = server.read();
					scores.addScore(new TicTacToeScore(name, elapsedTime / (float) 1000, game.totalTurns));
				} catch (IOException e) {
					System.out.println("Network error: exiting game");
				} finally {
					server.close();
				}
				return;
			case CLIENT:
				System.out.println("Enter IP Address: ");
				String ip = scanner.next();
				scanner.nextLine();
				System.out.println("Enter port number: ");
				int port = scanner.nextInt();
				Client client = Client.connect(ip, port);
				if (client == null) return;
				game = new TicTacToe();
				game.OXChoice = 'O';
				game.firstTurn = false;
				try {
					won = game.startGameMultiplayer(client);
					final long elapsedTime = Long.parseLong(client.read());
					System.out.println("\nGame finished in " + elapsedTime / (float) 1000 + " seconds\n");
					game.totalTurns = Integer.parseInt(client.read());
					String name;
					if (won) {
						System.out.println("Enter your name: ");
						name = scanner.next();
						client.write(name);
					}
				} catch (IOException e) {
					System.out.println("Network error: exiting game");
				} finally {
					client.close();
				}
		}
	}

	private static void play(TicTacToeHighScore scores) {
		do {
			TicTacToe game = new TicTacToe();
			String tempInput;
			System.out.println("What do you want to play as, O or X?\n" +
					"Enter choice: ");
			for (boolean invalidInput = true; invalidInput; ) {
				game.OXChoice = Character.toUpperCase(scanner.next().charAt(0));
				if (game.OXChoice == 'O' || game.OXChoice == 'X')
					invalidInput = false;
				else
					System.out.println("Only O or X allowed for input!!\nEnter choice: ");
			}
			System.out.println("Do you want to play first?");
			tempInput = scanner.next().toUpperCase();
			game.firstTurn = tempInput.equals("YES") || tempInput.equals("Y");
			final long startTime = System.currentTimeMillis();
			boolean won = game.startGameWithComputer();
			final long stopTime = System.currentTimeMillis();
			final float elapsedTime = (float) (stopTime - startTime) / 1000;
			System.out.println("\nYou finished in " + elapsedTime + " seconds\n");
			if (won) {
				System.out.println("Enter your name: ");
				scores.addScore(new TicTacToeScore(scanner.next(), elapsedTime, game.totalTurns));
			}
			System.out.println("\nEnter 'y' to play again, any other key will exit!");
		} while (scanner.next().equals("y"));
	}

	private boolean startGameWithComputer() {
		do {
			if (firstTurn) getPlayerInput();
			else getComputerInput();

			if (gameEnded()) break;

			if (firstTurn) getComputerInput();
			else getPlayerInput();
		} while (!gameEnded());
		System.out.println("Game Ended");
		showBoard();
		if (winnerChar == ' ')
			System.out.println("It's a tie!");
		else if (winnerChar == OXChoice) {
			System.out.println("Player won");
			return true;
		} else {
			System.out.println("Computer won");
		}
		return false;
	}

	private boolean startGameMultiplayer(Network network) throws IOException {
		do {
			if (firstTurn) network.write(String.valueOf(getPlayerInput()));
			else getRemotePlayerInput(network);

			if (gameEnded()) break;

			if (firstTurn) getRemotePlayerInput(network);
			else network.write(String.valueOf(getPlayerInput()));
		} while (!gameEnded());
		System.out.println("Game Ended");
		showBoard();
		if (winnerChar == ' ')
			System.out.println("It's a tie!");
		else if (winnerChar == OXChoice) {
			System.out.println("You won");
			return true;
		} else {
			System.out.println("Remote player won");
		}
		return false;
	}

	private int getPlayerInput() {
		++totalTurns;
		showBoard();
		boolean inputIssue;
		int index;
		do {
			inputIssue = false;
			System.out.println("Enter position: ");
			index = scanner.nextInt() - 1;
			if (index < 0 || index > 8) {
				System.out.println("Unknown Choice");
				inputIssue = true;
			} else if (board[(index) / 3][index % 3] != ' ') {
				System.out.println("Already filled!!");
				inputIssue = true;
			}
		} while (inputIssue);
		board[index / 3][index % 3] = OXChoice;
		return index;
	}


	private boolean gameEnded() {
		winnerChar = ' ';   //No winner decided (TIE condition)
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				winnerChar = board[i][0];
				return true;
			}
			if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				winnerChar = board[0][i];
				return true;
			}
		}

		if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			winnerChar = board[0][0];
			return true;
		}

		if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			winnerChar = board[0][2];
			return true;
		}

		return boardFilled();
	}

	private void getRemotePlayerInput(Network network) throws IOException {
		int index;
		System.out.println("Remote player playing move..");
		index = Integer.parseInt(network.read());
		board[index / 3][index % 3] = 'X';
	}


	private void getComputerInput() {
		int index;
		do {
			index = ThreadLocalRandom.current().nextInt(1, 10);
		} while (board[(index - 1) / 3][(index - 1) % 3] != ' ');

		System.out.println("Computer playing move..");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 3; i++) {
			//Horizontal
			if (board[i][0] == OXChoice && board[i][1] == OXChoice && board[i][2] == ' ') {
				index = i * 3 + 3;
				break;
			}
			if (board[i][1] == OXChoice && board[i][2] == OXChoice && board[i][0] == ' ') {
				index = i * 3 + 1;
				break;
			}
			if (board[i][0] == OXChoice && board[i][2] == OXChoice && board[i][1] == ' ') {
				index = i * 3 + 2;
				break;
			}

			// Vertical
			if (board[0][i] == OXChoice && board[1][i] == OXChoice && board[2][i] == ' ') {
				index = 7 + i;
				break;
			}
			if (board[1][i] == OXChoice && board[2][i] == OXChoice && board[0][i] == ' ') {
				index = 1 + i;
				break;
			}
			if (board[0][i] == OXChoice && board[2][i] == OXChoice && board[1][i] == ' ') {
				index = 4 + i;
				break;
			}
		}

		//Diagonal
		if (board[0][0] == OXChoice && board[1][1] == OXChoice && board[2][2] == ' ')
			index = 9;
		else if (board[0][0] == OXChoice && board[2][2] == OXChoice && board[1][1] == ' ')
			index = 5;
		else if (board[1][1] == OXChoice && board[2][2] == OXChoice && board[0][0] == ' ')
			index = 1;


			//Reverse Diagonal
		else if (board[0][2] == OXChoice && board[1][1] == OXChoice && board[2][0] == ' ')
			index = 7;
		else if (board[0][2] == OXChoice && board[2][0] == OXChoice && board[1][1] == ' ')
			index = 5;
		else if (board[1][1] == OXChoice && board[2][0] == OXChoice && board[0][2] == ' ')
			index = 3;

		--index;
		board[index / 3][index % 3] = OXChoice == 'O' ? 'X' : 'O';
	}

	private void showBoard() {
		int count = 1;
		System.out.print(" \u2554\u2550\u2550\u2550\u2566\u2550\u2550\u2550\u2566\u2550\u2550\u2550\u2557 ");
		for (char i[] : board) {
			System.out.println("\n \u2551   \u2551   \u2551   \u2551"); //FIRST LINE OF A ROW

			System.out.print(" \u2551");
			for (char j : i)
				System.out.print(" " + j + " \u2551");

			System.out.println("\n \u2551  " + count++ + "\u2551  " + count++ + "\u2551  " + count++ + "\u2551"); //THIRD LINE OF A ROW
			System.out.print(" \u2560\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2563 ");
		}
		System.out.println("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b \u255A\u2550\u2550\u2550\u2569\u2550\u2550\u2550\u2569\u2550\u2550\u2550\u255D");
	}

	private boolean boardFilled() {
		for (char i[] : board)
			for (char j : i)
				if (j == ' ')
					return false;
		return true;
	}


}
