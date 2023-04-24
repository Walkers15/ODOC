// https://www.acmicpc.net/problem/1006 습격자 초라기

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Apr2423_DP {
  public static StringBuilder sb = new StringBuilder();
  public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws NumberFormatException, IOException {
    int tc = Integer.parseInt(br.readLine());
    while (tc-- > 0) {
      test();
    }
    System.out.print(sb);
  }

  public static int n, w;
  public static int[][] arr;
  public static int[] a, b, c;

  public static int MAX_VALUE = 50000;

  public static void test() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    w = Integer.parseInt(st.nextToken());

    arr = new int[n + 1][2];
    a = new int[n + 1]; // 위 아래 모두 i - 1열까지 채움
    b = new int[n + 1]; // 1행만 i열까지 채움
    c = new int[n + 1]; // 2행만 i열까지 채움

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i][0] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i][1] = Integer.parseInt(st.nextToken());
    }

    a[0] = 0;
    b[0] = 1;
    c[0] = 1;

    solve(0);
    int result = Math.min(MAX_VALUE, a[n]);

    if (n > 1 && arr[0][0] + arr[n - 1][0] <= w) {
      a[1] = 1;
      b[1] = 2;
      c[1] = arr[0][1] + arr[1][1] <= w ? 1 : 2;
      solve(1);
      result = Math.min(result, c[n - 1] + 1);
    }

    if (n > 1 && arr[0][1] + arr[n - 1][1] <= w) {
      a[1] = 1;
      b[1] = arr[0][0] + arr[1][0] <= w ? 1 : 2;
      c[1] = 2;
      solve(1);
      result = Math.min(result, b[n - 1] + 1);
    }

    if (n > 1 && arr[0][0] + arr[n - 1][0] <= w && arr[0][1] + arr[n - 1][1] <= w) {
      a[1] = 0;
      b[1] = 1;
      c[1] = 1;
      solve(1);
      result = Math.min(result, a[n - 1] + 2);
    }
    sb.append(result + "\n");
  }

  public static void solve(int start) {
    for (int i = start; i < n; i++) {
      // 이전에 탐색한 b, c Case에서 1개 소대를 추가하여 완성하는 경우
      a[i + 1] = Math.min(b[i] + 1, c[i] + 1);

      if (arr[i][0] + arr[i][1] <= w) {
        // 1행과 2행 모두 i - 1 까지 채우고, 세로로 한개 소대를 추가함
        a[i + 1] = Math.min(a[i + 1], a[i] + 1);
      }

      if (i >= 1 && arr[i - 1][0] + arr[i][0] <= w && arr[i - 1][1] + arr[i][1] <= w) {
        // 1행과 2행 모두 i - 2 까지 채우고, 가로로 두개 소대를 추가함
        a[i + 1] = Math.min(a[i + 1], a[i - 1] + 2);
      }

      if (i <= n - 2) {
        // 1행과 2행 모두 i열까지 채우고 (a[i + 1]), 1행 i + 1열에 하나 채우기
        b[i + 1] = a[i + 1] + 1;
        if (arr[i][0] + arr[i + 1][0] <= w) {
          // 1행은 i-1열까지 2행은 i열까지 채우고 1행에 가로로 한 소대 넣기
          b[i + 1] = Math.min(b[i + 1], c[i] + 1);
        }

        // 1행과 2행 모두 i열까지 채우고 (a[i + 1]), 2행 i + 1열에 하나 채우기
        c[i + 1] = a[i + 1] + 1;
        if (arr[i][1] + arr[i + 1][1] <= w) {
          // 2행은 i-1열까지 1행은 i열까지 채우고 2행에 가로로 한 소대 넣기
          c[i + 1] = Math.min(c[i + 1], b[i] + 1);
        }
      }
    }
  }
}
