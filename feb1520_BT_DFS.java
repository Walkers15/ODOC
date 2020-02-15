//https://www.acmicpc.net/problem/14889
//백트래킹 DFS

package boj.java;

import java.util.Scanner;
import java.util.Stack;

public class feb1520 {
    static int n;
    static int[][] arr;
    static Stack<Integer> start = new Stack<>();
    static Stack<Integer> link = new Stack<>();
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n+1][n+1];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        dfs(0);
        System.out.println(result);
    }
    static boolean dfs(int num){
        if(num == n){
            int start_stat = 0;
            int link_stat = 0;
            if(start.size() == link.size()&& start.size() == n/2){
                for(int i = 0 ; i < n/2 ; i++){
                    for(int j = i+1 ; j<n/2 ; j++){
                        if(i == j) continue;
                        start_stat += arr[start.get(i)][start.get(j)] + arr[start.get(j)][start.get(i)];
                        link_stat +=  arr[link.get(i)][link.get(j)] + arr[link.get(j)][link.get(i)];
                    }
                }
                result  = Math.min(result,Math.abs(start_stat-link_stat));
            }
            return true;
        }
        start.push(num);
        dfs(num+1);
        start.pop();

        link.push(num);
        dfs(num+1);
        link.pop();
        return true;
    }
}
