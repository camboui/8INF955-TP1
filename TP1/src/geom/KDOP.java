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
	
	public boolean isConvex(){
		boolean isConvex = false;
		for (Iterator<Position> iterator = this.points.iterator(); iterator.hasNext();) {
			Position A = (Position) iterator.next();
			Position B = (Position) iterator.next();
			Position C = (Position) iterator.next();
			
		}
		return isConvex;
	}
	
	public boolean isConcave(){
		return !this.isConvex();
	}
	
}
