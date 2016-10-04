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
	 * @param p
	 *            The position to copy.
	 */
	public Position(Position p){
		this.x = p.getX();
		this.y = p.getY();
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
	 * 
	 * @param p
	 * @return true if p has the same x and same y
	 */
	public boolean equals(Position p){
		return (this.x == p.getX()) && (this.y == p.getY());
	}

	
	public float distance(Position p){
		return (float) Math.sqrt(
				Math.pow(this.x-p.getX(), 2)
				+ Math.pow(this.y-p.getY(), 2)				
				);
	}
}
