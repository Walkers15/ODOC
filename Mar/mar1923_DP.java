// https://www.acmicpc.net/problem/9184 신나는 함수 실행
// 메모이제이션 기본 예제

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class mar1923_DP {
  static int[][][] dp = new int[51][51][51];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      if (a == b && b == c && c == -1) {
        break;
      }
      sb.append(String.format("w(%d, %d, %d) = %d\n", a, b, c, w(a, b, c)));
    }

    System.out.println(sb);
  }

  public static int w(int a, int b, int c) {

    if (a <= 0 || b <= 0 || c <= 0) {
      return 1;
    }

    if (a > 20 || b > 20 || c > 20) {
      return w(20, 20, 20);
    }

    if (a < b && b < c) {
      if (dp[a][b][c] == 0) {
        dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
      }
      return dp[a][b][c];
    }

    if (dp[a][b][c] == 0) {
      dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }
    return dp[a][b][c];
  }
}
