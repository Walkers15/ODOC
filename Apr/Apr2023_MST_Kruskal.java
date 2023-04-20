// https://www.acmicpc.net/problem/1197 최소 스패닝 트리
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Apr2023_MST_Kruskal {
  static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());

    root = new int[v];
    for (int i = 0; i < v; i++) {
      root[i] = i;
    }

    ArrayList<Edge> edgeList = new ArrayList<>(e);

    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int v1 = Integer.parseInt(st.nextToken()) - 1;
      int v2 = Integer.parseInt(st.nextToken()) - 1;
      int cost = Integer.parseInt(st.nextToken());
      if (v1 < v2) {
        edgeList.add(new Edge(v1, v2, cost));
      } else {
        edgeList.add(new Edge(v2, v1, cost));
      }

    }

    Collections.sort(edgeList);

    int selected = 0;
    int result = 0;

    for (int i = 0; i < e && selected != -1; i++) {
      Edge current = edgeList.get(i);
      if (find(current.v1) != find(current.v2)) {
        result += current.cost;
        union(current.v1, current.v2);
      }
    }

    System.out.println(result);
  }

  static class Edge implements Comparable<Edge> {
    int v1;
    int v2;
    int cost;

    Edge(int v1, int v2, int cost) {
      this.v1 = v1;
      this.v2 = v2;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
      return this.cost - o.cost;
    }
  }

  static void union(int v1, int v2) {
    int root1 = find(v1);
    int root2 = find(v2);

    if (root1 != root2) {
      root[root2] = root1;
    }
  }

  static int find(int index) {
    if (root[index] == index) {
      return index;
    }
    root[index] = find(root[index]);
    return root[index];
  }
}