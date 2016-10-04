package geom;

/**
 * A point shape.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class Point extends Shape {

	/** Point constructor with a default position of (0,0) . */
	public Point() {
		super();
	}

	/**
	 * Point constructor.
	 * 
	 * @param x
	 *            The wanted position for the x axis.
	 * 
	 * @param y
	 *            The wanted position for the y axis.
	 */
	public Point(float x, float y) {
		super(x, y);
	}

	/**
	 * Point constructor.
	 * 
	 * @param position
	 *            The wanted position of the Shape.
	 * 
	 * @see geom.Position
	 */
	public Point(Position position) {
		this.position = position;
	}

	@Override
	public boolean isCollideTo(Shape shape) {
		if (shape instanceof Point) {
			// TODO point point
		} else if (shape instanceof Circle) {
			// TODO point circle
		} else if (shape instanceof AABB) {
			// TODO point aabb
		} else if (shape instanceof OBB) {
			// TODO point obb
		} else if (shape instanceof KDOP) {
			// TODO point kdop
		}
		// TODO
		return false;
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}
}
