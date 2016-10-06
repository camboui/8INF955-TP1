package geom;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Quentin MACE
 *
 */
public class PointTest {

	/**
	 * Test method for {@link geom.Point#Point()}.
	 */
	@Test
	public void testPoint() {
		Point tester = new Point();
		assertNotNull(tester);
		assertEquals(0, tester.getPosition().getX(), 0);
		assertEquals(0, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.Point#Point(double, double)}.
	 */
	@Test
	public void testPointdoubledouble() {
		Point tester = new Point(-1, 1);
		assertNotNull(tester);
		assertEquals(-1, tester.getPosition().getX(), 0);
		assertEquals(1, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.Point#Point(geom.Position)}.
	 */
	@Test
	public void testPointPosition() {
		Point tester = new Point(new Position(-1, 1));
		assertNotNull(tester);
		assertEquals(-1, tester.getPosition().getX(), 0);
		assertEquals(1, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.Point#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToPoint() {
		Point tester = new Point(new Position(1, 2));
		assertTrue(tester.isCollideTo(new Point(1, 2)));

		assertFalse(tester.isCollideTo(new Point(2, 1)));
	}

	/**
	 * Test method for {@link geom.Point#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToCircle() {

		assertTrue(new Point(2, 0).isCollideTo(new Circle(1, 0, 1)));
		assertTrue(new Point(0, 0).isCollideTo(new Circle(1, 0, 1)));
		assertTrue(new Point(1, 1).isCollideTo(new Circle(1, 0, 1)));
		assertTrue(new Point(1, -1).isCollideTo(new Circle(1, 0, 1)));
		assertTrue(new Point(1.5, 0.5).isCollideTo(new Circle(1, 0, 1)));
		assertTrue(new Point(1.5, -0.5).isCollideTo(new Circle(1, 0, 1)));
		assertTrue(new Point(0.5, 0.5).isCollideTo(new Circle(1, 0, 1)));
		assertTrue(new Point(0.5, -0.5).isCollideTo(new Circle(1, 0, 1)));
		assertTrue(new Point(1, 0).isCollideTo(new Circle(1, 0, 1)));
		assertTrue(new Point(1.5, 0).isCollideTo(new Circle(1, 0, 1)));

		assertFalse(new Point(1.71, 0.71).isCollideTo(new Circle(1, 0, 1)));
		assertFalse(new Point(0.29, 0.71).isCollideTo(new Circle(1, 0, 1)));
		assertFalse(new Point(1.71, -0.71).isCollideTo(new Circle(1, 0, 1)));
		assertFalse(new Point(0.29, -0.71).isCollideTo(new Circle(1, 0, 1)));

		assertFalse(new Point(1.87, 0.5).isCollideTo(new Circle(1, 0, 1)));
		assertFalse(new Point(0.13, 0.5).isCollideTo(new Circle(1, 0, 1)));
		assertFalse(new Point(1.87, -0.5).isCollideTo(new Circle(1, 0, 1)));
		assertFalse(new Point(0.13, -0.5).isCollideTo(new Circle(1, 0, 1)));

		assertFalse(new Point(1.51, 0.87).isCollideTo(new Circle(1, 0, 1)));
		assertFalse(new Point(0.49, 0.87).isCollideTo(new Circle(1, 0, 1)));
		assertFalse(new Point(1.51, -0.87).isCollideTo(new Circle(1, 0, 1)));
		assertFalse(new Point(0.49, -0.87).isCollideTo(new Circle(1, 0, 1)));
	}

	/**
	 * Test method for {@link geom.Point#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToAABB() {

		assertTrue(new Point(1, 1).isCollideTo(new AABB(1, 1, 3, 2)));
		assertTrue(new Point(4, 1).isCollideTo(new AABB(1, 1, 3, 2)));
		assertTrue(new Point(1, -1).isCollideTo(new AABB(1, 1, 3, 2)));
		assertTrue(new Point(4, -1).isCollideTo(new AABB(1, 1, 3, 2)));

		assertTrue(new Point(2, 1).isCollideTo(new AABB(1, 1, 3, 2)));
		assertTrue(new Point(1, 0).isCollideTo(new AABB(1, 1, 3, 2)));
		assertTrue(new Point(4, 0).isCollideTo(new AABB(1, 1, 3, 2)));
		assertTrue(new Point(2, -1).isCollideTo(new AABB(1, 1, 3, 2)));

		assertTrue(new Point(2, 0).isCollideTo(new AABB(1, 1, 3, 2)));
		assertTrue(new Point(3, 0).isCollideTo(new AABB(1, 1, 3, 2)));
		assertTrue(new Point(2, 0.5).isCollideTo(new AABB(1, 1, 3, 2)));
		assertTrue(new Point(3, -0.5).isCollideTo(new AABB(1, 1, 3, 2)));

		assertFalse(new Point(0, 0).isCollideTo(new AABB(1, 1, 3, 2)));
		assertFalse(new Point(1, 2).isCollideTo(new AABB(1, 1, 3, 2)));
		assertFalse(new Point(1, -2).isCollideTo(new AABB(1, 1, 3, 2)));
		assertFalse(new Point(4, 2).isCollideTo(new AABB(1, 1, 3, 2)));
		assertFalse(new Point(4, -2).isCollideTo(new AABB(1, 1, 3, 2)));
		assertFalse(new Point(0, 1).isCollideTo(new AABB(1, 1, 3, 2)));
		assertFalse(new Point(0, -1).isCollideTo(new AABB(1, 1, 3, 2)));
		assertFalse(new Point(5, 1).isCollideTo(new AABB(1, 1, 3, 2)));
		assertFalse(new Point(5, -1).isCollideTo(new AABB(1, 1, 3, 2)));
	}

	/**
	 * Test method for {@link geom.Point#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToOBB() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Point#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToKDOP() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Point#moveTo(geom.Position)}.
	 */
	@Test
	public void testMoveTo() {
		Point tester = new Point();
		tester.moveTo(new Position(2, 3));
		assertEquals(2, tester.getPosition().getX(), 0);
		assertEquals(3, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.Point#draw(java.awt.Graphics2D)}.
	 */
	@Test
	public void testDraw() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Point#Point(geom.Point)}.
	 */
	@Test
	public void testPointPoint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Point#equals(geom.Point)}.
	 */
	@Test
	public void testEqualsPoint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Point#isInside(geom.Circle)}.
	 */
	@Test
	public void testIsInsideCircle() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Point#isInside(geom.OBB)}.
	 */
	@Test
	public void testIsInsideOBB() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Point#distance(geom.Point)}.
	 */
	@Test
	public void testDistance() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Point#isInsideConvexPolygon(geom.KDOP)}.
	 */
	@Test
	public void testIsInsideConvexPolygon() {
		fail("Not yet implemented");
	}

}
