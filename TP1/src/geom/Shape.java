package geom;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import graphics.Window;

/**
 * Abstract class for the representation of 2D shapes.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 */
public abstract class Shape{

	/**
	 * The position of the shape.
	 * 
	 * @see geom.Position
	 */
	protected Position position;

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

	// TODO Faudrait trouver un truc simple pour ca
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
	 * draw the shape in the window.
	 * 
	 * @param g
	 *            The graphic window where we have to draw
	 */
	public abstract void draw(Graphics2D g);


}
