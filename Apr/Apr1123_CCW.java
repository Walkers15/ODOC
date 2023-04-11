// https://www.acmicpc.net/problem/17387 선분 교차

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Apr1123_CCW {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    Point a = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
    Point b = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

    st = new StringTokenizer(br.readLine());
    Point c = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
    Point d = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

    int p = ccw(a, b, c) * ccw(a, b, d);
    int q = ccw(c, d, a) * ccw(c, d, b);
    if (p == q && q == 0) {
      if (a.y == b.y && b.y == c.y && c.y == d.y) {
        long leftAB = Math.min(a.x, b.x);
        long rightAB = Math.max(a.x, b.x);
        long leftCD = Math.min(c.x, d.x);
        long rightCD = Math.max(c.x, d.x);

        if (leftAB <= rightCD && rightAB >= leftCD) {
          System.out.println(1);
        } else {
          System.out.println(0);
        }
      } else {
        long downAB = Math.min(a.y, b.y);
        long upAB = Math.max(a.y, b.y);
        long downCD = Math.min(c.y, d.y);
        long upCD = Math.max(c.y, d.y);

        if (downAB <= upCD && upAB >= downCD) {
          System.out.println(1);
        } else {
          System.out.println(0);
        }
      }
    } else if (p <= 0 && q <= 0) {
      System.out.println(1);
    } else {
      System.out.println(0);
    }
  }

  static int ccw(Point p1, Point p2, Point p3) {
    // 외적 (신발끈)
    long result = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y);

    if (result > 0) {
      return 1;
    } else if (result == 0) {
      return 0;
    } else {
      return -1;
    }
  }

  static class Point {
    long x;
    long y;

    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }
  }
}