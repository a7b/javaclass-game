import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;


public class WorldObject {

	private GeneralPath p;
	private AffineTransform at;

	public WorldObject() {
		this(new GeneralPath());
	}

	public WorldObject(Shape s) {
		this(new GeneralPath(s));
	}

	public WorldObject(Shape s, AffineTransform at) {
		this(new GeneralPath(s), at);
	}

	public WorldObject(GeneralPath p) {
		this(p, new AffineTransform());
	}

	public WorldObject(GeneralPath p, AffineTransform at) {
		this.p = p;
		this.at = at;
	}

	public Shape render() {
		return p.createTransformedShape(at);
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
		this.at = at;
	}
}
