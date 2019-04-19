import java.sql.Statement;

class ChessScore extends Score {

	private int elo=1200;

	public ChessScore(String name, float time, int elo) {
		super(name, time);
		this.elo = elo + this.elo;
	} //Add number of pieces left

	int n = this.elo;
	@Override
	public String getScore() {
		return name + "\t" + time + "\t" + "Chess ELO Rating: " + elo;
	}

	@Override
	public void updateDB(int id, Statement statement){
		super.updateDB(id, statement);
		String sql = "INSERT INTO rdbmsproject.chesscores VALUES ("  + id + ", " + this.elo + ");";
		String sqlHS = "UPDATE rdbmsproject.highscores SET `Type` = 'Chess' WHERE ID = " + id + ";";
		this.elo = 1200;
		try{
			statement.executeUpdate(sqlHS);
			statement.executeUpdate(sql);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
