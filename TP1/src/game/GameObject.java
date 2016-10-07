package game;

import geom.*;

/**
 * Used to display objects in the windows.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class GameObject {

	/**
	 * The shape to be displayed.
	 * 
	 * @see geom.Shape
	 */
	private Shape shape;

	/** The speed of the shape on the x axis. */
	private float xSpeed;
	/** The speed of the shape on the y axis. */
	private float ySpeed;

	/**
	 * Constructor.
	 * 
	 * @param shape
	 *            The wanted shape.
	 * 
	 * @param xSpeed
	 *            The wanted speed for the x axis.
	 * 
	 * @param ySpeed
	 *            The wanted speed for the y axis.
	 * 
	 * @see geom.Shape
	 */
	public GameObject(Shape shape, float xSpeed, float ySpeed) {
		this.setShape(shape);
		this.setxSpeed(xSpeed);
		this.setySpeed(ySpeed);
	}

	/** Move the object in the windows. */
	public void applyMove() {
		Position p = new Position(shape.getPosition().getX() + getxSpeed(), shape.getPosition().getY() + getySpeed());
		shape.moveTo(p);
	}

	/** Reverse the speed of the x axis. */
	public void reverseXspeed() {
		this.xSpeed = -this.xSpeed;
	}

	/** Reverse the speed of the y axis. */
	public void reverseYspeed() {
		this.ySpeed = -this.ySpeed;
	}

	/**
	 * @return the shape
	 */
	public Shape getShape() {
		return shape;
	}

	/**
	 * @param shape
	 *            the shape to set
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	/**
	 * @return the xSpeed
	 */
	public float getxSpeed() {
		return xSpeed;
	}

	/**
	 * @param xSpeed
	 *            the xSpeed to set
	 */
	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	/**
	 * @return the ySpeed
	 */
	public float getySpeed() {
		return ySpeed;
	}

	/**
	 * @param ySpeed
	 *            the ySpeed to set
	 */
	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}

}
