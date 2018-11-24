class ChessScore extends Score {

	float elo;

	public ChessScore(String name, float time, float elo) {
		super(name, time);
		this.elo = elo;
	} //Add number of pieces left

	@Override
	public String getScore() {
		return name + "\t" + time + "\t" + "Chess ELO Rating: " + elo;
	}
}

