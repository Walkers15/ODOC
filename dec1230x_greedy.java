//미완성

import java.util.*;

public class dec3019 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int vc = sc.nextInt(); //Village Count
        int Capacity = sc.nextInt();
        int bc = sc.nextInt();//boxCount
        ArrayList<box> arr = new ArrayList<>();
        for(int i = 0 ; i < bc ; i++){
            arr.add(new box(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(arr);
        System.out.println(arr);
    }
}
class box implements Comparable<box>{
    int from;
    int to;
    int count;
    box(int from, int to, int count){
        this.from = from;
        this.to = to;
        this.count = count;
    }
    @Override
    public int compareTo(box B){
        if(this.count > B.count) return -1;
        else if(this. count == B.count) return 0;
        else return 1;
    }
    public String toString(){
        return (this.from + " " + this.to + " " + this.count);
    }
}
