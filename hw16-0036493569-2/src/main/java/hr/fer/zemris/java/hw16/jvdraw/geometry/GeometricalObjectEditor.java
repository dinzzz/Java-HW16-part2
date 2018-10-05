package hr.fer.zemris.java.hw16.jvdraw.geometry;

import javax.swing.JPanel;

/**
 * An abstract class which represents an object editor.
 * 
 * @author Dinz
 *
 */
public abstract class GeometricalObjectEditor extends JPanel {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1145380928614714949L;

	/**
	 * Method which checks if the edited values are valid.
	 */
	public abstract void checkEditing();

	/**
	 * Method which finally edits the object in the model.
	 */
	public abstract void acceptEditing();

}
