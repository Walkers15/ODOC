import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KMP {
  static String t;
  static String p;
  static int[] pi;
  static StringBuilder sb = new StringBuilder();
  static int result = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    t = br.readLine();
    p = br.readLine();
    pi = new int[p.length()];

    makePi();
    kmp();

    System.out.print(result + "\n" + sb.toString());
  }

  public static void kmp() {
    int tLength = t.length();
    int pLength = p.length();
    int j = 0;
    for (int i = 0; i < tLength; i++) {
      while (j > 0 && t.charAt(i) != p.charAt(j)) {
        // 변경한 j가 0보다 큰 경우 중간단계를 여러번 건너뛸 수 있음!
        j = pi[j - 1];
      }
      if (t.charAt(i) == p.charAt(j)) {
        if (j == pLength - 1) {
          sb.append((i - pLength + 2) + "\n");
          result++;
          j = pi[j];
        } else {
          j++;
        }
      }
    }
  }

  public static void makePi() {
    pi[0] = 0;
    int pLength = p.length();
    int j = 0;
    for (int i = 1; i < pLength; i++) {
      while (j > 0 && p.charAt(i) != p.charAt(j)) {
        j = pi[j - 1];
      }

      if (p.charAt(j) == p.charAt(i)) {
        pi[i] = ++j;
      }
    }
  }

}
