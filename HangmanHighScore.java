import java.sql.ResultSet;

public class HangmanHighScore extends HighScore {

	public HangmanHighScore() {
		super();
	}

	@Override
	public void displayScoreList(){
		System.out.println("\tHIGH SCORES\n" +
				"\n" +
				"NAME\tTIME\tWRONG GUESSES");
		try{
			ResultSet result = statement.executeQuery("SELECT name, timetaken FROM rdbmsproject.hangmanscores a JOIN rdbmsproject.highscores b ON a.id = b.id ORDER BY TimeTaken ASC;");
			while(result.next()){
				String name = result.getString("name");
				String timeTaken = result.getString("timeTaken");
				int noOfWrongs = result.getInt("noOfWrongMoves");
				System.out.println(name + "\t" + timeTaken + "\t" + noOfWrongs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
