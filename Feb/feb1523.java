package Feb;
// https://www.acmicpc.net/problem/5430

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class feb1523 {
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String tStr = br.readLine();
    int t = 0;
    if (tStr != "") {
      t = Integer.parseInt(tStr);
    }

    for (int i = 0; i < t; i++) {
      test(br);
    }
    System.out.print(sb);
  }

  public static void test(BufferedReader br) throws IOException {
    String p = br.readLine();
    br.readLine();
    String[] arr = br.readLine().replace("[", "").replace("]", "").split(",");
    LinkedList<Integer> list = new LinkedList<>();
    int length = arr.length;
    for (int i = 0; i < length; i++) {
      // if (!(arr[i].equals(""))) {
      if (arr[i] != "") {
        list.add(Integer.parseInt(arr[i]));
      }
    }

    boolean popDir = true;

    int pLength = p.length();
    for (int i = 0; i < pLength; i++) {
      char operator = p.charAt(i);
      if (operator == 'R') {
        popDir = !popDir;
      } else {
        if (list.size() == 0) {
          sb.append("error\n");
          return;
        }

        if (popDir) {
          list.removeFirst();
        } else {
          list.removeLast();
        }
      }
    }

    if (popDir) {
      sb.append(list.toString().replace(" ", "") + "\n");
    } else {
      Integer[] reverseArr = list.toArray(new Integer[] {});
      if (reverseArr.length > 0) {
        sb.append("[");
        for (int i = reverseArr.length - 1; i >= 1; i--) {
          sb.append(reverseArr[i] + ",");
        }
        sb.append(reverseArr[0] + "]\n");
      } else {
        sb.append("[]\n");
      }
    }
  }
}