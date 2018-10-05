package hr.fer.zemris.java.hw16.jvdraw.geometry;

/**
 * Interface which describes an object visitor - each visitor does its own logic
 * on the object based on its type.
 * 
 * @author Dinz
 *
 */
public interface GeometricalObjectVisitor {
	/**
	 * Method which visits a line object.
	 * 
	 * @param line
	 *            Line.
	 */
	public abstract void visit(Line line);

	/**
	 * Method which visits a circle object.
	 * 
	 * @param circle
	 *            Circle.
	 */
	public abstract void visit(Circle circle);

	/**
	 * Method which visits a filled circle object.
	 * 
	 * @param filledCircle
	 *            Filled Circle.
	 */
	public abstract void visit(FilledCircle filledCircle);

}
