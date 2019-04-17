import java.sql.ResultSet;

public class ChessHighScore extends HighScore {

    public ChessHighScore() {
        super();
    }

    @Override
    public void displayScoreList(){
        System.out.println("\tHIGH SCORES\n" +
                "\n" +
                "NAME\tTIME\tELO");
        try{
            ResultSet result = statement.executeQuery("SELECT name, timetaken FROM rdbmsproject.chessscores a JOIN rdbmsproject.highscores b ON a.id = b.id ORDER BY TimeTaken ASC;");
            while(result.next()){
                String name = result.getString("name");
                String timeTaken = result.getString("timeTaken");
                int _elorating = result.getInt("elo_rating");
                System.out.println(name + "\t" + timeTaken + "\t" + _elorating);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}