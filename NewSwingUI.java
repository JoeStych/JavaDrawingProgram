import java.awt.*;
public class NewSwingUI implements UIContext {
  private Graphics graphics;
  private static NewSwingUI swingUI;
  private NewSwingUI() {
  }
  public static NewSwingUI getInstance() {
    if (swingUI == null) {
      swingUI = new NewSwingUI();
    }
    return swingUI;
  }
  public  void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }
  public void drawLabel(String s, Point p) {
    if (p != null) {
      if (s != null) {
        graphics.drawString(s, (int) p.getX(), (int) p.getY());
      }
    }
    int length = graphics.getFontMetrics().stringWidth(s);
    graphics.drawString("_", (int)p.getX() + length, (int)p.getY());
  }
  public void drawLine(Point point1,  Point point2) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (point1 != null) {
      i1 = Math.round((float) (point1.getX()));
      i2 = Math.round((float) (point1.getY()));
      if (point2 != null) {
        i3 = Math.round((float) (point2.getX()));
        i4 = Math.round((float) (point2.getY()));
      } else {
        i3 = i1;
        i4 = i2;
      }
      graphics.drawLine(i1, i2, i3, i4);
    }
  }
  public void drawTriangle(Point point1, Point point2, Point point3) {
        if (point1 != null && point2 != null && point3 != null) {
            int x1 = Math.round((float) point1.getX());
            int y1 = Math.round((float) point1.getY());
            int x2 = Math.round((float) point2.getX());
            int y2 = Math.round((float) point2.getY());
            int x3 = Math.round((float) point3.getX());
            int y3 = Math.round((float) point3.getY());

            int[] xPoints = { x1, x2, x3 };
            int[] yPoints = { y1, y2, y3 };

            graphics.drawPolygon(xPoints, yPoints, 3);
        }
    }

    public void drawPolygon(java.util.List<Point> points) {
      if (points != null && points.size() >= 3) {
          int[] xPoints = new int[points.size()];
          int[] yPoints = new int[points.size()];

          for (int i = 0; i < points.size(); i++) {
              xPoints[i] = Math.round((float) points.get(i).getX());
              yPoints[i] = Math.round((float) points.get(i).getY());
          }

          graphics.drawPolygon(xPoints, yPoints, points.size());
      }
  }
 
}
