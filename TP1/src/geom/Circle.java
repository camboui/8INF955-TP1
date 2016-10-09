package geom;

import java.awt.Graphics2D;

import javax.lang.model.element.UnknownElementException;

/**
 * A circle shape.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class Circle extends Shape {
	/** The radius of the circle. */
	protected double radius;

	/**
	 * Circle constructor with a default position of (0,0) and a radius of 10.
	 * 
	 * @see geom.Position
	 */
	public Circle() {
		super();
		this.radius = 10;
	}

	/**
	 * Circle constructor with a default center position of (0,0).
	 * 
	 * @param radius
	 *            The wanted radius of the circle.
	 * 
	 * @throws IllegalArgumentException
	 *             If the radius is null or negative.
	 * 
	 * @see geom.Position
	 */
	public Circle(double radius) throws IllegalArgumentException {
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
	public Circle(Position position, double radius) throws IllegalArgumentException {
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
	public Circle(double x, double y, double radius) throws IllegalArgumentException {
		super(x, y);
		if (radius <= 0)
			throw new IllegalArgumentException("radius is null or negative: " + radius);
		else {
			this.radius = radius;
		}
	}

	@Override
	public boolean isCollideTo(Shape shape) {
		if (shape instanceof Circle) {
			Circle otherCircle = (Circle) shape;
			return this.vsCircle(otherCircle);
		} else if (shape instanceof Point) {
			Point point = (Point) shape;
			return point.isInside(this);
		} else if (shape instanceof AABB) {
			KDOP kdop = ((AABB) shape).toKDOP();
			return kdop.pointInside(getPosition()) || kdop.minDistance(getPosition()) <= getRadius();
		} else if (shape instanceof OBB) {
			KDOP kdop = ((OBB) shape).toKDOP();
			return kdop.pointInside(getPosition()) || kdop.minDistance(getPosition()) <= getRadius();
		} else if (shape instanceof KDOP) {
			KDOP kdop = (KDOP) shape;
			return kdop.pointInside(getPosition()) || kdop.minDistance(getPosition()) <= getRadius();
		}
		throw new UnknownElementException(null, shape);
	}

	/**
	 * Test if two circle are colliding.
	 * 
	 * @param circle
	 *            The other circle for the collision
	 * 
	 * @return True if they are colliding, false if they don't.
	 */
	private boolean vsCircle(Circle circle) {
		double radiusSum = this.getRadius() + circle.getRadius();
		double dx = Math.abs(circle.getPosition().getX() - this.getPosition().getX());
		double dy = Math.abs(circle.getPosition().getY() - this.getPosition().getY());

		if (dx > radiusSum)
			return false;
		if (dy > radiusSum)
			return false;

		return ((radiusSum * radiusSum) >= ((dx * dx) + dy * dy));
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}

	/**
	 * radius getter.
	 *
	 * @return The radius.
	 */
	public double getRadius() {
		return this.radius;
	}

	/**
	 * radius setter.
	 *
	 * @param radius
	 *            The new radius.
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public void draw(Graphics2D g) {
		int r = (int) getRadius();
		g.fillOval((int) (getPosition().getX() - r), (int) (getPosition().getY() - r), r * 2, r * 2);

	}

}
