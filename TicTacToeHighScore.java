import java.sql.ResultSet;

public class TicTacToeHighScore extends HighScore {

	public TicTacToeHighScore() {
		super();
		sql = "SELECT * FROM rdbmsproject.tictactoescores a JOIN rdbmsproject.highscores b ON a.id = b.id";
	}

	@Override
	protected void requestOptions(){
		System.out.print("\n1. Search for a name\n" +
				"2. Sort by Name\n" +
				"3. Sort by Moves\n" +
				"4. Reset (Sorted by Time)\n" +
				"5. Exit\n" +
				"\n" +
				"Enter choice: ");
		int choice = intScanner.nextInt();
		switch (choice) {
			case 1:
				System.out.print("Enter name: ");
				setSqlWhereStringStatementFor("Name");
				break;
			case 2:
				sqlSortField = "Name";
				toggleOrder();
				break;
			case 3:
				sqlSortField = "noOfMoves";
				toggleOrder();
				break;
			case 4:
				sqlWhereStatement = "";
				sqlSortField = "TimeTaken";
				sqlSortOrder = "ASC";
				break;
			case 5:
				return;
			default:
				System.out.println("Unknown option selected, displaying High Scores again");
		}
		displayScoreList();

	}

	@Override
	public void displayScoreList(){
		System.out.println("\tHIGH SCORES\n" +
				"\n" +
				"NAME\tTIME\tMOVES");
		try{
			ResultSet result = getResultSet();
			while(result.next()){
				String name = result.getString("name");
				String timeTaken = result.getString("timeTaken");
				int noOfMoves = result.getInt("noOfMoves");
				System.out.println(name + "\t" + timeTaken + "\t\t" + noOfMoves);
			}
			requestOptions();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
