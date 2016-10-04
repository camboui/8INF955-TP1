package geom;

import java.util.Iterator;
import java.util.List;

/**
 * Abstract class for the representation of 2D shapes.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 */
public abstract class Shape {

	/**
	 * The position of the shape.
	 * 
	 * @see geom.Position
	 */
	protected Position position;

	/** The leftmost point. */
	protected float left;
	/** The topmost point. */
	protected float top;
	/** The rightmost point. */
	protected float right;
	/** The bottommost point. */
	protected float bottom;

	/** Shape constructor. */
	public Shape() {
		this.position = new Position();
	}

	/**
	 * Shape constructor.
	 * 
	 * @param x
	 *            The wanted position for the x axis.
	 * 
	 * @param y
	 *            The wanted position for the y axis.
	 */
	public Shape(float x, float y) {
		this.position = new Position(x, y);
	}

	/**
	 * Shape constructor.
	 * 
	 * @param position
	 *            The wanted position of the Shape.
	 * 
	 * @see geom.Position
	 */
	public Shape(Position position) {
		this.position = position;
	}

	/**
	 * Indicate if a shape is collide to another.
	 * 
	 * @param shape
	 *            The other shape that should be tested for the collision.
	 * 
	 * @return True if the two shapes collide, false if they don't.
	 */
	public abstract boolean isCollideTo(Shape shape);

	// TODO J'vois pas trop l'utilité ce truc la @Quentin
	/**
	 * Indicate if a shape is collide to one shape in list of shapes.
	 * 
	 * @param shapes
	 *            The list of shapes that should be tested for the collision.
	 * 
	 * @return True of the shape collide with one on the list.
	 */
	public boolean isCollide(List<Shape> shapes) {
		boolean isCollide = false;
		for (Iterator<Shape> iterator = shapes.iterator(); iterator.hasNext();) {
			Shape shape = (Shape) iterator.next();
			isCollide |= this.isCollideTo(shape);
		}
		return isCollide;
	}

	/**
	 * Move the shape to another position.
	 * 
	 * @param position
	 *            The new wanted position
	 */
	public abstract void moveTo(Position position);

	/**
	 * position getter.
	 *
	 * @return The position.
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * position setter.
	 *
	 * @param position
	 *            The new position.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * position setter.
	 *
	 * @param x
	 *            The new wanted value for the x axis.
	 * 
	 * @param y
	 *            The new wanted value for the y axis.
	 */
	public void setPosition(float x, float y) {
		this.position = new Position(x, y);
	}

	/**
	 * leftmost point getter.
	 *
	 * @return The leftmost point.
	 */
	public float getLeft() {
		return this.left;
	}

	/**
	 * left setter.
	 *
	 * @param left
	 *            The new leftmost point.
	 */
	public void setLeft(float left) {
		this.left = left;
	}

	/**
	 * topmost point getter.
	 *
	 * @return The topmost.
	 */
	public float getTop() {
		return this.top;
	}

	/**
	 * topmost setter.
	 *
	 * @param top
	 *            The new topmost.
	 */
	public void setTop(float top) {
		this.top = top;
	}

	/**
	 * rightmost getter.
	 *
	 * @return The rightmost.
	 */
	public float getRight() {
		return this.right;
	}

	/**
	 * rightmost setter.
	 *
	 * @param right
	 *            The new rightmost.
	 */
	public void setRight(float right) {
		this.right = right;
	}

	/**
	 * bottommost getter.
	 *
	 * @return The bottommost.
	 */
	public float getBottom() {
		return this.bottom;
	}

	/**
	 * bottommost setter.
	 *
	 * @param bottom
	 *            The new bottommost.
	 */
	public void setBottom(float bottom) {
		this.bottom = bottom;
	}

}
