import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;


public class LaserBeam implements ActionListener {
	private AffineTransform laserTransform;
	private WorldObject laser;
	private Timer tm;
	
	LaserBeam(int radians){
		laserTransform = new AffineTransform();
		laser = new WorldObject(new Rectangle2D.Double(640, 0, 10, 1280), laserTransform);
		laserTransform.rotate(radians);
		tm = new Timer(1500, this);
		tm.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tm){
			
		}
	}
}
