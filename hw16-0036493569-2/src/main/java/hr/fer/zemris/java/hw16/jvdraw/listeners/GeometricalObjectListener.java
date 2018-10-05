package hr.fer.zemris.java.hw16.jvdraw.listeners;

import hr.fer.zemris.java.hw16.jvdraw.geometry.GeometricalObject;

/**
 * Interface which describes the geometrical object listeners.
 * 
 * @author Dinz
 *
 */
public interface GeometricalObjectListener {
	
	/**
	 * Method which runs then the object has been changed.
	 * 
	 * @param o
	 *            Object.
	 */
	public void geometricalObjectChanged(GeometricalObject o);
}
