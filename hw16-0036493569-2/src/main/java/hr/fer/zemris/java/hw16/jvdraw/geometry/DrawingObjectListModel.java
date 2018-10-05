package hr.fer.zemris.java.hw16.jvdraw.geometry;

import javax.swing.AbstractListModel;

import hr.fer.zemris.java.hw16.jvdraw.listeners.DrawingModelListener;

/**
 * Class which represents a object list model in the context of the JVDraw
 * program.
 * 
 * @author Dinz
 *
 */
public class DrawingObjectListModel extends AbstractListModel<GeometricalObject> implements DrawingModelListener {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -3025284975968258473L;

	/**
	 * Model in the JVDraw context.
	 */
	private DrawingModel model;

	/**
	 * Constructs a new drawing object list model.
	 * 
	 * @param model
	 *            Model.
	 */
	public DrawingObjectListModel(DrawingModel model) {
		this.model = model;
		model.addDrawingModelListener(this);
	}

	@Override
	public int getSize() {
		return model.getSize();
	}

	@Override
	public GeometricalObject getElementAt(int index) {
		return model.getObject(index);
	}

	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {
		fireIntervalAdded(source, index0, index1);

	}

	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		fireIntervalRemoved(source, index0, index1);

	}

	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		fireContentsChanged(source, index0, index1);

	}

}
