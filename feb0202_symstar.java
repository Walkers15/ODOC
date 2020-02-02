//오늘은 세계 대칭의 날입니다
//그러므로 대칭 별찍기를 진행하였습니다
//https://www.acmicpc.net/problem/2442
package boj.java;
import java.util.Scanner;
public class feb2020 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int space = n-1;
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= space ; j++)
                System.out.print(" ");
            space--;
            for(int j = 1 ; j <= 2*i-1 ; j++)
                System.out.print("*");
            System.out.print("\n");
        }
    }
}
