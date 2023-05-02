// https://www.acmicpc.net/problem/1761 정점들의 거리
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Apr2623_LCA {
  static int n;
  static int k;
  static int[][] parent;
  static int[][] dist;
  static int[] depth;
  static ArrayList<ArrayList<Edge>> graph;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    k = (int) Math.ceil(Math.log(n) / Math.log(2));
    graph = new ArrayList<>();

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

    parent = new int[n + 1][k + 1];
    depth = new int[n + 1];
    dist = new int[n + 1][k + 1];

    dfs(1, 0, 0);

    for (int j = 1; j <= k; j++) {
      for (int i = 1; i <= n; i++) {
        parent[i][j] = parent[parent[i][j - 1]][j - 1];
        dist[i][j] = dist[i][j - 1] + dist[parent[i][j - 1]][j - 1];
      }
    }

    // StringBuilder test = new StringBuilder();
    // for (int i = 1; i <= n; i++) {
    // for (int j = 0; j <= k; j++) {
    // test.append(parent[i][j] + " ");
    // }
    // test.append("\n");
    // }
    // test.append("\n\n");
    // for (int i = 1; i <= n; i++) {
    // for (int j = 0; j <= k; j++) {
    // test.append(dist[i][j] + " ");
    // }
    // test.append("\n");
    // }
    // System.out.println(test);

    int m = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    while (m-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int v1 = Integer.parseInt(st.nextToken());
      int v2 = Integer.parseInt(st.nextToken());

      sb.append(lca_query(v1, v2) + "\n");
    }

    System.out.println(sb);
  }

  public static void dfs(int node, int prev, int cost) {
    depth[node] = depth[prev] + 1;
    parent[node][0] = prev;
    dist[node][0] = cost;
    for (Edge next : graph.get(node)) {
      if (next.to != prev) {
        dfs(next.to, node, next.cost);
      }
    }
  }

  public static int lca_query(int a, int b) {
    int sum = 0;

    // System.out.printf("a %d b %d depth[a] %d depth[b] %d\n", a, b, depth[a],
    // depth[b]);
    if (depth[a] != depth[b]) {
      if (depth[a] < depth[b]) {
        int temp = a;
        a = b;
        b = temp;
      }

      for (int i = k; i >= 0; i--) {
        // 가장 먼 조상부터 탐색, b보다 깊이가 얕아질 경우는 갱신하지 않음
        if (depth[parent[a][i]] >= depth[b]) {
          // System.out.println("add sum" + dist[a][i]);
          sum += dist[a][i];
          a = parent[a][i];
        }
      }
    }
    // System.out.printf("a %d b %d depth[a] %d depth[b] %d sum %d\n", a, b,
    // depth[a], depth[b], sum);
    if (a != b) {
      // 한칸씩 올려가면서 둘의 조상이 공통이 될때까지 찾음
      for (int i = k; i >= 0; i--) {
        if (parent[a][i] != parent[b][i]) {
          sum += dist[a][i] + dist[b][i];
          a = parent[a][i];
          b = parent[b][i];
        }
      }

      sum += dist[a][0] + dist[b][0];
    }
    return sum;
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
