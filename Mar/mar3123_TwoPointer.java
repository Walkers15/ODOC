// https://www.acmicpc.net/problem/1208 부분수열의 합 2

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class mar3123_TwoPointer {
  static ArrayList<Integer> leftList = new ArrayList<>();
  static ArrayList<Integer> rightList = new ArrayList<>();
  static int[] arr;
  static int n, s;
  static boolean isLeft;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    s = Integer.parseInt(st.nextToken());

    arr = new int[n];
    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    // 원본 배열을 두개로 분리하여 백트레킹 -> 해당 배열의 가능한 모든 부분합 검사
    isLeft = true;
    dfs(0, 0, n / 2);
    isLeft = false;
    dfs(0, n / 2, n);

    Collections.sort(leftList);
    Collections.sort(rightList);

    // System.out.println(leftList);
    // System.out.println(rightList);

    long result = checkSum();

    if (s == 0) {
      System.out.println(result - 1);
    } else {
      System.out.println(result);
    }

  }

  public static void dfs(int sum, int start, int end) {
    if (start == end) {
      if (isLeft) {
        leftList.add(sum);
      } else {
        rightList.add(sum);
      }
      return;
    }

    dfs(sum, start + 1, end);
    dfs(sum + arr[start], start + 1, end);
  }

  public static long checkSum() {
    int left = 0;
    int right = rightList.size() - 1;

    long result = 0;
    while (true) {

      if (left == leftList.size() || right < 0) {
        break;
      }

      int leftValue = leftList.get(left);
      int rightValue = rightList.get(right);

      // System.out.println(left + " " + right + " " + leftValue + " " + rightValue);
      if (leftValue + rightValue == s) {
        long leftCount = 0; // Left Value를 가진 원소의 갯수
        while (left < leftList.size() && leftList.get(left) == leftValue) {
          leftCount++;
          left++;
        }

        long rightCount = 0; // Right Value를 가진 원소의 갯수
        while (right >= 0 && rightList.get(right) == rightValue) {
          rightCount++;
          right--;
        }

        // System.out.printf("Find!! %d %d %d %d\n", leftValue, rightValue, leftCount,
        // rightCount);
        result += (rightCount * leftCount);
      }

      if (leftValue + rightValue > s) {
        right--;
      }

      if (leftValue + rightValue < s) {
        left++;
      }
    }

    return result;
  }
}
