import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;

public class HighScore implements SQLAuth {
	private Statement statement;
	private Scanner charScanner;
	protected Scanner intScanner;
	protected String sqlWhereStatement;
	protected String sqlSortOrder;
	protected String sqlSortField;
	protected String sql;
	private int id;

	public HighScore() {
		sqlSortOrder = "ASC";
		sqlSortField = "TimeTaken";
		sqlWhereStatement = "";
		charScanner = new Scanner(System.in);
		intScanner = new Scanner(System.in);
		sql = "SELECT * FROM rdbmsproject.highscores";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdbmsproject?autoReconnect=true&useSSL=false", USER, PASSWORD);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM rdbmsproject.highscores");
			while (rs.next())
				id = rs.getInt("COUNT(*)");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	protected void toggleOrder() {
		sqlSortOrder = sqlSortOrder.equals("ASC") ? "DESC" : "ASC";
	}

	public void addScore(Score score) {
		id++;
		score.updateDB(id, statement);
	}

	protected void setSqlWhereStringStatementFor(String field) {
		String value = charScanner.nextLine();
		sqlWhereStatement = "WHERE " + field + "='" + value + "'";
	}

	protected void setSqlWhereIntStatementFor(String field) {
		int value = intScanner.nextInt();
		sqlWhereStatement = "WHERE " + field + "=" + value;
	}

	protected void requestOptions() {
		System.out.print("\n1. Search for a name\n" +
				"2. Sort by Name\n" +
				"3. Reset\n" +
				"4. Exit\n" +
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
				sqlWhereStatement = "";
				sqlSortField = "TimeTaken";
				sqlSortOrder = "ASC";
				break;
			case 4:
				return;
			default:
				System.out.println("Unknown option selected, displaying High Scores again\n\n");
		}
		displayScoreList();
	}

	protected ResultSet getResultSet() {
		try {
			return statement.executeQuery(sql + " " + sqlWhereStatement + " ORDER BY " + sqlSortField + " " + sqlSortOrder + ";");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void displayScoreList() {
		System.out.println("\tHIGH SCORES\n" +
				"\n" +
				"NAME\tTIME\tTYPE");
		ResultSet result = getResultSet();
		try {
			while (result.next()) {
				String name = result.getString("name");
				String timeTaken = result.getString("timeTaken");
				String type = result.getString("Type");
				System.out.println(name + "\t" + timeTaken + "\t\t" + type);
			}
			requestOptions();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
