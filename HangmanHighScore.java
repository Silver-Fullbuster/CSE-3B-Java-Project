import java.sql.ResultSet;

public class HangmanHighScore extends HighScore {

	public HangmanHighScore() {
		super();
		sql = "SELECT * FROM rdbmsproject.hangmanscores a JOIN rdbmsproject.highscores b ON a.id = b.id";
	}

	@Override
	public void displayScoreList(){
		System.out.println("\tHIGH SCORES\n" +
				"\n" +
				"NAME\tTIME\tWRONG GUESSES");
		try{
			ResultSet result = getResultSet();
			while(result.next()){
				String name = result.getString("name");
				String timeTaken = result.getString("timeTaken");
				int noOfWrongs = result.getInt("noOfWrongMoves");
				System.out.println(name + "\t" + timeTaken + "\t" + noOfWrongs);
			}
			requestOptions();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
