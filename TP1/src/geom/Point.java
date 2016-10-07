package geom;

import java.awt.Graphics2D;
import javax.lang.model.element.UnknownElementException;

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
	public Point(double x, double y) {
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

	/**
	 * Copy constructor.
	 * 
	 * @param point
	 *            Equivalent to clone
	 */
	public Point(Point point) {
		this.position = point.getPosition();
	}

	@Override
	public boolean isCollideTo(Shape shape) {
		if (shape instanceof Point) {
			Point p = (Point) shape;
			return this.equals(p);
		} else if (shape instanceof Circle) {
			Circle c = (Circle) shape;
			return this.isInside(c);
		} else if (shape instanceof AABB) {
			AABB aabb = (AABB) shape;
			return this.isInside(aabb);
		} else if (shape instanceof OBB) {
			OBB obb = (OBB) shape;
			return this.isInside(obb);
		} else if (shape instanceof KDOP) {
			KDOP kdop = (KDOP) shape;
			return this.isInside(kdop);
			// return kdop.pointInside(getPosition());
		}
		throw new UnknownElementException(null, shape);
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}

	/**
	 * Equals method.
	 * 
	 * @param position
	 *            The wanted position to be tested on.
	 * 
	 * @return true if p is at the same position.
	 */
	public boolean equals(Point position) {
		return this.getPosition().equals(position.getPosition());
	}

	/**
	 * Test method to know if a point is inside a circle
	 * 
	 * @param circle
	 *            The wanted circle to be tested on.
	 * 
	 * @return true if the point is in the circle.
	 * 
	 * @see geom.Circle
	 */
	// Math.sqrt((x1-x0)*(x1-x0) + (y1-y0)*(y1-y0)) < r
	public boolean isInside(Circle circle) {

		return (((this.getPosition().getX() - circle.getPosition().getX())
				* (this.getPosition().getX() - circle.getPosition().getX())
				+ (this.getPosition().getY() - circle.getPosition().getY())
						* (this.getPosition().getY() - circle.getPosition().getY())) <= (circle.getRadius()
								* circle.getRadius()));
	}

	/**
	 * Test method to know if a point is inside a OBB.
	 * 
	 * @param obb
	 *            The wanted OBB to be tested on.
	 * 
	 * @return true if the point is in the OBB.
	 * 
	 * @see geom.OBB
	 */
	public boolean isInside(OBB obb) {

		double X = this.getPosition().getX() - obb.getPosition().getX();
		double Y = this.getPosition().getY() - obb.getPosition().getY();

		double newX = obb.getPosition().getX() + X * Math.cos(-obb.getAngle()) - Y * Math.sin(-obb.getAngle());
		double newY = obb.getPosition().getY() + X * Math.sin(-obb.getAngle()) + Y * Math.cos(-obb.getAngle());

		System.out.println(newX);
		System.out.println(newY);

		return ((newX <= (obb.getPosition().getX() + obb.getWidth())) && (newX >= (obb.getPosition().getX()))
				&& (newY >= (obb.getPosition().getY())) && (newY) <= (obb.getPosition().getY() + obb.getHeight()));
	}

	/**
	 * Test method to know if a point is inside a AABB.
	 * 
	 * @param aabb
	 *            The wanted AABB to be tested on.
	 * 
	 * @return true if the point is in the AABB.
	 * 
	 * @see geom.AABB
	 */
	public boolean isInside(AABB aabb) {
		return ((this.getPosition().getX() <= (aabb.getPosition().getX() + aabb.getWidth()))
				&& (this.getPosition().getX() >= (aabb.getPosition().getX()))
				&& (this.getPosition().getY() >= (aabb.getPosition().getY()))
				&& (this.getPosition().getY() <= (aabb.getPosition().getY() + aabb.getHeight())));
	}

	/**
	 * Test method to know if a point is inside a KDOP.
	 * 
	 * @param kdop
	 *            The wanted KDOP to be tested on.
	 * 
	 * @return true if the point is in the KDOP.
	 * 
	 * @see geom.AABB
	 */
	public boolean isInside(KDOP kdop) {
		int n = kdop.getPoints().size();
		int i, j;
		boolean b = false;

		for (i = 0, j = n - 1; i < n; j = i++) {
			if (((kdop.getPoints().get(i).getY() >= this.position.getY()) != (kdop.getPoints().get(j)
					.getY() >= this.position.getY()))
					&& (this.position.getX() <= (kdop.getPoints().get(j).getX() - kdop.getPoints().get(i).getX())
							* (position.getY() - kdop.getPoints().get(i).getY())
							/ (kdop.getPoints().get(j).getY() - kdop.getPoints().get(i).getY())
							+ kdop.getPoints().get(i).getX()))
				b = !b;
		}
		return b;
	}

	/**
	 * Calculate the distance between two points.
	 * 
	 * @param point
	 *            The wanted position for the operation.
	 * 
	 * @return The distance between two points.
	 * 
	 */
	public double distance(Point point) {
		return this.getPosition().distance(point.getPosition());
	}

	@Override
	public void draw(Graphics2D g) {
		int size = 2;
		g.fillRect((int) getPosition().getX(), (int) getPosition().getY(), size, size);
	}
}
