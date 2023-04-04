// https://www.acmicpc.net/problem/2623 음악프로그램

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Apr0523_TopologicalSort {
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
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int k = Integer.parseInt(st.nextToken());
      if (k >= 2) {
        int from = Integer.parseInt(st.nextToken()) - 1;
        for (int j = 1; j < k; j++) {
          int to = Integer.parseInt(st.nextToken()) - 1;
          dependency[to]++;
          graph.get(from).add(to);
          from = to;
        }
      }
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (dependency[i] == 0) {
        queue.add(i);
      }
    }

    StringBuilder sb = new StringBuilder();
    while (!queue.isEmpty()) {
      int current = queue.poll();
      sb.append((current + 1) + "\n");
      graph.get(current).forEach(next -> {
        dependency[next]--;
        if (dependency[next] == 0) {
          queue.add(next);
        }
      });
    }

    for (int i = 0; i < n; i++) {
      if (dependency[i] > 0) {
        System.out.println(0);
        return;
      }
    }

    System.out.print(sb);
  }
}
