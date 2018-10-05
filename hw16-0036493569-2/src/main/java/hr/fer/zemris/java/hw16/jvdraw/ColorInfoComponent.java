package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.Color;

import javax.swing.JLabel;

import hr.fer.zemris.java.hw16.jvdraw.listeners.ColorChangeListener;
import hr.fer.zemris.java.hw16.jvdraw.providers.IColorProvider;

/**
 * Class which implements an element which tracks the information about the
 * current foreground and background colors.
 * 
 * @author Dinz
 *
 */
public class ColorInfoComponent extends JLabel implements ColorChangeListener {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Foreground color provider.
	 */
	IColorProvider fgColorProvider;

	/**
	 * Background color provider.
	 */
	IColorProvider bgColorProvider;

	/**
	 * Foreground color.
	 */
	Color fgColor;

	/**
	 * Background color.
	 */
	Color bgColor;

	/**
	 * Constructs a new color info label.
	 * 
	 * @param fgColorProvider
	 *            Foreground color provider.
	 * @param bgColorProvider
	 *            Background color provider.
	 */
	public ColorInfoComponent(IColorProvider fgColorProvider, IColorProvider bgColorProvider) {
		this.fgColorProvider = fgColorProvider;
		this.bgColorProvider = bgColorProvider;
		fgColor = fgColorProvider.getCurrentColor();
		bgColor = bgColorProvider.getCurrentColor();
		fgColorProvider.addColorChangeListener(this);
		bgColorProvider.addColorChangeListener(this);
		output();
	}

	@Override
	public void newColorSelected(IColorProvider source, Color oldColor, Color newColor) {
		if (source.equals(fgColorProvider)) {
			fgColor = newColor;
		} else if (source.equals(bgColorProvider)) {
			bgColor = newColor;
		}
		output();

	}

	/**
	 * Outputs the text to the label.
	 */
	public void output() {
		String text = String.format("Foreground color: (%d, %d, %d), background color: (%d, %d, %d).", fgColor.getRed(),
				fgColor.getGreen(), fgColor.getBlue(), bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
		this.setText(text);
	}

}
