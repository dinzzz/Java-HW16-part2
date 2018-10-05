package hr.fer.zemris.java.hw16.jvdraw.providers;

import java.awt.Color;

import hr.fer.zemris.java.hw16.jvdraw.listeners.ColorChangeListener;

/**
 * Interface which describes a color provider in the JVDraw program.
 * 
 * @author Dinz
 *
 */
public interface IColorProvider {

	/**
	 * Gets the current color.
	 * 
	 * @return Current color.
	 */
	public Color getCurrentColor();

	/**
	 * Adds the color change listener to the listeners list.
	 * 
	 * @param l
	 *            Listener to be added.
	 */
	public void addColorChangeListener(ColorChangeListener l);

	/**
	 * Removes the color change listener from the listeners list.
	 * 
	 * @param l
	 *            Listener to be removed.
	 */
	public void removeColorChangeListener(ColorChangeListener l);
}
