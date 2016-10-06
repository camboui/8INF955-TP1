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
			// return this.isInside(aabb);
			return aabb.toKDOP().pointInside(getPosition());
		} else if (shape instanceof OBB) {
			OBB obb = (OBB) shape;
			// return this.isInside(obb);
			return obb.toKDOP().pointInside(getPosition());
		} else if (shape instanceof KDOP) {
			KDOP kdop = (KDOP) shape;
			// if (kdop.isConvex()) {
			// return this.isInsideConvexPolygon(kdop);
			// } else {
			// // TODO kdop concave
			// }
			return kdop.pointInside(getPosition());
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
						* (this.getPosition().getY() - circle.getPosition().getY())) < (circle.getRadius()
								* circle.getRadius()));
	}

	/**
	 * Test method to know if a point is inside a OBB.
	 * 
	 * @param abcd
	 *            The wanted OBB to be tested on.
	 * 
	 * @return true if the point is in the OBB.
	 * 
	 * @see geom.OBB
	 */
	public boolean isInside(OBB abcd) {
		/*
		 * A------------B | | | | | | D------------C
		 * 
		 * P is the current point
		 */
		Point A = new Point(abcd.getPosition());
		Point B = new Point((abcd.getPosition().getX() + abcd.getHeight() * Math.cos(abcd.getAngle())),
				(abcd.getPosition().getY() + abcd.getHeight() * Math.sin(abcd.getAngle())));
		Point C = new Point(
				(abcd.getPosition().getX() + abcd.getDiagLength() * Math.cos(abcd.getAngle() + Math.PI / 2.0)),
				(abcd.getPosition().getY() + abcd.getDiagLength() * Math.sin(abcd.getAngle() + Math.PI / 2.0)));
		Point D = new Point((abcd.getPosition().getX() + abcd.getWidth() * Math.cos(abcd.getAngle())),
				(abcd.getPosition().getY() + abcd.getWidth() * Math.sin(abcd.getAngle())));
		/* Triangles areas */
		double areaAPD = (this.distance(A) * this.distance(D) / 2.0);
		double areaDPC = (this.distance(D) * this.distance(C) / 2.0);
		double areaCPB = (this.distance(C) * this.distance(B) / 2.0);
		double areaBPA = (this.distance(B) * this.distance(A) / 2.0);
		double sum = areaAPD + areaDPC + areaCPB + areaBPA;
		/* Rectangle area */
		double areaABCD = abcd.getArea();

		/*
		 * if the sum of triangles areas is equal to the rectangle area then the
		 * point is inside the rectangle
		 */
		return sum == areaABCD;
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

	/**
	 * Test method to know if a point is inside a OBB.
	 * 
	 * @param kdop
	 *            The wanted kdop to be tested on.
	 * 
	 * @return true if the point is in the kdop.
	 * 
	 * @see geom.OBB
	 */
	public boolean isInsideConvexPolygon(KDOP kdop) {
		double sum = 0;
		double areaPolygonConvex = 0;
		Point O = new Point(kdop.getPoints().get(0));
		Point A = new Point(kdop.getPoints().get(kdop.getPoints().size() - 1));
		Point B = new Point(kdop.getPoints().get(0));
		for (int i = 0; i < kdop.getPoints().size() - 1; i++) {
			A = B;
			B.setPosition(kdop.getPoints().get(i + 1));
			areaPolygonConvex += (O.distance(A) * O.distance(B) / 2.0);
			sum += (this.distance(A) * this.distance(B) / 2.0);
		}
		/*
		 * if the sum of triangles areas is equal to the polygon's area then the
		 * point is inside the rectangle
		 */
		return sum == areaPolygonConvex;
	}

	@Override
	public void draw(Graphics2D g) {
		int size = 2;
		g.fillRect((int) getPosition().getX(), (int) getPosition().getY(), size, size);
	}
}
