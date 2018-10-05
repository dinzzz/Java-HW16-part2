package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import hr.fer.zemris.java.hw16.jvdraw.geometry.Circle;
import hr.fer.zemris.java.hw16.jvdraw.geometry.DocumentModel;
import hr.fer.zemris.java.hw16.jvdraw.geometry.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.geometry.DrawingObjectListModel;
import hr.fer.zemris.java.hw16.jvdraw.geometry.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.geometry.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.geometry.GeometricalObjectBBCalculator;
import hr.fer.zemris.java.hw16.jvdraw.geometry.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.geometry.GeometricalObjectPainter;
import hr.fer.zemris.java.hw16.jvdraw.geometry.Line;
import hr.fer.zemris.java.hw16.jvdraw.tools.CircleTool;
import hr.fer.zemris.java.hw16.jvdraw.tools.FilledCircleTool;
import hr.fer.zemris.java.hw16.jvdraw.tools.LineTool;
import hr.fer.zemris.java.hw16.jvdraw.tools.Tool;

/**
 * Class that represents a JVDraw, a simple user-friendly program which produces
 * vector graphics. The user can draw lines, circles and filled circles, export
 * the pictures made and also import/export appropriate .jvd files.
 * 
 * @author Dinz
 *
 */
public class JVDraw extends JFrame {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 3004844093031345455L;

	/**
	 * Current state of the program.
	 */
	private Tool currentState;

	/**
	 * Foreground color area.
	 */
	private JColorArea fgColorArea;

	/**
	 * Background color area.
	 */
	private JColorArea bgColorArea;

	/**
	 * Drawing canvas.
	 */
	private JDrawingCanvas canvas;

	/**
	 * Appropriate drawing model.
	 */
	private DrawingModel model = new DocumentModel();

	/**
	 * Path of the currently opened file.
	 */
	private Path path = null;

	/**
	 * Flag which determines if the current context in the editor is saved.
	 */
	private boolean saved = true;

