package hr.fer.zemris.java.hw16.jvdraw.geometry;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.listeners.GeometricalObjectListener;

/**
 * Class which represents a filled circle editor which is used when editing of
 * the filled circle is requested.
 * 
 * @author Dinz
 *
 */
public class FilledCircleEditor extends GeometricalObjectEditor {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -1825995372480629387L;

	/**
	 * Filled circle instance.
	 */
	private FilledCircle circle;

	/**
	 * New filled circle instance.
	 */
	private FilledCircle newCircle;

	/**
	 * Text field which represents an x coordinate of the center point.
	 */
	private JTextField sx;

	/**
	 * Text field which represents an y coordinate of the center point.
	 */
	private JTextField sy;

	/**
	 * Text field which represents a circle's radius.
	 */
	private JTextField rds;

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
	 * Text field which represents a red filling color component.
	 */
	private JTextField br;

	/**
	 * Text field which represents a green filling color component.
	 */
	private JTextField bg;

	/**
	 * Text field which represents a blue filling color component.
	 */
	private JTextField bb;

	/**
	 * Circle's center point.
	 */
	private Point startPoint;

	/**
	 * New radius value.
	 */
	private int newRadius;

	/**
	 * Color.
	 */
	private Color color;

	/**
	 * Filling color.
	 */
	private Color bgColor;
	
	/**
	 * Constructs a new filled circle editor.
	 * @param circle Filled circle instance.
	 */
	public FilledCircleEditor(FilledCircle circle) {
		this.circle = circle;

		this.setLayout(new GridLayout());
		Point startingPoint = circle.getStartingPoint();
		int radius = circle.getRadius();
		Color color = circle.getColor();
		Color bgColor = circle.getBgColor();

		JLabel startX = new JLabel("Starting X: ");
		sx = new JTextField(Integer.toString(startingPoint.x));
		JLabel startY = new JLabel("Starting Y: ");
		sy = new JTextField(Integer.toString(startingPoint.y));
		JLabel radiusl = new JLabel("Radius: ");
		rds = new JTextField(Integer.toString(radius));
		JLabel red = new JLabel("Red: ");
		r = new JTextField(Integer.toString(color.getRed()));
		JLabel green = new JLabel("Green: ");
		g = new JTextField(Integer.toString(color.getGreen()));
		JLabel blue = new JLabel("Blue: ");
		b = new JTextField(Integer.toString(color.getBlue()));
		JLabel redf = new JLabel("Fill Red: ");
		br = new JTextField(Integer.toString(bgColor.getRed()));
		JLabel greenf = new JLabel("Fill Green: ");
		bg = new JTextField(Integer.toString(bgColor.getGreen()));
		JLabel bluef = new JLabel("Fill Blue: ");
		bb = new JTextField(Integer.toString(bgColor.getBlue()));

		this.add(startX);
		this.add(sx);
		this.add(startY);
		this.add(sy);
		this.add(radiusl);
		this.add(rds);
		this.add(red);
		this.add(r);
		this.add(green);
		this.add(g);
		this.add(blue);
		this.add(b);
		this.add(redf);
		this.add(br);
		this.add(greenf);
		this.add(bg);
		this.add(bluef);
		this.add(bb);
	}

	@Override
	public void checkEditing() {
		try {
			int strtX = Integer.parseInt(sx.getText());
			int strtY = Integer.parseInt(sy.getText());
			int radius = Integer.parseInt(rds.getText());
			int rd = Integer.parseInt(r.getText());
			int gr = Integer.parseInt(g.getText());
			int bl = Integer.parseInt(b.getText());
			int brd = Integer.parseInt(br.getText());
			int bgr = Integer.parseInt(bg.getText());
			int bbl = Integer.parseInt(bb.getText());

			if (rd > 255 || rd < 0 || gr > 255 || rd < 0 || bl > 255 || bl < 0 || brd > 255 || brd < 0 || bgr > 255
					|| bgr < 0 || bbl > 255 || bbl < 0) {
				throw new IllegalArgumentException();
			}

			startPoint = new Point(strtX, strtY);
			newRadius = radius;
			color = new Color(rd, gr, bl);
			bgColor = new Color(brd, bgr, bbl);

			newCircle = new FilledCircle(startPoint, newRadius, color, bgColor);

		} catch (NumberFormatException exc) {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void acceptEditing() {
		circle.setStartingPoint(newCircle.getStartingPoint());
		circle.setRadius(newCircle.getRadius());
		circle.setColor(newCircle.getColor());
		circle.setBgColor(newCircle.getBgColor());

		for (GeometricalObjectListener l : circle.listeners) {
			l.geometricalObjectChanged(circle);
		}

	}

}
