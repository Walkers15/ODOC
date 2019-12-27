//이진 탐색 알고리즘(1920)
//처음에는 ArrayList를 이용해 list.contains()메소드를 사용했으나, 시간 초과
//이후 이진 탐색 알고리즘 적용
//Arrays.sort() 사용법 확인!
import java.io.*;
import java.util.*;

public class dec2619 {
    public static void main(String[] args)throws IOException{
        Scanner scan = new Scanner(System.in);
        int idx = scan.nextInt();
        int[] arr = new int[idx];
        for(int i = 0 ; i < idx ; i++)
            arr[i] = scan.nextInt();
        Arrays.sort(arr);
        int targetIdx = scan.nextInt();
        int[] target = new int[targetIdx];
        for(int i = 0 ; i < targetIdx ; i++)
            target[i] = scan.nextInt();
        for(int i = 0 ; i < targetIdx ; i++){
            System.out.println(BinSearch(arr,target[i]));
        }
    }
    static int BinSearch(int[] arr,int key){
        int start,end,mid;
        start = 0;
        end = arr.length-1;
        mid = (start+end)/2;
        while(start <= end){
            if(arr[mid] == key)
                return 1;
            else if(arr[mid] < key){
                start = mid+1;
                mid = (start+end)/2;
            } else {
                end = mid-1;
                mid = (start+end)/2;
            }
        }
        return 0;
    }
}
