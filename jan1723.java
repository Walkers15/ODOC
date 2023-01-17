// https://www.acmicpc.net/problem/1043
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class jan1723 {
  /**
   * 1. 모든 파티를 순회하며 진실을 아는 사람이 있는 경우 안다고 체크
   * 2. 모든 파티를 순회하며 모든 사람이 진실을 모를 경우 거짓말 가능한 횟수 증가
   * @param args
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int peopleCount = sc.nextInt();
    int partyCount = sc.nextInt();
    boolean[] knowTrues = new boolean[peopleCount];
    for (int i = 0; i < peopleCount; i++) {
      knowTrues[i] = false;
    }
    ArrayList<ArrayList<Integer>> parties = new ArrayList<ArrayList<Integer>>();
    // System.out.println(peopleCount);
    // System.out.println(partyCount);
    // System.out.print(parties.size());
    int trueCount = sc.nextInt();
    for (int i = 0; i < trueCount; i++) {
      int index = sc.nextInt();
      knowTrues[index - 1] = true;
    }

    
    for (int i = 0; i < partyCount; i++) {
      int memberCount = sc.nextInt();
      ArrayList<Integer> party = new ArrayList<Integer>();
      for (int j = 0; j < memberCount; j++) {
        party.add(sc.nextInt());
      }
      parties.add(party);
    }
    // System.out.println(Arrays.toString(knowTrues));
    // 전체 파티를 순회하면서 진실을 아는 사람이 낀 파티의 경우 다른 사람도 진실을 알게 변경
    for (int i = 0; i < partyCount; i++) {
      ArrayList<Integer> party = parties.get(i);
      // System.out.println(party.toString());
      // System.out.println(party.stream().toString());
      if (party.stream().anyMatch(member -> knowTrues[member - 1] == true)) {
        // System.out.println(party.toString());
        party.forEach(member -> knowTrues[member - 1] = true);
      }
      // System.out.println(Arrays.toString(knowTrues));
    }

    for (int i = 0; i < partyCount; i++) {
      ArrayList<Integer> party = parties.get(i);
      // System.out.println(party.toString());
      // System.out.println(party.stream().toString());
      if (party.stream().anyMatch(member -> knowTrues[member - 1] == true)) {
        // System.out.println(party.toString());
        party.forEach(member -> knowTrues[member - 1] = true);
      }
      // System.out.println(Arrays.toString(knowTrues));
    }

    int lieCount = 0; // 거짓을 말해도 되는 파티 수
    for (int i = 0; i < partyCount; i++) {
      ArrayList<Integer> party = parties.get(i);
      if (party.stream().allMatch(member -> knowTrues[member - 1] == false)) {
        // System.out.println(party.toString());
        lieCount++;
      }
    }

    System.out.print(lieCount);
    sc.close();
  }
}