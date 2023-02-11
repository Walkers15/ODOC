// https://www.acmicpc.net/problem/2630

import java.util.Scanner;

public class feb0823_DAC {
  public static int white = 0;
  public static int blue = 0;
  public static boolean[][] paper;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    paper = new boolean[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (sc.nextInt() == 1) {
          paper[i][j] = true;
        } else {
          paper[i][j] = false;
        }
      }
    }

    check(0, n, 0, n);

    System.out.println(white + "\n" + blue);
    sc.close();
  }

  public static void check(int x1, int x2, int y1, int y2) {
    boolean allCheck = true;
    boolean start = paper[x1][y1];

    for (int i = x1; i < x2; i++) {
      for (int j = y1; j < y2; j++) {
        if (start != paper[i][j]) {
          allCheck = false;
          break;
        }
      }
      if (allCheck == false) {
        break;
      }
    }

    if (allCheck == true) {
      if (start) {
        blue++;
      } else {
        white++;
      }
    } else {
      // 그냥 Math.round((x1 + x2) / 2) 하면 (x1 + x2) / 2 자체가 int로 인식되어버려 3.5 -> 4가 되어야
      // 하는 상황에서 3이 되어버림, 그래서 인자 중 하나를 float로 casting하여 문제 해결
      int midX = Math.round(((float) (x1) + x2) / 2);
      int midY = Math.round((float) (y1 + y2) / 2);

      // 종이를 사등분하여 다시 check
      // 제 1사분면
      check(x1, midX, y1, midY);
      // 제 2사분면
      check(midX, x2, y1, midY);
      // 제 3사분면
      check(x1, midX, midY, y2);
      // 제 4사분면
      check(midX, x2, midY, y2);
    }
    return;
  }
}
