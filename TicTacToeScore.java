import java.sql.Statement;

public class TicTacToeScore extends Score {

	private int noOfTurns;

	public TicTacToeScore(String name, float time, int noOfTurns) {
		super(name, time);
		this.noOfTurns = noOfTurns;
	}
	int n = noOfTurns;

	@Override
	public String getScore() {
		return name + "\t" + time + "\tTicTacToe turns: " + noOfTurns;
	}

	@Override
	public void updateDB(int id, Statement statement){
		super.updateDB(id, statement);
		String sql = "INSERT INTO rdbmsproject.tictactoescores VALUES ("  + id + ", " + noOfTurns +");";
		String sqlHS = "UPDATE rdbmsproject.highscores SET `Type` = 'TicTacToe' WHERE ID = " + id + ";";
		try{
//			statement.executeUpdate(sqlHS);
			statement.executeUpdate(sql);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
