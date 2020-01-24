//https://www.acmicpc.net/problem/1912
import java.util.Arrays;
import java.util.Scanner;

public class jan2520_1 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+1];
        for(int i = 0 ; i < n ; i++) arr[i] = sc.nextInt();
        int max = 0;
        int cur = 0;
        boolean flag = false;
        for(int i = 0 ; i < n ; i++){
            if(arr[i] > 0) flag = true;
            for(int j = i ; j < n ; j++){
                cur += arr[j];
                if(cur<0) cur = 0;
                if(max < cur) max = cur;
            }
            cur = 0;
        }
        if(flag == true) System.out.println(max);
        else{
            Arrays.sort(arr);
            System.out.println(arr[arr.length-2]);
        }
    }
}
