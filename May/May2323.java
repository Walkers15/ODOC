// https://www.acmicpc.net/problem/5597 과제 안 내신 분..?

package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class May2323 {
  public static final int STUDENT_COUNT = 30;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean[] student = new boolean[STUDENT_COUNT + 1];
    for (int i = 0; i < STUDENT_COUNT - 2; i++) {
      student[Integer.parseInt(br.readLine())] = true;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= STUDENT_COUNT; i++) {
      if (!student[i]) {
        sb.append(i + "\n");
      }
    }

    System.out.print(sb);
  }
}