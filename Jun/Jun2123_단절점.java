package Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Jun2123_단절점 {
  static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
  static int[] label;
  static int order = 1;
  static ArrayList<Integer> articulationPoints = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());

    label = new int[v];

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
  }

  static void dfs(int current, int prev) {
    label[current] = order++;
    int min = label[current];

    for (Edge edge : graph.get(current)) {
      if (edge.to == prev) {
        continue;
      }

      if (label[edge.to] == 0) {
        dfs(edge.to, current);

        if ()
      }
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