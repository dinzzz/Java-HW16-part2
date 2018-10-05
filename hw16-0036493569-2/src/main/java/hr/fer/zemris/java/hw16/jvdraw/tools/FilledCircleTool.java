package hr.fer.zemris.java.hw16.jvdraw.tools;

import java.awt.Graphics2D;

import java.awt.Point;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.geometry.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.geometry.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.providers.IColorProvider;

/**
 * Class which represents a filled circle tool, tool used to graphically
 * represent a filled circle on the canvas.
 * 
 * @author Dinz
 *
 */
public class FilledCircleTool implements Tool {

	/**
	 * Foreground color provider.
	 */
	private IColorProvider fgColor;

	/**
	 * Background color provider.
	 */
	private IColorProvider bgColor;

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
	 * Constructs a new filled circle tool.
	 * 
	 * @param fgColor
	 *            Foreground color provider.
	 * @param bgColor
	 *            Background color provider.
	 * @param model
	 *            Model.
	 */
	public FilledCircleTool(IColorProvider fgColor, IColorProvider bgColor, DrawingModel model) {
		this.fgColor = fgColor;
		this.bgColor = bgColor;
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
			model.add(new FilledCircle(startingPoint, radius, fgColor.getCurrentColor(), bgColor.getCurrentColor()));
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
			g2d.setColor(bgColor.getCurrentColor());
			g2d.fillOval(startingPoint.x - radius, startingPoint.y - radius, 2 * radius, 2 * radius);
			g2d.setColor(fgColor.getCurrentColor());
			g2d.drawOval(startingPoint.x - radius, startingPoint.y - radius, 2 * radius, 2 * radius);

		}

	}

}
