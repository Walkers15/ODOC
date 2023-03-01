//미완성

import java.util.*;

public class dec3019x_greedy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // int vc = sc.nextInt(); // Village Count
        // int Capacity = sc.nextInt();
        int bc = sc.nextInt();// boxCount
        ArrayList<box3> arr = new ArrayList<>();
        for (int i = 0; i < bc; i++) {
            arr.add(new box3(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(arr);
        System.out.println(arr);
        sc.close();
    }
}

class box implements Comparable<box3> {
    int from;
    int to;
    int count;

    box(int from, int to, int count) {
        this.from = from;
        this.to = to;
        this.count = count;
    }

    @Override
    public int compareTo(box3 B) {
        return Integer.compare(B.count, this.count);
    }

    public String toString() {
        return (this.from + " " + this.to + " " + this.count);
    }
}
