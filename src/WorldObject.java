import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;


public class WorldObject {

	private GeneralPath p;
	private AffineTransform at;

	public WorldObject() {
		this(new GeneralPath());
	}

	public WorldObject(GeneralPath p) {
		this(p, new AffineTransform());
	}

	public WorldObject(GeneralPath p, AffineTransform at) {
		this.at = at;
	}

	public GeneralPath path() {
		return p;
	}

	public void path(GeneralPath p) {
		this.p = p;
	}

	public AffineTransform transform() {
		return this.at;
	}

	public void transform(AffineTransform at) {
		p.transform(at);
		this.at = at;
	}
}
