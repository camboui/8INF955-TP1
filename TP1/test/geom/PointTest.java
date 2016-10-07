package geom;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		Circle tester = new Circle(1, 0, 1);

		assertTrue(new Point(2, 0).isCollideTo(tester));
		assertTrue(new Point(0, 0).isCollideTo(tester));
		assertTrue(new Point(1, 1).isCollideTo(tester));
		assertTrue(new Point(1, -1).isCollideTo(tester));
		assertTrue(new Point(1.5, 0.5).isCollideTo(tester));
		assertTrue(new Point(1.5, -0.5).isCollideTo(tester));
		assertTrue(new Point(0.5, 0.5).isCollideTo(tester));
		assertTrue(new Point(0.5, -0.5).isCollideTo(tester));
		assertTrue(new Point(1, 0).isCollideTo(tester));
		assertTrue(new Point(1.5, 0).isCollideTo(tester));

		assertFalse(new Point(1.71, 0.71).isCollideTo(tester));
		assertFalse(new Point(0.29, 0.71).isCollideTo(tester));
		assertFalse(new Point(1.71, -0.71).isCollideTo(tester));
		assertFalse(new Point(0.29, -0.71).isCollideTo(tester));

		assertFalse(new Point(1.87, 0.5).isCollideTo(tester));
		assertFalse(new Point(0.13, 0.5).isCollideTo(tester));
		assertFalse(new Point(1.87, -0.5).isCollideTo(tester));
		assertFalse(new Point(0.13, -0.5).isCollideTo(tester));

		assertFalse(new Point(1.51, 0.87).isCollideTo(tester));
		assertFalse(new Point(0.49, 0.87).isCollideTo(tester));
		assertFalse(new Point(1.51, -0.87).isCollideTo(tester));
		assertFalse(new Point(0.49, -0.87).isCollideTo(tester));
	}

	/**
	 * Test method for {@link geom.Point#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToAABB() {

		AABB tester = new AABB(0, 0, 3, 2);

		assertTrue(new Point(0, 0).isCollideTo(tester));
		assertTrue(new Point(0, 2).isCollideTo(tester));
		assertTrue(new Point(3, 2).isCollideTo(tester));
		assertTrue(new Point(3, 0).isCollideTo(tester));

		assertTrue(new Point(2, 0).isCollideTo(tester));
		assertTrue(new Point(1, 2).isCollideTo(tester));

		assertTrue(new Point(2, 0.5).isCollideTo(tester));
		assertTrue(new Point(3, 0.5).isCollideTo(tester));
		assertTrue(new Point(0.5, 0.5).isCollideTo(tester));

		assertFalse(new Point(-1, 0).isCollideTo(tester));
		assertFalse(new Point(0, 3).isCollideTo(tester));
		assertFalse(new Point(1, -2).isCollideTo(tester));
		assertFalse(new Point(4, 2).isCollideTo(tester));
		assertFalse(new Point(4, -2).isCollideTo(tester));
		assertFalse(new Point(0, -1).isCollideTo(tester));
		assertFalse(new Point(5, 1).isCollideTo(tester));
		assertFalse(new Point(5, -1).isCollideTo(tester));
	}

	/**
	 * Test method for {@link geom.Point#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToOBB() {

		OBB tester = new OBB(0, 0, 3, 2, 0);

		assertTrue(new Point(0, 0).isCollideTo(tester));
		assertTrue(new Point(0, 2).isCollideTo(tester));
		assertTrue(new Point(3, 2).isCollideTo(tester));
		assertTrue(new Point(3, 0).isCollideTo(tester));

		assertTrue(new Point(2, 0).isCollideTo(tester));
		assertTrue(new Point(1, 2).isCollideTo(tester));

		assertTrue(new Point(2, 0.5).isCollideTo(tester));
		assertTrue(new Point(3, 0.5).isCollideTo(tester));
		assertTrue(new Point(0.5, 0.5).isCollideTo(tester));

		assertFalse(new Point(-1, 0).isCollideTo(tester));
		assertFalse(new Point(0, 3).isCollideTo(tester));
		assertFalse(new Point(1, -2).isCollideTo(tester));
		assertFalse(new Point(4, 2).isCollideTo(tester));
		assertFalse(new Point(4, -2).isCollideTo(tester));
		assertFalse(new Point(0, -1).isCollideTo(tester));
		assertFalse(new Point(5, 1).isCollideTo(tester));
		assertFalse(new Point(5, -1).isCollideTo(tester));

		tester = new OBB(0, 0, 3, 1, Math.PI / 4);

		assertTrue(new Point(0, 0).isCollideTo(tester));
		assertTrue(new Point(1, 1).isCollideTo(tester));
		assertTrue(new Point(0, 1.41).isCollideTo(tester));

		assertFalse(new Point(1.1, 1).isCollideTo(tester));
		assertFalse(new Point(Math.cos(-Math.PI / 4) - Math.sin(-Math.PI / 4),
				Math.cos(-Math.PI / 4) + Math.sin(-Math.PI / 4)).isCollideTo(tester));
		assertFalse(new Point(0, 1.42).isCollideTo(tester));
	}

	/**
	 * Test method for {@link geom.Point#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToKDOP() {
		List<Position> positionList = new ArrayList<Position>();
		positionList.add(new Position(0, 0));
		positionList.add(new Position(0, 1));
		positionList.add(new Position(1, 2));
		positionList.add(new Position(2, 2));
		positionList.add(new Position(2, 1));
		positionList.add(new Position(3, 1));
		positionList.add(new Position(2, 0));
		positionList.add(new Position(2, -1));
		positionList.add(new Position(1, -2));
		positionList.add(new Position(0, -1));

		KDOP tester = new KDOP(positionList);

		assertFalse(new Point(0, 2).isCollideTo(tester));
		assertFalse(new Point(0, 1.5).isCollideTo(tester));
		assertFalse(new Point(0.5, 2).isCollideTo(tester));
		assertFalse(new Point(3, 2).isCollideTo(tester));
		assertFalse(new Point(3, 0).isCollideTo(tester));

		assertTrue(new Point(1.5, 1.5).isCollideTo(tester));
		assertTrue(new Point(1, 1).isCollideTo(tester));
		assertTrue(new Point(2.5, 0.5).isCollideTo(tester));

		assertTrue(new Point(0, 0).isCollideTo(tester));
		assertTrue(new Point(0, 1).isCollideTo(tester));
		assertTrue(new Point(1, 2).isCollideTo(tester));
		assertTrue(new Point(2, 2).isCollideTo(tester));
		assertTrue(new Point(2, 1).isCollideTo(tester));
		assertTrue(new Point(3, 1).isCollideTo(tester));
		assertTrue(new Point(2, 0).isCollideTo(tester));
		assertTrue(new Point(2, -1).isCollideTo(tester));
		assertTrue(new Point(1, -2).isCollideTo(tester));
		assertTrue(new Point(0, -1).isCollideTo(tester));

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
	 * Test method for {@link geom.Point#distance(geom.Point)}.
	 */
	@Test
	public void testDistance() {

		Point tester = new Point(1, 1);

		assertEquals(1, tester.distance(new Point(2, 1)), 0);
		assertEquals(Math.sqrt(2), tester.distance(new Point(2, 2)), 0);
	}

}
