package hr.fer.zemris.java.hw16.jvdraw.geometry;

import hr.fer.zemris.java.hw16.jvdraw.listeners.DrawingModelListener;

/**
 * Interface which describes a drawing model in the context of JVDraw program.
 * 
 * @author Dinz
 *
 */
public interface DrawingModel {

	/**
	 * Gets the size of the model.
	 * 
	 * @return Size of the model.
	 */
	public int getSize();

	/**
	 * Gets the object from the given index.
	 * 
	 * @param index
	 *            Given index.
	 * @return Geometrical object.
	 */
	public GeometricalObject getObject(int index);

	/**
	 * Adds an object to a model.
	 * 
	 * @param object
	 *            Object to be added.
	 */
	public void add(GeometricalObject object);

	/**
	 * Adds a listener to the listeners list.
	 * 
	 * @param l
	 *            Listener to be added.
	 */
	public void addDrawingModelListener(DrawingModelListener l);

	/**
	 * Removes the listener from the listeners list.
	 * 
	 * @param l
	 *            Listener to be removed.
	 */
	public void removeDrawingModelListener(DrawingModelListener l);

	/**
	 * Removes an object from the model.
	 * 
	 * @param object
	 *            Object to be removed.
	 */
	void remove(GeometricalObject object);

	/**
	 * Method which changes the order in the model.
	 * 
	 * @param object
	 *            Object which changes positions.
	 * @param offset
	 *            Offset.
	 */
	void changeOrder(GeometricalObject object, int offset);
}
