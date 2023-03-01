package Feb;
// https://www.acmicpc.net/problem/7662

// 틀린코드: worst case(편향적인 이진 트리)에 대해 n^2의 시간 복잡도를 가져 시간 초과가 출력됨

import java.util.LinkedList;
import java.util.Scanner;

public class feb0123 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    for (int i = 0; i < t; i++) {
      test(sc);
    }
    sc.close();
  }

  public static void test(Scanner sc) {
    int k = sc.nextInt();
    sc.nextLine();
    LinkedList<Integer> list = new LinkedList<>();
    for (int i = 0; i < k; i++) {
      String operation = sc.nextLine();
      char operator = operation.split(" ")[0].charAt(0);
      int n = Integer.parseInt(operation.split(" ")[1]);
      switch (operator) {
        case 'I':
          insert(list, n);
          break;
        case 'D':
          if (list.size() == 0) {
            continue;
          }
          if (n == -1) {
            list.removeFirst();
          } else {
            list.removeLast();
          }
          break;
      }
    }
    int size = list.size();
    if (size == 0) {
      System.out.println("EMPTY");
    } else if (size == 1) {
      System.out.println(list.get(0));
    } else {
      int max = list.get(size - 1);
      int min = list.get(0);
      System.out.println(max + " " + min);
    }

    // System.out.println(list.toString());
  }

  public static void insert(LinkedList<Integer> list, int num) {
    // insert by order
    int size = list.size();

    if (size == 0) {
      list.add(num);
      return;
    }

    if (size == 1) {
      if (list.get(0) < num) {
        list.add(num);
      } else {
        list.add(0, num);
      }
      return;
    }

    if (list.get(0) >= num) {
      list.add(0, num);
      return;
    }

    if (list.get(size - 1) < num) {
      list.add(size, num);
      return;
    }

    int start = 0;
    int end = size;

    while (true) {
      int index = (start + end) / 2;
      if (index == 0 || index == size) {
        list.add(index, num);
        break;
      }

      int n1 = list.get(index - 1);
      int n2 = list.get(index);

      if (n1 <= num && num <= n2) {
        list.add(index, num);
        break;
      }

      if (list.get(index) >= num) {
        start = index;
      } else {
        end = index;
      }

    }
  }
}
