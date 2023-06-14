// https://www.acmicpc.net/problem/5670 휴대폰 자판

package Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Jun1423_Trie {
  public static final int SIZE = 'Z' - 'A' + 1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String str = br.readLine();
    while (str != null && !str.equals("")) {
      Trie trie = new Trie();
      ArrayList<String> words = new ArrayList<>();
      int n = Integer.parseInt(str);

      for (int i = 0; i < n; i++) {
        String word = br.readLine();
        words.add(word);
        trie.insert(word);
      }

      double sum = 0;
      for (String word : words) {
        sum += trie.search(word);
      }

      sb.append(String.format("%.2f\n", sum / n));

      str = br.readLine();
    }

    System.out.println(sb);
  }

  static class Trie {

    private Node root;

    public Trie() {
      this.root = new Node();
    }

    public void insert(String word) {
      Node cur = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        if (cur.getChild(c) == null) {
          cur.setChild(c, new Node());
        }
        cur = cur.getChild(c);
      }
      cur.setLastWord(true);
    }

    public int search(String word) {
      int count = 1;
      Node cur = root.getChild(word.charAt(0));

      for (int i = 1; i < word.length(); i++) {
        int childCount = cur.getChildCount();
        if (childCount >= 2 || (childCount == 1 && cur.isLastWord())) {
          count++;
        }

        char c = word.charAt(i);
        cur = cur.getChild(c);
      }

      return count;
    }

    class Node {
      private Node[] child;
      private boolean isLastWord;
      private int childCount;

      public Node() {
        child = new Node[SIZE];
        isLastWord = false;
        childCount = 0;
      }

      public void setChild(char c, Node node) {
        child[c - 'a'] = node;
        childCount++;
      }

      public Node getChild(char c) {
        return child[c - 'a'];
      }

      public boolean isLastWord() {
        return this.isLastWord;
      }

      public void setLastWord(boolean isLastWord) {
        this.isLastWord = isLastWord;
      }

      public int getChildCount() {
        return this.childCount;
      }
    }
  }
}