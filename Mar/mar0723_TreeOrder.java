// https://www.acmicpc.net/problem/1991

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class mar0723_TreeOrder {
  public static StringBuilder sb = new StringBuilder();
  public static int[][] tree;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    tree = new int[n][2];
    int none = '.' - 'A';

    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int name = st.nextToken().charAt(0) - 'A';
      int left = st.nextToken().charAt(0) - 'A';
      int right = st.nextToken().charAt(0) - 'A';
      if (left != none) {
        tree[name][0] = left;
      } else {
        tree[name][0] = -1;
      }
      if (right != none) {
        tree[name][1] = right;
      } else {
        tree[name][1] = -1;
      }
    }
    preorder(0);
    sb.append("\n");
    inorder(0);
    sb.append("\n");
    postorder(0);
    sb.append("\n");
    System.out.print(sb);
  }

  public static void preorder(int node) {
    char name = (char) (node + 'A');
    sb.append(name);
    if (tree[node][0] != -1) {
      preorder(tree[node][0]);
    }

    if (tree[node][1] != -1) {
      preorder(tree[node][1]);
    }
  }

  public static void inorder(int node) {
    if (tree[node][0] != -1) {
      inorder(tree[node][0]);
    }

    char name = (char) (node + 'A');
    sb.append(name);

    if (tree[node][1] != -1) {
      inorder(tree[node][1]);
    }
  }

  public static void postorder(int node) {
    if (tree[node][0] != -1) {
      postorder(tree[node][0]);
    }

    if (tree[node][1] != -1) {
      postorder(tree[node][1]);
    }

    char name = (char) (node + 'A');
    sb.append(name);
  }
}
