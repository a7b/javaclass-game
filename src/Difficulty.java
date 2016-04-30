public class Difficulty {
	public static final Difficulty EASY = new Difficulty(32, 128, 60 * 1000);
	public static final Difficulty NORMAL = new Difficulty(64, 512, 30 * 1000);
	public static final Difficulty EXPERT = new Difficulty(128, 2048, 15 * 1000);

	private double wordSpeed;
	private double cannonFireRate;
	private int msToSpeedUp;

	Difficulty(double wordSpeed, double cannonFireRate, int msToSpeedUp) {
		this.setWordSpeed(wordSpeed);
		this.setCannonFireRate(cannonFireRate);
		this.setMsToSpeedUp(msToSpeedUp);
	}

	public double getWordSpeed() {
		return wordSpeed;
	}

	public void setWordSpeed(double wordSpeed) {
		this.wordSpeed = wordSpeed;
	}

	public double getCannonFireRate() {
		return cannonFireRate;
	}

	public void setCannonFireRate(double cannonFireRate) {
		this.cannonFireRate = cannonFireRate;
	}

	public int getMsToSpeedUp() {
		return msToSpeedUp;
	}

	public void setMsToSpeedUp(int msToSpeedUp) {
		this.msToSpeedUp = msToSpeedUp;
	}
}