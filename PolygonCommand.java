import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class PolygonCommand extends Command {
    private PolygonItem polygon;
    private int pointCount;
    private ArrayList<Point> points;

    public PolygonCommand() {
        polygon = new PolygonItem();
        pointCount = 0;
        points = new ArrayList<>();
    }

    public void setPolygonPoint(Point point) {
        points.add(point);
        polygon.addPoint(point);
        // Update the view with the partial polygon
        model.setChanged();
    }

    @Override
    public void execute() {
        model.addItem(polygon);
    }

    @Override
    public boolean undo() {
        model.removeItem(polygon);
        return true;
    }

    @Override
    public boolean redo() {
        execute();
        return true;
    }

    @Override
    public boolean end() {
        if (polygon.getPointCount() < 2) {
            undo();
            return false;
        }
        return true;
    }

    public void mouseClicked(MouseEvent e) {
        Point currentPoint = e.getPoint();
        setPolygonPoint(currentPoint);
        execute();
    }

    public void mousePressed(MouseEvent e) {
        // Additional logic if needed for mouse press event
    }

    public int getPointCount() {
        return points.size();
    }

    public ArrayList<Point> getPoints() {
        return new ArrayList<>(points);
    }
}