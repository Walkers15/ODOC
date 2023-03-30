// https://www.acmicpc.net/problem/1182 부분수열의 합

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mar3023_Combination {
  static int result = 0;
  static int n;
  static int s;
  static int[] arr;
  static boolean[] visit;
  static int[] sum;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    s = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    arr = new int[n];
    visit = new boolean[n];
    sum = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(arr); // 부분수열을 선택하는 문제(불연속 ok)이므로 정렬해도 큰 문제 없음
    dfs(0, 0);
    System.out.println(s == 0 ? result - 1 : result);
  }

  public static void dfs(int depth, Integer sum) {
    if (depth == n) {
      if (sum == s) {
        result++;
      }
      return;
    }

    dfs(depth + 1, sum + arr[depth]);
    dfs(depth + 1, sum);
  }
}
