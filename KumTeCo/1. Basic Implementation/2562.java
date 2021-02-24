import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[9];

        int max = Integer.MIN_VALUE;
        int index = 0;

        for(int i = 1 ; i < 10 ; i++){
            int value = Integer.parseInt(br.readLine());
            if(max < value){
                max = value;
                index = i;
            }
        }

        System.out.println(max);
        System.out.println(index);


    }
}