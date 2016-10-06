package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.GameObject;


public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

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
		setSize(width + getInsets().left + getInsets().right, height + getInsets().top + getInsets().bottom);
	}

	/**
	 * Draw all the shapes in parameter
	 * 
	 * @param shapes
	 *            list of shapes to draw
	 */
	public void drawAll(List<GameObject>... go) {

		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;

				RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				g2d.setRenderingHints(rh);

				for (int i = 0; i < go.length; i++) {
					List<GameObject> currentList = go[i];

					for (GameObject current : currentList) {
						if (current.getShape().getIsColliding())
							g2d.setPaint(new Color(1.0f, 0, 0));
						else
							g2d.setPaint(new Color(0, 0, 0));
						current.getShape().draw(g2d);
					}
				}
			};
		};
		add(panel);
		setVisible(true);
	}

}
