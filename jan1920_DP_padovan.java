//https://www.acmicpc.net/problem/9461
//DP == 점화식! 끝에서부터 경우의 수 생각해 볼 것, 식이 An = An-i + * 의 형태로 나와야 함
import java.util.Scanner;
public class jan1920 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int loop = sc.nextInt();
        long[] arr = new long[102];
        arr[0] = 0; arr[1] = 1; arr[2] = 1; arr[3] = 1; arr[4] = 2; arr[5] = 2;
        for(int i = 0 ; i < loop ; i++){
            int n = sc.nextInt();
            for(int j = 6 ; j <= n ;j++){
                arr[j] = arr[j-5] + arr[j-1];
            }
            System.out.println(arr[n]);
        }
    }

}
