import java.util.*;
public class jan0120 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        int[] time = new int[size];
        for(int i = 0 ; i < size ; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);
        int result = 0;
        time[0] = arr[0];
        result += time[0];
        for(int i = 1 ; i < size ; i++) {
            time[i] = time[i-1]+arr[i];
            result += time[i];
        }
        System.out.println(result);
    }
}
