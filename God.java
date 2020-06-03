import java.util.Scanner;


public class God {

	public static void launch() {
		ChessHighScore scores = new ChessHighScore();
		Scanner sc = new Scanner(System.in);

		int choice;
		do {
			System.out.println("\t\tCHESS\n" +
					"\n" +
					"1. Play\n" +
					"2. High Scores\n" +
					"3. Exit\n" +
					"4. Host (white)\n" +
					"5. Client (black)\n" + 
					"\n" +
					"Enter choice: ");
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				sc.nextLine();
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
				case 4: playMultiplayer(scores, MULTIPLAYER.SERVER);
					break;
				case 5: playMultiplayer(scores, MULTIPLAYER.CLIENT);
				default:
					System.out.println("Unknown Choice! Please try again!");
			}
		} while (choice != 5);

	}

	public static void playMultiplayer(ChessHighScore scores, MULTIPLAYER mode){
		Chess game;
		Player player = new Player();
		String p1, p2;
		switch(mode){
			case SERVER:
				Server server = Server.prep();
				if (server == null)
					return;
				
				// INITIAL STUPID MESSAGE
				Code Geass = new Code();
				Geass.command();
				Scanner sc = new Scanner(System.in);

				Chess.Pieces[][] chessboard = new Chess.Pieces[8][8]; // creates chessboard

				// FILL THE CHESSBOARD
				Chess chess = new Chess();
				chess.fillBoard(chessboard);

				// SERVER = WHITE; ENTER PLAYER DETAILS
				System.out.println("\nEnter player 1 name (white): ");
				p1 = sc.next();
				player.SetWhite(p1);
				
				// INSTRUCTIONS
				System.out.println("\nEnter 'exit' to forfeit in mid-game");
				System.out.println("\nInput the moves in Standard Algebraic Notation (example: a2 to a3 for leftmost white pawn)\n");
				sc.nextLine();

				while (true) {
					chess.printBoard(chessboard);
					// PLAY WHITE'S MOVE
					String moveServerToClient = sc.nextLine();
					if (moveServerToClient.equals("exit")) {
						break;
					}
					chess.move(chessboard, moveServerToClient);
					// SEND WHITE'S MOVE TO BLACK
					try{
						server.send(moveServerToClient);
					} catch (IOException e) {
						System.out.println("Network error: exiting the game");
						return;
					}
					// RECEIVE BLACK'S MOVE
					try{
						String moveClientToServer = client.read();
					} catch (IOException e) {
						System.out.println("Network error: exiting the game");
						return;
					}
					if (moveClientToServer.equals("exit")) {
						break;
					}
					// PLAY BLACK'S MOVE
					chess.move(chessboard, moveClientToServer);
				}
				System.out.println(p2+" wins!");
				// UPDATE SCORES
				scores.addScore(new ChessScore(p2, (float)222, 1234));
				break;

			case CLIENT:
				// CONNECT TO SERVER
				System.out.println("Enter IP Address: ");
				String ip = sc.next();
				sc.nextLine();
				System.out.println("Enter port number: ");
				int port = sc.nextInt();
				Client client = Client.connect(ip, port);
				if (client == null) return;

				// INITIAL STUPID MESSAGE
				Code Geass = new Code();
				Geass.command();
				Scanner sc = new Scanner(System.in);

				Chess.Pieces[][] chessboard = new Chess.Pieces[8][8]; // creates chessboard

				// FILL THE CHESSBOARD
				Chess chess = new Chess();
				chess.fillBoard(chessboard);

				// CLIENT = BLACK; ENTER PLAYER DETAILS
				System.out.println("Enter player 2 name (black): ");
				p2 = sc.next();
				player.SetBlack(p2);

				// INSTRUCTIONS
				System.out.println("\nEnter 'exit' to forfeit in mid-game");
				System.out.println("\nInput the moves in Standard Algebraic Notation (example: a2 to a3 for leftmost white pawn)\n");
				sc.nextLine();

				while (true) {
					chess.printBoard(chessboard);
					// RECEIVE MOVE FROM WHITE
					try{
						String moveServerToClient = client.read();
					} catch (IOException e) {
						System.out.println("Network error: exiting the game");
						return;
					}
					if (moveServerToClient.equals("exit")) {
						break;
					}
					// PLAY WHITE'S MOVE
					chess.move(chessboard, moveServerToClient);

					// PLAY BLACK'S MOVE
					String moveClientToServer = sc.nextLine();
					if (moveClientToServer.equals("exit")) {
						break;
					}
					chess.move(chessboard, moveClientToServer);
					// SEND BLACK'S MOVE TO THE SERVER
					try{
						server.send(moveClientToServer);
					} catch (IOException e) {
						System.out.println("Network error: exiting the game");
						return;
					}		
				}
				System.out.println(p1+" wins!");
				scores.addScore(new ChessScore(p1, (float) 222, 1234));
				break;
		}
	}

	public static void play(ChessHighScore scores){
		Code Geass = new Code();
		Geass.command();
		Scanner sc = new Scanner(System.in);


		Chess.Pieces[][] chessboard = new Chess.Pieces[8][8]; // creates chessboard

		String move;
		Chess chess = new Chess();

		chess.fillBoard(chessboard);        // fills chessboard with pieces

		do {
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

			System.out.println("\nInput the moves in Standard Algebraic Notation (example: a2 to a3 for leftmost white pawn)\n");

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
			System.out.println("Updated ELO of player " + p1 + " : " + elo);
			System.out.println("Time taken to complete: " + (endTime - startTime) / 1000 + " second(s)");
			//ChessScore score=new ChessScore(p1,(endTime-startTime)/1000);
			scores.addScore(new ChessScore(p1, (float) (endTime - startTime) / 1000, elo)); //pass updated elo
			System.out.println("Enter 'y' to play again; any other key will exit");
		} while (sc.next().equals("y"));

	}

}