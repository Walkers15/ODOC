// https://www.acmicpc.net/problem/1708 볼록 껍질

package Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Jun0923_ConvexHull {
  static ArrayList<Point> list = new ArrayList<>();

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      list.add(new Point(x, y));
    }

    list.sort((a, b) -> {
      if (a.y == b.y) {
        return a.x - b.x > 0 ? 1 : -1;
      } else {
        return a.y - b.y > 0 ? 1 : -1;
      }
    });

    Point criteria = new Point(list.get(0).x, list.get(0).y);
    long criteriaX = criteria.x;
    long criteriaY = criteria.y;

    list.sort((a, b) -> {
      if (ccw(a, b, criteria) == 0) {
        return dist(a, criteria) > dist(b, criteria) ? 1 : -1;
      }
      double angleA = Math.atan2(a.y - criteriaY, a.x - criteriaX);
      double angleB = Math.atan2(b.y - criteriaY, b.x - criteriaX);
      return angleA > angleB ? 1 : -1;
    });

    Stack<Integer> stack = new Stack<>();
    stack.add(0);
    stack.add(1);

    int index = 2;
    while (index < n) {
      while (stack.size() >= 2) {
        int second = stack.pop();
        int first = stack.lastElement();
        if (ccw(list.get(first), list.get(second), list.get(index)) > 0) {
          stack.push(second);
          break;
        }
      }

      stack.push(index++);
    }

    System.out.println(stack.size());
  }

  static long dist(Point p1, Point p2) {
    return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
  }

  static long ccw(Point p1, Point p2, Point p3) {
    // 외적 (신발끈)
    long result = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y);
    return result;
  }

  static class Point {
    long x;
    long y;
    long angle;

    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return this.x + " " + this.y;
    }
  }
}
