import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Heap{
    int size;
    int[] arr;
    int index = 1;

    public Heap(int size){
        this.size = size;
        arr = new int[size + 1];
    }

    public void push(int x){
        arr[index] = x;
        bottomUpSwap(index);
        index++;
    }

    public boolean isEmpty(){
        return index <= 1;
    }

    private void swap(int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void topDownSwap(int i){
        int childIdx = i;

        if(i * 2 <= size){
            if(arr[i * 2] > arr[i])
                childIdx = i * 2;
        }

        if(i * 2 + 1 <= size){
            if(arr[i * 2 + 1] > arr[i]){
                if(arr[childIdx] < arr[i * 2 + 1])
                    childIdx = i * 2 + 1;
            }
        }

        if(childIdx != i){
            swap(i, childIdx);
            topDownSwap(childIdx);
        }
    }

    public void bottomUpSwap(int i){
        int parentIdx = i;

        if(i / 2 > 0){
            if(arr[i / 2] < arr[i])
                parentIdx = i / 2;
        }
        else if(i / 2 + 1 > 0){
            if(arr[i / 2 + 1] < arr[i])
                parentIdx = i / 2 + 1;
        }

        if(parentIdx != i){
            swap(i, parentIdx);
            bottomUpSwap(parentIdx);
        }
    }

    public int pop(){
        if(isEmpty())
            return 0;

        int result = arr[1];

        arr[1] = arr[index - 1];
        arr[index - 1] = 0;
        index--;

        topDownSwap(1);

        return result;
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());

        Heap heap = new Heap(cnt);

        StringBuilder sb = new StringBuilder();

        while(cnt-- > 0){
            int value = Integer.parseInt(br.readLine());

            if(value == 0)
                sb.append(heap.pop()).append("\n");
            else
                heap.push(value);

        }

        System.out.print(sb.toString());
    }
}