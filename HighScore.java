import java.util.Comparator;
import java.sql.*;

public class HighScore implements SQLAuth {
	private Connection connection;
	private Statement statement;
	private int id;

	public HighScore() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdbmsproject?autoReconnect=true&useSSL=false", USER, PASSWORD);
			statement = connection.createStatement();
			id = statement.executeQuery("SELECT COUNT(*) FROM rdbmsproject.highscores").findColumn("count(*)");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addScore(Score score) {
		score.updateDB(id, statement);
		id++;
	}

	//TODO
	public void displayScoreList() {

	}
}
