// https://www.acmicpc.net/problem/6549 히스토그램에서 가장 큰 직사각형
package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class May0223_DAC {
  public static int[] hist;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      if (n == 0) {
        break;
      }

      hist = new int[n];

      for (int i = 0; i < n; i++) {
        hist[i] = Integer.parseInt(st.nextToken());
      }

      sb.append(getArea(0, n - 1) + "\n");
    }

    System.out.print(sb);

  }

  public static long getArea(int left, int right) {
    if (left == right) {
      return hist[left];
    }

    int mid = (left + right) / 2;

    long leftMax = getArea(left, mid);
    long rightMax = getArea(mid + 1, right);

    long max = Math.max(leftMax, rightMax);

    max = Math.max(max, getMidArea(left, right, mid));

    return max;
  }

  public static long getMidArea(int left, int right, int mid) {
    int leftPointer = mid;
    int rightPointer = mid;

    long height = hist[mid];

    long maxArea = height;

    while (left < leftPointer && rightPointer < right) {
      if (hist[leftPointer - 1] > hist[rightPointer + 1]) {
        leftPointer--;
        height = Math.min(height, hist[leftPointer]);
      } else {
        rightPointer++;
        height = Math.min(height, hist[rightPointer]);
      }

      maxArea = Math.max(maxArea, height * (rightPointer - leftPointer + 1));
    }

    while (right > rightPointer) {
      rightPointer++;
      height = Math.min(height, hist[rightPointer]);
      maxArea = Math.max(maxArea, height * (rightPointer - leftPointer + 1));
    }

    while (left < leftPointer) {
      leftPointer--;
      height = Math.min(height, hist[leftPointer]);
      maxArea = Math.max(maxArea, height * (rightPointer - leftPointer + 1));
    }

    return maxArea;
  }
}
