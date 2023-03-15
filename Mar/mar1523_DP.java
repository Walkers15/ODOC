// https://www.acmicpc.net/problem/9465

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class mar1523_DP {
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());

    while (t-- > 0) {
      test(br);
    }

    System.out.print(sb);
  }

  public static void test(BufferedReader br) throws NumberFormatException, IOException {
    int n = Integer.parseInt(br.readLine());
    int[][] map = new int[2][n + 1];
    map[0][0] = 0;
    map[1][0] = 0;

    int[][] result = new int[2][n + 1];

    for (int x = 0; x < 2; x++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= n; i++) {
        map[x][i] = Integer.parseInt(st.nextToken());
      }
    }

    result[0][0] = 0;
    result[1][0] = 0;
    result[0][1] = map[0][1];
    result[1][1] = map[1][1];

    for (int i = 2; i <= n; i++) {
      result[0][i] = Math.max(result[1][i - 2], result[1][i - 1]) + map[0][i];
      result[1][i] = Math.max(result[0][i - 2], result[0][i - 1]) + map[1][i];
    }

    sb.append(Math.max(result[0][n], result[1][n]) + "\n");
  }
}
