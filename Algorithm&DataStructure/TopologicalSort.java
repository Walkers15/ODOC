// https://www.acmicpc.net/problem/2252 줄 세우기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TopologicalSort {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    int[] dependency = new int[n];

    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken()) - 1;
      int to = Integer.parseInt(st.nextToken()) - 1;

      graph.get(from).add(to);
      dependency[to]++;
    }

    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      if (dependency[i] == 0) {
        queue.add(i);
      }
    }

    StringBuilder sb = new StringBuilder();
    while (!queue.isEmpty()) {

      int node = queue.poll();
      sb.append((node + 1) + " ");
      for (int i : graph.get(node)) {
        dependency[i]--;
        if (dependency[i] == 0) {
          queue.add(i);
        }

      }
    }

    System.out.println(sb);
  }

}