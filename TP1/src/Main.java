
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import geom.*;
import graphics.Window;

public class Main {

	public static void main(String[] args) {

		double start;
		double end;
		double delay;
		// set framerate
		double fps = 1000.0 / 60.0;
		Window w = new Window(500, 500);

		List<Shape> shapes = new ArrayList<Shape>();

		OBB obb = new OBB(new Position(150, 250), 100, 20, Math.PI / 3.0);
		// shapes.add(obb);

		Circle sun = new Circle(100, 100, 50);
		Circle planet = (new Circle(40, 40, 30));
		shapes.add(planet);
		shapes.add(sun);
		shapes.add(new Circle(250, 250, 100));

		// shapes.add(new Circle(300, 100, 100));
		// shapes.add(new AABB(new Position(10, 10), 100, 20));
		// shapes.add(new AABB(new Position(10, 10), 10, 200));
		// shapes.add(new AABB(new Position(100, 200), 100, 20));

		// List<Position> points = new ArrayList<Position>();
		// points.add(new Position(300, 300));
		// points.add(new Position(350, 300));
		// points.add(new Position(340, 340));
		// points.add(new Position(250, 300));
		// points.add(new Position(210, 230));
		// KDOP kdop = new KDOP(points);
		// shapes.add(kdop);

		ApplyLogic(shapes);

		// while window is open
		while (w.isActive()) {
			start = System.currentTimeMillis();

			// do logic
			ApplyLogic(shapes);

			obb.setAngle(obb.getAngle() + 0.1f);
			planet.setPosition(planet.getPosition().rotation(sun.getPosition(), 0.05f));

			w.drawAll(shapes);
			end = System.currentTimeMillis();
			delay = fps - (end - start);

			// sleep for stabilized 30 fps
			try {
				TimeUnit.MILLISECONDS.sleep((long) delay);
			} catch (InterruptedException e) {
				System.out.println("Can't sleep :" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void ApplyLogic(List<Shape> shapes) {
		for (Shape current : shapes) {
			List<Shape> temp = new ArrayList<Shape>(shapes);
			temp.remove(current);

//			for (Shape shape : temp) {
//				System.out.println(
//						"will compare " + current.getClass().getName() + " with " + shape.getClass().getName());
//			}
			// System.out.println(current.isCollide(temp));
			current.isCollide(temp);
		}
	}

}
