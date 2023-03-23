// https://www.acmicpc.net/problem/14003 가장 긴 증가하는 부분 수열 5
// 역추적을 위해 최장 길이를 저장하는 로직

package Mar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class mar2323_LIS_LowerBound {
  static int length = 0;

  public static void main(String args[]) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<Integer> dp = new ArrayList<>();
    dp.add(Integer.MIN_VALUE);

    int[] lisLength = new int[n + 1];

    for (int i = 0; i < n; i++) {
      if (dp.get(dp.size() - 1) < arr[i]) {
        dp.add(arr[i]);
        lisLength[i] = (dp.size() - 1);
        length++;
      } else {
        int k = lowerBound(dp, arr[i]);
        lisLength[i] = k;
        // path[i] = k;
        dp.set(k, arr[i]);
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(dp.size() - 1 + "\n");

    int pathFind = dp.size() - 1;

    Stack<Integer> stack = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {

      if (lisLength[i] == pathFind) {
        stack.add(arr[i]);

        pathFind--;
      }
    }

    while (!stack.isEmpty()) {
      sb.append(stack.pop() + " ");
    }

    bw.write(sb.toString());
    bw.close();
  }

  public static int lowerBound(ArrayList<Integer> dp, int key) {
    int high = length;
    int low = 1;
    int mid = low + (high - low) / 2;
    while (low < high) {
      mid = low + (high - low) / 2;
      if (dp.get(mid) >= key) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }
}