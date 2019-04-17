import java.sql.ResultSet;

public class TicTacToeHighScore extends HighScore {

	public TicTacToeHighScore() {
		super();
	}

	@Override
	public void displayScoreList(){
		System.out.println("\tHIGH SCORES\n" +
				"\n" +
				"NAME\tTIME\tTURNS");
		try{
			ResultSet result = statement.executeQuery("SELECT name, timetaken FROM rdbmsproject.tictactoescores a JOIN rdbmsproject.highscores b ON a.id = b.id ORDER BY TimeTaken ASC;");
			while(result.next()){
				String name = result.getString("name");
				String timeTaken = result.getString("timeTaken");
				int noOfTurns = result.getInt("noOfMoves");
				System.out.println(name + "\t" + timeTaken + "\t" + noOfTurns);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
