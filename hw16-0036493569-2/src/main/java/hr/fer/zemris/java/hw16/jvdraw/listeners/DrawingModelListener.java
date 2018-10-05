package hr.fer.zemris.java.hw16.jvdraw.listeners;

import hr.fer.zemris.java.hw16.jvdraw.geometry.DrawingModel;

/**
 * Interface which describes a drawing model listener.
 * 
 * @author Dinz
 *
 */
public interface DrawingModelListener {
	/**
	 * Method which activates when objects are added.
	 * 
	 * @param source
	 *            Source.
	 * @param index0
	 *            Index.
	 * @param index1
	 *            Index.
	 */
	public void objectsAdded(DrawingModel source, int index0, int index1);

	/**
	 * Method which activates when objects are removed.
	 * 
	 * @param source
	 *            Source.
	 * @param index0
	 *            Index.
	 * @param index1
	 *            Index.
	 */
	public void objectsRemoved(DrawingModel source, int index0, int index1);

	/**
	 * Method which activates when objects are changed.
	 * 
	 * @param source
	 *            Source.
	 * @param index0
	 *            Index.
	 * @param index1
	 *            Index.
	 */
	public void objectsChanged(DrawingModel source, int index0, int index1);

}
