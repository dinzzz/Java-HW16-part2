package hr.fer.zemris.java.hw16.jvdraw.geometry;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Class which represents an object painter in the context of JVDraw program.
 * This painter does the graphics logic for all the objects in the programs
 * context.
 * 
 * @author Dinz
 *
 */
public class GeometricalObjectPainter implements GeometricalObjectVisitor {
	
	/**
	 * Graphics.
	 */
	private Graphics2D g;
	
	/**
	 * Constructs a new object painter.
	 * @param g Graphics.
	 */
	public GeometricalObjectPainter(Graphics2D g) {
		this.g = g;
	}

	@Override
	public void visit(Line line) {
		Point startingPoint = line.getStartingPoint();
		Point endingPoint = line.getEndingPoint();
		g.setColor(line.getColor());
		g.drawLine(startingPoint.x, startingPoint.y, endingPoint.x, endingPoint.y);

	}

	@Override
	public void visit(Circle circle) {
		Point startingPoint = circle.getStartingPoint();
		g.setColor(circle.getColor());
		g.drawOval(startingPoint.x - circle.getRadius(), startingPoint.y - circle.getRadius(), 2 * circle.getRadius(),
				2 * circle.getRadius());

	}

	@Override
	public void visit(FilledCircle filledCircle) {
		Point startingPoint = filledCircle.getStartingPoint();
		g.setColor(filledCircle.getBgColor());
		g.fillOval(startingPoint.x - filledCircle.getRadius(), startingPoint.y - filledCircle.getRadius(),
				2 * filledCircle.getRadius(), 2 * filledCircle.getRadius());
		g.setColor(filledCircle.getColor());
		g.drawOval(startingPoint.x - filledCircle.getRadius(), startingPoint.y - filledCircle.getRadius(),
				2 * filledCircle.getRadius(), 2 * filledCircle.getRadius());

	}

}
