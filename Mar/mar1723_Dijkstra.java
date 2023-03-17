// https://www.acmicpc.net/problem/1753

// 정점의 갯수가 최대 2만개 -> int 2차원 배열을 이용하면 1.6GB 메모리 필요
// ArrayList를 통해 구현해야 함

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class mar1723_Dijkstra {
  static int[] dist;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());

    ArrayList<PriorityQueue<Edge>> map = new ArrayList<>();
    dist = new int[v];
    int MAX = 10000000;
    int start = Integer.parseInt(br.readLine()) - 1;
    for (int i = 0; i < v; i++) {
      map.add(new PriorityQueue<>());
    }
    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(st.nextToken()) - 1;
      int to = Integer.parseInt(st.nextToken()) - 1;
      int cost = Integer.parseInt(st.nextToken());

      // boolean contains = false;
      // for (Edge edge : map.get(from)) {
      // if (edge.to == to) {
      // contains = true;
      // if (edge.cost > cost) {
      // edge.cost = cost;
      // break;
      // }
      // }
      // }

      // if (!contains) {
      // map.get(from).add(new Edge(to, cost));
      // }
      map.get(from).add(new Edge(to, cost));
    }

    for (int i = 0; i < v; i++) {
      int cost = MAX;
      // for (Edge edge : map.get(start)) {
      // if (edge.to == i) {
      // cost = edge.cost;
      // }
      // }
      dist[i] = cost;
    }

    dist[start] = 0;
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    pq.add(new Edge(start, 0));
    boolean[][] visited = new boolean[v][v];
    while (!pq.isEmpty()) {
      Edge now = pq.poll();
      for (Edge edge : map.get(now.to)) {
        if (!visited[now.to][edge.to]) {
          visited[now.to][edge.to] = true;
          if (dist[edge.to] > now.cost + edge.cost) {
            dist[edge.to] = now.cost + edge.cost;
            pq.add(new Edge(edge.to, dist[edge.to]));
          }
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < dist.length; i++) {
      if (dist[i] != MAX) {
        sb.append(dist[i] + "\n");
      } else {
        sb.append("INF" + "\n");
      }
    }

    System.out.println(sb);
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