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
public class PositionTest {

	/**
	 * Test method for {@link geom.Position#Position()}.
	 */
	@Test
	public void testPosition() {
		Position tester = new Position();
		assertNotNull(tester);
		assertEquals(0, tester.getX(), 0);
		assertEquals(0, tester.getY(), 0);
	}

	/**
	 * Test method for {@link geom.Position#Position(double, double)}.
	 */
	@Test
	public void testPositiondoubledouble() {
		Position tester = new Position(-1, 1);
		assertNotNull(tester);
		assertEquals(-1, tester.getX(), 0);
		assertEquals(1, tester.getY(), 0);
	}

	/**
	 * Test method for {@link geom.Position#Position(geom.Position)}.
	 */
	@Test
	public void testPositionPosition() {
		Position tester = new Position(new Position(-1, 1));
		assertNotNull(tester);
		assertEquals(-1, tester.getX(), 0);
		assertEquals(1, tester.getY(), 0);
	}

	/**
	 * Test method for {@link geom.Position#getX()}.
	 */
	@Test
	public void testGetX() {
		Position tester = new Position(-1, 1);
		assertEquals(-1, tester.getX(), 0);
	}

	/**
	 * Test method for {@link geom.Position#setX(double)}.
	 */
	@Test
	public void testSetX() {
		Position tester = new Position(-1, 1);
		tester.setX(-2);
		assertEquals(-2, tester.getX(), 0);
	}

	/**
	 * Test method for {@link geom.Position#getY()}.
	 */
	@Test
	public void testGetY() {
		Position tester = new Position(-1, 1);
		assertEquals(1, tester.getY(), 0);
	}

	/**
	 * Test method for {@link geom.Position#setY(double)}.
	 */
	@Test
	public void testSetY() {
		Position tester = new Position(-1, 1);
		tester.setX(2);
		assertEquals(2, tester.getX(), 0);
	}

	/**
	 * Test method for {@link geom.Position#equals(geom.Position)}.
	 */
	@Test
	public void testEqualsPosition() {
		Position tester = new Position(-1, 1);
		assertTrue(tester.equals(new Position(-1, 1)));
		assertFalse(tester.equals(new Position(0, 1)));
		assertFalse(tester.equals(new Position(-1, 0)));
	}

	/**
	 * Test method for {@link geom.Position#distance(geom.Position)}.
	 */
	@Test
	public void testDistance() {
		Position tester = new Position(0, 0);
		assertEquals(1, tester.distance(new Position(1, 0)), 0);
		assertEquals(1, tester.distance(new Position(-1, 0)), 0);
		assertEquals(1, tester.distance(new Position(0, 1)), 0);
		assertEquals(1, tester.distance(new Position(-1, 0)), 0);
		assertEquals(Math.sqrt(2), tester.distance(new Position(-1, 1)), 0.000001);
		assertEquals(Math.sqrt(2), tester.distance(new Position(-1, -1)), 0.000001);
		assertEquals(Math.sqrt(2), tester.distance(new Position(1, 1)), 0.000001);
		assertEquals(Math.sqrt(2), tester.distance(new Position(-1, -1)), 0.000001);
	}

}
