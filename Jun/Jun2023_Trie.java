// https://www.acmicpc.net/problem/19585 전설

package Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Jun2023_Trie {
  public static final int MAX_LENGTH = 1000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    HashSet<String> nicknames = new HashSet<>();

    int teamCount = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());

    Trie trie = new Trie();
    for (int i = 0; i < teamCount; i++) {
      trie.insert(br.readLine());
    }

    for (int i = 0; i < n; i++) {
      nicknames.add(br.readLine());
    }

    int q = Integer.parseInt(br.readLine());
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < q; i++) {
      String team = br.readLine();
      boolean find = false;
      Node cur = trie.root;
      for (int j = 0; j < team.length(); j++) {
        char c = team.charAt(j);
        if (cur.getChild(c) == null) {
          break;
        }

        cur = cur.getChild(c);

        if (cur.isLastWord) {
          String nickname = team.substring(j + 1, team.length());
          if (nicknames.contains(nickname)) {
            result.append("Yes\n");
            find = true;
            break;
          }
        }
      }

      if (!find) {
        result.append("No\n");
      }
    }

    System.out.print(result);
  }

  static class Trie {
    Node root = new Node();

    public void insert(String word) {
      Node cur = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        if (cur.getChild(c) == null) {
          cur.setChild(c, new Node());
        }
        cur = cur.getChild(c);
      }
      cur.isLastWord = true;
    }
  }

  static class Node {
    Node[] child = new Node['z' - 'a' + 1];
    boolean isLastWord = false;

    public void setChild(char c, Node node) {
      child[c - 'a'] = node;
    }

    public Node getChild(char c) {
      return child[c - 'a'];
    }
  }
}