	/**
	 * Main method which runs the class.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new JVDraw().setVisible(true);
		});
	}

	/**
	 * Constructs a new JVDraw instance.
	 */
	public JVDraw() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (!saved) {
					int reply = JOptionPane.showConfirmDialog(JVDraw.this, "Save changes before exiting?");
					if (reply == JOptionPane.YES_OPTION) {
						saveAction.actionPerformed(null);
						JVDraw.this.dispose();
					} else if (reply == JOptionPane.NO_OPTION) {
						JVDraw.this.dispose();
					} else {
						return;
					}
				} else {
					JVDraw.this.dispose();
				}
			}
		});
		setLocation(0, 0);
		setSize(1000, 600);
		setTitle("JVDraw 1.0");
		initGUI();
	}

	/**
	 * Initializes graphical user interface of the program.
	 */
	private void initGUI() {
		createActions();
		createMenubar();
		createToolbar();
		createCanvas();
		createObjectList();
		currentState = new LineTool(fgColorArea, model);
		canvas.setState(currentState);
	}

	/**
	 * Creates action which are used in the JVDraw program.
	 */
	private void createActions() {
		openAction.putValue(Action.NAME, "Open");
		openAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control O"));
		openAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
		openAction.putValue(Action.SHORT_DESCRIPTION, "Used to open file from disk");

		saveAction.putValue(Action.NAME, "Save");
		saveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));
		saveAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
		saveAction.putValue(Action.SHORT_DESCRIPTION, "Saves the current document");

		saveAsAction.putValue(Action.NAME, "Save as...");
		saveAsAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control A"));
		saveAsAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
		saveAsAction.putValue(Action.SHORT_DESCRIPTION, "Saves the document at a desired location");

		exportAction.putValue(Action.NAME, "Export");
		exportAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control E"));
		exportAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		exportAction.putValue(Action.SHORT_DESCRIPTION, "Exports the document");

		exitAction.putValue(Action.NAME, "Exit");
		exitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control Q"));
		exitAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
		exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit JVDraw");

	}

	/**
	 * Sets the saved flag.
	 * 
	 * @param b
	 *            Boolean to be set.
	 */
	public void setSaved(boolean b) {
		saved = b;
	}

	/**
	 * Creates list of objects which is present in the context of the program.
	 */
	private void createObjectList() {
		JList<GeometricalObject> list = new JList<>(new DrawingObjectListModel(model));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					GeometricalObject object = list.getSelectedValue();
					if (object != null) {
						GeometricalObjectEditor editor = object.createGeometricalObjectEditor();
						int answer = JOptionPane.showConfirmDialog(JVDraw.this, editor, "Modify",
								JOptionPane.OK_CANCEL_OPTION);
						if (answer == JOptionPane.YES_OPTION) {
							try {
								editor.checkEditing();
								editor.acceptEditing();
							} catch (IllegalArgumentException ex) {
								JOptionPane.showMessageDialog(JVDraw.this, "Invalid input while editing.", "Error!",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});

		list.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					if (list.hasFocus()) {
						try {
							GeometricalObject object = list.getSelectedValue();
							model.remove(object);
						} catch (Exception ignorable) {

						}
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_ADD || e.getKeyCode() == KeyEvent.VK_PLUS) {
					if (list.hasFocus()) {
						try {
							GeometricalObject object = list.getSelectedValue();
							model.changeOrder(object, 1);
						} catch (Exception ignorable) {

						}
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_SUBTRACT || e.getKeyCode() == KeyEvent.VK_MINUS) {
					if (list.hasFocus()) {
						try {
							GeometricalObject object = list.getSelectedValue();
							model.changeOrder(object, -1);
						} catch (Exception ignorable) {

						}
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

		});

		JScrollPane pane = new JScrollPane(list);
		this.getContentPane().add(pane, BorderLayout.LINE_END);

	}

	/**
	 * Creates a canvas in the JVDraw context.
	 */
	private void createCanvas() {
		canvas = new JDrawingCanvas(model, this);
		canvas.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				currentState.mouseClicked(e);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				currentState.mousePressed(e);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				currentState.mouseReleased(e);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});

		canvas.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				currentState.mouseDragged(e);

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				currentState.mouseMoved(e);
				canvas.repaint();

			}

		});

		this.getContentPane().add(canvas, BorderLayout.CENTER);
	}

	/**
	 * Creates the toolbar.
	 */
	private void createToolbar() {

		JToolBar tb = new JToolBar();
		fgColorArea = new JColorArea(Color.BLACK);
		bgColorArea = new JColorArea(Color.WHITE);
		fgColorArea.setMaximumSize(fgColorArea.getPreferredSize());
		bgColorArea.setMaximumSize(bgColorArea.getPreferredSize());

		ButtonGroup group = new ButtonGroup();
		JToggleButton line = new JToggleButton("Line");
		JToggleButton fCircle = new JToggleButton("Filled Circle");
		JToggleButton circle = new JToggleButton("Circle");

		line.addActionListener(e -> {
			currentState = new LineTool(fgColorArea, model);
			canvas.setState(currentState);
		});

		circle.addActionListener(e -> {
			currentState = new CircleTool(fgColorArea, model);
			canvas.setState(currentState);
		});

		fCircle.addActionListener(e -> {
			currentState = new FilledCircleTool(fgColorArea, bgColorArea, model);
			canvas.setState(currentState);
		});

		group.add(line);
		group.add(circle);
		group.add(fCircle);
		line.setSelected(true);

		tb.add(fgColorArea);
		tb.addSeparator();
		tb.add(bgColorArea);
		tb.addSeparator();
		tb.addSeparator();
		tb.add(line);
		tb.add(circle);
		tb.add(fCircle);
		this.getContentPane().add(tb, BorderLayout.PAGE_START);

		ColorInfoComponent info = new ColorInfoComponent(fgColorArea, bgColorArea);
		this.getContentPane().add(info, BorderLayout.PAGE_END);
	}

	/**
	 * Creates the menu bar.
	 */
	private void createMenubar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		fileMenu.add(new JMenuItem(openAction));
		fileMenu.add(new JMenuItem(saveAction));
		fileMenu.add(new JMenuItem(saveAsAction));
		fileMenu.add(new JMenuItem(exportAction));
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem(exitAction));

		this.setJMenuBar(menuBar);
	}

	/**
	 * Action which occurs when the user wants to exit the program.
	 */
	private final Action exitAction = new AbstractAction() {

		/**
		 * Serial.
		 */
		private static final long serialVersionUID = 6011802363039077830L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!saved) {
				int reply = JOptionPane.showConfirmDialog(JVDraw.this, "Save changes before exiting?");
				if (reply == JOptionPane.YES_OPTION) {
					saveAction.actionPerformed(null);
					JVDraw.this.dispose();
				} else if (reply == JOptionPane.NO_OPTION) {
					JVDraw.this.dispose();
				} else {
					return;
				}
			}
			JVDraw.this.dispose();
		}

	};

	/**
	 * Action which is executed when user wants to export the result to an image
	 * file.
	 */
	private final Action exportAction = new AbstractAction() {

		/**
		 * Serial.
		 */
		private static final long serialVersionUID = 6011802363039077830L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("gif", ".gif");
			FileNameExtensionFilter filter2 = new FileNameExtensionFilter("jpg", ".jpg");
			FileNameExtensionFilter filter3 = new FileNameExtensionFilter("png", ".png");
			fc.addChoosableFileFilter(filter);
			fc.addChoosableFileFilter(filter2);
			fc.addChoosableFileFilter(filter3);
			fc.setDialogTitle("Export file as");
			if (fc.showSaveDialog(JVDraw.this) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			File fileName = fc.getSelectedFile();
			Path filePath = fileName.toPath();

			if (Files.exists(filePath)) {
				int reply = JOptionPane.showConfirmDialog(JVDraw.this, "This file already exists. Overwrite?",
						"Overwrite file?", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

				} else {
					return;
				}

			}

			GeometricalObjectBBCalculator bbcalc = new GeometricalObjectBBCalculator();
			for (int i = 0; i < model.getSize(); i++) {
				model.getObject(i).accept(bbcalc);
			}
			try {
				Rectangle boundingBox = bbcalc.getBoudningBox();
				BufferedImage image = new BufferedImage(boundingBox.width, boundingBox.height,
						BufferedImage.TYPE_3BYTE_BGR);
				Graphics2D g = image.createGraphics();
				g.translate(-boundingBox.getMinX(), -boundingBox.getMinY());
				for (int i = 0; i < model.getSize(); i++) {
					model.getObject(i).accept(new GeometricalObjectPainter(g));
				}
				g.dispose();

				if (fc.getFileFilter().getDescription() != "jpg" && fc.getFileFilter().getDescription() != "gif"
						&& fc.getFileFilter().getDescription() != "png") {
					fc.setFileFilter(filter2);
				}

				if (!filePath.toString().endsWith(fc.getFileFilter().getDescription())) {
					filePath = Paths.get(filePath.toString() + "." + fc.getFileFilter().getDescription());
				}

				try {
					ImageIO.write(image, fc.getFileFilter().getDescription(), filePath.toFile());
				} catch (IOException ex) {
					ex.getMessage();
				}
				JOptionPane.showMessageDialog(JVDraw.this, "File has been saved to " + filePath.toString(),
						"File saved", JOptionPane.INFORMATION_MESSAGE);
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(JVDraw.this, "No objects!.", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}

	};

	/**
	 * Action which occurs when user wants to save the program context to desired
	 * location.
	 */
	private final Action saveAsAction = new AbstractAction() {

		/**
		 * Serial.
		 */
		private static final long serialVersionUID = 6011802363039077830L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JVD", "jvd", "jvd");
			fc.setFileFilter(filter);
			fc.setDialogTitle("Save file as");
			if (fc.showSaveDialog(JVDraw.this) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			File fileName = fc.getSelectedFile();
			Path filePath = fileName.toPath();

			if (Files.exists(filePath)) {
				int reply = JOptionPane.showConfirmDialog(JVDraw.this, "This file already exists. Overwrite?",
						"Overwrite file?", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

				} else {
					return;
				}

			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < model.getSize(); i++) {
				sb.append(model.getObject(i).toOutput() + "\n");
			}

			try {
				if (fileName.toString().endsWith(".jvd")) {
					PrintWriter out = new PrintWriter(fileName);
					out.println(sb.toString());
					out.close();
					path = fileName.toPath();
					saved = true;
				} else {
					PrintWriter out = new PrintWriter(fileName + ".jvd");
					out.println(sb.toString());
					out.close();
					path = Paths.get(fileName.toString() + ".jvd");
					saved = true;
				}

			} catch (FileNotFoundException e1) {
			}

		}

	};

	/**
	 * Action which occurs when user wants to save the programs context.
	 */
	private final Action saveAction = new AbstractAction() {

		/**
		 * Serial.
		 */
		private static final long serialVersionUID = 6011802363039077830L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (path == null) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JVD", "jvd", "jvd");
				fc.setFileFilter(filter);
				fc.setDialogTitle("Save file as");
				if (fc.showSaveDialog(JVDraw.this) != JFileChooser.APPROVE_OPTION) {
					return;
				}

				File fileName = fc.getSelectedFile();
				Path filePath = fileName.toPath();

				if (Files.exists(filePath)) {
					int reply = JOptionPane.showConfirmDialog(JVDraw.this, "This file already exists. Overwrite?",
							"Overwrite file?", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {

					} else {
						return;
					}

				}

				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < model.getSize(); i++) {
					sb.append(model.getObject(i).toOutput() + "\n");
				}

				try {
					if (fileName.toString().endsWith(".jvd")) {
						PrintWriter out = new PrintWriter(fileName);
						out.println(sb.toString());
						out.close();
						path = fileName.toPath();
						saved = true;
					} else {
						PrintWriter out = new PrintWriter(fileName + ".jvd");
						out.println(sb.toString());
						out.close();
						path = Paths.get(fileName.toString() + ".jvd");
						saved = true;
					}

				} catch (FileNotFoundException e1) {
				}
			} else {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < model.getSize(); i++) {
					sb.append(model.getObject(i).toOutput() + "\n");
				}
				PrintWriter out;
				try {
					out = new PrintWriter(path.toString());
					out.println(sb.toString());
					out.close();
					saved = true;
				} catch (FileNotFoundException e1) {
				}
			}

		}

	};

	/**
	 * Action which occurs when user want to open a new file in the editor from the
	 * computer.
	 */
	private final Action openAction = new AbstractAction() {

		/**
		 * Serial.
		 */
		private static final long serialVersionUID = 6011802363039077830L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JVD", "jvd", "jvd");
			fc.setFileFilter(filter);
			fc.setDialogTitle("Open file");
			if (fc.showOpenDialog(JVDraw.this) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			File fileName = fc.getSelectedFile();
			Path filePath = fileName.toPath();

			if (!filePath.toString().endsWith(".jvd")) {
				JOptionPane.showMessageDialog(JVDraw.this, "Invalid file type!", "Invalid type!",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!Files.isReadable(filePath)) {
				JOptionPane.showMessageDialog(JVDraw.this, "File" + fileName.getAbsolutePath() + "does not exist.",
						"File not found", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				List<String> lines = Files.readAllLines(filePath);
				List<GeometricalObject> objects = parse(lines);

				for (int i = 0; i < model.getSize(); i++) {
					model.remove(model.getObject(i));
					i--;
				}

				path = filePath;

				for (GeometricalObject object : objects) {
					model.add(object);
				}

				saved = true;

			} catch (IOException | IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(JVDraw.this, "Error while reading document", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}

		}

		/**
		 * Method which parses a file text to appropriate objects.
		 * 
		 * @param lines
		 *            Lines of the text.
		 * @return List of objects.
		 */
		private List<GeometricalObject> parse(List<String> lines) {
			List<GeometricalObject> list = new ArrayList<>();
			for (String line : lines) {
				if (line.trim().isEmpty()) {
					continue;
				}
				line = line.trim();
				String[] split = line.split(" ");
				try {
					if (split[0].equals("LINE")) {
						if (split.length != 8) {
							throw new IllegalArgumentException();
						}

						int startX = Integer.parseInt(split[1]);
						int startY = Integer.parseInt(split[2]);
						int endX = Integer.parseInt(split[3]);
						int endY = Integer.parseInt(split[4]);
						int red = Integer.parseInt(split[5]);
						int green = Integer.parseInt(split[6]);
						int blue = Integer.parseInt(split[7]);

						Point startPoint = new Point(startX, startY);
						Point endPoint = new Point(endX, endY);
						Color color = new Color(red, green, blue);

						Line lineObj = new Line(startPoint, endPoint, color);
						list.add(lineObj);
					} else if (split[0].equals("CIRCLE")) {
						if (split.length != 7) {
							throw new IllegalArgumentException();
						}

						int startX = Integer.parseInt(split[1]);
						int startY = Integer.parseInt(split[2]);
						int radius = Integer.parseInt(split[3]);
						int red = Integer.parseInt(split[4]);
						int green = Integer.parseInt(split[5]);
						int blue = Integer.parseInt(split[6]);

						Point startPoint = new Point(startX, startY);
						Color color = new Color(red, green, blue);

						Circle circle = new Circle(startPoint, radius, color);
						list.add(circle);
					} else if (split[0].equals("FCIRCLE")) {
						if (split.length != 10) {
							throw new IllegalArgumentException();
						}

						int startX = Integer.parseInt(split[1]);
						int startY = Integer.parseInt(split[2]);
						int radius = Integer.parseInt(split[3]);
						int red = Integer.parseInt(split[4]);
						int green = Integer.parseInt(split[5]);
						int blue = Integer.parseInt(split[6]);
						int redB = Integer.parseInt(split[7]);
						int greenB = Integer.parseInt(split[8]);
						int blueB = Integer.parseInt(split[9]);

						Point startPoint = new Point(startX, startY);
						Color color = new Color(red, green, blue);
						Color bgColor = new Color(redB, greenB, blueB);
						FilledCircle circle = new FilledCircle(startPoint, radius, color, bgColor);
						list.add(circle);

					} else {
						throw new IllegalArgumentException();
					}
				} catch (NumberFormatException ex) {
					throw new IllegalArgumentException();
				}

			}
			return list;
		}

	};

}
