import java.awt.*;
import java.util.ArrayList;

public class TriangleItem extends Item {
    private Point[] trianglePoints;

    public TriangleItem(Point[] trianglePoints) {
        if (trianglePoints == null || trianglePoints.length != 3) {
            throw new IllegalArgumentException("TriangleItem requires an array of 3 points");
        }
        this.trianglePoints = trianglePoints.clone();
    }

    @Override
    public boolean includes(Point point) {
        // Check if the point is inside the triangle using barycentric coordinates
        double x = point.getX();
        double y = point.getY();

        double x1 = trianglePoints[0].getX();
        double y1 = trianglePoints[0].getY();
        double x2 = trianglePoints[1].getX();
        double y2 = trianglePoints[1].getY();
        double x3 = trianglePoints[2].getX();
        double y3 = trianglePoints[2].getY();

        double denominator = (y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3);

        double alpha = ((y2 - y3) * (x - x3) + (x3 - x2) * (y - y3)) / denominator;
        double beta = ((y3 - y1) * (x - x3) + (x1 - x3) * (y - y3)) / denominator;
        double gamma = 1 - alpha - beta;

        return alpha >= 0 && beta >= 0 && gamma >= 0;
    }

    @Override
    public void render(UIContext uiContext) {
        uiContext.drawLine(trianglePoints[0], trianglePoints[1]);
        uiContext.drawLine(trianglePoints[1], trianglePoints[2]);
        uiContext.drawLine(trianglePoints[2], trianglePoints[0]);
    }

    @Override
    public void translate(int deltaX, int deltaY) {
        for (Point point : trianglePoints) {
            point.setLocation(point.getX() + deltaX, point.getY() + deltaY);
        }
    }
}