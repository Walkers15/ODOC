// https://www.acmicpc.net/problem/2357 최솟값과 최댓값

package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class May0323_SegmentTree {
  public static int[] maxTree;
  public static int[] minTree;
  public static int[] arr;

  public static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    arr = new int[n];

    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    int height = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
    maxTree = new int[1 << height];
    minTree = new int[1 << height];

    init(1, 0, n - 1);
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int[] result = query(1, 0, n - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
      sb.append(result[0] + " " + result[1] + "\n");
    }

    System.out.print(sb);
  }

  public static void init(int node, int start, int end) {
    if (start == end) {
      minTree[node] = arr[start];
      maxTree[node] = arr[start];
    } else {

      int mid = (start + end) / 2;
      init(node * 2, start, mid);
      init(node * 2 + 1, mid + 1, end);
      minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
      maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
    }
  }

  public static int[] query(int node, int start, int end, int left, int right) {
    if (left > end || right < start) {
      return null;
    }

    if (left <= start && end <= right) {
      return new int[] { minTree[node], maxTree[node] };
    }

    int mid = (start + end) / 2;
    int[] leftResult = query(node * 2, start, mid, left, right);
    int[] rightResult = query(node * 2 + 1, mid + 1, end, left, right);

    int[] result = new int[2];
    if (leftResult == null) {
      return rightResult;
    } else if (rightResult == null) {
      return leftResult;
    } else {
      result[0] = Math.min(leftResult[0], rightResult[0]);
      result[1] = Math.max(leftResult[1], rightResult[1]);

      return result;
    }
  }
}
