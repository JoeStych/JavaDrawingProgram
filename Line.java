import java.awt.*;
public class Line extends Item {
  private Point point1;
  private Point point2;
  public Line(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
  }
  public Line(Point point1) {
    this.point1 = point1;
	point2 = null;
  }
  public Line() {
	  point1 = null;
	  point2 = null;
  }
  public boolean includes(Point point) {
    return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0));
  }
  public void render(UIContext uiContext) {
    uiContext.drawLine(point1, point2);
  }
  public void setPoint1(Point point) {
    point1 = point;
  }
  public void setPoint2(Point point) {
    point2 = point;
  }
  public Point getPoint1() {
    return point1;
  }
  public Point getPoint2() {
    return point2;
  }
  public String toString() {
    return "Line  from " + point1 + " to " + point2;
  }

  public void translate(int deltaX, int deltaY) {
      // Translate both points by deltaX and deltaY
      if (point1 != null) {
          point1.setLocation(point1.getX() + deltaX, point1.getY() + deltaY);
      }
      if (point2 != null) {
          point2.setLocation(point2.getX() + deltaX, point2.getY() + deltaY);
      }
  }
}

