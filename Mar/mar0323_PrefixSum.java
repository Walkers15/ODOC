// https://www.acmicpc.net/problem/11660

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class mar0323_PrefixSum {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[][] map = new int[n + 1][n + 1];
    int[][] sum = new int[n + 1][n + 1];

    for (int i = 0; i <= n; i++) {
      map[0][i] = 0;
      map[i][0] = 0;
      sum[0][i] = 0;
      sum[i][0] = 0;
    }
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        sum[i][j] = map[i][j] + sum[i][j - 1];
      }
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        sum[i][j] += sum[i - 1][j];
      }
    }
    // System.out.println();

    // for (int i = 0; i <= n; i++) {
    // for (int j = 0; j <= n; j++) {
    // System.out.print(sum[i][j] + " ");
    // }
    // System.out.println();
    // }

    StringBuilder sb = new StringBuilder();
    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());

      int result = sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
      sb.append(result + "\n");
    }

    System.out.print(sb);
  }
}
