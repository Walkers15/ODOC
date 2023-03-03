// https://www.acmicpc.net/problem/15654

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mar0323_BT {
  public static int n;
  public static int m;
  public static boolean[] visited;
  public static int[] arr;
  public static int[] result;
  public static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new int[n];
    result = new int[m];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);

    visited = new boolean[n];
    dfs(0);

    System.out.println(sb);
  }

  static void dfs(int depth) {
    if (depth == m) {
      for (int num : result) {
        sb.append(num + " ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        visited[i] = true;
        result[depth] = arr[i];
        dfs(depth + 1);
        visited[i] = false;
      }
    }
  }
}
