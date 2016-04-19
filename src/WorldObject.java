import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class WorldObject {

	private static final Double[] ORIGIN = { Double.valueOf(0),
			Double.valueOf(0) };

	protected ArrayList<Shape> shapes;
	protected ArrayList<AffineTransform> transforms;
	protected ArrayList<Double[]> centers;
	protected ArrayList<Color> colors;

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
		this.shapes = new ArrayList<>();
		this.transforms = new ArrayList<>();
		this.centers = new ArrayList<>();
		this.colors = new ArrayList<>();
		this.shapes.add(s);
		this.transforms.add(new AffineTransform());
		this.centers.add(ORIGIN);
		this.colors.add(Color.BLACK);
		// the transform of the whole object
		this.transform = at;
	}

	public void render(Graphics2D g) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape s = shapes.get(i);
			// transform each individual Shape
			s = transforms.get(i).createTransformedShape(s);
			// transform the object
			s = transform.createTransformedShape(s);
			g.setColor(colors.get(i));
			g.fill(s);
		}
	}

	public Shape renderMesh(Shape s) {
		int index = shapes.indexOf(s);
		return transforms.get(index).createTransformedShape(shapes.get(index));
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public Shape addShape(Shape s) {
		getShapes().add(s);
		Rectangle2D bb = s.getBounds2D();
		Double[] center = {Double.valueOf(bb.getCenterX()), Double.valueOf(bb.getCenterY())};
		setTransform(s, new AffineTransform());
		setCenter(s, center);
		setColor(s, Color.BLACK);
		return s;
	}

	public Shape lastShape() {
		return getShapes().get(shapes.size() - 1);
	}

	public AffineTransform getTransform() {
		return transform;
	}

	public AffineTransform getTransform(Shape s) {
		return transforms.get(shapes.indexOf(s));
	}

	public void setTransform(Shape s, AffineTransform at) {
		int index = shapes.indexOf(s);
		if (index >= transforms.size()) {
			transforms.add(at);
		} else {
			transforms.set(index, at);
		}
	}

	public Double[] getCenter(Shape s) {
		return centers.get(shapes.indexOf(s));
	}

	public void setCenter(Shape s, Double[] center) {
		int index = shapes.indexOf(s);
		if (index >= centers.size()) {
			centers.add(center);
		} else {
			centers.set(index, center);
		}
	}

	public Color getColor(Shape s) {
		return colors.get(shapes.indexOf(s));
	}

	public void setColor(Shape s, Color color) {
		int index = shapes.indexOf(s);
		if (index >= colors.size()) {
			colors.add(color);
		} else {
			colors.set(index, color);
		}
	}
}
