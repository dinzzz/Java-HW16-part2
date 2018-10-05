package hr.fer.zemris.java.hw16.jvdraw.geometry;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.listeners.GeometricalObjectListener;

/**
 * Class which represents a line editor whose work is to produce the logic
 * behind editing when requested.
 * 
 * @author Dinz
 *
 */
public class LineEditor extends GeometricalObjectEditor {
	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 5569875241770927888L;

	/**
	 * Line instance.
	 */
	private Line line;

	/**
	 * Newly created line.
	 */
	private Line newLine;

	/**
	 * Text field which represents an X coordinate of the starting point.
	 */
	private JTextField sx;

	/**
	 * Text field which represents an Y coordinate of the starting point.
	 */
	private JTextField sy;

	/**
	 * Text field which represents an X coordinate of the ending point.
	 */
	private JTextField ex;

	/**
	 * Text field which represents an Y coordinate of the ending point.
	 */
	private JTextField ey;

	/**
	 * Text field which represents a red color component.
	 */
	private JTextField r;

	/**
	 * Text field which represents a green color component.
	 */
	private JTextField g;

	/**
	 * Text field which represents a blue color component.
	 */
	private JTextField b;

	/**
	 * Starting point of the line.
	 */
	private Point startPoint;

	/**
	 * Ending point of the line.
	 */
	private Point endPoint;

	/**
	 * Color of the line.
	 */
	private Color color;

	/**
	 * Constructs a new line editor.
	 * 
	 * @param line
	 *            Line.
	 */
	public LineEditor(Line line) {
		this.line = line;
		this.setLayout(new GridLayout());
		Point startingPoint = line.getStartingPoint();
		Point endingPoint = line.getEndingPoint();
		Color color = line.getColor();

		JLabel startX = new JLabel("Starting X: ");
		sx = new JTextField(Integer.toString(startingPoint.x));
		JLabel startY = new JLabel("Starting Y: ");
		sy = new JTextField(Integer.toString(startingPoint.y));
		JLabel endX = new JLabel("Ending X: ");
		ex = new JTextField(Integer.toString(endingPoint.x));
		JLabel endY = new JLabel("Ending Y: ");
		ey = new JTextField(Integer.toString(endingPoint.y));
		JLabel red = new JLabel("Red: ");
		r = new JTextField(Integer.toString(color.getRed()));
		JLabel green = new JLabel("Green: ");
		g = new JTextField(Integer.toString(color.getGreen()));
		JLabel blue = new JLabel("Blue: ");
		b = new JTextField(Integer.toString(color.getBlue()));

		this.add(startX);
		this.add(sx);
		this.add(startY);
		this.add(sy);
		this.add(endX);
		this.add(ex);
		this.add(endY);
		this.add(ey);
		this.add(red);
		this.add(r);
		this.add(green);
		this.add(g);
		this.add(blue);
		this.add(b);
	}

	@Override
	public void checkEditing() {
		try {
			int strtX = Integer.parseInt(sx.getText());
			int strtY = Integer.parseInt(sy.getText());
			int ndX = Integer.parseInt(ex.getText());
			int ndY = Integer.parseInt(ey.getText());
			int rd = Integer.parseInt(r.getText());
			int gr = Integer.parseInt(g.getText());
			int bl = Integer.parseInt(b.getText());

			if (rd > 255 || rd < 0 || gr > 255 || rd < 0 || bl > 255 || bl < 0) {
				throw new IllegalArgumentException();
			}

			startPoint = new Point(strtX, strtY);
			endPoint = new Point(ndX, ndY);
			color = new Color(rd, gr, bl);

			newLine = new Line(startPoint, endPoint, color);

		} catch (NumberFormatException exc) {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void acceptEditing() {
		line.setStartingPoint(newLine.getStartingPoint());
		line.setEndingPoint(newLine.getEndingPoint());
		line.setColor(newLine.getColor());

		for (GeometricalObjectListener l : line.listeners) {
			l.geometricalObjectChanged(line);
		}
	}

}
