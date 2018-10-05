package hr.fer.zemris.java.hw16.jvdraw.listeners;

import java.awt.Color;

import hr.fer.zemris.java.hw16.jvdraw.providers.IColorProvider;

/**
 * Interface which describes a color change listener.
 * 
 * @author Dinz
 *
 */
public interface ColorChangeListener {
	/**
	 * Method which activates when the new color is selected.
	 * 
	 * @param source
	 *            Source.
	 * @param oldColor
	 *            Old color.
	 * @param newColor
	 *            New color.
	 */
	public void newColorSelected(IColorProvider source, Color oldColor, Color newColor);
}
