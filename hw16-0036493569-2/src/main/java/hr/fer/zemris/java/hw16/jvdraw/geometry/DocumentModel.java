package hr.fer.zemris.java.hw16.jvdraw.geometry;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw16.jvdraw.listeners.DrawingModelListener;
import hr.fer.zemris.java.hw16.jvdraw.listeners.GeometricalObjectListener;

/**
 * Class which represents a document model - a model which stores the objects in
 * the context of the JVDraw program.
 * 
 * @author Dinz
 *
 */
public class DocumentModel implements DrawingModel, GeometricalObjectListener {

	/**
	 * List of objects.
	 */
	private List<GeometricalObject> objects = new ArrayList<>();

	/**
	 * List of listeners.
	 */
	private List<DrawingModelListener> listeners = new ArrayList<>();

	@Override
	public int getSize() {
		return objects.size();
	}

	@Override
	public GeometricalObject getObject(int index) {
		return objects.get(index);
	}

	@Override
	public void add(GeometricalObject object) {
		int index = objects.size();
		objects.add(object);
		object.addGeometricalObjectListener(this);
		for (DrawingModelListener l : listeners) {
			l.objectsAdded(this, index, index);
		}

	}

	@Override
	public void addDrawingModelListener(DrawingModelListener l) {
		listeners.add(l);

	}

	@Override
	public void removeDrawingModelListener(DrawingModelListener l) {
		listeners.remove(l);
	}

	@Override
	public void remove(GeometricalObject object) {
		int index = objects.size();
		objects.remove(object);
		for (DrawingModelListener l : listeners) {
			l.objectsRemoved(this, index, index);
		}

	}

	@Override
	public void changeOrder(GeometricalObject object, int offset) {
		if (offset == 0) {
			return;
		}
		int index = objects.indexOf(object);
		int index0 = index + offset;
		objects.remove(object);

		if (index + offset < 0) {
			objects.add(0, object);
			index0 = 0;
		} else {
			if (index + offset >= objects.size()) {
				objects.add(object);
			} else {
				objects.add(index + offset, object);
			}
		}

		for (DrawingModelListener l : listeners) {
			l.objectsChanged(this, index, index0);
		}

	}

	@Override
	public void geometricalObjectChanged(GeometricalObject o) {
		for (DrawingModelListener l : listeners) {
			l.objectsChanged(this, objects.indexOf(o), objects.indexOf(o));
		}

	}

}
