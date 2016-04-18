import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;


public class WorldObject {

	private static final Double[] ORIGIN = { Double.valueOf(0),
			Double.valueOf(0) };

	protected LinkedList<Shape> shapes;
	protected LinkedList<AffineTransform> transforms;
	protected LinkedList<Double[]> centers;
	protected LinkedList<Color> colors;

	protected AffineTransform transform;

	public WorldObject() {
		this(new GeneralPath());
	}

	public WorldObject(GeneralPath p) {
		this((Shape) p);
	}

	public WorldObject(GeneralPath p, AffineTransform at) {
		this((Shape) p, at);
	}

	public WorldObject(Shape s) {
		this(s, new AffineTransform());
	}

	public WorldObject(Shape s, AffineTransform at) {
		this.shapes = new LinkedList<>();
		this.transforms = new LinkedList<>();
		this.centers = new LinkedList<>();
		this.colors = new LinkedList<>();
		this.shapes.push(s);
		this.transforms.push(new AffineTransform());
		this.centers.push(ORIGIN);
		this.colors.push(null);
		// the transform of the whole object
		this.transform = at;
	}

	public void render(Graphics2D g) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape s = shapes.get(i);
			s = transforms.get(i).createTransformedShape(s);
			s = transform.createTransformedShape(s);
			Color color = colors.get(i);
			if (color != null) {
				g.setColor(color);
			}
			g.fill(s);
		}
	}

	public Shape renderMesh(Shape s) {
		int index = shapes.indexOf(s);
		return transforms.get(index).createTransformedShape(shapes.get(index));
	}

	public LinkedList<Shape> getShapes() {
		return shapes;
	}

	public AffineTransform getTransform() {
		return transform;
	}

	public AffineTransform getTransform(Shape s) {
		return transforms.get(shapes.indexOf(s));
	}

	public void setTransform(Shape s, AffineTransform at) {
		transforms.set(shapes.indexOf(s), at);
	}

	public Double[] getCenter(Shape s) {
		return centers.get(shapes.indexOf(s));
	}

	public void setCenter(Shape s, Double[] center) {
		centers.set(shapes.indexOf(s), center);
	}

	public Color getColor(Shape s) {
		return colors.get(shapes.indexOf(s));
	}

	public void setColor(Shape s, Color color) {
		colors.set(shapes.indexOf(s), color);
	}
}
