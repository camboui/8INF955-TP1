package geom;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Test class for {@link geom.Circle}
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class CircleTest {

	/**
	 * Test method for {@link geom.Circle#Circle()}.
	 */
	@Test
	public void testCircle() {
		Circle tester = new Circle();
		assertNotNull(tester);
		assertEquals(0, tester.getPosition().getX(), 0);
		assertEquals(0, tester.getPosition().getY(), 0);
		assertEquals(10, tester.getRadius(), 0);
	}

	/**
	 * Test method for {@link geom.Circle#Circle(double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCircledoubleNullException() {
		new Circle(0);
	}

	/**
	 * Test method for {@link geom.Circle#Circle(double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCircledoubleNegativeException() {
		new Circle(-1);
	}

	/**
	 * Test method for {@link geom.Circle#Circle(double)}.
	 */
	@Test
	public void testCircledouble() {
		Circle tester = new Circle(5);
		assertEquals(0, tester.getPosition().getX(), 0);
		assertEquals(0, tester.getPosition().getY(), 0);
		assertEquals(5, tester.getRadius(), 0);
	}

	/**
	 * Test method for {@link geom.Circle#Circle(geom.Position, double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCirclePositionNulldoubleException() {
		new Circle(new Position(1, 1), 0);
	}

	/**
	 * Test method for {@link geom.Circle#Circle(geom.Position, double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCirclePositionNegativedoubleException() {
		new Circle(new Position(1, 1), -1);
	}

	/**
	 * Test method for {@link geom.Circle#Circle(geom.Position, double)}.
	 */
	@Test
	public void testCirclePositiondouble() {
		Circle tester = new Circle(new Position(-1, 1), 5);
		assertEquals(-1, tester.getPosition().getX(), 0);
		assertEquals(1, tester.getPosition().getY(), 0);
		assertEquals(5, tester.getRadius(), 0);
	}

	/**
	 * Test method for {@link geom.Circle#Circle(double, double, double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCircledoubledoubleNulldoubleException() {
		new Circle(1, 1, 0);
	}

	/**
	 * Test method for {@link geom.Circle#Circle(double, double, double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCircledoubledoubleNegativedoubleException() {
		new Circle(1, 1, -1);
	}

	/**
	 * Test method for {@link geom.Circle#Circle(double, double, double)}.
	 */
	@Test
	public void testCircledoubledoubledouble() {
		Circle tester = new Circle(-1, 1, 5);
		assertEquals(-1, tester.getPosition().getX(), 0);
		assertEquals(1, tester.getPosition().getY(), 0);
		assertEquals(5, tester.getRadius(), 0);
	}

	/**
	 * Test method for {@link geom.Circle#getRadius()}.
	 */
	@Test
	public void testGetRadius() {
		Circle tester = new Circle(-1, 1, 5);
		assertEquals(5, tester.getRadius(), 0);
	}

	/**
	 * Test method for {@link geom.Circle#setRadius(double)}.
	 */
	@Test
	public void testSetRadius() {
		Circle tester = new Circle(-1, 1, 5);
		tester.setRadius(6);
		assertEquals(6, tester.getRadius(), 0);
	}

	/**
	 * Test method for {@link geom.Circle#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToPoint() {
		Circle tester = new Circle(1, 0, 1);
		assertTrue(tester.isCollideTo(new Point(2, 0)));
		assertTrue(tester.isCollideTo(new Point(0, 0)));
		assertTrue(tester.isCollideTo(new Point(1, 1)));
		assertTrue(tester.isCollideTo(new Point(1, -1)));
		assertTrue(tester.isCollideTo(new Point(1.5, 0.5)));
		assertTrue(tester.isCollideTo(new Point(1.5, -0.5)));
		assertTrue(tester.isCollideTo(new Point(0.5, 0.5)));
		assertTrue(tester.isCollideTo(new Point(0.5, -0.5)));
		assertTrue(tester.isCollideTo(new Point(1, 0)));
		assertTrue(tester.isCollideTo(new Point(1.5, 0)));

		assertFalse(tester.isCollideTo(new Point(1.71, 0.71)));
		assertFalse(tester.isCollideTo(new Point(0.29, 0.71)));
		assertFalse(tester.isCollideTo(new Point(1.71, -0.71)));
		assertFalse(tester.isCollideTo(new Point(0.29, -0.71)));

		assertFalse(tester.isCollideTo(new Point(1.87, 0.5)));
		assertFalse(tester.isCollideTo(new Point(0.13, 0.5)));
		assertFalse(tester.isCollideTo(new Point(1.87, -0.5)));
		assertFalse(tester.isCollideTo(new Point(0.13, -0.5)));

		assertFalse(tester.isCollideTo(new Point(1.51, 0.87)));
		assertFalse(tester.isCollideTo(new Point(0.49, 0.87)));
		assertFalse(tester.isCollideTo(new Point(1.51, -0.87)));
		assertFalse(tester.isCollideTo(new Point(0.49, -0.87)));
	}

	/**
	 * Test method for {@link geom.Circle#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToCircle() {
		Circle tester = new Circle(1, 0, 1);
		assertTrue(tester.isCollideTo(new Circle(3, 0, 1)));
		assertTrue(tester.isCollideTo(new Circle(1, 0, 1)));
		assertTrue(tester.isCollideTo(new Circle(1, 2, 1)));
		assertTrue(tester.isCollideTo(new Circle(1, -2, 1)));

		assertTrue(tester.isCollideTo(new Circle(1, 0, 1)));
		assertTrue(tester.isCollideTo(new Circle(1, 0, 0.5)));
		assertTrue(tester.isCollideTo(new Circle(1, 0, 2)));

		assertTrue(tester.isCollideTo(new Circle(2, 0, 1)));
		assertTrue(tester.isCollideTo(new Circle(1, 1, 1)));

		assertFalse(tester.isCollideTo(new Circle(2, 1, 0.4)));
		assertFalse(tester.isCollideTo(new Circle(0, 1, 0.4)));
		assertFalse(tester.isCollideTo(new Circle(2, -1, 0.4)));
		assertFalse(tester.isCollideTo(new Circle(0, 1, 0.4)));

		assertFalse(tester.isCollideTo(new Circle(3, 2, 1.8)));
	}

	/**
	 * Test method for {@link geom.Circle#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToAABB() {
		Circle tester = new Circle(1, 0, 1);
		assertTrue(tester.isCollideTo(new AABB(2, 0, 1, 1)));
		assertTrue(tester.isCollideTo(new AABB(2, -1, 1, 1)));
		assertTrue(tester.isCollideTo(new AABB(2, -1, 1, 2)));
		assertTrue(tester.isCollideTo(new AABB(1.5, 0, 1, 2)));

		assertTrue(tester.isCollideTo(new AABB(1, 1, 1, 1)));
		assertTrue(tester.isCollideTo(new AABB(0, 1, 1, 1)));
		assertTrue(tester.isCollideTo(new AABB(1, 1, 1, 1)));
		assertTrue(tester.isCollideTo(new AABB(0.5, 1, 2, 1)));

		assertTrue(tester.isCollideTo(new AABB(1, 1, 1, 1)));
		assertTrue(tester.isCollideTo(new AABB(0, 1, 1, 1)));
		assertTrue(tester.isCollideTo(new AABB(0, 1, 2, 1)));
		assertTrue(tester.isCollideTo(new AABB(0, 0.5, 2, 1)));

		assertTrue(tester.isCollideTo(new AABB(-1, -1, 1, 1)));
		assertTrue(tester.isCollideTo(new AABB(-1, 0, 1, 1)));
		assertTrue(tester.isCollideTo(new AABB(-1, 0, 1, 2)));
		assertTrue(tester.isCollideTo(new AABB(-0.5, 0, 1, 2)));

		assertTrue(tester.isCollideTo(new AABB(0, 1, 2, 2)));
		assertTrue(tester.isCollideTo(new AABB(0.5, 0.5, 1, 1)));

		assertFalse(tester.isCollideTo(new AABB(2.1, 1, 2, 2)));
		assertFalse(tester.isCollideTo(new AABB(0, 2, 2, 0.9)));
		assertFalse(tester.isCollideTo(new AABB(-1, 1, 0.9, 2)));
		assertFalse(tester.isCollideTo(new AABB(-1.1, 2, 2, 2)));

		assertFalse(tester.isCollideTo(new AABB(1.87, 1.5, 2, 1)));
		assertFalse(tester.isCollideTo(new AABB(1.87, 0.5, 2, 1)));
		assertFalse(tester.isCollideTo(new AABB(0, 1.5, 0.13, 1)));
		assertFalse(tester.isCollideTo(new AABB(0, 0.5, 0.13, 1)));

		assertFalse(tester.isCollideTo(new AABB(1.71, 1.71, 2, 1)));
		assertFalse(tester.isCollideTo(new AABB(0, 0.71, 0.29, 1)));

		assertFalse(tester.isCollideTo(new AABB(1.51, 1.87, 2, 1)));
		assertFalse(tester.isCollideTo(new AABB(0, 0.87, 0.49, 1)));

	}

	/**
	 * Test method for {@link geom.Circle#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToOBB() {
		Circle tester = new Circle(1, 0, 1);
		assertTrue(tester.isCollideTo(new OBB(2, 0, 1, 1, 0)));
		assertTrue(tester.isCollideTo(new OBB(2, -1, 1, 1, 0)));
		assertTrue(tester.isCollideTo(new OBB(2, -1, 1, 2, 0)));
		assertTrue(tester.isCollideTo(new OBB(1.5, 0, 1, 2, 0)));

		assertTrue(tester.isCollideTo(new OBB(1, 1, 1, 1, 0)));
		assertTrue(tester.isCollideTo(new OBB(0, 1, 1, 1, 0)));
		assertTrue(tester.isCollideTo(new OBB(1, 1, 1, 1, 0)));
		assertTrue(tester.isCollideTo(new OBB(0.5, 1, 2, 1, 0)));

		assertTrue(tester.isCollideTo(new OBB(1, 1, 1, 1, 0)));
		assertTrue(tester.isCollideTo(new OBB(0, 1, 1, 1, 0)));
		assertTrue(tester.isCollideTo(new OBB(0, 1, 2, 1, 0)));
		assertTrue(tester.isCollideTo(new OBB(0, 0.5, 2, 1, 0)));

		assertTrue(tester.isCollideTo(new OBB(-1, -1, 1, 1, 0)));
		assertTrue(tester.isCollideTo(new OBB(-1, 0, 1, 1, 0)));
		assertTrue(tester.isCollideTo(new OBB(-1, 0, 1, 2, 0)));
		assertTrue(tester.isCollideTo(new OBB(-0.5, 0, 1, 2, 0)));

		assertTrue(tester.isCollideTo(new OBB(0, 1, 2, 2, 0)));
		assertTrue(tester.isCollideTo(new OBB(0.5, 0.5, 1, 1, 0)));

		assertFalse(tester.isCollideTo(new OBB(2.1, 1, 2, 2, 0)));
		assertFalse(tester.isCollideTo(new OBB(0, 2, 2, 0.9, 0)));
		assertFalse(tester.isCollideTo(new OBB(-1, 1, 0.9, 2, 0)));
		assertFalse(tester.isCollideTo(new OBB(-1.1, 2, 2, 2, 0)));

		assertFalse(tester.isCollideTo(new OBB(1.87, 1.5, 2, 1, 0)));
		assertFalse(tester.isCollideTo(new OBB(1.87, 0.5, 2, 1, 0)));
		assertFalse(tester.isCollideTo(new OBB(0, 1.5, 0.13, 1, 0)));
		assertFalse(tester.isCollideTo(new OBB(0, 0.5, 0.13, 1, 0)));

		assertFalse(tester.isCollideTo(new OBB(1.71, 1.71, 2, 1, 0)));
		assertFalse(tester.isCollideTo(new OBB(0, 0.71, 0.29, 1, 0)));

		assertFalse(tester.isCollideTo(new OBB(1.51, 1.87, 2, 1, 0)));
		assertFalse(tester.isCollideTo(new OBB(0, 0.87, 0.49, 1, 0)));
	}

	/**
	 * Test method for {@link geom.Circle#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToKDOP() {
		List<Position> positionList = new ArrayList<Position>();
		positionList.add(new Position(0, 0));
		positionList.add(new Position(0, 1));
		positionList.add(new Position(1, 2));
		positionList.add(new Position(2, 2));
		positionList.add(new Position(3, 1));
		positionList.add(new Position(2, -1));
		positionList.add(new Position(1, -2));
		positionList.add(new Position(0, -1));

		KDOP testerKDOP = new KDOP(positionList);

		assertTrue(new Circle(1, 0, 1).isCollideTo(testerKDOP));
		assertTrue(new Circle(1, 1, 1).isCollideTo(testerKDOP));
		assertTrue(new Circle(1, -1, 1).isCollideTo(testerKDOP));
		assertTrue(new Circle(-1, 0, 1).isCollideTo(testerKDOP));
		assertTrue(new Circle(0, 2, 1).isCollideTo(testerKDOP));
		assertTrue(new Circle(1, -1, 1).isCollideTo(testerKDOP));

		assertFalse(new Circle(3, 2, 0.5).isCollideTo(testerKDOP));
		assertFalse(new Circle(3, -1, 0.5).isCollideTo(testerKDOP));
	}

	/**
	 * Test method for {@link geom.Circle#moveTo(geom.Position)}.
	 */
	@Test
	public void testMoveTo() {
		Circle tester = new Circle();
		tester.moveTo(new Position(2, 3));
		assertEquals(2, tester.getPosition().getX(), 0);
		assertEquals(3, tester.getPosition().getY(), 0);
	}

}
