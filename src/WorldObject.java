import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class WorldObject {

	protected static final Double[] ORIGIN = { Double.valueOf(0),
			Double.valueOf(0) };

	protected ArrayList<Shape> shapes;
	protected ArrayList<AffineTransform> transforms;
	protected ArrayList<Double[]> centers;
	protected ArrayList<Color> colors;

	protected AffineTransform transform;
	protected Double[] center;

	public WorldObject() {
		this(null);
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
		// make non null
		if (at == null) {
			at = new AffineTransform();
		}
		this.shapes = new ArrayList<>();
		this.transforms = new ArrayList<>();
		this.centers = new ArrayList<>();
		this.colors = new ArrayList<>();
		if (s != null) {
			addShape(s);
		} else {
			// default center 0,0
			this.center = ORIGIN;
		}
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

	public Modifyable addShape(Shape s) {
		getShapes().add(s);
		Rectangle2D bb = s.getBounds2D();
		Double[] center = { bb.getCenterX(), bb.getCenterY() };
		setTransform(s, new AffineTransform());
		setCenter(s, center);
		setColor(s, Color.BLACK);
		return new Modifyable(s);
	}

	public Shape lastShape() {
		return shapes.get(shapes.size() - 1);
	}


	public AffineTransform getTransform() {
		return transform;
	}

	// TODO make methods like this
	public AffineTransform getTransform(Shape s) {
		return getTransform(shapes.indexOf(s));
	}

	public AffineTransform getTransform(int index) {
		return transforms.get(index);
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

	public Double[] getCenter() {
		return center;
	}

	public void setCenter(Double[] center) {
		this.center = center;
	}

	public class Modifyable {
		private Shape shape;
		private int index;

		public Modifyable(Shape shape) {
			this.shape = shape;
			this.index = shapes.indexOf(shape);
		}

		public Shape getShape() {
			return shape;
		}

		public AffineTransform transform() {
			return transforms.get(index);
		}

		public Modifyable transform(AffineTransform at) {
			if (index >= transforms.size()) {
				transforms.add(at);
			} else {
				transforms.set(index, at);
			}
			return this;
		}

		public Double[] center() {
			return centers.get(index);
		}

		public Modifyable center(Double[] center) {
			if (index >= centers.size()) {
				centers.add(center);
			} else {
				centers.set(index, center);
			}
			return this;
		}

		public Color color() {
			return colors.get(index);
		}

		public Modifyable color(Color color) {
			if (index >= colors.size()) {
				colors.add(color);
			} else {
				colors.set(index, color);
			}
			return this;
		}
	}
}
