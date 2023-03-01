package Jan;
// https://www.acmicpc.net/problem/1918

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class jan1823_stack {
  public static List<Character> OPERATOR_DEFINE = Arrays.asList('(', ')', '-', '+', '*', '/');

  public static void main(String[] args) {
    HashMap<Character, Integer> priority = new HashMap<Character, Integer>();
    priority.put('(', 0);
    priority.put('+', 1);
    priority.put('-', 1);
    priority.put('*', 2);
    priority.put('/', 2);
    priority.put(')', 3);

    Stack<Character> operators = new Stack<>();
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    sc.close();

    int len = input.length();

    for (int i = 0; i < len; i++) {
      char current = input.charAt(i);
      if (OPERATOR_DEFINE.contains(current)) {
        if (current == '(') {
          operators.push(current);
          continue;
        }

        int current_prioirty = priority.get(current);

        if (current_prioirty == 3) {
          current_prioirty = 0;
        }

        if (operators.size() > 0 && priority.get(operators.lastElement()) >= current_prioirty) {
          while (true) {
            // 현재 우선순위보다 낮은 원소들만 남을때까지 Stack Pop
            char popOperator = operators.pop();
            int popOperatorPriority = priority.get(popOperator);
            if (popOperatorPriority == 1 || popOperatorPriority == 2) {
              System.out.print(popOperator);
            }
            if (popOperator == '(' || operators.size() == 0
                || priority.get(operators.lastElement()) < current_prioirty) {
              break;
            }
          }
          if (current != ')') {
            operators.push(current);
          }
        } else {
          if (current != ')') {
            operators.push(current);
          }

        }
      } else {
        System.out.print(current);
      }
    }

    while (operators.size() > 0) {
      char operator = operators.pop();
      if (operator != '(' && operator != ')') {
        System.out.print(operator);
      }
    }
  }
}
