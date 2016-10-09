package geom;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Test class for {@link geom.KDOP}
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class KDOPTest {

	/**
	 * Test method for {@link geom.KDOP#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToPoint() {
		List<Position> positionList = new ArrayList<Position>();
		positionList.add(new Position(0, 0));
		positionList.add(new Position(0, 1));
		positionList.add(new Position(1, 2));
		positionList.add(new Position(2, 2));
		positionList.add(new Position(3, 1));
		positionList.add(new Position(2, -1));
		positionList.add(new Position(1, -2));
		positionList.add(new Position(0, -1));
		KDOP tester = new KDOP(positionList);

		assertFalse(tester.isCollideTo(new Point(0, 2)));
		assertFalse(tester.isCollideTo(new Point(0, 1.5)));
		assertFalse(tester.isCollideTo(new Point(0.5, 2)));

		assertFalse(tester.isCollideTo(new Point(3, 2)));
		assertFalse(tester.isCollideTo(new Point(3, 0)));

		assertTrue(tester.isCollideTo(new Point(1.5, 1.5)));
		assertTrue(tester.isCollideTo(new Point(1, 1)));
		assertTrue(tester.isCollideTo(new Point(2.5, 0.5)));

		assertTrue(tester.isCollideTo(new Point(0.5, 0)));
		assertTrue(tester.isCollideTo(new Point(0.5, 1)));
		assertTrue(tester.isCollideTo(new Point(1.5, 2)));
		assertTrue(tester.isCollideTo(new Point(2, 2)));
		assertTrue(tester.isCollideTo(new Point(2, 1)));
		assertTrue(tester.isCollideTo(new Point(3, 1)));
		assertTrue(tester.isCollideTo(new Point(2, 0)));
		assertTrue(tester.isCollideTo(new Point(2, -1)));
		assertTrue(tester.isCollideTo(new Point(1, -1)));
		assertTrue(tester.isCollideTo(new Point(1, 0.5)));
	}

	/**
	 * Test method for {@link geom.KDOP#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToCircle() {
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

		assertTrue(testerKDOP.isCollideTo(new Circle(1, 0, 1)));
		assertTrue(testerKDOP.isCollideTo(new Circle(1, 1, 1)));
		assertTrue(testerKDOP.isCollideTo(new Circle(1, -1, 1)));
		assertTrue(testerKDOP.isCollideTo(new Circle(-1, 0, 1)));
		assertTrue(testerKDOP.isCollideTo(new Circle(0, 2, 1)));
		assertTrue(testerKDOP.isCollideTo(new Circle(1, -1, 1)));

		assertFalse(testerKDOP.isCollideTo(new Circle(3, 2, 0.5)));
		assertFalse(testerKDOP.isCollideTo(new Circle(3, -1, 0.5)));
	}

	/**
	 * Test method for {@link geom.KDOP#isCollideTo(geom.Shape)}.
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
		KDOP tester = new KDOP(positionList);

		assertTrue(testerKDOP.isCollideTo(tester));

		positionList.clear();
		positionList.add(new Position(0, 0));
		positionList.add(new Position(-1, 0));
		positionList.add(new Position(0, -1));
		tester = new KDOP(positionList);
		assertTrue(testerKDOP.isCollideTo(tester));

	}

	/**
	 * Test method for {@link geom.KDOP#moveTo(geom.Position)}.
	 */
	@Test
	public void testMoveTo() {
		List<Position> positionList = new ArrayList<Position>();
		positionList.add(new Position(0, 0));
		positionList.add(new Position(0, 1));
		positionList.add(new Position(1, 2));
		positionList.add(new Position(2, 2));
		positionList.add(new Position(3, 1));
		positionList.add(new Position(2, -1));
		positionList.add(new Position(1, -2));
		positionList.add(new Position(0, -1));
		KDOP tester = new KDOP(positionList);

		tester.moveTo(new Position(1, 1));

		assertEquals(1, tester.getPoints().get(0).getX(), 0);
		assertEquals(1, tester.getPoints().get(0).getY(), 0);

		assertEquals(1, tester.getPoints().get(1).getX(), 0);
		assertEquals(2, tester.getPoints().get(1).getY(), 0);

		assertEquals(2, tester.getPoints().get(2).getX(), 0);
		assertEquals(3, tester.getPoints().get(2).getY(), 0);

		assertEquals(3, tester.getPoints().get(3).getX(), 0);
		assertEquals(3, tester.getPoints().get(3).getY(), 0);

		assertEquals(4, tester.getPoints().get(4).getX(), 0);
		assertEquals(2, tester.getPoints().get(4).getY(), 0);

		assertEquals(3, tester.getPoints().get(5).getX(), 0);
		assertEquals(0, tester.getPoints().get(5).getY(), 0);

		assertEquals(2, tester.getPoints().get(6).getX(), 0);
		assertEquals(-1, tester.getPoints().get(6).getY(), 0);

		assertEquals(1, tester.getPoints().get(7).getX(), 0);
		assertEquals(0, tester.getPoints().get(7).getY(), 0);
	}

	/**
	 * Test method for {@link geom.KDOP#KDOP(java.util.List)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testKDOPListOfPositionException1() {
		List<Position> positionList = new ArrayList<Position>();
		new KDOP(positionList);
	}

	/**
	 * Test method for {@link geom.KDOP#KDOP(java.util.List)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testKDOPListOfPositionException2() {
		List<Position> positionList = new ArrayList<Position>();
		positionList.add(new Position(0, 0));
		positionList.add(new Position(0, 1));
		new KDOP(positionList);
	}

	/**
	 * Test method for {@link geom.KDOP#KDOP(java.util.List)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testKDOPListOfPositionException3() {
		List<Position> positionList = new ArrayList<Position>();
		positionList.add(new Position(0, 0));
		positionList.add(new Position(0, 1));
		positionList.add(new Position(1, 2));
		positionList.add(new Position(2, 2));
		positionList.add(new Position(2, 1));
		positionList.add(new Position(3, 1));
		new KDOP(positionList);
	}

	/**
	 * Test method for {@link geom.KDOP#KDOP(java.util.List)}.
	 */
	@Test
	public void testKDOPListOfPosition() {
		List<Position> positionList = new ArrayList<Position>();
		positionList.add(new Position(0, 0));
		positionList.add(new Position(0, 1));
		positionList.add(new Position(1, 2));

		KDOP tester = new KDOP(positionList);

		assertNotNull(tester);

		assertEquals(3, tester.getPoints().size());

		assertEquals(0, tester.getPoints().get(0).getX(), 0);
		assertEquals(0, tester.getPoints().get(0).getY(), 0);

		assertEquals(0, tester.getPoints().get(1).getX(), 0);
		assertEquals(1, tester.getPoints().get(1).getY(), 0);

		assertEquals(1, tester.getPoints().get(2).getX(), 0);
		assertEquals(2, tester.getPoints().get(2).getY(), 0);
	}

	/**
	 * Test method for {@link geom.KDOP#pointInside(geom.Position)}.
	 */
	@Test
	public void testPointInside() {
		List<Position> positionList = new ArrayList<Position>();
		positionList.add(new Position(0, 0));
		positionList.add(new Position(0, 1));
		positionList.add(new Position(1, 2));
		positionList.add(new Position(2, 2));
		positionList.add(new Position(3, 1));
		positionList.add(new Position(2, -1));
		positionList.add(new Position(1, -2));
		positionList.add(new Position(0, -1));
		KDOP tester = new KDOP(positionList);

		assertTrue(tester.pointInside(new Position(2, -1)));

		assertFalse(tester.pointInside(new Position(0, 2)));
		assertFalse(tester.pointInside(new Position(0, 1.5)));
		assertFalse(tester.pointInside(new Position(0.5, 2)));

		assertFalse(tester.pointInside(new Position(3, 2)));
		assertFalse(tester.pointInside(new Position(3, 0)));

		assertTrue(tester.pointInside(new Position(1.5, 1.5)));
		assertTrue(tester.pointInside(new Position(1, 1)));
		assertTrue(tester.pointInside(new Position(2.5, 0.5)));

		assertTrue(tester.pointInside(new Position(0.5, 0)));
		assertTrue(tester.pointInside(new Position(0.5, 1)));
		assertTrue(tester.pointInside(new Position(1.5, 2)));
		assertTrue(tester.pointInside(new Position(2, 2)));
		assertTrue(tester.pointInside(new Position(2, 1)));
		assertTrue(tester.pointInside(new Position(3, 1)));
		assertTrue(tester.pointInside(new Position(2, 0)));
		assertTrue(tester.pointInside(new Position(2, -1)));
		assertTrue(tester.pointInside(new Position(1, -1)));
		assertTrue(tester.pointInside(new Position(1, 0.5)));
	}

	/**
	 * Test method for {@link geom.KDOP#minDistance(geom.Position)}.
	 */
	@Test
	public void testMinDistance() {
		List<Position> positionList = new ArrayList<Position>();
		positionList.add(new Position(0, 0));
		positionList.add(new Position(0, 1));
		positionList.add(new Position(1, 2));
		positionList.add(new Position(2, 2));
		positionList.add(new Position(3, 1));
		positionList.add(new Position(2, -1));
		positionList.add(new Position(1, -2));
		positionList.add(new Position(0, -1));
		KDOP tester = new KDOP(positionList);

		assertEquals(0.0, tester.minDistance(new Position(1, 2)), 0);
		assertEquals(0.0, tester.minDistance(new Position(0, 0)), 0);
		assertEquals(Math.sqrt(2) / 2, tester.minDistance(new Position(3, 2)), 0.00001);
		assertEquals(2.0, tester.minDistance(new Position(-2, -1)), 0);
		assertEquals(0.0, tester.minDistance(new Position(2, 2)), 0);
		assertEquals(Math.sqrt(2) / 2, tester.minDistance(new Position(2, 1)), 0.00001);

	}

}
