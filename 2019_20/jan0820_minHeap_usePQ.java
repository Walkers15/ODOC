import java.util.*;

public class jan0820_minHeap_usePQ {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int loop = sc.nextInt();
        int n;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < loop; i++) {
            n = sc.nextInt();
            if (n == 0) {
                if (heap.isEmpty())
                    System.out.println("0");
                else
                    System.out.println(heap.poll());
            } else
                heap.add(n);
        }
        sc.close();
    }
}
