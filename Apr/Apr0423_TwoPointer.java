// https://www.acmicpc.net/problem/1806 부분합

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Apr0423_TwoPointer {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int s = Integer.parseInt(st.nextToken());

    int[] arr = new int[n];
    int[] sum = new int[n + 1];
    st = new StringTokenizer(br.readLine());
    sum[0] = 0;

    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      sum[i + 1] = sum[i] + arr[i];
    }

    int left = 0;
    int right = 0;

    int result = Integer.MAX_VALUE;
    while (left <= right && right < n) {
      int current = 0;
      if (left == right) {
        current = arr[left];
      } else {
        current = sum[right + 1] - sum[left];
      }

      if (current >= s) {
        result = Math.min(result, right - left + 1);
        left++;
      } else {
        right++;
      }
    }

    System.out.println(result == Integer.MAX_VALUE ? 0 : result);
  }
}