public class Difficulty {
	public static final Difficulty EASY = new Difficulty(32, 128, 360 * 1000, true);
	public static final Difficulty NORMAL = new Difficulty(64, 512, 180 * 1000, true);
	public static final Difficulty EXPERT = new Difficulty(128, 2048, 90 * 1000, false);

	public final double wordSpeed;
	public final double cannonFireRate;
	public final int msToSpeedUp;
	public final boolean verifyGuess;

	Difficulty(double wordSpeed, double cannonFireRate, int msToSpeedUp, boolean verifyGuess) {
		this.wordSpeed = wordSpeed;
		this.cannonFireRate = cannonFireRate;
		this.msToSpeedUp = msToSpeedUp;
		this.verifyGuess = verifyGuess;
	}


}