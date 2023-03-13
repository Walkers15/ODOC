// https://www.acmicpc.net/problem/2263

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class mar0923_TreeOrder {
  static StringBuilder sb = new StringBuilder();

  static int[] postOrder;
  static int[] inOrder;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());

    postOrder = new int[n];
    inOrder = new int[n];

    StringTokenizer in = new StringTokenizer(br.readLine());
    StringTokenizer post = new StringTokenizer(br.readLine());

    for (int i = 0; i < n; i++) {
      inOrder[i] = Integer.parseInt(in.nextToken());
      postOrder[i] = Integer.parseInt(post.nextToken());
    }

    findRoot(0, n - 1, 0, n - 1);
    System.out.print(sb);
  }

  /**
   * 루트 찾아주는 재귀함수
   * 
   * @param is Inorder Start Index
   * @param ie Inorder End Index
   * @param ps Postorder Start Index
   * @param pe Postorder End Index
   */
  public static void findRoot(int is, int ie, int ps, int pe) {
    if (is > ie || ps > pe) {
      return;
    }

    int root = postOrder[pe];
    sb.append(root + " ");

    for (int i = is; i <= ie; i++) {
      if (inOrder[i] == root) {
        findRoot(is, i - 1, ps, ps + i - is - 1);
        findRoot(i + 1, ie, ps + i - is, pe - 1);
      }
    }
  }
}
