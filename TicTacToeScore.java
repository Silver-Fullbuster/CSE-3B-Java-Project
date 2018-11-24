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

}
