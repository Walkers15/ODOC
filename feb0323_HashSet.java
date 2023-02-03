// https://www.acmicpc.net/problem/1764

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class feb0323_HashSet {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    sc.nextLine();
    HashSet<String> neverListen = new HashSet<>();
    ArrayList<String> unknowns = new ArrayList<String>();
    for (int i = 0; i < n; i++) {
      neverListen.add(sc.nextLine());
    }

    for (int i = 0; i < m; i++) {
      String neverHear = sc.nextLine();
      if (neverListen.contains(neverHear)) {
        unknowns.add(neverHear);
      }
    }

    System.out.println(unknowns.size());
    Arrays.sort(unknowns.toArray(new String[0]));
    String result = "";
    for (String name : unknowns) {
      result += (name + "\n");
    }
    System.out.print(result);
    sc.close();
  }
}
