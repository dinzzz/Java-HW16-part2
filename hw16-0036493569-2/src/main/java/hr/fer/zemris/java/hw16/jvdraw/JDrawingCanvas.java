package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import hr.fer.zemris.java.hw16.jvdraw.geometry.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.geometry.GeometricalObjectPainter;
import hr.fer.zemris.java.hw16.jvdraw.listeners.DrawingModelListener;
import hr.fer.zemris.java.hw16.jvdraw.tools.Tool;

/**
 * Class that represents a drawing canvas in the context of JVDraw program. This
 * is where all the objects in the program's context are drawn and manipulated
 * with.
 * 
 * @author Dinz
 *
 */
public class JDrawingCanvas extends JComponent implements DrawingModelListener {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 6006909978677342018L;

	/**
	 * JVDraw instance
	 */
	private JVDraw prgrm;
	/**
	 * Drawing model instance.
	 */
	private DrawingModel model;

	/**
	 * Current state.
	 */
	private Tool state;

	/**
	 * Constructs a new drawing canvas.
	 * 
	 * @param model
	 *            Drawing model.
	 * @param prgrm
	 *            JVDraw instance.
	 * 
	 */
	public JDrawingCanvas(DrawingModel model, JVDraw prgrm) {
		this.model = model;
		this.prgrm = prgrm;
		model.addDrawingModelListener(this);
		setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		setBackground(Color.WHITE);
	}

	/**
	 * Sets the current canvas state.
	 * 
	 * @param state
	 *            State to be set.
	 */
	public void setState(Tool state) {
		this.state = state;
	}

	@Override
	protected void paintComponent(Graphics g) {
		for (int i = 0; i < model.getSize(); i++) {
			model.getObject(i).accept(new GeometricalObjectPainter((Graphics2D) g));
		}
		state.paint((Graphics2D) g);
	}

	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {
		repaint();
		prgrm.setSaved(false);
	}

	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		repaint();
		prgrm.setSaved(false);
	}

	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		repaint();
		prgrm.setSaved(false);

	}

}
