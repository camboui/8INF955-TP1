package geom;

/**
 * Representation of the position of a shape by it leftmost point and topmost
 * point.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 */
public class Position {

	/** The x reference point position. */
	private float x;
	/** The y reference point position. */
	private float y;

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
	public Position(float x, float y) {
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
	public float getX() {
		return x;
	}

	/**
	 * x setter.
	 *
	 * @param x
	 *            The new x reference point position.
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * y getters.
	 *
	 * @return The y reference point position.
	 */
	public float getY() {
		return y;
	}

	/**
	 * y setter.
	 *
	 * @param y
	 *            The new y reference point position.
	 */
	public void setY(float y) {
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
	public float distance(Position position) {
		return (float) Math.sqrt(Math.pow(this.x - position.getX(), 2) + Math.pow(this.y - position.getY(), 2));
	}
}
