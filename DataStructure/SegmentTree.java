package DataStructure;

public class SegmentTree {
  private long[] tree;
  private long[] arr;

  SegmentTree(long[] arr) {
    this.arr = arr;
    int count = arr.length;
    int h = (int) Math.ceil(Math.log(count) / Math.log(2));
    int size = 1 << (h + 1);
    this.tree = new long[size];
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
      // 리프 노드 (맨 끝)인 경우 원본 배열의 값을 저장해야 함
      tree[node] = arr[start];
    } else {
      init(node * 2, start, (start + end) / 2); // 내 왼쪽 자식
      init(node * 2 + 1, (start + end) / 2 + 1, end); // 내 오른쪽 자식
      tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
  }

  long query(int node, int start, int end, int left, int right) {
    if (left > end || right < start) {
      // 바깥 범위의 연산인 경우
      return 0;
    }
    if (left <= start && end <= right) {
      // 구간합의 범위가 start ~ end를 완전히 포함하는 경우
      return tree[node];
    }

    return query(node * 2, start, (start + end) / 2, left, right)
        + query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
  }

  long update(int node, int start, int end, int index, int value) {
    if (index < start || index > end) {
      // 바깥 범위의 연산인 경우
      return tree[node];
    } else if (start == index && index == end) {
      // 리프 노드인 경우 값 변경
      tree[node] = value;
      return tree[node];
    } else {
      // 루트 ~ 리프 사이의 노드인 경우
      tree[node] = update(node * 2, start, (start + end) / 2, index, value)
          + update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
      return tree[node];
    }
  }
}
