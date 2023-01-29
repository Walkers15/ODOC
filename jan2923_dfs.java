// https://www.acmicpc.net/problem/2606
import java.util.ArrayList;
import java.util.Scanner;

public class jan2923_dfs {
    public static ArrayList<Integer>[] graph;
    public static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int computerCount = sc.nextInt();
        int edgeCount = sc.nextInt();
        graph = new ArrayList[computerCount + 1];
        visited = new boolean[computerCount + 1];
        for (int i = 1; i <= computerCount; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeCount; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        // 1번 바이러스 기준으로 dfs 시작
        visited[1] = true;
        dfs(1);

        int infectedCount = 0;
        for (int i = 1; i <= computerCount; i++) {
            if (visited[i]) {
                infectedCount++;
            }
        }

        System.out.println(infectedCount - 1); // 1번 컴퓨터 제외
    }

    public static void dfs(int n) {
        for (int i: graph[n]) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }
    }

}
