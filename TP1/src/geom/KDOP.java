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

	/**
	 * 
	 * @param p
	 * @return true if the point p is inside the polygon
	 */
	public boolean pointInside(Position p) {
		int n = this.getPoints().size();
		int i, j;
		boolean b = false;

		for (i = 0, j = n - 1; i < n; j = i++) {
			if (((getPoints().get(i).getY() > p.getY()) != (getPoints().get(j).getY() > p.getY()))
					&& (p.getX() <= (getPoints().get(j).getX() - getPoints().get(i).getX())
							* (p.getY() - getPoints().get(i).getY())
							/ (getPoints().get(j).getY() - getPoints().get(i).getY()) + getPoints().get(i).getX()))
				b = !b;
		}
		return b;
	}

	/**
	 * 
	 * @param target
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
				// https://en.wikipedia.org/wiki/Hyperplane_separation_theorem
				boolean thisConvex = isConvex();
				boolean otherConvex = kdop.isConvex();
				if (thisConvex && otherConvex) {
					return kdop_kdop(kdop);
				} else if (!thisConvex && !otherConvex) {
					List<KDOP> thisPolygons = this.toConvexKDOP();

					List<KDOP> otherPolygons = this.toConvexKDOP();
					for (KDOP kdop1 : thisPolygons) {
						for (KDOP kdop2 : otherPolygons) {
							if (kdop1.kdop_kdop(kdop2) == true)
								return true;
						}
					}
					return false;
				} else if (!thisConvex && otherConvex) {
					List<KDOP> thisPolygons = this.toConvexKDOP();
					for (KDOP kdop1 : thisPolygons) {
						if (kdop.kdop_kdop(kdop) == true)
							return true;
					}
					return false;
				} else if (thisConvex && !otherConvex) {
					List<KDOP> otherPolygons = this.toConvexKDOP();
					for (KDOP kdop1 : otherPolygons) {
						if (kdop_kdop(kdop1) == true)
							return true;
					}
					return false;
				}
			}
		}
		return false;
	}

	public List<KDOP> toConvexKDOP() {
		//TODO
		return new ArrayList<KDOP>();
	}

	public Position[] getAxes() {

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

	public double dot(Position p1, Position p2) {
		return p1.getX() * p2.getX() + p1.getY() * p2.getY();
	}

	public Position project(Position axis) {
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

	public boolean doesOverlap(Position p1, Position p2) {
		return (p1.getY() > p2.getX() || p1.getX() > p2.getY());

	}

	public double getOverlap(Position p1, Position p2) {
		return (p1.getY() < p2.getY()) ? p1.getY() - p2.getX() : p2.getY() - p1.getX();
	}

	public boolean kdop_kdop(KDOP kdop) {
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
		// MTV mtv = new MTV(smallest, overlap);
		// if we get here then we know that every axis had overlap on it
		// so we can guarantee an intersection
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
	public boolean isConvex() {
		// Cas particulier
		Position A, B, C;
		for (int i = 0; i < this.points.size() - 2; i++) {
			A = this.points.get(i); // Dernier point
			B = this.points.get(i + 1); // Premier point
			C = this.points.get(i + 2); // Deuxieme point

			if (getAngle(A, B, C) > 180)
				return false;
		}
		// Cas Particulier
		A = this.points.get(this.points.size() - 2); // Avant-dernier point
		B = this.points.get(this.points.size() - 1); // Dernier point
		C = this.points.get(0); // Premier point
		return !(getAngle(A, B, C) > 180);
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
		return (bax * bcx + bay * bcy);
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
