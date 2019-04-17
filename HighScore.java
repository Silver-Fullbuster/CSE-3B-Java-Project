import java.sql.*;

public class HighScore implements SQLAuth {
	private Connection connection;
	protected Statement statement;
	private int id;

	public HighScore() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdbmsproject?autoReconnect=true&useSSL=false", USER, PASSWORD);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM rdbmsproject.highscores");
			while(rs.next())
				id = rs.getInt("COUNT(*)");
			System.out.println(id);
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
		System.out.println("\tHIGH SCORES\n" +
				"\n" +
				"NAME\tTIME\tTYPE");
		try{
			ResultSet result = statement.executeQuery("SELECT name, timetaken FROM rdbmsproject.highscores ORDER BY TimeTaken ASC;");
			while(result.next()){
				String name = result.getString("name");
				String timeTaken = result.getString("timeTaken");
				String type = result.getString("type");
				System.out.println(name + "\t" + timeTaken + "\t" + type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
