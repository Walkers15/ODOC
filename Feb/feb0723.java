package Feb;
// https://www.acmicpc.net/problem/1931

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class feb0723 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    ArrayList<Meeting> times = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int start = sc.nextInt();
      int end = sc.nextInt();

      times.add(new Meeting(start, end));
    }
    Collections.sort(times);
    int count = 0;
    int endTime = 0;

    for (int i = 0; i < n; i++) {
      Meeting meet = times.get(i);
      if (endTime <= meet.start) {
        endTime = meet.end;
        count++;
      }
    }

    System.out.println(count);
    sc.close();
  }

  static class Meeting implements Comparable<Meeting> {
    int start;
    int end;

    Meeting(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Meeting a) {
      int endDiff = this.end - a.end;
      if (endDiff == 0) {
        return this.start - a.start;
      } else {
        return endDiff;
      }
    }

    @Override
    public String toString() {
      return this.start + " " + this.end;
    }
  }
}
