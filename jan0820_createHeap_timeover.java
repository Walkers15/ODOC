import java.util.*;
public class jan0820 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int loop = sc.nextInt();
        int n;
        minHeap heap = new minHeap();
        for(int i = 0 ; i < loop ; i++){
            n = sc.nextInt();
            if( n == 0 ) heap.delete();
            else heap.insert(n);
        }
    }
}
class minHeap{
    ArrayList<Integer> heap;
    public minHeap() {
        heap = new ArrayList<>();
        heap.add(0);
    }
    void insert(int n){
        heap.add(n);
        int idx = heap.size()-1;
        while(idx > 1 && heap.get(idx/2) > heap.get(idx) ){
            int temp = heap.get(idx/2);
            heap.set(idx/2, heap.get(idx));
            heap.set(idx,temp);
            idx /= 2;
        }
    }
    void delete(){
        if(heap.size() == 1){
            System.out.println("0");
        } else {
            int idx = heap.size() - 1;
            System.out.println(heap.get(1));
            heap.set(1,heap.get(idx));
            heap.remove(idx);
            idx = 1;
            while(idx*2 < (heap.size()) /2){
                if(heap.get(idx*2) < heap.get(idx*2+1)){
                    if(heap.get(idx) > heap.get(idx*2)){
                        int temp = heap.get(idx);
                        heap.set(idx,heap.get(idx*2));
                        heap.set(idx*2,temp);
                        idx *= 2;
                    }
                } else {
                    if(heap.get(idx) > heap.get(idx*2+1)) {
                        int temp = heap.get(idx);
                        heap.set(idx, heap.get(idx * 2 + 1));
                        heap.set(idx * 2, temp);
                        idx = idx*2 + 1;
                    }
                }
            }
        }
    }
}
