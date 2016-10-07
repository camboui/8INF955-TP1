/**
 * 
 */
package geom;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Quentin MACE
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
		AABB tester = new AABB(1, 1, 3, 2);

		assertTrue(tester.isCollideTo(new Point(1, 1)));
		assertTrue(tester.isCollideTo(new Point(4, 1)));
		assertTrue(tester.isCollideTo(new Point(1, -1)));
		assertTrue(tester.isCollideTo(new Point(4, -1)));

		assertTrue(tester.isCollideTo(new Point(2, 1)));
		assertTrue(tester.isCollideTo(new Point(1, 0)));
		assertTrue(tester.isCollideTo(new Point(4, 0)));
		assertTrue(tester.isCollideTo(new Point(2, -1)));

		assertTrue(tester.isCollideTo(new Point(2, 0)));
		assertTrue(tester.isCollideTo(new Point(3, 0)));
		assertTrue(tester.isCollideTo(new Point(2, 0.5)));
		assertTrue(tester.isCollideTo(new Point(3, -0.5)));

		assertFalse(tester.isCollideTo((new Point(0, 0))));
		assertFalse(tester.isCollideTo((new Point(1, 2))));
		assertFalse(tester.isCollideTo((new Point(1, -2))));
		assertFalse(tester.isCollideTo((new Point(4, 2))));
		assertFalse(tester.isCollideTo((new Point(4, -2))));
		assertFalse(tester.isCollideTo((new Point(0, 1))));
		assertFalse(tester.isCollideTo((new Point(0, -1))));
		assertFalse(tester.isCollideTo((new Point(5, 1))));
		assertFalse(tester.isCollideTo((new Point(5, -1))));
	}

	/**
	 * Test method for {@link geom.OBB#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToAABB() {
		AABB tester = new AABB(0,0,2,1);
		
		assertTrue(tester.isCollideTo(new AABB(2,-1,1,1)));
		assertTrue(tester.isCollideTo(new AABB(2,1,1,1)));
		assertTrue(tester.isCollideTo(new AABB(2,2,1,3)));
		assertTrue(tester.isCollideTo(new AABB(-1,0,1,1)));
		assertTrue(tester.isCollideTo(new AABB(-1,2,1,1)));
		assertTrue(tester.isCollideTo(new AABB(-1,2,1,3)));
		
		assertTrue(tester.isCollideTo(new AABB(0.5,0.5,0.25,0.25)));
		assertTrue(tester.isCollideTo(new AABB(0.5,1.5,1,3)));
		
		assertFalse(tester.isCollideTo(new AABB(2.1,0,1,1)));
		assertFalse(tester.isCollideTo(new AABB(2.1,2,1,1)));
		assertFalse(tester.isCollideTo(new AABB(2.1,2,1,3)));
		assertFalse(tester.isCollideTo(new AABB(-1.1,0,1,1)));
		assertFalse(tester.isCollideTo(new AABB(-1.1,2,1,1)));
		assertFalse(tester.isCollideTo(new AABB(-1.1,2,1,3)));
		 
	}

	/**
	 * Test method for {@link geom.OBB#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToCircle() {
		Circle tester = new Circle(1, 0, 1);
		assertTrue(new AABB(2, 0, 1, 1).isCollideTo(tester));
		assertTrue(new AABB(2, 1, 1, 1).isCollideTo(tester));
		assertTrue(new AABB(2, 0, 1, 2).isCollideTo(tester));
		assertTrue(new AABB(1.5, 0, 1, 2).isCollideTo(tester));

		assertTrue(new AABB(1, 2, 1, 1).isCollideTo(tester));
		assertTrue(new AABB(0, 2, 1, 1).isCollideTo(tester));
		assertTrue(new AABB(0, 2, 2, 1).isCollideTo(tester));
		assertTrue(new AABB(0, 1.5, 2, 1).isCollideTo(tester));

		assertTrue(new AABB(1, 1, 1, 1).isCollideTo(tester));
		assertTrue(new AABB(0, 1, 1, 1).isCollideTo(tester));
		assertTrue(new AABB(0, 1, 2, 1).isCollideTo(tester));
		assertTrue(new AABB(0, 0.5, 2, 1).isCollideTo(tester));

		assertTrue(new AABB(-1, 1, 1, 1).isCollideTo(tester));
		assertTrue(new AABB(-1, 0, 1, 1).isCollideTo(tester));
		assertTrue(new AABB(-1, 0, 1, 2).isCollideTo(tester));
		assertTrue(new AABB(-0.5, 0, 1, 2).isCollideTo(tester));

		assertTrue(new AABB(0, 1, 2, 2).isCollideTo(tester));
		assertTrue(new AABB(0.5, 0.5, 1, 1).isCollideTo(tester));
		assertTrue(new AABB(-1, 2, 4, 4).isCollideTo(tester));

		assertFalse(new AABB(2.1, 1, 2, 2).isCollideTo(tester));
		assertFalse(new AABB(0, 2, 2, 0.9).isCollideTo(tester));
		assertFalse(new AABB(-1, 1, 0.9, 2).isCollideTo(tester));
		assertFalse(new AABB(-1.1, 2, 2, 2).isCollideTo(tester));

		assertFalse(new AABB(1.87, 1.5, 2, 1).isCollideTo(tester));
		assertFalse(new AABB(1.87, -0.5, 2, 1).isCollideTo(tester));
		assertFalse(new AABB(0, 1.5, 0.13, 1).isCollideTo(tester));
		assertFalse(new AABB(0, -0.5, 0.13, 1).isCollideTo(tester));

		assertFalse(new AABB(1.71, 1.71, 2, 1).isCollideTo(tester));
		assertFalse(new AABB(1.71, -0.71, 2, 1).isCollideTo(tester));
		assertFalse(new AABB(0, 1.71, 0.29, 1).isCollideTo(tester));
		assertFalse(new AABB(0, -0.71, 0.29, 1).isCollideTo(tester));

		assertFalse(new AABB(1.51, 1.87, 2, 1).isCollideTo(tester));
		assertFalse(new AABB(1.51, -0.87, 2, 1).isCollideTo(tester));
		assertFalse(new AABB(0, 1.87, 0.49, 1).isCollideTo(tester));
		assertFalse(new AABB(0, -0.87, 0.49, 1).isCollideTo(tester));
	}

	/**
	 * Test method for {@link geom.OBB#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToOBB() {
		
		AABB tester = new AABB(0,0,2,1);
		
		assertTrue(tester.isCollideTo(new OBB(2,-1,1,1,0)));
		assertTrue(tester.isCollideTo(new OBB(2,1,1,1,0)));
		assertTrue(tester.isCollideTo(new OBB(2,2,1,3,0)));
		assertTrue(tester.isCollideTo(new OBB(-1,0,1,1,0)));
		assertTrue(tester.isCollideTo(new OBB(-1,2,1,1,0)));
		assertTrue(tester.isCollideTo(new OBB(-1,2,1,3,0)));
		
		assertTrue(tester.isCollideTo(new OBB(3,1,1,1,90)));
		
		assertTrue(tester.isCollideTo(new OBB(0.5,0.5,0.25,0.25,0)));
		assertTrue(tester.isCollideTo(new OBB(0.5,1.5,1,3,0)));
		
		assertFalse(tester.isCollideTo(new OBB(2.1,0,1,1,0)));
		assertFalse(tester.isCollideTo(new OBB(2.1,2,1,1,0)));
		assertFalse(tester.isCollideTo(new OBB(2.1,2,1,3,0)));
		assertFalse(tester.isCollideTo(new OBB(-1.1,0,1,1,0)));
		assertFalse(tester.isCollideTo(new OBB(-1.1,2,1,1,0)));
		assertFalse(tester.isCollideTo(new OBB(-1.1,2,1,3,0)));
	}

	/**
	 * Test method for {@link geom.OBB#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToKDOP() {
		fail("Not yet implemented");
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
