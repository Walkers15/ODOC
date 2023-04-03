// https://www.acmicpc.net/problem/2467 용액

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Apr0323_TwoPointer {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];

    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int left = 0;
    int right = n - 1;
    int[] liquid = null;
    int approximate = Integer.MAX_VALUE - 100;
    while (left < right) {
      int composite = arr[left] + arr[right];
      // System.out.println(Math.abs(0 - composite) + " " + Math.abs(0 -
      // approximate));
      if (Math.abs(0 - composite) < Math.abs(0 - approximate)) {
        approximate = composite;
        liquid = new int[] { arr[left], arr[right] };
      }

      if (composite > 0) {
        right--;
      }

      if (composite < 0) {
        left++;
      }

      if (composite == 0) {
        break;
      }
    }

    System.out.println(liquid[0] + " " + liquid[1]);
  }
}