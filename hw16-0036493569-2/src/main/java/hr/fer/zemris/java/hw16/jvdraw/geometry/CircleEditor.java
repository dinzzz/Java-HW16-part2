package hr.fer.zemris.java.hw16.jvdraw.geometry;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.listeners.GeometricalObjectListener;

/**
 * Class which represents a circle editor, a tool which is used for editing the
 * circle object when requested.
 * 
 * @author Dinz
 *
 */
public class CircleEditor extends GeometricalObjectEditor {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 7431742571697825618L;

	/**
	 * Circle instance.
	 */
	private Circle circle;

	/**
	 * Newly created circle instance.
	 */
	private Circle newCircle;

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
	 * Constructs a new circle editor.
	 * 
	 * @param circle
	 *            Circle instance.
	 */
	public CircleEditor(Circle circle) {
		this.circle = circle;

		this.setLayout(new GridLayout());
		Point startingPoint = circle.getStartingPoint();
		int radius = circle.getRadius();
		Color color = circle.getColor();

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

			if (rd > 255 || rd < 0 || gr > 255 || rd < 0 || bl > 255 || bl < 0) {
				throw new IllegalArgumentException();
			}

			startPoint = new Point(strtX, strtY);
			newRadius = radius;
			color = new Color(rd, gr, bl);

			newCircle = new Circle(startPoint, newRadius, color);

		} catch (NumberFormatException exc) {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void acceptEditing() {

		circle.setStartingPoint(newCircle.getStartingPoint());
		circle.setRadius(newCircle.getRadius());
		circle.setColor(newCircle.getColor());

		for (GeometricalObjectListener l : circle.listeners) {
			l.geometricalObjectChanged(circle);
		}

	}

}
