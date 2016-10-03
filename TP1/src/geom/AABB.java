package geom;

/**
 * A axis-aligned bounding box.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class AABB extends OBB {

	/**
	 * AABB constructor.
	 * 
	 * @see geom.OBB
	 */
	public AABB() {
		super();
	}

	/**
	 * AABB constructor with a Position and a default width of 10, height of 10
	 * and angle of 0.
	 * 
	 * @param position
	 *            The wanted position of the AABB.
	 * 
	 * @see geom.Position
	 */
	public AABB(Position position) {
		super(position);
	}

	/**
	 * OBB constructor with a width height and angle and a default position of
	 * (0,0).
	 * 
	 * @param width
	 *            The wanted with of the AABB.
	 * 
	 * @param height
	 *            The wanted height of the AABB.
	 * 
	 * @throws IllegalArgumentException
	 *             If the width or the height are negative or null.
	 */
	public AABB(float width, float height) throws IllegalArgumentException {
		super(width, height, 0);
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
	 * @throws IllegalArgumentException
	 *             If the width or the height are negative or null.
	 */
	public AABB(Position position, float width, float height) throws IllegalArgumentException {
		super(position, width, height, 0);
	}
}
