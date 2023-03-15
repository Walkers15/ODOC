// https://www.acmicpc.net/problem/1167
// 트리의 루트에서 시작하여 길이가 가장 큰 노드를 찾으면 그게 지름의 한 점이 된다

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class mar1623_DFS_TreeLength {
  static ArrayList<ArrayList<Node>> tree;
  static int endIndex = 0;
  static int result = 0;
  static boolean[] visited;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int v = Integer.parseInt(br.readLine());
    tree = new ArrayList<>();

    // 트리를 인접 리스트로 구현
    for (int i = 0; i < v; i++) {
      tree.add(new ArrayList<>());
    }

    for (int i = 0; i < v; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()) - 1;
      ArrayList<Node> node = tree.get(n);

      while (true) {
        int next = Integer.parseInt(st.nextToken()) - 1;
        if (next == -2) {
          break;
        }
        int cost = Integer.parseInt(st.nextToken());
        node.add(new Node(next, cost));
      }
    }

    // 지름의 한 끝점 찾기
    visited = new boolean[v];
    visited[0] = true;
    dfs(0, 0);

    // 해당 점에서 반대편 찾아서 지름 구하기
    visited = new boolean[v];
    visited[endIndex] = true;
    dfs(endIndex, 0);

    System.out.println(result);
  }

  static void dfs(int n, int cost) {
    for (Node node : tree.get(n)) {
      if (!visited[node.index]) {
        visited[node.index] = true;
        if (cost + node.cost > result) {
          endIndex = node.index;
          result = cost + node.cost;
        }
        dfs(node.index, cost + node.cost);
      }

    }
  }

  static class Node {
    int index;
    int cost;

    Node(int index, int cost) {
      this.index = index;
      this.cost = cost;
    }
  }
}
