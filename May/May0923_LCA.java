// https://www.acmicpc.net/problem/3176 도로 네트워크

package May;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class May0923_LCA {
  static int[][] parent;
  static int[][] max;
  static int[][] min;
  static int[] depth;
  static int MAX_HEIGHT;
  static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
  static boolean[] visited;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    MAX_HEIGHT = (int) (Math.ceil(Math.log(n) / Math.log(2)));

    parent = new int[n + 1][MAX_HEIGHT + 1];
    max = new int[n + 1][MAX_HEIGHT + 1];
    min = new int[n + 1][MAX_HEIGHT + 1];
    depth = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < n - 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int v1 = Integer.parseInt(st.nextToken());
      int v2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      graph.get(v1).add(new Edge(v2, cost));
      graph.get(v2).add(new Edge(v1, cost));
    }

    // 트리 구성
    visited = new boolean[n + 1];
    for (int i = 1; i <= n; i++) {
      if (!visited[i]) {
        visited[i] = true;
        dfs(i, 0, 0);
      }
    }

    for (int j = 1; j <= MAX_HEIGHT; j++) {
      for (int i = 1; i <= n; i++) {
        int temp = parent[i][j - 1];
        parent[i][j] = parent[temp][j - 1];
        max[i][j] = Math.max(max[temp][j - 1], max[i][j - 1]);
        min[i][j] = Math.min(min[temp][j - 1], min[i][j - 1]);
      }
    }

    int k = Integer.parseInt(br.readLine());
    while (k > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int v1 = Integer.parseInt(st.nextToken());
      int v2 = Integer.parseInt(st.nextToken());
      query_lca(v1, v2);
      k--;
    }

    System.out.print(sb);
  }

  public static void query_lca(int a, int b) {
    if (depth[a] < depth[b]) {
      // a의 depth가 항상 b보다 크도록 설정
      int temp = a;
      a = b;
      b = temp;
    }

    int minCost = Integer.MAX_VALUE;
    int maxCost = -1;

    if (depth[a] != depth[b]) {
      for (int i = MAX_HEIGHT; i >= 0; i--) {
        if (depth[parent[a][i]] >= depth[b]) {
          maxCost = Math.max(maxCost, max[a][i]);
          minCost = Math.min(minCost, min[a][i]);

          a = parent[a][i];

        }
      }
    }

    if (a != b) {
      for (int i = MAX_HEIGHT; i >= 0; i--) {
        if (parent[a][i] != parent[b][i]) {
          maxCost = Math.max(maxCost, max[a][i]);
          maxCost = Math.max(maxCost, max[b][i]);
          minCost = Math.min(minCost, min[a][i]);
          minCost = Math.min(minCost, min[b][i]);
          a = parent[a][i];
          b = parent[b][i];
        }
      }

      maxCost = Math.max(maxCost, max[a][0]);
      maxCost = Math.max(maxCost, max[b][0]);
      minCost = Math.min(minCost, min[a][0]);
      minCost = Math.min(minCost, min[b][0]);
    }

    sb.append(minCost + " " + maxCost + "\n");
  }

  public static void dfs(int node, int prev, int cost) {
    parent[node][0] = prev;
    max[node][0] = cost;
    min[node][0] = cost;
    depth[node] = depth[prev] + 1;

    for (Edge next : graph.get(node)) {
      if (next.to != prev && !visited[next.to]) {
        visited[next.to] = true;
        dfs(next.to, node, next.cost);
      }
    }
  }

  static class Edge {
    int to;
    int cost;

    Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }
}
