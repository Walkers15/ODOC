// https://www.acmicpc.net/problem/1533 길의 개수
package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class May1223_인접행렬경우의수_정점분할 {
  // 1. 인접행렬을 X번 거듭제곱하면, 간선을 X개 거쳐서 가는 경우의 수가 나옴
  // 2. 간선에 가중치가 있는 경우에는 정점을 분할하여 행렬을 만들어줌

  static long[][] graph;
  static long[][] result;
  static int n;
  static final long MOD = 1000003;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken()) - 1;
    int end = Integer.parseInt(st.nextToken()) - 1;
    int time = Integer.parseInt(st.nextToken());

    graph = new long[n * 5][n * 5];
    result = new long[n * 5][n * 5];

    // 각 정점을 다섯개로 분할 (정점 분할)
    for (int i = 0; i < n; i++) {
      for (int j = 1; j < 5; j++) {
        graph[i * 5 + j][i * 5 + j - 1] = 1;
      }
    }

    // 행렬 제곱을 위해 결과행렬을 항등행렬로 만듦
    for (int i = 0; i < n * 5; i++) {
      result[i][i] = 1;
    }

    for (int i = 0; i < n; i++) {
      String adj = br.readLine();
      for (int j = 0; j < n; j++) {
        int cost = adj.charAt(j) - '0';
        if (cost == 1) {
          graph[i * 5][j * 5] = 1;
        } else if (cost > 1) {
          // i번 정점에서 cost - 1만큼의 시간을 보낸 후 j로 이동해야 함
          graph[i * 5][j * 5 + cost - 1] = 1;
        }
      }
    }

    // 행렬을 time번 거듭제곱해줌
    while (time > 0) {
      if (time % 2 == 1) {
        result = multiply(result, graph);
      }

      graph = multiply(graph, graph);
      time /= 2;
    }

    System.out.println(result[start * 5][end * 5]);
  }

  public static long[][] multiply(long[][] mt1, long[][] mt2) {
    long[][] result = new long[n * 5][n * 5];
    for (int i = 0; i < n * 5; i++) {
      for (int j = 0; j < n * 5; j++) {
        for (int k = 0; k < n * 5; k++) {
          result[i][j] += mt1[i][k] * mt2[k][j];
          result[i][j] %= MOD;
        }
      }
    }

    return result;
  }
}
