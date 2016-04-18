import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class LaserBeam extends WorldObject {
	
	LaserBeam(int radians) {
		super(new Rectangle2D.Double(640, 0, 10, 1280));
		setColor(shapes.getLast(), Color.GREEN);
		transform.rotate(radians);
	}
}
