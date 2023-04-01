// https://www.acmicpc.net/problem/15657 N과 M(8)

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mar2923_BT {
  static int m;
  static int n;
  static int[] num;
  static int[] arr;
  static boolean[] visited;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    num = new int[n];
    arr = new int[m];
    visited = new boolean[n];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      num[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(num);

    dfs(0);
    System.out.print(sb);
  }

  public static void dfs(int depth) {
    if (depth == m) {
      for (int i : arr) {
        sb.append(i + " ");
      }
      sb.append("\n");
      return;
    }

    int prev = 0;
    for (int i = 0; i < n; i++) {
      if (!visited[i] && prev != num[i]) {
        visited[i] = true;
        arr[depth] = num[i];
        prev = arr[depth];
        dfs(depth + 1);
        visited[i] = false;
      }
    }
  }
}
