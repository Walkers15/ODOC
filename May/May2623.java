// https://www.acmicpc.net/problem/2475 검증수

package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class May2623 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");

    int sum = 0;
    for (int i = 0; i < input.length; i++) {
      sum += Math.pow(Integer.parseInt(input[i]), 2);
    }

    System.out.println(sum % 10);
  }
}