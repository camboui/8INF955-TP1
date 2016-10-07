package geom;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for {@link geom.AABB}
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class AABBTest {

	/**
	 * Test method for {@link geom.AABB#AABB()}.
	 */
	@Test
	public void testAABB() {
		AABB tester = new AABB();

		assertNotNull(tester);

		assertEquals(0, tester.getAngle(), 0);
		assertEquals(10, tester.getWidth(), 0);
		assertEquals(10, tester.getHeight(), 0);

		assertEquals(0, tester.getPosition().getX(), 0);
		assertEquals(0, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.AABB#AABB(geom.Position)}.
	 */
	@Test
	public void testAABBPosition() {
		AABB tester = new AABB(new Position(2, 3));

		assertNotNull(tester);

		assertEquals(0, tester.getAngle(), 0);
		assertEquals(10, tester.getWidth(), 0);
		assertEquals(10, tester.getHeight(), 0);

		assertEquals(2, tester.getPosition().getX(), 0);
		assertEquals(3, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.AABB#AABB(double, double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAABBdoubleExceptiondouble() {
		new AABB(-1, 10);
	}

	/**
	 * Test method for {@link geom.AABB#AABB(double, double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAABBdoubledoubleException() {
		new AABB(10, -1);
	}

	/**
	 * Test method for {@link geom.AABB#AABB(double, double)}.
	 */
	@Test
	public void testAABBdoubledouble() {
		AABB tester = new AABB(2, 3);

		assertNotNull(tester);

		assertEquals(0, tester.getAngle(), 0);
		assertEquals(2, tester.getWidth(), 0);
		assertEquals(3, tester.getHeight(), 0);

		assertEquals(0, tester.getPosition().getX(), 0);
		assertEquals(0, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.AABB#AABB(geom.Position, double, double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAABBPositiondoubleExceptiondouble() {
		new AABB(new Position(1, 1), 10, -1);
	}

	/**
	 * Test method for {@link geom.AABB#AABB(geom.Position, double, double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAABBPositiondoubledoubleException() {
		new AABB(new Position(1, 1), -1, 10);
	}

	/**
	 * Test method for {@link geom.AABB#AABB(geom.Position, double, double)}.
	 */
	@Test
	public void testAABBPositiondoubledouble() {
		AABB tester = new AABB(new Position(2, 3), 5, 15);

		assertNotNull(tester);

		assertEquals(0, tester.getAngle(), 0);
		assertEquals(5, tester.getWidth(), 0);
		assertEquals(15, tester.getHeight(), 0);

		assertEquals(2, tester.getPosition().getX(), 0);
		assertEquals(3, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.OBB#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToPoint() {
		AABB tester = new AABB(0, 0, 3, 2);

		assertTrue(tester.isCollideTo(new Point(0, 0)));
		assertTrue(tester.isCollideTo(new Point(0, 2)));
		assertTrue(tester.isCollideTo(new Point(3, 2)));
		assertTrue(tester.isCollideTo(new Point(3, 0)));

		assertTrue(tester.isCollideTo(new Point(2, 0)));
		assertTrue(tester.isCollideTo(new Point(1, 2)));

		assertTrue(tester.isCollideTo(new Point(2, 0.5)));
		assertTrue(tester.isCollideTo(new Point(3, 0.5)));
		assertTrue(tester.isCollideTo(new Point(0.5, 0.5)));

		assertFalse(tester.isCollideTo(new Point(-1, 0)));
		assertFalse(tester.isCollideTo(new Point(0, 3)));
		assertFalse(tester.isCollideTo(new Point(1, -2)));
		assertFalse(tester.isCollideTo(new Point(4, 2)));
		assertFalse(tester.isCollideTo(new Point(4, -2)));
		assertFalse(tester.isCollideTo(new Point(0, -1)));
		assertFalse(tester.isCollideTo(new Point(5, 1)));
		assertFalse(tester.isCollideTo(new Point(5, -1)));
	}

	/**
	 * Test method for {@link geom.OBB#moveTo(geom.Position)}.
	 */
	@Test
	public void testMoveTo() {
		AABB tester = new AABB();
		tester.moveTo(new Position(2, 3));
		assertEquals(2, tester.getPosition().getX(), 0);
		assertEquals(3, tester.getPosition().getY(), 0);
	}

}
