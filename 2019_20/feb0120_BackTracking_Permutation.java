//https://www.acmicpc.net/problem/15649
//백트래킹 알고리즘 - DFS , 작동 원리 이해할 

import java.util.Scanner;

public class feb0120_BackTracking_Permutation {
    static int n, m;
    static boolean[] visit = new boolean[9];
    static int[] arr = new int[9];

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        dfs(0);
        sc.close();
    }

    public static void dfs(int num) {
        if (num == m) {
            for (int i = 0; i < m; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
        } else {
            for (int i = 1; i <= n; i++) {
                if (visit[i] == false) {
                    arr[num] = i;
                    visit[i] = true;
                    dfs(num + 1);
                    visit[i] = false;
                }
            }
        }
    }
}
