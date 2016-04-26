import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


public class LaserBeam extends WorldObject {
	public LaserBeam() {
		this(0);
	}
	
	public LaserBeam(double radians) {
		this(radians, ORIGIN);
	}
	
	public LaserBeam(double radians, Double[] center) {
		AffineTransform at = AffineTransform.getTranslateInstance(center[0], center[1]);
		at.rotate(radians);
		addShape(new Rectangle2D.Double(0, -5, 720, 10)).color(Color.GREEN).transform(at);
	}
}
