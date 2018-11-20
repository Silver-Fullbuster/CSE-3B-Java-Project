import java.util.ArrayList;
import java.util.Comparator;

public class HighScore {
    private ArrayList<Score> scoreList = new ArrayList<>();

    public void addScore(Score score) {
        scoreList.add(score);
        scoreList.sort(Comparator.comparingInt(Score::getTime));
    }

    private void displayScoreList() {
        System.out.println("\n\n\tHIGH SCORES\n" +
                "\n" +
                "Position\tNAME\tTIME\n");
        int count = 0;
        for(Score sc : scoreList)
            System.out.println(++count + "\t" + sc.getName() +"\t" + sc.getTime());
    }
}
