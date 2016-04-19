import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class LaserBeam extends WorldObject {
	LaserBeam(int radians) {
		super(new Rectangle2D.Double(635, 0, 10, 1280));
		setColor(lastShape(), Color.GREEN);
		transform.rotate(radians);
	}
}
