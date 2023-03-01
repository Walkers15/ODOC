
//
import java.util.*;

public class dec3119x {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vc = sc.nextInt(); // Village Count
        int Capacity = sc.nextInt();
        int bc = sc.nextInt();// boxCount
        ArrayList<box2> arr = new ArrayList<>();
        for (int i = 0; i < bc; i++) {
            arr.add(new box2(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(arr);
        int tc = 0;// temp Capacity
        for (int i = 0; i < vc; i++) {
            while (tc != Capacity) {
                for (int j = 0; j < bc; j++) {
                    if (arr.get(j).from == i) {
                        tc += arr.get(j).count;
                        if (tc > Capacity)
                            tc = Capacity;
                    }
                }
            }
        }
        System.out.println(arr);
        sc.close();
    }
}

class box2 implements Comparable<box2> {
    int from;
    int to;
    int count;

    box2(int from, int to, int count) {
        this.from = from;
        this.to = to;
        this.count = count;
    }

    @Override
    public int compareTo(box2 B) {
        if (this.to > B.to)
            return 1;
        else if (this.to == B.to) {
            if (this.count > B.count)
                return -1;
            else if (this.count < B.count)
                return 1;
            return 0;
        } else
            return -1;
    }

    public String toString() {
        return (this.from + " " + this.to + " " + this.count);
    }
}
