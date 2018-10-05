package hr.fer.zemris.java.hw16.jvdraw.geometry;

import java.awt.Color;
import java.awt.Point;

/**
 * Class which represents a filled circle object in the JVDraw program.
 * 
 * @author Dinz
 *
 */
public class FilledCircle extends GeometricalObject {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 4674199587366100842L;

	/**
	 * Central point of the circle.
	 */
	private Point startingPoint;

	/**
	 * Radius of the circle.
	 */
	private int radius;

	/**
	 * Color of the circle's outline.
	 */
	private Color color;

	/**
	 * Color of the circle's filling.
	 */
	private Color bgColor;

	/**
	 * Constructs a new filled circle.
	 * 
	 * @param startingPoint
	 *            Center point of the circle.
	 * @param radius
	 *            Radius.
	 * @param color
	 *            Outline color.
	 * @param bgColor
	 *            Filling color.
	 */
	public FilledCircle(Point startingPoint, int radius, Color color, Color bgColor) {
		super();
		this.startingPoint = startingPoint;
		this.radius = radius;
		this.color = color;
		this.bgColor = bgColor;
	}

	@Override
	public void accept(GeometricalObjectVisitor v) {
		v.visit(this);

	}

	@Override
	public GeometricalObjectEditor createGeometricalObjectEditor() {
		return new FilledCircleEditor(this);
	}

	/**
	 * Gets the center point of the circle.
	 * 
	 * @return Point.
	 */
	public Point getStartingPoint() {
		return startingPoint;
	}

	/**
	 * Gets the circle's radius.
	 * 
	 * @return Radius.
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Gets the circle's outline color.
	 * 
	 * @return Outline color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Gets the circle's filling color.
	 * 
	 * @return Filling color.
	 */
	public Color getBgColor() {
		return bgColor;
	}

	/**
	 * Sets the center point of the circle.
	 * 
	 * @param startingPoint
	 *            Point to be set.
	 */
	public void setStartingPoint(Point startingPoint) {
		this.startingPoint = startingPoint;
	}

	/**
	 * Sets the circle's radius.
	 * 
	 * @param radius
	 *            Radius to be set.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Sets the circle's outline color.
	 * 
	 * @param color
	 *            Color to be set.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Sets the circle's filling color.
	 * 
	 * @param bgColor
	 *            Color to be set.
	 */
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * Produces a string format of the filled circle.
	 */
	public String toString() {
		return String.format("%s (%d,%d), %d, #%02X%02X%02X", "Filled circle", startingPoint.x, startingPoint.y, radius,
				bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
	}

	@Override
	public String toOutput() {
		return String.format("%s %d %d %d %d %d %d %d %d %d", "FCIRCLE", startingPoint.x, startingPoint.y, radius,
				color.getRed(), color.getGreen(), color.getBlue(), bgColor.getRed(), bgColor.getGreen(),
				bgColor.getBlue());
	}

}
