// https://www.acmicpc.net/problem/1541

import java.util.Scanner;

public class feb1323_StrToInt {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String[] expression = sc.nextLine().split("-");

    int result = getNumber(expression[0]);
    int length = expression.length;
    for (int i = 1; i < length; i++) {
      result -= getNumber(expression[i]);
    }

    System.out.println(result);
    sc.close();
  }

  public static int getNumber(String expression) {
    String[] numbers = expression.split("\\+");
    int result = 0;
    for (int i = 0; i < numbers.length; i++) {
      result += Integer.parseInt(numbers[i]);
    }

    return result;
  }
}
