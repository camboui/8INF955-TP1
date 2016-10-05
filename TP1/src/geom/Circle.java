package geom;

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

			double radiusSum = this.getRadius() + otherCircle.getRadius();
			double dx = Math.abs(otherCircle.getPosition().getX() - this.getPosition().getX());
			double dy = Math.abs(otherCircle.getPosition().getY() - this.getPosition().getY());

			if (dx > radiusSum)
				return false;
			if (dy > radiusSum)
				return false;

			return ((radiusSum * radiusSum) >= ((dx * dx) + dy * dy));
		} else if (shape instanceof Point) {
			Point point = (Point) shape;
			// (x-center_x)^2 + (y - center_y)^2 < radius^2
			return ((point.getPosition().getX() - this.getPosition().getX() * point.getPosition().getX()
					- this.getPosition().getX() + point.getPosition().getY()
					- this.getPosition().getY() * point.getPosition().getY()
					- this.getPosition().getY()) < this.getRadius() * this.getRadius());
		} else if (shape instanceof AABB) {
			AABB aabb = (AABB) shape;
			// TODO Cas simple d'eliminations
			Position position;
			// x > , y >
			if (this.getPosition().getX() > aabb.getPosition().getX()
					&& this.getPosition().getY() > aabb.getPosition().getY()) {
				position = new Position(aabb.getPosition().getX() + aabb.getWidth() / 2,
						aabb.getPosition().getY() + aabb.getHeight() / 2);
			}
			// x > , y <
			else if (this.getPosition().getX() > aabb.getPosition().getX()
					&& this.getPosition().getY() < aabb.getPosition().getY()) {
				position = new Position(aabb.getPosition().getX() + aabb.getWidth() / 2,
						aabb.getPosition().getY() - aabb.getHeight() / 2);
			}
			// x < , y >
			else if (this.getPosition().getX() < aabb.getPosition().getX()
					&& this.getPosition().getY() > aabb.getPosition().getY()) {
				position = new Position(aabb.getPosition().getX() - aabb.getWidth() / 2,
						aabb.getPosition().getY() + aabb.getHeight() / 2);
			}
			// x < , y <
			else {
				position = new Position(aabb.getPosition().getX() - aabb.getWidth() / 2,
						aabb.getPosition().getY() - aabb.getHeight() / 2);
			}

			return (((position.getX() - this.getPosition().getX()) * (position.getX() - this.getPosition().getX()))
					+ ((position.getY() - this.getPosition().getY())
							* (position.getY() - this.getPosition().getY())) < (this.getRadius() * this.getRadius()));
		} else if (shape instanceof OBB) {
			OBB obb = (OBB) shape;
			Circle rotateCircle = new Circle(this.getPosition(), this.getRadius());

			// TODO Delete
			// obb.setPosition(0, 0);
			// rotateCircle.setPosition(rotateCircle.getPosition().getX() -
			// obb.getPosition().getX(),
			/// rotateCircle.getPosition().getX() - obb.getPosition().getX());
			obb.setPosition(
					(obb.getPosition().getX() * Math.cos(obb.getAngle())
							+ obb.getPosition().getY() * Math.sin(obb.getAngle())),
					(-obb.getPosition().getX() * Math.sin(obb.getAngle())
							+ obb.getPosition().getY() * Math.cos(obb.getAngle())));

			rotateCircle.setPosition(
					(rotateCircle.getPosition().getX() * Math.cos(obb.getAngle())
							+ rotateCircle.getPosition().getY() * Math.sin(obb.getAngle())),
					(-rotateCircle.getPosition().getX() * Math.sin(obb.getAngle())
							+ rotateCircle.getPosition().getY() * Math.cos(obb.getAngle())));

			rotateCircle.isCollideTo((AABB) obb);

		} else if (shape instanceof KDOP) {
			// TODO Circle KDOP
		}
		throw new UnknownElementException(null, shape);
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

}
