// https://www.acmicpc.net/problem/16287 Parcel

package May;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class May2223_DP {
  // 중간에서 만나기 (meet in the middle)
  // 4개의 합을 조합하는 경우 (n^4) 를 2 + 2로 나눌 수 있음 (n ^ 2)
  static final int MAX_WEIGHT = 400000;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int w = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    int[] arr = new int[n];
    boolean[] dp = new boolean[MAX_WEIGHT + 1];

    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        // 두 개의 조합 (i번째를 기준으로 오른쪽애들을 계속 선택)
        int cur = arr[i] + arr[j];
        if (cur >= w || w - cur > MAX_WEIGHT) {
          continue;
        }
        if (dp[w - cur]) {
          System.out.println("YES");
          return;
        }
      }
      for (int j = 0; j < i; j++) {
        // 왼쪽 애들로 조합 만들기
        dp[arr[i] + arr[j]] = true;
      }
    }

    System.out.println("NO");
  }
}
