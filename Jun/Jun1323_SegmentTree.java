// https://www.acmicpc.net/problem/2042 구간 합 구하기

package Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jun1323_SegmentTree {
  static long[] tree;
  static long[] arr;
  static int depth;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    depth = (int) Math.ceil(Math.log(n) / Math.log(2));
    int size = 1 << (depth + 1);

    arr = new long[n];
    tree = new long[size];

    for (int i = 0; i < n; i++) {
      arr[i] = Long.parseLong(br.readLine());
    }

    init(0, n - 1, 1);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < m + k; i++) {
      st = new StringTokenizer(br.readLine());
      if (st.nextToken().charAt(0) == '1') {
        int index = Integer.parseInt(st.nextToken());
        long value = Long.parseLong(st.nextToken());
        update(0, n - 1, 1, index - 1, value);
      } else {
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        sb.append(query(1, 0, n - 1, left - 1, right - 1) + "\n");
      }
    }

    System.out.println(sb);
  }

  static void init(int start, int end, int current) {
    if (start == end) {
      tree[current] = arr[start];
    } else {
      int mid = (start + end) / 2;
      init(start, mid, current * 2);
      init(mid + 1, end, current * 2 + 1);
      tree[current] = tree[current * 2] + tree[current * 2 + 1];
    }
  }

  static long update(int start, int end, int target, int index, long value) {
    if (index < start || index > end) {
      return tree[target];
    } else if (start == index && index == end) {
      tree[target] = value;
      return tree[target];
    } else {
      int mid = (start + end) / 2;
      tree[target] = update(start, mid, target * 2, index, value) + update(mid + 1, end, target * 2 + 1, index, value);
      return tree[target];
    }
  }

  static long query(int current, int start, int end, int left, int right) {
    if (left > end || right < start) {
      return 0;
    } else if (left <= start && end <= right) {
      return tree[current];
    }

    int mid = (start + end) / 2;
    return query(current * 2, start, mid, left, right) + query(current * 2 + 1, mid + 1, end, left, right);
  }
}
