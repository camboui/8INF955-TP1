
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
		// Definir le framerate
		double fps = 1000.0 / 30.0;
		Window w = new Window(500, 500);

		List<Shape> shapes = new ArrayList<Shape>();
		//shapes.add(new OBB(new Position(250, 250), 100, 20,90));
		shapes.add(new Circle(100, 100, 50));
		shapes.add(new Point(0, 0));
		shapes.add(new Circle(300, 100, 100));
		shapes.add(new AABB(new Position(10, 10), 100, 20));
		
		List<Position> points = new ArrayList<Position>();
		points.add(new Position(300,300));
		points.add(new Position(350,300));
		points.add(new Position(340,340));
		points.add(new Position(250,300));
		points.add(new Position(210,230));
		KDOP kdop = new KDOP(points);
		shapes.add(kdop);

		// tant que fenêtre non fermée
		while (w.isActive()) {
			// start = prendre le temps actuel
			start = System.currentTimeMillis();

			// appliquer la logique
			// afficher
			w.drawAll(shapes);

			// end = prendre le temp actuel
			end = System.currentTimeMillis();

			// delay = fps - (end - start)
			delay = fps - (end - start);

			// pause(delay)
			try {
				TimeUnit.MILLISECONDS.sleep((long) delay);
			} catch (InterruptedException e) {
				System.out.println("Can't sleep :" + e.getMessage());
				e.printStackTrace();
			}
		}

	}

}
