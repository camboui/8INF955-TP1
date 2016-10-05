package geom;

/**
 * Representation of the position of a shape by it leftmost point and topmost
 * point.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 */
public class Position {

	/** The x reference point position. */
	private double x;
	/** The y reference point position. */
	private double y;

	/** Position constructor. */
	public Position() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Position constructor.
	 * 
	 * @param x
	 *            The x reference point position.
	 * @param y
	 *            The y reference point position.
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Position copy constructor.
	 * 
	 * @param position
	 *            The position to copy.
	 */
	public Position(Position position) {
		this.x = position.getX();
		this.y = position.getY();
	}

	/**
	 * x getters.
	 *
	 * @return The x reference point position.
	 */
	public double getX() {
		return x;
	}

	/**
	 * x setter.
	 *
	 * @param x
	 *            The new x reference point position.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * y getters.
	 *
	 * @return The y reference point position.
	 */
	public double getY() {
		return y;
	}

	/**
	 * y setter.
	 *
	 * @param y
	 *            The new y reference point position.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Equals method.
	 * 
	 * @param position
	 *            The position to be tested on.
	 * 
	 * @return true if p has the same x and same y
	 * 
	 * @see geom.Position
	 */
	public boolean equals(Position position) {
		return (this.x == position.getX()) && (this.y == position.getY());
	}

	/**
	 * Calculate the distance between two positions.
	 * 
	 * @param position
	 *            The wanted position for the operation
	 * 
	 * @return The distance between two positions
	 * 
	 */
	public double distance(Position position) {
		return Math.sqrt(Math.pow(this.x - position.getX(), 2) + Math.pow(this.y - position.getY(), 2));
	}

	/**
	 * 
	 * @param originRot
	 *            The origin point of the rotation
	 * @param angle
	 *            The angle of the rotation
	 * @return the rotated point according to originRot
	 */
	public Position rotation(Position originRot, double angle) {
		Position ret = new Position();
		// X' = (X - Xc) * cos(A) - (Y - Yc) * sin(A) + Xc
		ret.setX(
				(x - originRot.getX()) * Math.cos(angle) - (y - originRot.getY()) * Math.sin(angle) + originRot.getX());
		// Y' = (Y - Yc) * cos(A) + (X - Xc) * sin(A) + Yc
		ret.setY(
				(y - originRot.getY()) * Math.cos(angle) + (x - originRot.getX()) * Math.sin(angle) + originRot.getY());
		return ret;
	}
}
