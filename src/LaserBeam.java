import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;


public class LaserBeam extends WorldObject{
	private Timer tm;
	
	LaserBeam(int radians) {
		super(new Rectangle2D.Double(640, 0, 10, 1280));
		setColor(Color.GREEN);
		at.rotate(radians);
	}
}
