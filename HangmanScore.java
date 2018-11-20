public class HangmanScore extends Score {
	
	private int noOfWrongGuesses;

	public HangmanScore(String name, float time, int noOfWrongGuesses) {
		super(name, time);
		this.noOfWrongGuesses = noOfWrongGuesses;
	}

	@Override
	public String getScore() {
		return name + "\t" + time + "\tHangman: " + noOfWrongGuesses + " wrong guesses";
	}
}