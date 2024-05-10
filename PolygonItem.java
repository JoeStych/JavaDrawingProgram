import java.awt.*;
import java.util.ArrayList;

public class PolygonItem extends Item {
    private ArrayList<Point> points;

    public PolygonItem() {
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    @Override
    public boolean includes(Point point) {
        int crossings = 0;

        for (int i = 0, j = points.size() - 1; i < points.size(); j = i++) {
            if ((points.get(i).getY() > point.getY()) != (points.get(j).getY() > point.getY()) &&
                (point.getX() < (points.get(j).getX() - points.get(i).getX()) * (point.getY() - points.get(i).getY())
                        / (points.get(j).getY() - points.get(i).getY()) + points.get(i).getX())) {
                crossings++;
            }
        }

        return crossings % 2 != 0;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = new ArrayList<>(points);
    }

    @Override
    public void render(UIContext uiContext) {
        if (points.size() >= 3) {
            uiContext.drawPolygon(points);
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public int getPointCount() {
        return points.size();
    }

    @Override
    public String toString() {
        return "Polygon with " + points.size() + " points";
    }

    @Override
    public void translate(int deltaX, int deltaY) {
        for (Point point : points) {
            point.setLocation(point.getX() + deltaX, point.getY() + deltaY);
        }
    }
}