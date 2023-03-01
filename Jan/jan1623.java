package Jan;

// https://www.acmicpc.net/problem/11720
import java.io.*;
import java.util.*;

public class jan1623 {
  public static void main(String[] args) throws IOException {
    Scanner scan = new Scanner(System.in);
    scan.nextLine();
    String str = scan.nextLine();
    int num = 0;
    int len = str.length();

    for (int i = 0; i < len; i++) {
      num += str.charAt(i) - '0';
    }
    System.out.print(num);
    scan.close();
  }
}
