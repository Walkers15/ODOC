// https://www.acmicpc.net/problem/11049 행렬 곱셈 순서
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Apr0123_DP {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    int[][] mat = new int[n][2];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      mat[i][0] = Integer.parseInt(st.nextToken());
      mat[i][1] = Integer.parseInt(st.nextToken());
    }

    int[][] dp = new int[n][n];

    for (int gap = 1; gap < n; gap++) {
      // 구간 범위의 크기 / dp[1][3], dp[2][4], dp[10][12] 등은 gap이 2임
      for (int i = 0; i < n - gap; i++) {
        int j = i + gap;
        dp[i][j] = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
          // i ~ k까지의 행렬곱 최솟값 + (k + 1) ~ j 까지의 행렬곱 최솟값
          // dp 배열의 합 계산을 통해 두 개의 행렬을 만듦
          // 그럼 i행 * k열 행렬과 k행 j열 행렬의 곱이므로 i행 * k열 * j열을 더하면 총 계산량이 됨
          dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + mat[i][0] * mat[k][1] * mat[j][1]);
        }
      }
    }

    System.out.println(dp[0][n - 1]);
  }
}
