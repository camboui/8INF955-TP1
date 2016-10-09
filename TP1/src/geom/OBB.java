package geom;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A oriented bounding box.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class OBB extends Shape {

	/** The width of the box. */
	protected double width;

	/** The height of the box. */
	protected double height;

	/** The angle of the box. */
	private double angle;

	/**
	 * OBB constructor with a default width of 10, height of 10 and angle of 0
	 * and a default position of (0,0).
	 */
	public OBB() {
		super();
		this.width = 10;
		this.height = 10;
		this.angle = 0;
	}

	/**
	 * OBB constructor with a Position and a default width of 10, height of 10
	 * and angle of 0.
	 * 
	 * @param position
	 *            The wanted position of the OBB.
	 * 
	 * @see geom.Position
	 */
	public OBB(Position position) {
		super(position);
		this.width = 10;
		this.height = 10;
		this.angle = 0;
	}

	/**
	 * OBB constructor with a width height and angle and a default position of
	 * (0,0).
	 * 
	 * @param width
	 *            The wanted with of the OBB.
	 * 
	 * @param height
	 *            The wanted height of the OBB.
	 * 
	 * @param angle
	 *            The wanted angle of the OBB.
	 * 
	 * @throws IllegalArgumentException
	 *             If the width or the height are negative or null.
	 */
	public OBB(double width, double height, double angle) throws IllegalArgumentException {
		super();
		if (width <= 0)
			throw new IllegalArgumentException("width is negative or null: " + width);
		else if (height <= 0)
			throw new IllegalArgumentException("height is negative or null: " + height);
		else {
			this.width = width;
			this.height = height;
			this.angle = angle;
		}
	}

	/**
	 * OBB Constructor.
	 * 
	 * @param position
	 *            The wanted position of the OBB.
	 * 
	 * @param width
	 *            The wanted with of the OBB.
	 * 
	 * @param height
	 *            The wanted height of the OBB.
	 * 
	 * @param angle
	 *            The wanted angle of the OBB.
	 * 
	 * @throws IllegalArgumentException
	 *             If the width or the height are negative or null.
	 * 
	 * @see geom.Position
	 */
	public OBB(Position position, double width, double height, double angle) throws IllegalArgumentException {
		super(position);
		if (width <= 0)
			throw new IllegalArgumentException("width is negative or null: " + width);
		else if (height <= 0)
			throw new IllegalArgumentException("height is negative or null: " + height);
		else {
			this.width = width;
			this.height = height;
			this.angle = angle;
		}
	}

	/**
	 * OBB Constructor.
	 * 
	 * @param x
	 *            The wanted position of the x axis.
	 * 
	 * @param y
	 *            The wanted position for this y axis.
	 * 
	 * @param width
	 *            The wanted with of the OBB.
	 * 
	 * @param height
	 *            The wanted height of the OBB.
	 * 
	 * @param angle
	 *            The wanted angle of the OBB.
	 * 
	 * @throws IllegalArgumentException
	 *             If the width or the height are negative or null.
	 */
	public OBB(double x, double y, double width, double height, double angle) throws IllegalArgumentException {
		super(x, y);
		if (width <= 0)
			throw new IllegalArgumentException("width is negative or null: " + width);
		else if (height <= 0)
			throw new IllegalArgumentException("height is negative or null: " + height);
		else {
			this.width = width;
			this.height = height;
			this.angle = angle;
		}
	}

	@Override
	public boolean isCollideTo(Shape shape) {
			return toKDOP().isCollideTo(shape);
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}

	/**
	 * width getter.
	 *
	 * @return The width.
	 */
	public double getWidth() {
		return this.width;
	}

	/**
	 * width setter.
	 *
	 * @param width
	 *            The new width.
	 * 
	 * @throws IllegalArgumentException
	 *             If the width is negative or null.
	 */
	public void setWidth(double width) {
		if (width <= 0)
			throw new IllegalArgumentException("width is negative or null: " + width);
		this.width = width;
	}

	/**
	 * height getter.
	 *
	 * @return The height.
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * height setter.
	 *
	 * @param height
	 *            The new height.
	 * 
	 * @throws IllegalArgumentException
	 *             If the height is negative or null.
	 */
	public void setHeight(double height) {
		if (height <= 0)
			throw new IllegalArgumentException("width is negative or null: " + height);
		this.height = height;
	}

	/**
	 * angle getter.
	 *
	 * @return The angle.
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * angle setter.
	 *
	 * @param angle
	 *            The new angle.
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	/**
	 * Return the diagonal length.
	 * 
	 * @return The diagonal length.
	 */
	public double getDiagLength() {
		return Math.sqrt(this.height * this.height + this.width * this.width);
	}

	/**
	 * Return the area.
	 * 
	 * @return The area.
	 */
	public double getArea() {
		return this.width * this.height;
	}

	/**
	 * Transform an OBB into a KDOP. 
	 * 
	 * @return the KDOP corresponding to this OBB
	 */
	public KDOP toKDOP() {
		List<Position> points = new ArrayList<Position>();

		Position center = new Position(getPosition().getX() + getWidth() / 2, getPosition().getY() + getHeight() / 2);

		points.add(new Position(getPosition().rotation(center, getAngle())));
		points.add(new Position(getPosition().getX() + getWidth(), getPosition().getY()).rotation(center, getAngle()));
		points.add(new Position(getPosition().getX() + getWidth(), getPosition().getY() + getHeight()).rotation(center,
				getAngle()));
		points.add(new Position(getPosition().getX(), getPosition().getY() + getHeight()).rotation(center, getAngle()));

		return new KDOP(points);
	}

	@Override
	public void draw(Graphics2D g) {
		toKDOP().draw(g);
	}
}
