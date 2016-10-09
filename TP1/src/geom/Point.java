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
		return this.isInside(obb.toKDOP());
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
		int i;
		for (i = 0; i < n; i++) {
			Position A = kdop.getPoints().get(i);
			Position B;
			if (i == n - 1)
				B = kdop.getPoints().get(0);
			else
				B = kdop.getPoints().get(i + 1);
			Position D = new Position(B.getX() - A.getX(), B.getY() - A.getY());
			Position T = new Position(position.getX() - A.getX(), position.getY() - A.getY());

			double d = D.getX() * T.getY() - D.getY() * T.getX();
			if (d < 0 && kdop.isRotationClockwise || (d > 0 && !kdop.isRotationClockwise))
				return false;
		}
		return true;
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
