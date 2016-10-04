package geom;

/**
 * A circle shape.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class Circle extends Shape {
	/** The radius of the circle. */
	protected float radius;

	/**
	 * Circle constructor with a default position of (0,0) and a radius of 10.
	 * 
	 * @see geom.Position
	 */
	public Circle() {
		super();
		this.radius = 10;
	}

	/**
	 * Circle constructor with a default center position of (0,0).
	 * 
	 * @param radius
	 *            The wanted radius of the circle.
	 * 
	 * @throws IllegalArgumentException
	 *             If the radius is null or negative.
	 * 
	 * @see geom.Position
	 */
	public Circle(float radius) throws IllegalArgumentException {
		super();
		if (radius <= 0)
			throw new IllegalArgumentException("radius is null or negative: " + radius);
		else {
			this.radius = radius;
		}
	}

	/**
	 * Circle constructor with a position and a radius.
	 * 
	 * @param position
	 *            The wanted position for the x and y center of the circle.
	 * 
	 * @param radius
	 *            The wanted radius of the circle.
	 * 
	 * @throws IllegalArgumentException
	 *             If the radius is null or negative.
	 * 
	 * @see geom.Position
	 */
	public Circle(Position position, float radius) throws IllegalArgumentException {
		super(position);
		if (radius <= 0)
			throw new IllegalArgumentException("radius is null or negative: " + radius);
		else {
			this.radius = radius;
		}
	}

	/**
	 * Circle constructor.
	 * 
	 * @param x
	 *            The wanted position for the x center of the circle.
	 * 
	 * @param y
	 *            The wanted position for the y center of the circle.
	 * 
	 * @param radius
	 *            The wanted radius of the circle.
	 * 
	 * @throws IllegalArgumentException
	 *             If the radius is null or negative.
	 * 
	 * @see geom.Position
	 */
	public Circle(float x, float y, float radius) throws IllegalArgumentException {
		super(x, y);
		if (radius <= 0)
			throw new IllegalArgumentException("radius is null or negative: " + radius);
		else {
			this.radius = radius;
		}
	}

	@Override
	public boolean isCollideTo(Shape shape) {
		if(shape instanceof Circle){
			Circle otherCircle = (Circle)shape;
			
			float radiusSum = this.getRadius() + otherCircle.getRadius();
			float dx = Math.abs(otherCircle.getPosition().getX() - this.getPosition().getX());
			float dy = Math.abs(otherCircle.getPosition().getY() - this.getPosition().getY());
			
			if(dx > radiusSum) return false;
			if(dy > radiusSum) return false;
			
			return ((radiusSum*radiusSum) >= ((dx*dx) + dy*dy));
		}
		else if(shape instanceof Point){
			Point point = (Point)shape;
			//(x-center_x)^2 + (y - center_y)^2 < radius^2
			return ((point.getPosition().getX()-this.getPosition().getX()*point.getPosition().getX()-this.getPosition().getX()
					+ point.getPosition().getY()-this.getPosition().getY()*point.getPosition().getY()-this.getPosition().getY())
					< this.getRadius()*this.getRadius());
		}
		else if(shape instanceof AABB){
			//TODO
		}
		else if(shape instanceof OBB){
			//TODO
		}
		else if(shape instanceof KDOP){
			//TODO
		}
		//TODO
		return false;
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}

	/**
	 * radius getter.
	 *
	 * @return The radius.
	 */
	public float getRadius() {
		return this.radius;
	}

	/**
	 * radius setter.
	 *
	 * @param radius
	 *            The new radius.
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}

}
