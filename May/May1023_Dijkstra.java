// https://www.acmicpc.net/problem/5719 거의 최단 경로

package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class May1023_Dijkstra {
  // 1. 경로 저장하며 다익스트라 진행
  // 2. 종점에서부터 경로 역추적하여 최단거리에 사용된 경로 제거
  // 3. 이후 다시 다익스트라 진행시 두 번째 최단거리 나옴

  static StringBuilder sb = new StringBuilder();
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      if (n != 0 && m != 0) {
        test(n, m);
      } else {
        break;
      }
    }

    System.out.print(sb);
  }

  static PriorityQueue<int[]> pq;
  static ArrayList<ArrayList<Edge>> graph;
  static ArrayList<ArrayList<Integer>> path;
  static int[] dist;
  static int start, end;
  static boolean[][] isShortestPath;

  public static void test(int n, int m) throws IOException {
    pq = new PriorityQueue<>((int[] a, int[] b) -> a[1] - b[1]);
    graph = new ArrayList<>();
    path = new ArrayList<>();

    dist = new int[n];

    for (int i = 0; i < n; i++) {
      path.add(new ArrayList<>());
      graph.add(new ArrayList<>());
    }

    StringTokenizer st = new StringTokenizer(br.readLine());
    start = Integer.parseInt(st.nextToken());
    end = Integer.parseInt(st.nextToken());

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int v1 = Integer.parseInt(st.nextToken());
      int v2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      graph.get(v1).add(new Edge(v2, cost));
    }

    isShortestPath = new boolean[n][n];
    dijkstra();
    findPath(end);
    int result = dijkstra();

    sb.append(result == Integer.MAX_VALUE ? -1 : result);
    sb.append("\n");
  }

  static int dijkstra() {
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[start] = 0;
    pq.add(new int[] { start, 0 });
    while (!pq.isEmpty()) {
      int[] current = pq.poll();
      int from = current[0];
      int cost = current[1];

      for (Edge edge : graph.get(from)) {
        if (isShortestPath[from][edge.to]) {
          continue;
        }

        int moveCost = cost + edge.cost;
        if (dist[edge.to] == moveCost) {
          path.get(edge.to).add(from);
        } else if (moveCost < dist[edge.to]) {
          path.get(edge.to).clear();
          path.get(edge.to).add(from);
          dist[edge.to] = moveCost;
          pq.add(new int[] { edge.to, moveCost });
        }
      }
    }
    return dist[end];
  }

  static void findPath(int node) {
    if (node == start) {
      return;
    }

    for (int parent : path.get(node)) {
      if (!isShortestPath[parent][node]) {
        isShortestPath[parent][node] = true;
        findPath(parent);
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