package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JComponent;

import hr.fer.zemris.java.hw16.jvdraw.listeners.ColorChangeListener;
import hr.fer.zemris.java.hw16.jvdraw.providers.IColorProvider;

/**
 * Class which represents a color area, an area which is responsible for
 * choosing the color in the context of JVDraw program.
 * 
 * @author Dinz
 *
 */
public class JColorArea extends JComponent implements IColorProvider {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -5142370114213089765L;

	/**
	 * Currently selected color.
	 */
	private Color selectedColor;

	/**
	 * Old color.
	 */
	private Color oldColor;

	/**
	 * List of color listeners.
	 */
	private List<ColorChangeListener> colorListeners = new ArrayList<>();

	/**
	 * Constructs a new color area with the given color.
	 * 
	 * @param selectedColor
	 *            Initially selected color.
	 */
	public JColorArea(Color selectedColor) {
		this.selectedColor = selectedColor;

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(JColorArea.this, "Select new color", selectedColor);
				JColorArea.this.setSelectedColor(color);
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(selectedColor);
		g.fillRect(getInsets().left, getInsets().top, getPreferredSize().width, getPreferredSize().height);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(15, 15);
	}

	/**
	 * Sets selected color.
	 * 
	 * @param selectedColor
	 *            Color to be selcted.
	 */
	public void setSelectedColor(Color selectedColor) {
		oldColor = this.selectedColor;
		this.selectedColor = selectedColor;
		notifyListeners();
	}

	/**
	 * Notifies listeners.
	 */
	private void notifyListeners() {
		for (ColorChangeListener l : colorListeners) {
			l.newColorSelected(this, oldColor, selectedColor);
		}

	}

	@Override
	public Color getCurrentColor() {
		return selectedColor;
	}

	@Override
	public void addColorChangeListener(ColorChangeListener l) {
		colorListeners.add(l);

	}

	@Override
	public void removeColorChangeListener(ColorChangeListener l) {
		colorListeners.remove(l);

	}
}
