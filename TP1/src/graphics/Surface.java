package graphics;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Surface extends JPanel {

	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setPaint(new Color(150, 150, 150));

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		g2d.fillRect(30, 20, 50, 50);
		g2d.fillRect(120, 20, 90, 60);
		g2d.fillRoundRect(250, 20, 70, 60, 25, 25);

		g2d.fill(new Ellipse2D.Double(10, 100, 80, 100));
		g2d.fillArc(120, 130, 110, 100, 5, 150);
		g2d.fillOval(270, 130, 50, 50);

		// draw GeneralPath (polygon)
		int x1Points[] = { 300, 400, 300, 400 };
		int y1Points[] = { 300, 350, 350, 300 };
		GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
		polygon.moveTo(x1Points[0], y1Points[0]);

		for (int index = 1; index < x1Points.length; index++) {
			polygon.lineTo(x1Points[index], y1Points[index]);
		}

		polygon.closePath();
		g2d.setColor(Color.blue);
		g2d.fill(polygon);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		doDrawing(g);
	}
}
