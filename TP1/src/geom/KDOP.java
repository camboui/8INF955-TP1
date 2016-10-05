package geom;

import java.util.Iterator;
import java.util.List;

/**
 * A k-discrete oriented polytope.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class KDOP extends Shape {

	/**
	 * The list of points representing the polytope.
	 * 
	 * @see geom.Position
	 */
	List<Position> points;

	/**
	 * KDOP constructor with a default position of (0,0).
	 * 
	 * @param points
	 *            The list of points representing the polytope.
	 * 
	 * 
	 * @throws IllegalArgumentException
	 *             If the list of points is empty.
	 */
	// TODO 3 points minimum?
	public KDOP(List<Position> points) throws IllegalArgumentException {
		super();
		if (points.isEmpty())
			throw new IllegalArgumentException("Empty list of points");
		else {
			this.points = points;
		}
	}

	/**
	 * KDOP constructor.
	 * 
	 * @param points
	 *            The list of points representing the polytope.
	 * 
	 * @param position
	 *            The wanted position of the KDOP.
	 * 
	 * @throws IllegalArgumentException
	 *             If the list of points is empty.
	 *
	 * 
	 */
	// TODO 3 points minimum?
	public KDOP(List<Position> points, Position position) throws IllegalArgumentException {
		super(position);
		if (points.isEmpty())
			throw new IllegalArgumentException("Empty list of points");
		else if (points.get(0) != position)
			throw new IllegalArgumentException("First element of the list is different than the position.");
		else {
			this.points = points;
		}
	}

	@Override
	public boolean isCollideTo(Shape shape) {
		if (shape instanceof KDOP) {
			// TODO kdoo kdop
		} else if (shape instanceof Circle) {
			// TODO kdop circle
		} else if (shape instanceof Point) {
			// TODO kdop point
		} else if (shape instanceof AABB) {
			// TODO kdop aabb
		} else if (shape instanceof OBB) {
			// TODO kdop obb
		}

		// TODO
		return false;
	}

	@Override
	public void moveTo(Position position) {
		float moveX, moveY;
		moveX = position.getX() - this.position.getX();
		moveY = position.getY() - this.position.getY();
		this.position = position;
		for (Iterator<Position> iterator = this.points.iterator(); iterator.hasNext();) {
			Position positionIt = (Position) iterator.next();
			positionIt.setX(positionIt.getX() + moveX);
			positionIt.setY(positionIt.getY() + moveY);
		}
	}

	/**
	 * Method to know if a KDOP is convex.
	 * 
	 * @return true if the KDOP is convex, false if not.
	 */
	public boolean isConvex() {
		// Cas particulier
		Position A = this.points.get(this.points.size() - 1); // Dernier point
		Position B = this.points.get(0); // Premier point
		Position C = this.points.get(1); // Deuxieme point
		for (int i = 0; i < this.points.size() - 2; i++) {
			if (getAngle(A, B, C) > Math.PI)
				return false;
			A = B;
			B = C;
			C = this.points.get(i + 1);
		}
		// Cas Particulier
		A = this.points.get(this.points.size() - 2); // Avant-dernier point
		B = this.points.get(this.points.size() - 1); // Dernier point
		C = this.points.get(0); // Premier point
		return !(getAngle(A, B, C) > Math.PI);
	}

	/**
	 * Return the angle between a,b,c. B is the middle point.
	 * 
	 * @param a
	 *            The first point.
	 * @param b
	 *            The middle point.
	 * @param c
	 *            The last point.
	 * @return The angle between a,b,c.
	 */
	private float getAngle(Position a, Position b, Position c) {
		float bax = a.getX() - b.getX();
		float bay = a.getY() - b.getY();
		float bcx = c.getX() - b.getX();
		float bcy = c.getY() - b.getY();
		return (float) Math.acos((bax * bcx + bay * bcy)
				/ (Math.sqrt(Math.pow(bax, 2) + Math.pow(bay, 2)) * Math.sqrt(Math.pow(bcx, 2) + Math.pow(bcy, 2))));
	}

	/**
	 * Method to know if a KDOP is concave.
	 * 
	 * @return true if the KDOP is concave, false if not.
	 */
	public boolean isConcave() {
		return !this.isConvex();
	}

	/**
	 * All the points of the KDOP
	 * 
	 * @return A list of positions representing the points of the KDOP.
	 */
	public List<Position> getPoints() {
		return this.points;
	}
	/*
	 * public float getAreaPolygonConvex(){ return 0; }
	 * 
	 * public float getAreaPolygonConcave(){ return 0; }
	 * 
	 * public float getArea(){ if(this.isConvex()) return
	 * this.getAreaPolygonConvex(); return this.getAreaPolygonConcave(); }
	 */

}
