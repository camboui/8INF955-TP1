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
public class PointTest {

	/**
	 * Test method for {@link geom.Point#Point()}.
	 */
	@Test
	public void testPoint() {
		Point tester = new Point();
		assertNotNull(tester);
		assertEquals(0,tester.getPosition().getX(),0);
		assertEquals(0,tester.getPosition().getY(),0);
	}

	/**
	 * Test method for {@link geom.Point#Point(double, double)}.
	 */
	@Test
	public void testPointdoubledouble() {
		Point tester = new Point(-1,1);
		assertNotNull(tester);
		assertEquals(-1,tester.getPosition().getX(),0);
		assertEquals(1,tester.getPosition().getY(),0);
	}

	/**
	 * Test method for {@link geom.Point#Point(geom.Position)}.
	 */
	@Test
	public void testPointPosition() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Point#isCollideTo(geom.Shape)}.
	 */
	@Test
	public void testIsCollideTo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Point#moveTo(geom.Position)}.
	 */
	@Test
	public void testMoveTo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geom.Shape#isCollide(java.util.List)}.
	 */
	@Test
	public void testIsCollide() {
		fail("Not yet implemented");
	}

}
