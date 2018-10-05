package hr.fer.zemris.java.hw16.jvdraw.tools;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * Interface which describes an object tool, responsible for representing the
 * objects on the canvas.
 * 
 * @author Dinz
 *
 */
public interface Tool {

	/**
	 * Method which handles the object representation when mouse is pressed.
	 * 
	 * @param e
	 *            Mouse event.
	 */
	public void mousePressed(MouseEvent e);

	/**
	 * Method which handles the object representation when mouse is released.
	 * 
	 * @param e
	 *            Mouse event.
	 */
	public void mouseReleased(MouseEvent e);

	/**
	 * Method which handles the object representation when mouse is clicked.
	 * 
	 * @param e
	 *            Mouse event.
	 */
	public void mouseClicked(MouseEvent e);

	/**
	 * Method which handles the object representation when mouse is moved.
	 * 
	 * @param e
	 *            Mouse event.
	 */
	public void mouseMoved(MouseEvent e);

	/**
	 * Method which handles the object representation when mouse is dragged.
	 * 
	 * @param e
	 *            Mouse event.
	 */
	public void mouseDragged(MouseEvent e);

	/**
	 * Method which paints the object to the canvas.
	 * 
	 * @param g2d
	 *            Graphics.
	 */
	public void paint(Graphics2D g2d);
}