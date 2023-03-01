// https://www.acmicpc.net/problem/14428

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class feb2723 {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuffer sb = new StringBuffer();

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    StringTokenizer array = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      a[i] = Integer.parseInt(array.nextToken());
    }

    SegmentTree tree = new SegmentTree(a);
    tree.init(1, 0, n - 1);

    int m = Integer.parseInt(br.readLine());

    while (m-- > 0) {
      StringTokenizer query = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(query.nextToken());
      if (command == 1) {
        int index = Integer.parseInt(query.nextToken());
        int value = Integer.parseInt(query.nextToken());
        tree.update(1, 0, n - 1, index - 1, value);
      } else {
        int index1 = Integer.parseInt(query.nextToken());
        int index2 = Integer.parseInt(query.nextToken());
        sb.append((tree.query(1, 0, n - 1, index1 - 1, index2 - 1) + 1) + "\n");
      }
    }

    System.out.print(sb);
  }

  static class SegmentTree {
    private int[] tree;
    private int[] a;

    SegmentTree(int[] a) {
      this.a = a;
      int count = a.length;
      int h = (int) Math.ceil(Math.log(count) / Math.log(2));
      int size = 1 << (h + 1);
      this.tree = new int[size];
    }

    /**
     * 세그먼트 트리 초기화에 사용하는 재귀 함수
     * 
     * @param node  노드 번호
     * @param start 노드에 저장될 원본 배열 인덱스의 시작
     * @param end   노드에 저장될 원본 배열 인덱스의 끝
     */
    void init(int node, int start, int end) {
      if (start == end) {
        // 리프 노드 (맨 끝)인 경우 원본 배열의 인덱스 저장
        tree[node] = start;
      } else {
        init(node * 2, start, (start + end) / 2); // 내 왼쪽 자식
        init(node * 2 + 1, (start + end) / 2 + 1, end); // 내 오른쪽 자식
        if (a[tree[node * 2]] <= a[tree[node * 2 + 1]]) {
          tree[node] = tree[node * 2];
        } else {
          tree[node] = tree[node * 2 + 1];
        }
      }
    }

    int query(int node, int start, int end, int left, int right) {
      if (left > end || right < start) {
        // 바깥 범위의 연산인 경우
        return -1;
      }
      if (left <= start && end <= right) {
        // 구간합의 범위가 start ~ end를 완전히 포함하는 경우
        return tree[node];
      }

      int leftIndex = query(node * 2, start, (start + end) / 2, left, right);
      int rightIndex = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

      if (leftIndex == -1) {
        return rightIndex;
      } else if (rightIndex == -1) {
        return leftIndex;
      } else {
        if (a[leftIndex] <= a[rightIndex]) {
          return leftIndex;
        } else {
          return rightIndex;
        }
      }

    }

    void update(int node, int start, int end, int index, int value) {
      if (index < start || index > end) {
        // 바깥 범위의 연산인 경우
        return;
      } else if (start == index && index == end) {
        // 리프 노드인 경우 값 변경
        // tree[node] = index;
        a[index] = value;
      } else {
        // 루트 ~ 리프 사이의 노드인 경우
        update(node * 2, start, (start + end) / 2, index, value);
        update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
        if (a[tree[node * 2]] <= a[tree[node * 2 + 1]]) {
          tree[node] = tree[node * 2];
        } else {
          tree[node] = tree[node * 2 + 1];
        }
      }
    }
  }
}
