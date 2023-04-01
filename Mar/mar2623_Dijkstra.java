// https://www.acmicpc.net/problem/1916 최소비용 구하기

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class mar2623_Dijkstra {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int m = Integer.parseInt(br.readLine());

    ArrayList<PriorityQueue<Edge>> graph = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      graph.add(new PriorityQueue<>());
    }

    for (int i = 0; i < m; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(st.nextToken()) - 1;
      int to = Integer.parseInt(st.nextToken()) - 1;
      int cost = Integer.parseInt(st.nextToken());

      graph.get(from).add(new Edge(to, cost));
    }

    StringTokenizer st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken()) - 1;
    int end = Integer.parseInt(st.nextToken()) - 1;

    // Dijkstra
    int[] dist = new int[n];
    int MAX = 1000000000;
    Arrays.fill(dist, MAX);

    dist[start] = 0;

    PriorityQueue<Edge> queue = new PriorityQueue<>();
    queue.add(new Edge(start, 0));
    while (!queue.isEmpty()) {
      Edge now = queue.poll();
      for (Edge edge : graph.get(now.to)) {
        if (dist[now.to] < now.cost) {
          continue;
        }
        if (dist[edge.to] > now.cost + edge.cost) {
          dist[edge.to] = now.cost + edge.cost;
          queue.add(new Edge(edge.to, dist[edge.to]));
        }

      }
    }

    System.out.println(dist[end]);
  }

  static class Edge implements Comparable<Edge> {
    int to;
    int cost;

    Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
      return this.cost - e.cost;
    }
  }
}
