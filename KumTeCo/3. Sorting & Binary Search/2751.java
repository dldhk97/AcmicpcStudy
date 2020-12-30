import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static int[] copyArr;

    public static void merge(int[] arr, int start, int middle, int end){
        int i = start;
        int j = middle + 1;
        int key = start;

        while(i <= middle && j <= end){
            if(arr[i] <= arr[j]){
                copyArr[key] = arr[i];
                i++;
            }
            else {
                copyArr[key] = arr[j];
                j++;
            }

            key++;
        }

        if(i > middle){
            for(int x = j ; x <= end ; x++){
                copyArr[key] = arr[x];
                key++;
            }
        }
        else{
            for(int x = i ; x <= middle ; x++){
                copyArr[key] = arr[x];
                key++;
            }
        }

        for(int x = start ; x <= end ; x++){
            arr[x] = copyArr[x];
        }

    }

    public static void mergeSort(int[] arr, int start, int end){
        int middle = (start + end) / 2;

        if(start < end){
            mergeSort(arr, start, middle);
            mergeSort(arr, middle + 1, end);

            merge(arr, start, middle, end);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        copyArr = new int[N];

        for(int i = 0 ; i < N ; i++){
            int j = Integer.parseInt(br.readLine());
            arr[i] = j;
        }

        mergeSort(arr, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for(int i : arr){
            sb.append(i);
            sb.append('\n');
        }
        System.out.println(sb.toString());

    }
}