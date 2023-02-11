// https://www.acmicpc.net/problem/18870

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class feb0923 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    // 원본 배열
    int[] arr = new int[n];
    // 중복없이 담을 Set
    HashSet<Integer> sortSet = new HashSet<>();

    for (int i = 0; i < n; i++) {
      int x = sc.nextInt();
      arr[i] = x;
      sortSet.add(x);
    }

    // Set은 Sort가 불가능하므로 ArrayList로 변환하여 정렬
    ArrayList<Integer> sortArr = new ArrayList<>(sortSet);
    Collections.sort(sortArr);

    // 정렬된 값을 기준으로 TreeMap 추가
    TreeMap<Integer, Integer> sortMap = new TreeMap<>();
    for (int i = 0; i < sortArr.size(); i++) {
      sortMap.put(sortArr.get(i), i);

    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append(sortMap.get(arr[i]) + " ");
    }

    System.out.println(sb);
    sc.close();
  }
}
