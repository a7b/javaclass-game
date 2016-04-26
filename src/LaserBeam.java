import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


public class LaserBeam extends WorldObject {

	public static final int LIFE = 20000;
	public static final double SPEED = 3000;

	protected final long birth;

	// delta transformation
	protected final AffineTransform dtransform;

	public LaserBeam() {
		this(0);
	}
	
	public LaserBeam(double radians) {
		this(radians, ORIGIN);
	}
	
	// called once per tick
	public void update() {
		getTransform(0).concatenate(dtransform);
	}

	public boolean dead() {
		return System.currentTimeMillis() - birth > LIFE;
	}

	public LaserBeam(double radians, Double[] center) {
		AffineTransform at = AffineTransform.getTranslateInstance(center[0], center[1]);
		at.rotate(radians);
		addShape(new Rectangle2D.Double(0, -5, 100, 10)).color(Color.GREEN)
				.transform(at);

		birth = System.currentTimeMillis();
		dtransform = AffineTransform.getTranslateInstance(SPEED / Context.TICK,
				0);
	}
}
