package hr.fer.zemris.java.hw16.jvdraw.geometry;

import java.awt.Rectangle;

/**
 * Class which represents a bounding box calculator in the JVDraw program which
 * calculates and creates a rectangle which covers all the object in the canvas.
 * 
 * @author Dinz
 *
 */
public class GeometricalObjectBBCalculator implements GeometricalObjectVisitor {

	/**
	 * Minimum Y coordinate.
	 */
	int minY = Integer.MAX_VALUE;

	/**
	 * Minimum X coordinate.
	 */
	int minX = Integer.MAX_VALUE;

	/**
	 * Maximum Y coordinate.
	 */
	int maxY = Integer.MIN_VALUE;

	/**
	 * Maximum X coordinate.
	 */
	int maxX = Integer.MIN_VALUE;

	/**
	 * Flag which denotes if the rectangle is valid.
	 */
	boolean set = false;

	@Override
	public void visit(Line line) {
		int lineMaxY;
		int lineMaxX;
		int lineMinY;
		int lineMinX;

		if (line.getStartingPoint().x > line.getEndingPoint().x) {
			lineMaxX = line.getStartingPoint().x;
			lineMinX = line.getEndingPoint().x;
		} else {
			lineMinX = line.getStartingPoint().x;
			lineMaxX = line.getEndingPoint().x;
		}

		if (line.getStartingPoint().y > line.getEndingPoint().y) {
			lineMaxY = line.getStartingPoint().y;
			lineMinY = line.getEndingPoint().y;
		} else {
			lineMinY = line.getStartingPoint().y;
			lineMaxY = line.getEndingPoint().y;
		}

		if (minY > lineMinY) {
			minY = lineMinY;
		}
		if (minX > lineMinX) {
			minX = lineMinX;
		}
		if (maxX < lineMaxX) {
			maxX = lineMaxX;
		}
		if (maxY < lineMaxY) {
			maxY = lineMaxY;
		}

		set = true;

	}

	@Override
	public void visit(Circle circle) {
		int x = circle.getStartingPoint().x;
		int y = circle.getStartingPoint().y;

		int CmaxX = x + circle.getRadius();
		int CmaxY = y + circle.getRadius();
		int CminX = x - circle.getRadius();
		int CminY = y - circle.getRadius();

		if (minY > CminY) {
			minY = CminY;
		}
		if (minX > CminX) {
			minX = CminX;
		}
		if (maxX < CmaxX) {
			maxX = CmaxX;
		}
		if (maxY < CmaxY) {
			maxY = CmaxY;
		}

		set = true;

	}

	@Override
	public void visit(FilledCircle filledCircle) {
		int x = filledCircle.getStartingPoint().x;
		int y = filledCircle.getStartingPoint().y;

		int CmaxX = x + filledCircle.getRadius();
		int CmaxY = y + filledCircle.getRadius();
		int CminX = x - filledCircle.getRadius();
		int CminY = y - filledCircle.getRadius();

		if (minY > CminY) {
			minY = CminY;
		}
		if (minX > CminX) {
			minX = CminX;
		}
		if (maxX < CmaxX) {
			maxX = CmaxX;
		}
		if (maxY < CmaxY) {
			maxY = CmaxY;
		}

		set = true;

	}

	/**
	 * Method which gets the created bounding box.
	 * 
	 * @return Created rectangle.
	 */
	public Rectangle getBoudningBox() {
		if (!set) {
			throw new IllegalArgumentException("No objects.");
		}
		return new Rectangle(minX, minY, maxX - minX, maxY - minY);
	}

}
