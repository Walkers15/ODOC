// https://www.acmicpc.net/problem/11438 LCA 2

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Apr2523_LCA {
  static int n;
  static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  static int[][] parent;
  static int[] depth;
  static int MAX_HEIGHT;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());

    depth = new int[n + 1];
    MAX_HEIGHT = (int) Math.ceil(Math.log(n) / Math.log(2));
    parent = new int[n + 1][MAX_HEIGHT + 1];
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < n - 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    depth[0] = 0;
    dfs(1, 0);

    // for (int i = 0; i <= n; i++) {
    // System.out.print(depth[i] + " ");
    // }
    // System.out.println();

    for (int j = 1; j <= MAX_HEIGHT; j++) {
      for (int i = 1; i <= n; i++) {
        parent[i][j] = parent[parent[i][j - 1]][j - 1];
      }
    }

    // StringBuilder test = new StringBuilder();
    // for (int i = 1; i <= n; i++) {
    // for (int j = 0; j <= MAX_HEIGHT; j++) {
    // test.append(parent[i][j] + " ");
    // }
    // test.append("\n");
    // }
    // System.out.println(test);

    int m = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    while (m-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      sb.append(query_lca(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) + "\n");
    }

    System.out.print(sb);
  }

  public static void dfs(int node, int prev) {
    parent[node][0] = prev;
    depth[node] = depth[prev] + 1;
    for (int next : graph.get(node)) {
      if (next != prev) {
        dfs(next, node);
      }
    }
  }

  public static int query_lca(int a, int b) {
    int lca = 0;

    // System.out.printf("a %d b %d depth[a] %d depth[b] %d\n", a, b, depth[a],
    // depth[b]);
    if (depth[a] != depth[b]) {
      if (depth[a] > depth[b]) {
        int temp = a;
        a = b;
        b = temp;
      }

      // a와 b의 depth가 같아질때까지 b의 depth을 binary lift 해줌
      for (int i = MAX_HEIGHT; i >= 0; i--) {
        if (depth[a] <= depth[parent[b][i]]) {
          b = parent[b][i];
        }
      }
    }
    lca = a;
    if (a != b) {
      // 부모를 하나씩 타고 올라가면서 공통 조상이 나올때까지 탐색
      for (int i = MAX_HEIGHT; i >= 0 && a != b; i--) {
        if (parent[a][i] != parent[b][i]) {
          a = parent[a][i];
          b = parent[b][i];
        }
        lca = parent[a][i];
      }

    }
    return lca;
  }
}
