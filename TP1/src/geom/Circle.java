package geom;

/**
 * A circle shape.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class Circle extends Shape {
	/** The radius of the circle. */
	protected float radius;

	/**
	 * Circle constructor with a default position of (0,0) and a radius of 10.
	 * 
	 * @see geom.Position
	 */
	Circle() {
		super();
		this.radius = 10;
	}

	/**
	 * Circle constructor with a default position of (0,0).
	 * 
	 * @param radius
	 *            The wanted radius of the circle.
	 * 
	 * @throws IllegalArgumentException
	 *             If the radius is null or negative.
	 * 
	 * @see geom.Position
	 */
	Circle(float radius) throws IllegalArgumentException {
		super();
		if (radius <= 0)
			throw new IllegalArgumentException("radius is null or negative: " + radius);
		else {
			this.radius = radius;
		}
	}

	/**
	 * Circle constructor with a position and a radius.
	 * 
	 * @param position
	 *            The wanted position for the x and y center of the circle.
	 * 
	 * @param radius
	 *            The wanted radius of the circle.
	 * 
	 * @throws IllegalArgumentException
	 *             If the radius is null or negative.
	 * 
	 * @see geom.Position
	 */
	Circle(Position position, float radius) throws IllegalArgumentException {
		super(position);
		if (radius <= 0)
			throw new IllegalArgumentException("radius is null or negative: " + radius);
		else {
			this.radius = radius;
		}
	}

	/**
	 * Circle constructor.
	 * 
	 * @param x
	 *            The wanted position for the x center of the circle.
	 * 
	 * @param y
	 *            The wanted position for the y center of the circle.
	 * 
	 * @param radius
	 *            The wanted radius of the circle.
	 * 
	 * @throws IllegalArgumentException
	 *             If the radius is null or negative.
	 * 
	 * @see geom.Position
	 */
	Circle(float x, float y, float radius) throws IllegalArgumentException {
		super(x, y);
		if (radius <= 0)
			throw new IllegalArgumentException("radius is null or negative: " + radius);
		else {
			this.radius = radius;
		}
	}

	@Override
	boolean isCollideTo(Shape s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void moveTo(Position position) {
		this.position = position;
	}
}
