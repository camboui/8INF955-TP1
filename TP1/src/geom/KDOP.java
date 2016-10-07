package geom;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
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
	// TODO 3 points minimum? Heuuuu? Il sert a quoi position ici appart foutre
	// la merde?
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

	/**
	 * 
	 * @param position
	 *            The wanted position to be tested.
	 *            
	 * @return true if the point p is inside the polygon
	 */
	public boolean pointInside(Position position) {
		int n = this.getPoints().size();
		int i, j;
		boolean b = false;

		for (i = 0, j = n - 1; i < n; j = i++) {
			if (((getPoints().get(i).getY() > position.getY()) != (getPoints().get(j).getY() > position.getY()))
					&& (position.getX() <= (getPoints().get(j).getX() - getPoints().get(i).getX())
							* (position.getY() - getPoints().get(i).getY())
							/ (getPoints().get(j).getY() - getPoints().get(i).getY()) + getPoints().get(i).getX()))
				b = !b;
		}
		return b;
	}

	/**
	 * 
	 * @param target
	 *            The wanted position to be tested.
	 *            
	 * @return the minimum distance between a point and the polygon edge
	 */
	public float minDistance(Position target) {
		double current, min = Float.POSITIVE_INFINITY;
		for (int i = 0; i < points.size() - 1; i++) {
			current = target.minDistanceToLine(points.get(i), points.get(i + 1));
			if (current < min) {
				min = current;
			}
		}
		current = target.minDistanceToLine(points.get(0), points.get(points.size() - 1));
		if (current < min) {
			min = current;
		}

		return (float) min;
	}

	@Override
	public boolean isCollideTo(Shape shape) {
		if (shape instanceof Circle) {
			Circle c = (Circle) shape;
			return pointInside(c.getPosition()) || minDistance(c.getPosition()) <= c.getRadius();
		} else if (shape instanceof Point) {
			Point p = (Point) shape;
			return pointInside(p.getPosition());
		} else {
			KDOP kdop = null;
			if (shape instanceof OBB || shape instanceof AABB) {
				kdop = ((OBB) shape).toKDOP();
			} else {
				kdop = (KDOP) shape;
			}

			if (kdop != null) {
				// TODO transform into convex polygons
				// TODO apply SAT algorithm
				// https://en.wikipedia.org/wiki/Hyperplane_separation_theorem

			}

		}

		// TODO
		return false;
	}

	@Override
	public void moveTo(Position position) {
		double moveX, moveY;
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
	private double getAngle(Position a, Position b, Position c) {
		double bax = a.getX() - b.getX();
		double bay = a.getY() - b.getY();
		double bcx = c.getX() - b.getX();
		double bcy = c.getY() - b.getY();
		return Math.acos((bax * bcx + bay * bcy)
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
	 * public double getdoubleolygonConvex(){ return 0; }
	 * 
	 * public double getAreaPolygonConcave(){ return 0; }
	 * 
	 * public double getArea(){ if(this.isConvex()) return
	 * this.getAreaPolygonConvex(); return this.getAreaPolygonConcave(); }
	 */

	@Override
	public void draw(Graphics2D g) {
		// draw GeneralPath (polygon)

		if (points.size() > 0) {
			Iterator<Position> iterator = this.points.iterator();
			Position positionIt = (Position) iterator.next();

			GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, points.size());
			polygon.moveTo(positionIt.getX(), positionIt.getY());

			while (iterator.hasNext()) {
				positionIt = (Position) iterator.next();
				polygon.lineTo(positionIt.getX(), positionIt.getY());
			}

			polygon.closePath();
			g.fill(polygon);
		}
	}
}
