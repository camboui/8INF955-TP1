package game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import geom.*;
import graphics.Window;

/**
 * Main Class
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class Main {

	/**
	 * Main
	 * 
	 * @param args
	 *            Args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		double start;
		double end;
		double delay;
		// set framerate
		double fps = 1000.0 / 60.0;
		int height = 500;
		int width = 500;
		Window w = new Window(height, width);

		List<GameObject> walls = createWalls(width, height);
		List<GameObject> entities = createShapes();

		// while window is open
		while (w.isActive()) {
			start = System.currentTimeMillis();

			// do logic
			applyLogic(entities, walls);

			w.drawAll(entities, walls);

			end = System.currentTimeMillis();
			delay = fps - (end - start);

			// sleep for stabilized fps
			try {
				TimeUnit.MILLISECONDS.sleep((long) delay);
			} catch (InterruptedException e) {
				System.out.println("Can't sleep :" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Apply the logic.
	 * 
	 * @param go
	 *            The game object.
	 * 
	 * @param walls
	 *            The walls.
	 */
	public static void applyLogic(List<GameObject> go, List<GameObject> walls) {
		// move every object and check collision with walls
		for (GameObject current : go) {
			if (current.getShape() instanceof OBB) {
				((OBB) current.getShape()).addAngle(Math.PI / 1000);
			}
			current.applyMove();

			if (walls.get(0).getShape().isCollideTo(current.getShape())
					|| walls.get(1).getShape().isCollideTo(current.getShape())) {
				current.reverseYspeed();
			}
			if (walls.get(2).getShape().isCollideTo(current.getShape())
					|| walls.get(3).getShape().isCollideTo(current.getShape())) {
				current.reverseXspeed();
			}
		}

		// check for collision with other gameObjects
		for (GameObject current : go) {
			List<GameObject> temp = new ArrayList<GameObject>(go);
			temp.remove(current);
			current.getShape().isCollideGo(temp);
		}
	}

	/**
	 * 
	 * @param width 
	 * 		the width of the window
	 * @param height 
	 * 		the height of the window
	 * @return the list of gameObjects describing walls
	 */
	public static List<GameObject> createWalls(int width, int height) {
		List<GameObject> walls = new ArrayList<GameObject>();
		// 0 and 1 are horizontal
		walls.add(new GameObject(new AABB(0, 0, width, 3), 0, 0));
		walls.add(new GameObject(new AABB(0, height - 3, width, 3), 0, 0));

		// 2 and 3 are vertical
		walls.add(new GameObject(new AABB(width - 3, 0, 3, height), 0, 0));
		walls.add(new GameObject(new AABB(0, 0, 3, height), 0, 0));
		return walls;
	}

	/**
	 * 
	 * @return a list full of gameObject representing different kind of Shape
	 */
	public static List<GameObject> createShapes() {
		List<GameObject> entities = new ArrayList<GameObject>();
		List<Position> points = new ArrayList<Position>();
		points.add(new Position(300, 300));
		points.add(new Position(340, 340));
		points.add(new Position(250, 300));
		points.add(new Position(210, 230));

		KDOP kdop = new KDOP(points);
		entities.add(new GameObject(kdop, -1, -1));

		List<Position> points2 = new ArrayList<Position>();
		points2.add(new Position(20, 400));
		points2.add(new Position(90, 410));
		points2.add(new Position(110, 440));
		points2.add(new Position(110, 450));
		points2.add(new Position(40, 420));

		KDOP kdop2 = new KDOP(points2);
		entities.add(new GameObject(kdop2, -1, -1));

		Circle c = new Circle(300, 190, 70);
		entities.add(new GameObject(c, 1, 1));

		Circle c2 = new Circle(100, 190, 10);
		entities.add(new GameObject(c2, -1.3f, 1.4f));

		OBB obb = new OBB(new Position(40, 20), 40, 90, Math.PI / 3);
		entities.add(new GameObject(obb, 1.2f, -1));

		AABB aabb = new AABB(350, 400, 80, 45);
		entities.add(new GameObject(aabb, -1.4f, -1.7f));

		Point point = new Point(70, 300);
		entities.add(new GameObject(point, -1.1f, -1.9f));
		return entities;
	}

}
