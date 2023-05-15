// https://www.acmicpc.net/problem/13334 철로
package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class May1323_PQ {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[][] map = new int[n][2];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (a < b) {
        map[i][0] = a;
        map[i][1] = b;
      } else {
        map[i][1] = a;
        map[i][0] = b;
      }
    }

    int max = 0;
    int d = Integer.parseInt(br.readLine());

    Arrays.sort(map, Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt((int[] a) -> a[0]));

    PriorityQueue<Integer> queue = new PriorityQueue<>();

    for (int i = 0; i < n; i++) {
      int[] current = map[i];
      int start = current[0];
      int end = current[1];

      if (end - start <= d) {
        queue.add(start);
      } else {
        continue;
      }

      while (!queue.isEmpty()) {
        int left = queue.peek();
        if (end - left <= d) {
          break;
        } else {
          queue.poll();
        }
      }
      max = Math.max(max, queue.size());
    }

    System.out.println(max);
  }
}
