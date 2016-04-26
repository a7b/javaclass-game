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
	protected ArrayList<Double> rotations;
	protected ArrayList<Double[]> centers;
	protected ArrayList<Color> colors;

	protected AffineTransform transform;
	protected Double rotation;
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
		this.rotations = new ArrayList<>();
		this.centers = new ArrayList<>();
		this.colors = new ArrayList<>();
		if (s != null) {
			addShape(s);
			this.center = this.centers.get(0);
		} else {
			// default center 0,0
			this.center = ORIGIN;
		}
		// the transform of the whole object
		this.transform = at;
		this.rotation = 0.;
	}

	public void render(Graphics2D g) {
		for (int i = 0; i < shapes.size(); i++) {
			g.setColor(colors.get(i));
			g.fill(renderMesh(i));
		}
	}

	public Shape renderMesh(Shape s) {
		return renderMesh(shapes.indexOf(s));
	}

	public Shape renderMesh(int index) {
		Shape shape = shapes.get(index);
		Double[] localCenter = centers.get(index);

		AffineTransform t = new AffineTransform();

		// local transform
		t.concatenate(transforms.get(index));
		// local rotation
		t.rotate(rotations.get(index), localCenter[0], localCenter[1]);
		// global transform
		t.concatenate(transform);
		// global rotation
		t.rotate(rotation, center[0], center[1]);

		return t.createTransformedShape(shape);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public Modifyable addShape(Shape s) {
		getShapes().add(s);
		Rectangle2D bb = s.getBounds2D();
		Double[] center = { bb.getCenterX(), bb.getCenterY() };
		setTransform(s, new AffineTransform());
		setRotation(s, 0.);
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

	public void setTransform(AffineTransform transform) {
		this.transform = transform;
	}

	public WorldObject rotate(Double rotation) {
		setRotation(rotation + getRotation());
		return this;
	}

	public Double getRotation() {
		return rotation;
	}

	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

	public Double[] getCenter() {
		return center;
	}

	public void setCenter(Double[] center) {
		this.center = center;
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

	public Double getRotation(Shape s) {
		return getRotation(shapes.indexOf(s));
	}

	public Double getRotation(int index) {
		return rotations.get(index);
	}

	public void setRotation(Shape s, Double rotation) {
		int index = shapes.indexOf(s);
		if (index >= rotations.size()) {
			rotations.add(rotation);
		} else {
			rotations.set(index, rotation);
		}
	}

	public Double[] getCenter(Shape s) {
		return getCenter(shapes.indexOf(s));
	}

	public Double[] getCenter(int index) {
		return centers.get(index);
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
		return getColor(shapes.indexOf(s));
	}

	public Color getColor(int index) {
		return colors.get(index);
	}

	public void setColor(Shape s, Color color) {
		int index = shapes.indexOf(s);
		if (index >= colors.size()) {
			colors.add(color);
		} else {
			colors.set(index, color);
		}
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

		public Double[] rotation() {
			return centers.get(index);
		}

		public Modifyable rotation(Double rotation) {
			if (index >= rotations.size()) {
				rotations.add(rotation);
			} else {
				rotations.set(index, rotation);
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
