// https://www.acmicpc.net/problem/11055  가장 큰 증가하는 부분 수열
// LIS O(n^2)로 풀기
// 내일은 lower bound를 이용한 O(nlogn)으로 풀기!

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class mar2023_DP {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] dp = new int[n]; // 현재까지 LIS의 최대 크기
    int[] arr = new int[n];

    int result = 0;

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      dp[i] = arr[i];
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i] > arr[j]) {
          dp[i] = Math.max(dp[i], dp[j] + arr[i]);
        }
      }
    }

    for (int i = 0; i < n; i++) {
      result = Math.max(result, dp[i]);
    }

    System.out.println(result);
  }
}
