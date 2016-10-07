package geom;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
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
			if (!this.isConvex())
				throw new IllegalArgumentException("Polygon is not convex");
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
			if (((getPoints().get(i).getY() >= position.getY()) != (getPoints().get(j).getY() >= position.getY()))
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
			if (shape instanceof OBB) {
				kdop = ((OBB) shape).toKDOP();
			} else if (shape instanceof AABB) {
				kdop = ((AABB) shape).toKDOP();
			} else {
				kdop = (KDOP) shape;
			}

			if (kdop != null) {
				return kdop_kdop(kdop);
			}
		}
		return false;
	}

	private Position[] getAxes() {

		Position[] axes = new Position[this.getPoints().size()];
		// loop over the vertices
		for (int i = 0; i < axes.length; i++) {
			// get the current vertex
			Position p1 = this.getPoints().get(i);
			// get the next vertex
			Position p2 = this.getPoints().get(i + 1 == axes.length ? 0 : i + 1);

			// subtract the two to get the edge vector
			// TODO SOUSTRACTION de vecteur ?
			Position edge = new Position(p1.getX() - p2.getX(), p1.getY() - p2.getY());
			// get either perpendicular vector
			Position normal = new Position(edge.getY(), -edge.getX());
			// the perp method is just (x, y) => (-y, x) or (y, -x)
			axes[i] = normal;
		}
		return axes;
	}

	private double dot(Position p1, Position p2) {
		return p1.getX() * p2.getX() + p1.getY() * p2.getY();
	}

	private Position project(Position axis) {
		double min = dot(axis, this.getPoints().get(0));
		double max = min;
		for (int i = 1; i < this.getPoints().size(); i++) {
			// NOTE: the axis must be normalized to get accurate projections
			double p = dot(axis, this.getPoints().get(i));
			if (p < min) {
				min = p;
			} else if (p > max) {
				max = p;
			}
		}
		Position proj = new Position(min, max);
		return proj;
	}

	private boolean doesOverlap(Position p1, Position p2) {
		return (p1.getY() >= p2.getX() || p1.getX() >= p2.getY());

	}

	private double getOverlap(Position p1, Position p2) {
		return (p1.getY() <= p2.getY()) ? p1.getY() - p2.getX() : p2.getY() - p1.getX();
	}

	private boolean kdop_kdop(KDOP kdop) {
		double overlap = Double.POSITIVE_INFINITY;
		Position smallest = null;
		Position[] axes1 = kdop.getAxes();
		Position[] axes2 = getAxes();
		// loop over the axes1
		for (int i = 0; i < axes1.length; i++) {
			Position axis = axes1[i];
			// project both shapes onto the axis
			Position p1 = this.project(axis);
			Position p2 = kdop.project(axis);
			// do the projections overlap?
			if (!doesOverlap(p1, p2)) {
				// then we can guarantee that the shapes do not overlap
				return false;
			} else {
				// get the overlap
				double o = getOverlap(p1, p2);
				// check for minimum
				if (o < overlap) {
					// then set this one as the smallest
					overlap = o;
					smallest = axis;
				}
			}
		}
		// loop over the axes2
		for (int i = 0; i < axes2.length; i++) {
			Position axis = axes2[i];
			// project both shapes onto the axis
			Position p1 = this.project(axis);
			Position p2 = kdop.project(axis);
			// do the projections overlap?
			if (!doesOverlap(p1, p2)) {
				// then we can guarantee that the shapes do not overlap
				return false;
			} else {
				// get the overlap
				double o = getOverlap(p1, p2);
				// check for minimum
				if (o < overlap) {
					// then set this one as the smallest
					overlap = o;
					smallest = axis;
				}
			}
		}
		return true;
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
	private boolean isConvex() {
		Position A = this.points.get(this.points.size() - 1);
		Position B = this.points.get(0);
		Position C = this.points.get(1);

		double cp = crossProduct(A, B, C);
		boolean decided = (cp != 0);
		boolean isPositiv = (cp > 0);

		for (int i = 0; i < this.points.size() - 2; i++) {

			A = this.points.get(i);
			B = this.points.get(i + 1);
			C = this.points.get(i + 2);

			cp = crossProduct(A, B, C);
			if (!decided && cp > 0) {
				isPositiv = (cp > 0);
				decided = true;
			}

			if (cp != 0 && cp > 0 != isPositiv)
				return false;
		}
		A = this.points.get(this.points.size() - 2);
		B = this.points.get(this.points.size() - 1);
		C = this.points.get(0);
		cp = crossProduct(A, B, C);
		return (cp != 0 || (cp > 0) == isPositiv);
	}

	/**
	 * Calculates the cross product between vector ab and bc.
	 * 
	 * @param a
	 *            The first point.
	 * 
	 * @param b
	 *            The middle point.
	 * 
	 * @param c
	 *            The last point.
	 * 
	 * @return The cross product between vector ab and bc.
	 */
	public static double crossProduct(Position a, Position b, Position c) {
		return (b.getX() - a.getX()) * (c.getY() - b.getY()) - (b.getY() - a.getY()) * (c.getX() - b.getX());
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
