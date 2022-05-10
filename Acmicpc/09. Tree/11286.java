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

        for(int i = tail ; i > 0 ; i /= 2){
            if(arr[i] == 0) continue;

            if(Math.abs(arr[i]) < Math.abs(arr[i / 2])){
                swap(i, i / 2);

            }
            else if(Math.abs(arr[i]) == Math.abs(arr[i / 2])){
                if(arr[i] < arr[i / 2]){
                    swap(i, i / 2);

                }
            }
        }
    }

    public int pop(){
        if(isEmpty()) return 0;

        int result = arr[1];
        arr[1] = arr[tail];
        arr[tail--] = 0;

        for(int i = 2 ; i <= tail ; i *= 2){
            if(arr[i] == 0) continue;

            int child = i;
            if(i + 1 <= tail){
                if(Math.abs(arr[i]) > Math.abs(arr[i + 1])){
                    child = i + 1;
                }
                else if(Math.abs(arr[i]) == Math.abs(arr[i + 1])){
                    if(arr[i] > arr[i + 1])
                        child = i + 1;
                }
            }

            if(Math.abs(arr[i / 2]) > Math.abs(arr[child])){
                swap(i / 2, child);

                i = child;
            }
            else if(Math.abs(arr[i / 2]) == Math.abs(arr[child])){
                if(arr[i / 2] > arr[child]){
                    swap(i / 2, child);

                    i = child;
                }
            }
        }

        return result;
    }

    public boolean isEmpty(){
        return tail < 1;
    }


    public void swap(int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void print(){
        int height = 1;
        for(int i = 1 ; i <= tail ; i++){
            if(i > height - 1){
                height *= 2;
                System.out.println();
            }
            System.out.print(arr[i] + "\t");
        }
        System.out.println("\n-----------------");
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

            if(N == 5){
                int stop = 1;
            }


            if(value == 0)
                sb.append(heap.pop()).append("\n");
            else
                heap.push(value);
        }

        System.out.print(sb.toString());

    }
}