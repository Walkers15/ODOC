//https://www.acmicpc.net/problem/15652
//비 내림차순 백트레킹 수열만들기 - 중복 허용 백트래킹과 무슨 차이 있는지 볼 것

import java.util.Scanner;

public class feb0702 {
    static int n, m;
    static int[] arr = new int[9];
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        dfs(0);
        System.out.println(sb);
        sc.close();
    }

    public static void dfs(int num) {

        if (num == m) {
            for (int i = 0; i < m; i++)
                sb.append(arr[i] + " ");
            sb.append("\n");
        } else {
            for (int i = 1; i <= n; i++) {
                if (num != 0 && arr[num - 1] > i)
                    continue;
                arr[num] = i;
                dfs(num + 1);
            }
        }
    }
}
