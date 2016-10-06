package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import geom.Circle;
import geom.Shape;

public class Window extends JFrame {

	/**
	 * Creates a new window
	 * 
	 * @param width
	 *            the width of the window in pixels
	 * @param height
	 *            the height of the window in pixels
	 */
	public Window(int width, int height) {
		setTitle("TP1");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(width+getInsets().left+getInsets().right, height+getInsets().top+getInsets().bottom);
	}

	/**
	 * Draw all the shapes in parameter
	 * 
	 * @param shapes
	 *            list of shapes to draw
	 */
	public void drawAll(List<Shape> shapes) {

		JPanel panel = new JPanel() {
	        @Override
	        public Dimension getPreferredSize() {
	            return new Dimension(500, 500);
	        }
	        
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;

				RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				g2d.setRenderingHints(rh);

				for (Shape current : shapes) {
					if (current.getIsColliding())
						g2d.setPaint(new Color(1.0f, 0, 0));
					else
						g2d.setPaint(new Color(0, 0, 0));
					current.draw(g2d);
				}
			};
		};
		add(panel);
		setVisible(true);
	}

}
