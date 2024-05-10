import java.awt.*;

public class TriangleCommand extends Command {
    private TriangleItem triangleItem;
    private Point[] trianglePoints;

    public TriangleCommand(Point[] trianglePoints) {
        this.trianglePoints = trianglePoints;
        triangleItem = new TriangleItem(trianglePoints);
    }

    @Override
    public boolean undo() {
        model.removeItem(triangleItem);
        return true;
    }

    @Override
    public boolean redo() {
        execute();
        return true;
    }

    @Override
    public void execute() {
        triangleItem = new TriangleItem(trianglePoints);
        model.addItem(triangleItem);
    }

    private boolean isValidTriangle() {
        // Check if the trianglePoints form a valid triangle
        if (trianglePoints == null || trianglePoints.length != 3) {
            return false; // Invalid if not exactly three points
        }

        Point p1 = trianglePoints[0];
        Point p2 = trianglePoints[1];
        Point p3 = trianglePoints[2];

        // Check if the points are distinct
        return !p1.equals(p2) && !p1.equals(p3) && !p2.equals(p3);
    }

    @Override
    public boolean end() {
        if (!isValidTriangle()) {
            undo(); // If the triangle is not valid, undo the command
            return false;
        }
        return true;
    }
}