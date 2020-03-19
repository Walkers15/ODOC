//https://www.acmicpc.net/problem/1912
//DP 연속합. O(N)시간에 풀 수 있음!
package boj.java;
import java.util.Scanner;
public class scratch {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+1];
        for(int i = 0 ; i < n ; i++) arr[i] = sc.nextInt();
        int max = 0;
        int cur = 0;
        boolean flag = false;
        for(int i = 1 ; i < n ; i++){
            if(arr[i-1] > 0 && arr[i-1]+arr[i] > 0) {
                flag = true;
                arr[i] = arr[i - 1] + arr[i];
            }
            if(arr[i] > max) max = arr[i];
        }
        if(flag) System.out.println(max);
        else{
            cur = Integer.MIN_VALUE;
            for(int i = 0 ; i < n ; i++){
                if(arr[i] > cur) cur = arr[i];
            }
            System.out.println(cur);
        }
    }
}
