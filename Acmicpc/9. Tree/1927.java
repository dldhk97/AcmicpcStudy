import java.io.BufferedReader;
import java.io.InputStreamReader;

class Heap{
    int size, tail = 0;
    int[] arr;

    public Heap(int size){
        this.size = size;

        arr = new int[size + 1];
    }

    public void push(int x){
        arr[++tail] = x;

        bottomUp(tail);
    }

    public int pop(){
        if(isEmpty()) return 0;

        int result = arr[1];
        arr[1] = arr[tail];
        arr[tail--] = 0;

        topDown(1);

        return result;
    }

    public boolean isEmpty(){
        return tail < 1;
    }

    public void bottomUp(int index){
        int parentIdx = index;

        if(0 < index / 2){
            if(arr[index / 2] > arr[index])
                parentIdx = index / 2;
        }

        if(parentIdx != index){
            swap(index, parentIdx);
            bottomUp(parentIdx);
        }
    }

    public void topDown(int index){
        int childIdx = index;

        if(index * 2 < size + 1){
            if(arr[index * 2] != 0){
                if(arr[index * 2] < arr[index])
                    childIdx = index * 2;
            }
        }
        if(index * 2 + 1 < size + 1){
            if(arr[index * 2 + 1] != 0){
                if(arr[index * 2 + 1] < arr[childIdx])
                    childIdx = index * 2 + 1;
            }
        }

        if(childIdx != index){
            swap(childIdx, index);
            topDown(childIdx);
        }
    }

    public void swap(int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Heap heap = new Heap(N);

        StringBuilder sb = new StringBuilder();

        while(N-- > 0){
            int value = Integer.parseInt(br.readLine());

            if(value == 0){
                sb.append(heap.pop()).append("\n");
            }
            else{
                heap.push(value);
            }
        }

        System.out.println(sb.toString());

    }
}