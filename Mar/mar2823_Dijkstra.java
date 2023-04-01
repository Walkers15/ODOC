// https://www.acmicpc.net/problem/1504 특정한 최단 경로

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class mar2823_Dijkstra {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());

    ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1;
      int b = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken());

      graph.get(a).add(new Edge(b, c));
      graph.get(b).add(new Edge(a, c));
    }

    st = new StringTokenizer(br.readLine());
    int v1 = Integer.parseInt(st.nextToken()) - 1;
    int v2 = Integer.parseInt(st.nextToken()) - 1;

    // 전략
    // s dijkstra
    // v1 dijkstra
    // v2 dijkstra
    // s -> v1 -> v2 -> e
    // s -> v2 -> v1 -> e 비용 비교

    int[] startDist = dijkstra(graph, 0);
    int[] v1Dist = dijkstra(graph, v1);
    int[] v2Dist = dijkstra(graph, v2);
    int result = Math.min(startDist[v1] + v1Dist[v2] + v2Dist[n - 1], startDist[v2] + v2Dist[v1] + v1Dist[n - 1]);
    System.out.println(result >= 100000000 ? -1 : result);
  }

  public static int[] dijkstra(ArrayList<ArrayList<Edge>> graph, int start) {
    int[] dist = new int[graph.size()];
    Arrays.fill(dist, 100000000);
    dist[start] = 0;

    PriorityQueue<Edge> queue = new PriorityQueue<>();
    queue.add(new Edge(start, 0));

    while (!queue.isEmpty()) {
      Edge current = queue.poll();
      int from = current.to;

      for (Edge next : graph.get(from)) {
        if (dist[next.to] < current.cost) {
          continue;
        }

        if (dist[next.to] > current.cost + next.cost) {
          dist[next.to] = current.cost + next.cost;
          queue.add(new Edge(next.to, dist[next.to]));
        }
      }
    }

    return dist;
  }

  static class Edge implements Comparable<Edge> {
    int to;
    int cost;

    Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
      return this.cost - o.cost;
    }
  }
}
