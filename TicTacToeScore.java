import java.sql.Statement;

public class TicTacToeScore extends Score {

	private int noOfTurns;

	public TicTacToeScore(String name, float time, int noOfTurns) {
		super(name, time);
		this.noOfTurns = noOfTurns;
	}

	@Override
	public String getScore() {
		return name + "\t" + time + "\tTicTacToe turns: " + noOfTurns;
	}

	@Override
	public void updateDB(int id, Statement statement){
		super.updateDB(id, statement);
		String sql = "INSERT INTO rdbmsproject.tictactoescores VALUES ("  + id + ", " + noOfTurns +");";
		try{
			statement.executeUpdate(sql);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
