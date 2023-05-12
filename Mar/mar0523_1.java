// https://www.acmicpc.net/problem/10830 행렬 제곱
package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class mar0523_1 {
  public static int n;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    long b = Long.parseLong(st.nextToken());

    int[][] mt = new int[n][n];
    int[][] result = new int[n][n];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        mt[i][j] = Integer.parseInt(st.nextToken()) % 1000;
      }
      result[i][i] = 1;
    }

    while (b > 0) {
      if (b % 2 == 1) {
        result = multiply(result, mt);
      }

      mt = multiply(mt, mt);
      b /= 2;
    }

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        sb.append(result[i][j] + " ");
      }
      sb.append("\n");
    }

    System.out.print(sb);
  }

  public static int[][] multiply(int[][] mt1, int[][] mt2) {
    int[][] result = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          result[i][j] += mt1[i][k] * mt2[k][j];
          result[i][j] %= 1000;
        }
      }
    }

    return result;
  }
}
