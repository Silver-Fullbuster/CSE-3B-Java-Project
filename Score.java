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

    public void updateDB(int id, Statement statement) {
        if (this instanceof ChessScore) {
            String sql = "INSERT INTO rdbmsproject.highscores VALUES (" + id + ", '" + name + "', " + time + ", 'Chess');";
            try {
                statement.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (this instanceof HangmanScore) {
            String sql = "INSERT INTO rdbmsproject.highscores VALUES (" + id + ", '" + name + "', " + time + ", 'Hangman');";
            try {
                statement.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String sql = "INSERT INTO rdbmsproject.highscores VALUES (" + id + ", '" + name + "', " + time + ", 'TicTacToe');";
            try {
                statement.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

