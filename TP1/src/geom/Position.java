package geom;

/** 
 * Representation of the position of a shape by it leftmost point and topmost point.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 */
public class Position {
	
	/** The leftmost point. */
	private float x;
	/** The topmost point. */
	private float y;
	
	/** Position constructor. */
	public Position(){
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Position constructor. 
	 * @param x 
	 * 				The leftmost point.
	 * @param y 
	 * 				The topmost point.
	 */
	public Position(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * x getters.
	 *
	 * @return The x.
	 */
	public float getX() {
		return x;
	}

	/**
	 * x setter.
	 *
	 * @param x
	 *            The new x name.
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * y getters.
	 *
	 * @return The y.
	 */
	public float getY() {
		return y;
	}

	/**
	 * y setter.
	 *
	 * @param y
	 *            The new y name.
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	
}
