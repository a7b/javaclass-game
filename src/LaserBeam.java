import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class LaserBeam extends WorldObject {
	public LaserBeam() {
		this(0);
	}
	
	public LaserBeam(double radians) {
		this(radians, ORIGIN);
	}
	
	public LaserBeam(double radians, Double[] center) {
		addShape(new Rectangle2D.Double(635, 10, 10, 720)).color(Color.GREEN).center(center);
		transform.rotate(radians);
		//transform.rotate(Math.PI);
	}
}
