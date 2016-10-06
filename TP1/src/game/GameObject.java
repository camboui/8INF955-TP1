package game;

import geom.*;

public class GameObject {

	private Shape shape;

	private float xSpeed;
	private float ySpeed;

	public GameObject(Shape s, float xs, float ys) {
		this.setShape(s);
		this.setxSpeed(xs);
		this.setySpeed(ys);
	}

	public void applyMove() {
		Position p = new Position(shape.getPosition().getX() + getxSpeed(), shape.getPosition().getY() + getySpeed());
		shape.moveTo(p);
	}

	public void reverseXspeed() {
		this.xSpeed = -this.xSpeed;
	}

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
