package hr.fer.zemris.java.hw16.jvdraw.geometry;

import java.awt.Color;
import java.awt.Point;

/**
 * Class which represents a line object in the JVDraw program.
 * 
 * @author Dinz
 *
 */
public class Line extends GeometricalObject {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -3384614481242453346L;

	/**
	 * Starting point of the line.
	 */
	private Point startingPoint;

	/**
	 * Ending point of the line.
	 */
	private Point endingPoint;

	/**
	 * Color of the line.
	 */
	private Color color;

	/**
	 * Constructs a new line.
	 * 
	 * @param startingPoint
	 *            Starting point.
	 * @param endingPoint
	 *            Ending point.
	 * @param color
	 *            Color.
	 */
	public Line(Point startingPoint, Point endingPoint, Color color) {
		this.startingPoint = startingPoint;
		this.endingPoint = endingPoint;
		this.color = color;
	}

	@Override
	public void accept(GeometricalObjectVisitor v) {
		v.visit(this);
	}

	@Override
	public GeometricalObjectEditor createGeometricalObjectEditor() {
		return new LineEditor(this);
	}

	/**
	 * Gets the line's starting point.
	 * 
	 * @return Starting point.
	 */
	public Point getStartingPoint() {
		return startingPoint;
	}

	/**
	 * Gets the line's ending point.
	 * 
	 * @return Ending point.
	 */
	public Point getEndingPoint() {
		return endingPoint;
	}

	/**
	 * Gets the line's color.
	 * 
	 * @return Color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the line starting point.
	 * 
	 * @param startingPoint
	 *            Point to be set.
	 */
	public void setStartingPoint(Point startingPoint) {
		this.startingPoint = startingPoint;
	}

	/**
	 * Sets the line ending point.
	 * 
	 * @param endingPoint
	 *            Point to be set.
	 */
	public void setEndingPoint(Point endingPoint) {
		this.endingPoint = endingPoint;
	}

	/**
	 * Sets the line's color.
	 * 
	 * @param color
	 *            Color to be set.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Produces a string output for the line object.
	 */
	public String toString() {
		return String.format("%s (%d,%d)-(%d,%d)", "Line", startingPoint.x, startingPoint.y, endingPoint.x,
				endingPoint.y);
	}

	@Override
	public String toOutput() {
		return String.format("%s %d %d %d %d %d %d %d", "LINE", startingPoint.x, startingPoint.y, endingPoint.x,
				endingPoint.y, color.getRed(), color.getGreen(), color.getBlue());
	}

}
