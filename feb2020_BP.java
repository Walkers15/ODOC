//https://www.acmicpc.net/problem/2798
//전형적인 브루트-포스 문제, DFS로 해결하는 방법도 있을 듯?

import java.util.Scanner;

public class feb2020_BP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int min = Integer.MAX_VALUE;
        int cur = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    cur = arr[i] + arr[j] + arr[k];
                    if (cur <= m && m - cur < min) {
                        if (m - cur == 0) {
                            System.out.println(m);
                            System.exit(0);
                        } else
                            min = m - cur;
                    }
                }
            }
        }
        System.out.println(m - min);
        sc.close();
    }
}
