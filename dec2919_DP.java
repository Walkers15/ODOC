//다이나믹 프로그래밍 연습,1965번
//DP말고 nlogn 시간에 가능한 LIS 알고리즘 공부해볼 것
import java.util.Scanner;

public class dec2819 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int max = 0;
        int[] arr = new int[num+1];
        int[] box = new int[num+1];
        for(int i = 0 ; i < num ; i++){
            arr[i] = sc.nextInt();
        }
 //       for(int i = 0 ; i < num ; i++)
  //         System.out.println(arr[i]);
        for(int i = 0 ; i < num ; i++){
            if(box[i] == 0)
                box[i] = 1;
            for(int j = 0 ; j < i ; j++){
                if(arr[i] > arr[j]){
                    if(box[i] < box[j]+1)
                        box[i] = box[j]+1;
                }
            }
        }
        for(int i = 0 ; i < num ; i++){
            if(box[i] > max)
                max = box[i];
        }
        System.out.println(max);
    }
}
