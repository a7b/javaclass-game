import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


public class LaserBeam extends WorldObject {

	public static final int LIFE = 200;

	protected final long birth;

	public LaserBeam() {
		this(0);
	}
	
	public LaserBeam(double radians) {
		this(radians, ORIGIN);
	}
	
	public boolean dead() {
		return System.currentTimeMillis() - birth > LIFE;
	}

	public LaserBeam(double radians, Double[] center) {
		AffineTransform at = AffineTransform.getTranslateInstance(center[0], center[1]);
		at.rotate(radians);
		addShape(new Rectangle2D.Double(0, -5, 1470, 10)).color(Color.GREEN).transform(at);

		birth = System.currentTimeMillis();
	}
}
