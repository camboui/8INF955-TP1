package geom;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * Test class for {@link geom.OBB}
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class OBBTest {

	/**
	 * Test method for {@link geom.OBB#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideToPoint() {
		OBB tester = new OBB(0, 0, 3, 2, 0);

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

		tester = new OBB(0, 0, 3, 1, Math.PI / 4);
		Position center = new Position(tester.getWidth() / 2, tester.getHeight() / 2);

		Point p = new Point(0, 0);
		p.setPosition(p.getPosition().rotation(center, Math.PI / 4));
		assertTrue(tester.isCollideTo(p));

		p = new Point(3, 0);
		p.setPosition(p.getPosition().rotation(center, Math.PI / 4));
		assertTrue(tester.isCollideTo(p));

		p = new Point(0, 1);
		p.setPosition(p.getPosition().rotation(center, Math.PI / 4));
		assertTrue(tester.isCollideTo(p));

		p = new Point(3, 1);
		p.setPosition(p.getPosition().rotation(center, Math.PI / 4));
		assertTrue(tester.isCollideTo(p));

		p = new Point(center);
		p.setPosition(p.getPosition().rotation(center, Math.PI / 4));
		assertTrue(tester.isCollideTo(p));

		assertFalse(tester.isCollideTo(new Point(3, 0)));
		assertFalse(tester.isCollideTo(new Point(0, 1)));
		assertFalse(tester.isCollideTo(new Point(3, 1)));

	}

	/**
	 * Test method for {@link geom.OBB#moveTo(geom.Position)}.
	 */
	@Test
	public void testMoveTo() {
		OBB tester = new OBB();
		tester.moveTo(new Position(2, 3));
		assertEquals(2, tester.getPosition().getX(), 0);
		assertEquals(3, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.OBB#OBB()}.
	 */
	@Test
	public void testOBB() {
		OBB tester = new OBB();

		assertNotNull(tester);

		assertEquals(0, tester.getAngle(), 0);
		assertEquals(10, tester.getWidth(), 0);
		assertEquals(10, tester.getHeight(), 0);

		assertEquals(0, tester.getPosition().getX(), 0);
		assertEquals(0, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.OBB#OBB(geom.Position)}.
	 */
	@Test
	public void testOBBPosition() {
		OBB tester = new OBB(new Position(2, 3));

		assertNotNull(tester);

		assertEquals(0, tester.getAngle(), 0);
		assertEquals(10, tester.getWidth(), 0);
		assertEquals(10, tester.getHeight(), 0);

		assertEquals(2, tester.getPosition().getX(), 0);
		assertEquals(3, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.OBB#OBB(double, double, double)}.
	 */
	@Test
	public void testOBBDoubleDoubleDouble() {
		OBB tester = new OBB(5, 15, 10);

		assertNotNull(tester);

		assertEquals(10, tester.getAngle(), 0);
		assertEquals(5, tester.getWidth(), 0);
		assertEquals(15, tester.getHeight(), 0);

		assertEquals(0, tester.getPosition().getX(), 0);
		assertEquals(0, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for
	 * {@link geom.OBB#OBB(geom.Position, double, double, double)}.
	 */
	@Test
	public void testOBBPositionDoubleDoubleDouble() {
		OBB tester = new OBB(new Position(2, 3), 5, 15, 10);

		assertNotNull(tester);

		assertEquals(10, tester.getAngle(), 0);
		assertEquals(5, tester.getWidth(), 0);
		assertEquals(15, tester.getHeight(), 0);

		assertEquals(2, tester.getPosition().getX(), 0);
		assertEquals(3, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for
	 * {@link geom.OBB#OBB(double, double, double, double, double)}.
	 */
	@Test
	public void testOBBDoubleDoubleDoubleDoubleDouble() {
		OBB tester = new OBB(2, 3, 5, 15, 10);

		assertNotNull(tester);

		assertEquals(10, tester.getAngle(), 0);
		assertEquals(5, tester.getWidth(), 0);
		assertEquals(15, tester.getHeight(), 0);

		assertEquals(2, tester.getPosition().getX(), 0);
		assertEquals(3, tester.getPosition().getY(), 0);
	}

	/**
	 * Test method for {@link geom.OBB#setWidth(double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetWidth() {
		new OBB().setWidth(-1);
	}

	/**
	 * Test method for {@link geom.OBB#setHeight(double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetHeight() {
		new OBB().setHeight(-1);
	}

	/**
	 * Test method for {@link geom.OBB#getDiagLength()}.
	 */
	@Test
	public void testGetDiagLength() {
		OBB tester = new OBB(0, 0, 1, 1, 0);
		assertEquals(Math.sqrt(2), tester.getDiagLength(), 0);

	}

	/**
	 * Test method for {@link geom.OBB#getArea()}.
	 */
	@Test
	public void testGetArea() {
		OBB tester = new OBB(0, 0, 2, 3, 0);
		assertEquals(6, tester.getArea(), 0);
	}

	/**
	 * Test method for {@link geom.OBB#toKDOP()}.
	 */
	@Test
	public void testToKDOP() {
		OBB tester = new OBB(0, 0, 1, 1, 0);
		List<Position> positionList = tester.toKDOP().getPoints();
		assertEquals(4, positionList.size());

		assertEquals(0, positionList.get(0).getX(), 0);
		assertEquals(0, positionList.get(0).getY(), 0);

		assertEquals(1, positionList.get(1).getX(), 0);
		assertEquals(0, positionList.get(1).getY(), 0);

		assertEquals(1, positionList.get(2).getX(), 0);
		assertEquals(1, positionList.get(2).getY(), 0);

		assertEquals(0, positionList.get(3).getX(), 0);
		assertEquals(1, positionList.get(3).getY(), 0);
	}

}
