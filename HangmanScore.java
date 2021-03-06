import java.sql.Statement;

public class HangmanScore extends Score {

	private int noOfWrongGuesses;

	public HangmanScore(String name, float time, int noOfWrongGuesses) {
		super(name, time);
		this.noOfWrongGuesses = noOfWrongGuesses;
	}

	@Override
	public String getScore() {
		return name + "\t" + time + "\tHangman: " + noOfWrongGuesses + " wrong guesses";
	}

	@Override
	public void updateDB(int id, Statement statement){
		super.updateDB(id, statement);
		String sqlHS = "UPDATE rdbmsproject.highscores SET `Type` = 'Hangman' WHERE ID = " + id + ";";
		String sql = "INSERT INTO rdbmsproject.hangmanscores VALUES ("  + id + ", " + noOfWrongGuesses + ");";
		try{
			statement.executeUpdate(sqlHS);
			statement.executeUpdate(sql);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}