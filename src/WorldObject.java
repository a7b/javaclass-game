import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;


public class WorldObject {

	protected GeneralPath p;
	protected AffineTransform at;
	protected double[] center;
	protected Color color;

	public WorldObject() {
		this(new GeneralPath());
	}

	public WorldObject(Shape s) {
		this(new GeneralPath(s));
	}

	public WorldObject(Shape s, AffineTransform at) {
		this(new GeneralPath(s), at, new double[] { 0, 0 });
	}

	public WorldObject(Shape s, AffineTransform at, double[] center) {
		this(new GeneralPath(s), at, center);
	}

	public WorldObject(GeneralPath p) {
		this(p, new AffineTransform(), new double[] { 0, 0 });
	}

	public WorldObject(GeneralPath p, AffineTransform at) {
		this(p, at, new double[] { 0, 0 });
	}

	public WorldObject(GeneralPath p, AffineTransform at, double[] center) {
		this.p = p;
		this.at = at;
		this.center = center;
	}

	public void render(Graphics2D g) {
		if (color != null) {
			g.setColor(color);
		}
		g.fill(renderMesh());
	}

	public Shape renderMesh() {
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

	public double[] getCenter() {
		return center;
	}

	public void setCenter(double[] center) {
		this.center = center;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
