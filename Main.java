import games.tictactoe.TicTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Tic Tac Toe\n" +
                "2. Exit");
        if(scanner.nextInt() == 1)
            TicTacToe.launch();

    }
}