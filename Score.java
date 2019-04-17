import java.sql.*;

public class Score {
	protected String name;
	protected float time;

	public Score(String name, float time) {
		this.name = name;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public float getTime() {
		return time;
	}

	public String getScore() {
		return name + "\t" + time;
	}

	public void updateDB(int id, Statement statement){
		String sql = "INSERT INTO rdbmsproject.highscores VALUES ("  + id + ", '" + name + "', " + time + ", 'unknown');";
		try{
			statement.executeUpdate(sql);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
