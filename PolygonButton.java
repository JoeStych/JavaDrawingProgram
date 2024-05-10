import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PolygonButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private PolygonCommand polygonCommand;
    private UndoManager undoManager;

    public PolygonButton(UndoManager undoManager, View view, JPanel drawingPanel) {
        super("Polygon");
        this.undoManager = undoManager;
        addActionListener(this);
        this.view = view;
        this.drawingPanel = drawingPanel;
        mouseHandler = new MouseHandler();
        polygonCommand = new PolygonCommand();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
        undoManager.beginCommand(polygonCommand);
    }

    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            Point currentPoint = View.mapPoint(event.getPoint());
    
            int button = event.getButton();
    
            if (SwingUtilities.isRightMouseButton(event)) {
                // Right-click: close the polygon
                if (polygonCommand.getPointCount() >= 3) {
                    polygonCommand.setPolygonPoint(polygonCommand.getPoints().get(0));
                    drawingPanel.repaint();
                    drawingPanel.removeMouseListener(this);
                    view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    undoManager.endCommand(polygonCommand);
                }
            } else {
                // Left-click: continue adding points to the polygon
                polygonCommand.setPolygonPoint(currentPoint);
                drawingPanel.repaint(); // Repaint the drawing panel after each point is added
            }
        }
    }
}