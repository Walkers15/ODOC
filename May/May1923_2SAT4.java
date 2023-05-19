// https://www.acmicpc.net/problem/11281 2-SAT 4
package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class May1923_2SAT4 {
  // 1. i 와 ~i 를 2i / 2i - 1 로 정점 만들기
  // 2. SCC 만들기 (타잔 알고리즘)
  // 3. 한 SCC에 i과 ~i가 동시에 있다면 불가능!
  // 4. SCC를 출력하는게 아니라 각 정점들이 몇 번째 SCC에 있는지만 알면 되므로 따로 그래프 생성하지 않음
  // 5. 이후 SCC 위상정렬 역순으로 확인하며 값 정하기 (먼저 만나는 값이 ~A라면 A를 true로 설정)

  static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  static int n, m, sccIndex, labelIndex;
  static int[] label;
  static boolean[] finished;
  static int[] scc;
  static Stack<Integer> stack = new Stack<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    for (int i = 0; i < n * 2; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      // 1 ~1 2 ~2 3 ~3 4 ~4 가
      // 0 1 2 3 4 5 6... 이 되도록 설정
      a = a > 0 ? (a - 1) * 2 : Math.abs(a) * 2 - 1;
      b = b > 0 ? (b - 1) * 2 : Math.abs(b) * 2 - 1;

      // CNF가 참이려면 A, B 둘중 하나는 참이어야 함. 그러므로
      // ~A 면 B는 반드시 참이어야 함
      graph.get(getNotOf(a)).add(b);
      // ~B 면 A는 반드시 참이어야 함
      graph.get(getNotOf(b)).add(a);
    }

    // 타잔 알고리즘을 이용하여 scc들을 찾음
    label = new int[n * 2];
    finished = new boolean[n * 2];
    scc = new int[n * 2];
    for (int i = 0; i < 2 * n; i++) {
      if (label[i] == 0) {
        findSCC(i);
        sccIndex++;
      }
    }

    for (int i = 0; i < n; i++) {
      if (scc[i * 2] == scc[i * 2 + 1]) {
        System.out.println(0);
        return;
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append("1\n");

    int[][] arr = new int[n * 2][2];
    for (int i = 0; i < n * 2; i++) {
      arr[i][0] = scc[i];
      arr[i][1] = i;
    }

    // 값 정하기
    //
    Boolean[] result = new Boolean[n];
    Arrays.fill(result, null);
    Arrays.sort(arr, (arr1, arr2) -> arr1[0] - arr2[0]);

    for (int i = n * 2 - 1; i >= 0; i--) {
      int cur = arr[i][1];
      if (result[cur / 2] == null) {
        result[cur / 2] = (cur % 2 == 0) ? false : true;
      }
    }

    for (int i = 0; i < n; i++) {
      if (result[i]) {
        sb.append("1 ");
      } else {
        sb.append("0 ");
      }
    }

    System.out.print(sb);
  }

  public static int getNotOf(int x) {
    if (x % 2 == 1) {
      return x - 1;
    } else {
      return x + 1;
    }
  }

  public static int findSCC(int n) {
    label[n] = ++labelIndex;
    stack.add(n);

    int result = label[n];
    for (int next : graph.get(n)) {
      if (label[next] == 0) {
        result = Math.min(result, findSCC(next));
      } else if (!finished[next]) {
        // 역방향 간선 혹은 교차 간선인 경우 (먼저 방문되었으나 SCC는 아닌 놈)
        // result 값 선정에 해당 정점의 label 사용
        // 역방향 간선이 있는 경우 이를 통해 result 값이 label[n]보다 작아짐
        result = Math.min(result, label[next]);
      }
    }

    if (result == label[n]) {
      // System.out.println("SCC!");
      // 자기 자손들도 다 뒤져봤는데 나보다 더 위로 갈 수 있는 역방향 간선이 없음!
      // 그러므로 내가 SCC의 시작임
      // stack에서 나 나올때까지 나보다 위에 쌓여있는놈들(내 자손들) 빼줌
      while (true) {
        int current = stack.pop();
        scc[current] = sccIndex;
        finished[current] = true;
        if (current == n) {
          sccIndex++;
          break;
        }
      }
    }

    return result;
  }
}
