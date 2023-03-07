// https://www.acmicpc.net/problem/11404

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mar0823_FloydWarshall {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());
    int m = Integer.parseInt(br.readLine());

    int[][] map = new int[n][n];
    int[][] dist = new int[n][n];

    for (int i = 0; i < n; i++) {
      // Arrays.fill(map[i], Integer.MAX_VALUE);
      Arrays.fill(map[i], 1000000000);
      map[i][i] = 0;
    }

    for (int i = 0; i < m; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken()) - 1;
      int to = Integer.parseInt(st.nextToken()) - 1;
      int cost = Integer.parseInt(st.nextToken());

      if (map[from][to] > cost) {
        // 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
        map[from][to] = cost;
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        dist[i][j] = map[i][j];
      }
    }

    // 플로이드 와샬
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (dist[i][j] > dist[i][k] + dist[k][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (dist[i][j] >= 1000000000) {
          sb.append(0 + " ");
        } else {
          sb.append(dist[i][j] + " ");
        }

      }
      sb.append("\n");
    }

    System.out.print(sb);
  }
}
