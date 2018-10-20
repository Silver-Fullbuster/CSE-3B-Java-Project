package games.tictactoe;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class TicTacToe { //TODO: Possibly implement an interface for scores
    private char OXChoice;
    private boolean firstTurn;
    private char board[][];
    private char winnerChar;
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void launch() {
        do {
            TicTacToe obj = new TicTacToe();
            //TODO: Decide design for startup of game
            System.out.println("What do you want to play as, O or X?\n" +
                    "Enter choice: ");
            obj.OXChoice = scanner.next().charAt(0); //TODO: Ensure O or X input
            System.out.println("Do you want to play first?");
            obj.firstTurn = scanner.next().equals("Yes"); //TODO: Multiple inputs
            obj.startGame();
            System.out.println("Another game? y/n"); //TODO: UX
        } while (scanner.nextLine().equals("n"));
    }

    private void startGame() {
        do {
            showBoard();
            if (firstTurn) getPlayerInput();
            else getComputerInput();

            if (gameEnded()) break;

            if (firstTurn) getComputerInput();
            else getPlayerInput();
        } while (!gameEnded());
        System.out.println("Game Ended");
        showBoard();
        if (OXChoice == winnerChar)
            System.out.println("Player won");
        else
            System.out.println("Computer won");
        //TODO: End Game Scoring
    }

    private boolean gameEnded() {
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

    private void getPlayerInput() {
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
    }

    private void getComputerInput() { //TODO: Better logic
        int index;
        do {
            index = ThreadLocalRandom.current().nextInt(0, 9);
        } while (board[index / 3][index % 3] != ' ');
        board[index / 3][index % 3] = OXChoice == 'O' ? 'X' : 'O';
    }

    private void showBoard() {
        //TODO: Proper design
        for (char i[] : board) {
            for (char j : i) {
                System.out.print(j + "|");
            }
            System.out.print("\b \n");
        }
    }

    private boolean boardFilled() {
        for (char i[] : board)
            for (char j : i)
                if (j == ' ')
                    return false;
        return true;
    }

    private TicTacToe() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }
}
