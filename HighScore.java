import java.util.ArrayList;
import java.util.Comparator;

public class HighScore {
    private ArrayList<Score> scoreList = new ArrayList<>();

    public void addScore(Score score) {
        scoreList.add(score);
        scoreList.sort((a, b) -> {
            float temp = a.getTime() - b.getTime();
            return (int) temp;
        });
    }

    public void displayScoreList() {
        System.out.println("\tHIGH SCORES\n" +
                "\n" +
                "POS\tNAME\tTIME\tEXTRA\n");
        int count = 0;
        for(Score sc : scoreList)
            System.out.println(++count + "\t" + sc.getScore());
    }
}
