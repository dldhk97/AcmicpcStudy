import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void quickSort(int[] arr, int start, int end){
        int pivot = start;

        int left = start + 1;
        int right = end;

        while(left <= right){
            if(arr[right] > arr[pivot]){
                right--;
            }

            if(arr[left] < arr[pivot]){
                left++;
            }

            if(left <= right){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }

        if(start < end){
            int temp = arr[pivot];
            arr[pivot] = arr[right];
            arr[right] = temp;

            quickSort(arr, start, right - 1);
            quickSort(arr, right + 1, end);
        }

        return;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for(int i = 0 ; i < N ; i++){
            int j = Integer.parseInt(br.readLine());
            arr[i] = j;
        }

        quickSort(arr, 0, N - 1);

        for(int i : arr){
            System.out.println(i);
        }

    }
}