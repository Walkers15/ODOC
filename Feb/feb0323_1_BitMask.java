package Feb;
// https://www.acmicpc.net/problem/11723

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class feb0323_1_BitMask {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int s = 0;
    int m = Integer.parseInt(br.readLine());

    for (int i = 0; i < m; i++) {
      StringTokenizer command = new StringTokenizer(br.readLine());
      String operation = command.nextToken();
      int x = 0;
      switch (operation) {
        case "add":
          x = Integer.parseInt(command.nextToken());
          if ((s & (1 << x)) > 0) {
            s += 1 << x;
          }
          s += 1 << x;
          break;

        case "remove":
          x = Integer.parseInt(command.nextToken());
          if ((s & (1 << x)) > 0) {
            s -= 1 << x;
          }
          break;

        case "check":
          x = Integer.parseInt(command.nextToken());
          if ((s & (1 << x)) > 0) {
            sb.append("1\n");
          } else {
            sb.append("0\n");
          }
          break;

        case "toggle":
          x = Integer.parseInt(command.nextToken());
          if ((s & (1 << x)) > 0) {
            s -= 1 << x;
          } else {
            s += 1 << x;
          }
          break;

        case "all":
          s = (1 << 22) - 1;
          break;
        case "empty":
          s = 0;
          break;
      }
    }

    System.out.print(sb);
    br.close();
  }
}
