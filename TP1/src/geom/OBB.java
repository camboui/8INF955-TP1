package geom;

/**
 * A oriented bounding box.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class OBB extends Shape {

	/** The width of the box. */
	protected float width;

	/** The height of the box. */
	protected float height;

	/** The angle of the box. */
	protected float angle;

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
	public OBB(float width, float height, float angle) throws IllegalArgumentException {
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
	 */
	public OBB(Position position, float width, float height, float angle) throws IllegalArgumentException {
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

	@Override
	public boolean isCollideTo(Shape shape) {
		if(shape instanceof Circle){
				//TODO
		}
		else if(shape instanceof Point){
			//TODO
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
}
