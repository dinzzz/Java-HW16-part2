package hr.fer.zemris.java.hw16.jvdraw.tools;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.geometry.Circle;
import hr.fer.zemris.java.hw16.jvdraw.geometry.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.providers.IColorProvider;

/**
 * Class which represents a circle tool, tool used to graphically represent a
 * circle on the canvas.
 * 
 * @author Dinz
 *
 */
public class CircleTool implements Tool {

	/**
	 * Foreground color provider.
	 */
	private IColorProvider fgColor;

	/**
	 * Model.
	 */
	private DrawingModel model;

	/**
	 * Circle's starting point.
	 */
	private Point startingPoint;

	/**
	 * Radius of the circle.
	 */
	private int radius;

	/**
	 * Flag that denotes if the mouse has been clicked.
	 */
	boolean clicked = false;

	/**
	 * Constructs a new circle tool.
	 * 
	 * @param fgColor
	 *            Foreground color provider.
	 * @param model
	 *            Model.
	 */
	public CircleTool(IColorProvider fgColor, DrawingModel model) {
		this.fgColor = fgColor;
		this.model = model;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (clicked) {
			Point end = e.getPoint();
			radius = (int) Point.distance(startingPoint.x, startingPoint.y, end.x, end.y);
			model.add(new Circle(startingPoint, radius, fgColor.getCurrentColor()));
			clicked = false;
			return;
		}

		startingPoint = e.getPoint();
		clicked = true;

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (clicked) {
			Point end = e.getPoint();
			radius = (int) Point.distance(startingPoint.x, startingPoint.y, end.x, end.y);
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics2D g2d) {
		if (clicked) {
			g2d.setColor(fgColor.getCurrentColor());
			g2d.drawOval(startingPoint.x - radius, startingPoint.y - radius, 2 * radius, 2 * radius);
		}
	}

}
