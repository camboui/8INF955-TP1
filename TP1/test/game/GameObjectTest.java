package game;

import static org.junit.Assert.*;

import org.junit.Test;

import geom.Point;

/**
 * Test class for {@link game.GameObject}
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class GameObjectTest {

	/**
	 * Test method for {@link game.GameObject#applyMove()}.
	 */
	@Test
	public void testApplyMove() {
		GameObject go = new GameObject(new Point(), 2, 3);
		go.applyMove();

		assertEquals(2, go.getShape().getPosition().getX(), 0);
		assertEquals(3, go.getShape().getPosition().getY(), 0);

		go.applyMove();

		assertEquals(4, go.getShape().getPosition().getX(), 0);
		assertEquals(6, go.getShape().getPosition().getY(), 0);
	}

}
