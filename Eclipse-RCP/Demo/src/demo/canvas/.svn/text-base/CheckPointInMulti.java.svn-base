package demo.canvas;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Point;

/**
 * 判断一个点，是否在一个多边形区域内
 */
public class CheckPointInMulti {
 public boolean isPointInPolygon(Point point,Map<Integer,Point> pointMap) {
  boolean isInside = false;
  double ESP = 1e-9;
  int count = 0;
  double linePoint1x;
  double linePoint1y;
  double linePoint2x = 180;
  double linePoint2y;

  linePoint1x = point.x;
  linePoint1y = point.y;
  linePoint2y = point.y;

  for (int i = 1; i <= pointMap.size()-1; i++) {
   double cx1 = pointMap.get(i).x;
   double cy1 = pointMap.get(i).y;
   double cx2 = pointMap.get(i + 1).x;
   double cy2 = pointMap.get(i + 1).y;
   if (isPointOnLine(point.x, point.y, cx1, cy1, cx2, cy2)) {
    return true;
   }
   if (Math.abs(cy2 - cy1) < ESP) {
    continue;
   }

   if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x,
     linePoint2y)) {
    if (cy1 > cy2)
     count++;
   } else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y,
     linePoint2x, linePoint2y)) {
    if (cy2 > cy1)
     count++;
   } else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x,
     linePoint1y, linePoint2x, linePoint2y)) {
    count++;
   }
  }
  System.out.println(count);
  if (count % 2 == 1) {
   isInside = true;
  }

  return isInside;
 }

 public double Multiply(double px0, double py0, double px1, double py1,
   double px2, double py2) {
  return ((px1 - px0) * (py2 - py0) - (px2 - px0) * (py1 - py0));
 }

 public boolean isPointOnLine(double px0, double py0, double px1,
   double py1, double px2, double py2) {
  boolean flag = false;
  double ESP = 1e-9;
  if ((Math.abs(Multiply(px0, py0, px1, py1, px2, py2)) < ESP)
    && ((px0 - px1) * (px0 - px2) <= 0)
    && ((py0 - py1) * (py0 - py2) <= 0)) {
   flag = true;
  }
  return flag;
 }

 public boolean isIntersect(double px1, double py1, double px2, double py2,
   double px3, double py3, double px4, double py4) {
  boolean flag = false;
  double d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3);
  if (d != 0) {
   double r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3))
     / d;
   double s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1))
     / d;
   if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {
    flag = true;
   }
  }
  return flag;
 }
 
 public static void main(String[] args) {
		Point point = new Point(11, 30);
		Map<Integer,Point> pointMap = new HashMap<Integer, Point>();
		pointMap.put(1, new Point(112, 33));
		pointMap.put(2, new Point(113, 31));
		pointMap.put(3, new Point(114, 32));
		CheckPointInMulti test = new CheckPointInMulti();
		System.out.println(test.isPointInPolygon(point, pointMap));
	}
}