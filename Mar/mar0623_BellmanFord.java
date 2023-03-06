// https://www.acmicpc.net/problem/1865

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class mar0623_BellmanFord {
  public static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());

    while (tc-- > 0) {
      test(br);
    }

    System.out.print(sb);
  }

  public static void test(BufferedReader br) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int w = Integer.parseInt(st.nextToken());

    int[] dist = new int[n];
    // 중요! 백준이는 어디서든 출발할 수 있으므로 따로 시작점을 두지 않고 모든 dist의 값을 0으로 설정
    // Arrays.fill(dist, Integer.MAX_VALUE);

    ArrayList<Edge> graph = new ArrayList<>();

    for (int i = 0; i < m; i++) {
      // 도로는 방향이 없으므로 간선을 두 개 생성
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken()) - 1;
      int e = Integer.parseInt(st.nextToken()) - 1;
      int t = Integer.parseInt(st.nextToken());
      graph.add(new Edge(s, e, t));
      graph.add(new Edge(e, s, t));
    }

    for (int i = 0; i < w; i++) {
      // 웜홀은 방향이 있음
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken()) - 1;
      int e = Integer.parseInt(st.nextToken()) - 1;
      int t = Integer.parseInt(st.nextToken()) * -1; // 시간이 줄어드므로 음수로 저장
      graph.add(new Edge(s, e, t));
    }

    // 벨만-포드 알고리즘
    for (int i = 0; i < n - 1; i++) {
      for (Edge e : graph) {
        if (dist[e.from] != Integer.MAX_VALUE && dist[e.to] > dist[e.from] + e.cost) {
          dist[e.to] = dist[e.from] + e.cost;
        }
      }
    }

    // 음수 사이클 존재하는지 확인
    // 만약 위의 edge-relaxation을 V-1 만큼 반복한 이후에도 그래프가 갱신된다면 음수 사이클이 있는 것
    for (Edge e : graph) {
      if (dist[e.from] != Integer.MAX_VALUE && dist[e.to] > dist[e.from] + e.cost) {
        sb.append("YES\n");
        return;
      }
    }
    sb.append("NO\n");
  }

  static class Edge {
    int from;
    int to;
    int cost;

    Edge(int v, int w, int cost) {
      this.from = v;
      this.to = w;
      this.cost = cost;
    }
  }
}
