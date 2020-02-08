//https://www.acmicpc.net/problem/9663
//백트래킹 N-Queen 문제, 체스판은 NxN이지만, 왜 1차원 배열로만 표현해도 풀 수 있는지 생각해볼 것
//N_Queen이 성립하려면, 한 줄에 퀸이 하나만 있어야 하므로(퀸은 룩의 이동이 가능하므로)굳이 2차원 배열을 생각할 필요가 없음
import java.util.Scanner;
public class feb0820 {
    static int n;
    static int count = 0;
    static int[] cols;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cols = new int[n];
        backTracking(0);
        System.out.println(count);
    }
    public static void backTracking(int level){
        if(level == n){
            count++;
        } else {
            for(int i = 0 ; i < n ; i++){
                cols[level] = i;
                if(isPossible(level))
                    backTracking(level+1);
            }
        }
    }
    public static boolean isPossible(int level){
        for(int i = 0 ; i < level ; i++){
            //직선, 혹은 대각선(가로, 세로 차의 절댓값이 같음)
            if(cols[i] == cols[level] || Math.abs(level-i) == Math.abs(cols[level] - cols[i]))
                return false;
        }
        return true;
    }
}
 
