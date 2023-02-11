
// https://www.acmicpc.net/problem/11724
import java.util.ArrayList;
import java.util.Scanner;

public class feb0623_DFS {
    public static ArrayList<ArrayList<Integer>> graph;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList<>();
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int connectedComponent = 0;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                connectedComponent++;
                visited[i] = true;
                dfs(i);
            }
        }

        System.out.println(connectedComponent);
        sc.close();
    }

    public static void dfs(int n) {
        for (int i : graph.get(n)) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }
    }
}
