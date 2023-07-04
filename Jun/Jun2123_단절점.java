// https://www.acmicpc.net/problem/11266 단절점
package Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Jun2123_단절점 {
  static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
  static int[] label;
  static int[] low;
  static boolean[] isArticulationPoint;
  static int order = 1;
  static ArrayList<Integer> articulationPoints = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());

    label = new int[v];
    low = new int[v];
    isArticulationPoint = new boolean[v];

    for (int i = 0; i < v; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken()) - 1;
      int to = Integer.parseInt(st.nextToken()) - 1;

      graph.get(from).add(new Edge(from, to));
      graph.get(to).add(new Edge(to, from));
    }

    for (int i = 0; i < v; i++) {
      if (label[i] == 0) {
        dfs(i, -1);
      }
    }

    StringBuilder sb = new StringBuilder();
    int count = 0;
    for (int i = 0; i < v; i++) {
      if (isArticulationPoint[i]) {
        count++;
        sb.append((i + 1) + " ");
      }
    }

    if (count == 0) {
      System.out.print(0);
      return;
    }

    sb.insert(0, count + "\n");
    System.out.print(sb);
  }

  static void dfs(int current, int prev) {
    label[current] = order++;
    low[current] = label[current];
    int child = 0;
    boolean isAP = false;
    for (Edge edge : graph.get(current)) {
      if (edge.to == prev) {
        continue;
      }

      if (label[edge.to] == 0) {
        child++;
        dfs(edge.to, current);
        low[current] = Math.min(low[current], low[edge.to]);
        if (label[current] <= low[edge.to]) {
          isAP = true;
        }
      } else {
        low[current] = Math.min(low[current], label[edge.to]);
      }
    }

    if ((prev == -1 && child > 1) || (prev != -1 && isAP == true)) {
      isArticulationPoint[current] = true;
    }
  }

  static class Edge {
    int from;
    int to;

    Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }
}