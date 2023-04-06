// https://www.acmicpc.net/problem/2239 스도쿠
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Apr0623_BT {
  static boolean[][] rowCheck = new boolean[9][10];
  static boolean[][] colCheck = new boolean[9][10];
  static boolean[][] squareCheck = new boolean[9][10];

  static int square(int x, int y) { // 몇번째 작은 사각형인지 계산
    return (x / 3) * 3 + y / 3;
  }

  public static void go(int[][] a, int cnt) {
    if (cnt == 81) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 9; i++) {
        Arrays.stream(a[i]).forEach(num -> sb.append(num));
        sb.append("\n");
      }
      System.out.print(sb);
      System.exit(0); // 더 이상 재귀가 돌지 않도록 프로그램 종료, 없으면 시간초과
    }
    int x = cnt / 9; // 행 구하기
    int y = cnt % 9; // 열 구하기

    if (a[x][y] != 0) {
      go(a, cnt + 1);
    } else {
      for (int k = 1; k <= 9; k++) {
        if (!rowCheck[x][k] && !colCheck[y][k] && !squareCheck[square(x, y)][k]) { // 3가지 경우 모두 k가 없을 경우
          rowCheck[x][k] = true; // 다시 방문하지 않기위한
          colCheck[y][k] = true;
          squareCheck[square(x, y)][k] = true;
          a[x][y] = k; // 0을 k로 바꿈
          go(a, cnt + 1); // 다음 단계
          // 백트래킹을 위한
          a[x][y] = 0;
          rowCheck[x][k] = false;
          colCheck[y][k] = false;
          squareCheck[square(x, y)][k] = false;
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[][] a = new int[9][9];
    for (int i = 0; i < 9; i++) {
      String line = br.readLine();
      for (int j = 0; j < 9; j++) {
        a[i][j] = Character.getNumericValue(line.charAt(j));
        // System.out.println(a[i][j]);
        if (a[i][j] != 0) {
          rowCheck[i][a[i][j]] = true;
          colCheck[j][a[i][j]] = true;
          squareCheck[square(i, j)][a[i][j]] = true;
        }
      }
    }
    go(a, 0);

  }
}
