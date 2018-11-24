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
}
