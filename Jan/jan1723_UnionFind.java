package Jan;

// https://www.acmicpc.net/problem/1043
import java.util.Scanner;
import java.util.ArrayList;

public class jan1723_UnionFind {
  public static int[] root;

  public static void main(String[] args) {
    // 1. 입력 잘 받기
    Scanner sc = new Scanner(System.in);
    int peopleCount = sc.nextInt();
    int partyCount = sc.nextInt();
    boolean[] knowTrues = new boolean[peopleCount];
    root = new int[peopleCount + 1];
    for (int i = 0; i < peopleCount; i++) {
      knowTrues[i] = false;
      root[i] = i;
    }

    ArrayList<ArrayList<Integer>> parties = new ArrayList<ArrayList<Integer>>();
    int trueCount = sc.nextInt();
    for (int i = 0; i < trueCount; i++) {
      int index = sc.nextInt();
      knowTrues[index - 1] = true;
    }

    for (int i = 0; i < partyCount; i++) {
      int memberCount = sc.nextInt();
      ArrayList<Integer> party = new ArrayList<Integer>();
      for (int j = 0; j < memberCount; j++) {
        party.add(sc.nextInt() - 1);
      }
      parties.add(party);
    }

    // 2. 같은 파티에 참석한 적 있는 사람들은 루트가 같아지도록 해줌
    for (int i = 0; i < partyCount; i++) {
      ArrayList<Integer> party = parties.get(i);
      for (int j = 0; j < party.size() - 1; j++) {
        int memberA = party.get(j);
        int memberB = party.get(j + 1);
        if (find(memberA) != find(memberB)) {
          // 같은 파티의 멤버이지만 루트가 다름 -> 통일해야 함
          union(memberA, memberB); // union을 통해 루트 통일
        }
      }
    }

    // 3. 진실을 아는 사람이 있는 파티는 모든 사람이 진실을 알게 해야 함
    // 진실을 아는 사람이 있을 때 같은 루트를 가지는 모든 사람들을 진실을 알게 변경
    boolean[] visit = new boolean[peopleCount];
    for (int i = 0; i < peopleCount; i++) {
      if (knowTrues[i] == true && visit[i] == false) {
        int root = find(i);
        for (int j = 0; j < peopleCount; j++) {
          if (find(j) == root) {
            visit[j] = true;
            knowTrues[j] = true;
          }
        }
      }
    }

    // 4. 거짓을 말해도 되는 파티 수 계산 - 파티에 참여한 모든 인원이 진실을 알지 않아야 함
    int lieCount = 0;
    for (int i = 0; i < partyCount; i++) {
      ArrayList<Integer> party = parties.get(i);
      if (party.stream().allMatch(member -> knowTrues[member] == false)) {
        lieCount++;
      }
    }

    System.out.print(lieCount);
    sc.close();
  }

  public static int find(int index) {
    if (root[index] == index) {
      return index;
    }
    root[index] = find(root[index]);
    return root[index];
  }

  public static void union(int a, int b) {
    int root_b = find(b);
    root[root_b] = a;
  }
}