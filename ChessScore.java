import java.sql.Statement;

class ChessScore extends Score {

	private float elo;

	public ChessScore(String name, float time, float elo) {
		super(name, time);
		this.elo = elo;
	} //Add number of pieces left

	@Override
	public String getScore() {
		return name + "\t" + time + "\t" + "Chess ELO Rating: " + elo;
	}

	@Override
	public void updateDB(int id, Statement statement){
		super.updateDB(id, statement);
		String sqlHS = "UPDATE rdbmsproject.highscores SET `Type` = 'Chess' WHERE ID = " + id + ";";
		String sql = "INSERT INTO rdbmsproject.chessscores VALUES ("  + id + ", " + elo + ");";
		try{
			statement.executeUpdate(sqlHS);
			statement.executeUpdate(sql);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

