package hr.fer.zemris.java.hw16.jvdraw.geometry;

import java.awt.Color;
import java.awt.Point;

/**
 * Class which represents a circle in the JVDraw program.
 * 
 * @author Dinz
 *
 */
public class Circle extends GeometricalObject {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 4674099587366100842L;

	/**
	 * Center point of the circle.
	 */
	private Point startingPoint;

	/**
	 * Circle's radius.
	 */
	private int radius;

	/**
	 * Color of the circle.
	 */
	private Color color;

	/**
	 * Constructs a new circle from the center point, radius and color.
	 * 
	 * @param startingPoint
	 *            Center point.
	 * @param radius
	 *            Radius.
	 * @param color
	 *            Color.
	 */
	public Circle(Point startingPoint, int radius, Color color) {
		super();
		this.startingPoint = startingPoint;
		this.radius = radius;
		this.color = color;
	}

	/**
	 * Gets circle's center point.
	 * 
	 * @return Center point.
	 */
	public Point getStartingPoint() {
		return startingPoint;
	}

	/**
	 * Gets circle's radius.
	 * 
	 * @return Radius.
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Gets the color of the circle.
	 * 
	 * @return Color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the circle's center point.
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
	 * Sets the circle's color.
	 * 
	 * @param color
	 *            Color to be set.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void accept(GeometricalObjectVisitor v) {
		v.visit(this);

	}

	@Override
	public GeometricalObjectEditor createGeometricalObjectEditor() {
		return new CircleEditor(this);
	}

	/**
	 * Produces a string format of the circle.
	 */
	public String toString() {
		return String.format("%s (%d,%d), %d", "Circle", startingPoint.x, startingPoint.y, radius);
	}

	@Override
	public String toOutput() {
		return String.format("%s %d %d %d %d %d %d", "CIRCLE", startingPoint.x, startingPoint.y, radius, color.getRed(),
				color.getGreen(), color.getBlue());
	}

}
