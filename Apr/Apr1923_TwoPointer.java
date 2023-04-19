// https://www.acmicpc.net/problem/2473 세 용액

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Apr1923_TwoPointer {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    long[] arr = new long[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Long.parseLong(st.nextToken());
    }

    Arrays.sort(arr);

    long min = Long.MAX_VALUE;

    int[] result = null;

    for (int i = 0; i < n; i++) {
      int fixed = i;
      int left = i + 1;
      int right = n - 1;

      while (left < right) {
        long sum = arr[fixed] + arr[left] + arr[right];
        long diff = Math.abs(0 - sum);
        if (diff < min) {
          result = new int[] { fixed, left, right };
          min = diff;
        }
        if (sum == 0) {
          // 0을 찾은 경우는 바로 강제종료해도 ok
          System.out.println(arr[fixed] + " " + arr[left] + " " + arr[right]);
          System.exit(0);
        } else if (sum < 0) {
          left++;
        } else {
          right--;
        }
      }
    }

    System.out.println(arr[result[0]] + " " + arr[result[1]] + " " + arr[result[2]]);
  }
}
