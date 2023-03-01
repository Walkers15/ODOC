
//https://www.acmicpc.net/problem/2565
//LIS DP 식 > j로 보고 있는 항의 LIS 길이 + 1 이 i로 보고 있는 LIS항의 길이보다 길고, arr[j] < arr[i] 이면이 왜 성립하는지 생각해 볼 것
import java.util.*;

public class jan2420_LIS_DP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<line> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new line(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(arr);
        int[] dp_a = new int[n + 1];
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (dp_a[i] == 0)
                dp_a[i] = 1;
            for (int j = 0; j < i; j++) {
                if (dp_a[j] + 1 > dp_a[i] && arr.get(i).b > arr.get(j).b)
                    dp_a[i] = dp_a[j] + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(dp_a[i]);
            if (result < dp_a[i])
                result = dp_a[i];
        }
        System.out.println(n - result);
        sc.close();
    }

    static class line implements Comparable<line> {
        int a;
        int b;

        line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(line b) {
            if (this.a < b.a)
                return -1;
            else
                return 1;
        }
    }
}
