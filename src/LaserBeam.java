import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


public class LaserBeam {
	private AffineTransform laserTransform;
	private WorldObject laser;
	
	LaserBeam(int vectorx, int vectory, int anchorx, int anchory){
		laserTransform = new AffineTransform();
		laser = new WorldObject(new Rectangle2D.Double(30, 40, 10, 500), laserTransform);
		laserTransform.rotate(vectorx, vectory, anchorx, anchory);
	}
}
