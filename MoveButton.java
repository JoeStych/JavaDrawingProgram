import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class MoveButton extends JButton implements ActionListener {
    private JPanel drawingPanel;
    private View view;
    private MouseHandler mouseHandler;
    private MoveCommand moveCommand;
    private UndoManager undoManager;

    public MoveButton(UndoManager undoManager, View view, JPanel drawingPanel) {
        super("Move");
        addActionListener(this);
        this.view = view;
        this.drawingPanel = drawingPanel;
        this.undoManager = undoManager;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        moveCommand = new MoveCommand();
        drawingPanel.addMouseListener(mouseHandler);
        drawingPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    private class MouseHandler extends MouseAdapter {
        private Point startPoint;

        public void mousePressed(MouseEvent event) {
            startPoint = View.mapPoint(event.getPoint());
        }

        public void mouseReleased(MouseEvent event) {
            Point endPoint = View.mapPoint(event.getPoint());
            int deltaX = endPoint.x - startPoint.x;
            int deltaY = endPoint.y - startPoint.y;

            if (moveCommand.setTranslation(deltaX, deltaY)) {
                undoManager.beginCommand(moveCommand);
                drawingPanel.removeMouseListener(this);
                undoManager.endCommand(moveCommand);
            } else {
                undoManager.cancelCommand();
            }

            drawingPanel.setCursor(Cursor.getDefaultCursor());
        }
    }
}