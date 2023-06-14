public class Trie {
  public static final int SIZE = 'Z' - 'A';

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

  public boolean search(String word) {
    Node cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (cur.getChild(c) == null) {
        return false;
      } else {
        cur = cur.getChild(c);
      }
    }

    return cur.isLastWord();
  }

  public boolean startsWith(String prefix) {
    Node current = root;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      Node node = current.getChild(c);
      if (node == null) {
        return false;
      }
      current = node;
    }
    return true;
  }

  class Node {
    private Node[] child;
    private boolean isLastWord;

    public Node() {
      child = new Node[SIZE];
      isLastWord = false;
    }

    public void setChild(char c, Node node) {
      child[c - 'a'] = node;
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
  }
}