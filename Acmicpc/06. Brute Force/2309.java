import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main{


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = 9;
        int sum = 0;

        ArrayList<Integer> heights = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            int height = Integer.parseInt(br.readLine());
            heights.add(height);
            sum += height;
        }

        heights.sort(Integer::compareTo);

        for(int i = 0 ; i < N - 1 ; i++){
            for(int j = i + 1 ; j < N ; j++){
                int iValue = heights.get(i);
                int jValue = heights.get(j);
                if(sum - iValue - jValue == 100){
                    heights.remove(Integer.valueOf(iValue));
                    heights.remove(Integer.valueOf(jValue));

                    StringBuilder sb = new StringBuilder();
                    for(int h : heights){
                        sb.append(h);
                        sb.append("\n");
                    }
                    System.out.println(sb.toString());
                    return;
                }
            }
        }
    }
}