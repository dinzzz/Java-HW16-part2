package hr.fer.zemris.java.hw16.jvdraw.tools;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import hr.fer.zemris.java.hw16.jvdraw.geometry.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.geometry.Line;
import hr.fer.zemris.java.hw16.jvdraw.providers.IColorProvider;

/**
 * Class that represents a line tool, tool responsible to graphically represent
 * a tool on the canvas in the JVDraw program.
 * 
 * @author Dinz
 *
 */
public class LineTool implements Tool {

	/**
	 * Foreground color provider.
	 */
	private IColorProvider fgColor;

	/**
	 * Model.
	 */
	private DrawingModel model;

	/**
	 * Starting point of the line.
	 */
	private Point startingPoint;

	/**
	 * Ending point of the line.
	 */
	private Point endingPoint;

	/**
	 * Boolean which denotes if the line is set to be drawn.
	 */
	private boolean set = false;

	/**
	 * Boolean which denotes if the mouse has been clicked.
	 */
	boolean clicked = false;

	/**
	 * Constructs a new line tool.
	 * 
	 * @param fgColor
	 *            Foreground color provider.
	 * @param model
	 *            Model.
	 */
	public LineTool(IColorProvider fgColor, DrawingModel model) {
		this.fgColor = fgColor;
		this.model = model;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (clicked) {
			endingPoint = e.getPoint();
			set = false;
			clicked = false;
			model.add(new Line(startingPoint, endingPoint, fgColor.getCurrentColor()));
			return;
		}
		startingPoint = e.getPoint();
		clicked = true;
		set = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (clicked) {
			endingPoint = e.getPoint();
			set = true;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void paint(Graphics2D g2d) {
		if (set) {
			g2d.setColor(fgColor.getCurrentColor());
			g2d.drawLine(startingPoint.x, startingPoint.y, endingPoint.x, endingPoint.y);
		}
	}

	/**
	 * Sets the foreground color provider.
	 * 
	 * @param fgColor
	 *            Foreground color provider.
	 */
	public void setFgColor(IColorProvider fgColor) {
		this.fgColor = fgColor;
	}

}
