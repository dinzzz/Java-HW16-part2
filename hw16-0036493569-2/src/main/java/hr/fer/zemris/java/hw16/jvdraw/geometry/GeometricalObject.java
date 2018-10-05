package hr.fer.zemris.java.hw16.jvdraw.geometry;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import hr.fer.zemris.java.hw16.jvdraw.listeners.GeometricalObjectListener;

/**
 * Abstract class which represents a general geometrical object in the context
 * of the JVDraw program.
 * 
 * @author Dinz
 *
 */
public abstract class GeometricalObject extends JComponent {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -551102505998373102L;

	/**
	 * List of object listeners.
	 */
	List<GeometricalObjectListener> listeners = new ArrayList<>();

	/**
	 * Method which is called when an objects accepts a visitor.
	 * 
	 * @param v
	 *            Visitor to be accepted.
	 */
	public abstract void accept(GeometricalObjectVisitor v);

	/**
	 * Creates a new object editor.
	 * 
	 * @return Object editor.
	 */
	public abstract GeometricalObjectEditor createGeometricalObjectEditor();

	/**
	 * Adds a new object listener to the listeners list.
	 * 
	 * @param l
	 *            Listener to be added.
	 */
	public void addGeometricalObjectListener(GeometricalObjectListener l) {
		listeners.add(l);
	}

	/**
	 * Removes an object listener from the listeners list.
	 * 
	 * @param l
	 *            Listener to be removed.
	 */
	public void removeGeometricalObjectListener(GeometricalObjectListener l) {
		listeners.remove(l);
	}

	/**
	 * Method which transforms the object to a format for the output to the .jvd
	 * file.
	 * 
	 * @return Appropriate string format.
	 */
	public abstract String toOutput();

}
