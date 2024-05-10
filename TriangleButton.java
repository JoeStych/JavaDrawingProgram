import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TriangleButton extends JButton implements ActionListener {
    private JPanel drawingPanel;
    private View view;
    private MouseHandler mouseHandler;
    private TriangleCommand triangleCommand;
    private UndoManager undoManager;

    public TriangleButton(UndoManager undoManager, View view, JPanel drawingPanel) {
        super("Triangle");
        this.undoManager = undoManager;
        addActionListener(this);
        this.view = view;
        this.drawingPanel = drawingPanel;
        this.mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        private int pointCount = 0;
        private Point[] trianglePoints = new Point[3];

        public void mouseClicked(MouseEvent event) {
            Point currentPoint = View.mapPoint(event.getPoint());

            if (pointCount < 2) {
                trianglePoints[pointCount] = currentPoint;
                pointCount++;
            } else if (pointCount == 2) {
                trianglePoints[pointCount] = currentPoint;
                pointCount = 0;

                triangleCommand = new TriangleCommand(trianglePoints);
                undoManager.beginCommand(triangleCommand);

                drawingPanel.removeMouseListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(triangleCommand);
            }
        }
    }
}