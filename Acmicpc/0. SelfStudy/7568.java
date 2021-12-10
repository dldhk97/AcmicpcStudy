import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] list = new int[N][3];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            list[i][0] = weight;
            list[i][1] = height;
        }

        for(int i = 0 ; i < N ; i++){
            int rank = 1;
            for(int j = 0 ; j < N ; j++){
                if(i == j){
                    continue;
                }

                int weightA = list[i][0];
                int weightB = list[j][0];

                int heightA = list[i][1];
                int heightB = list[j][1];

                if(weightA == weightB || heightA == heightB){
                    continue;
                }

                if(weightA < weightB && heightA < heightB){
                    rank++;
                }
            }
            list[i][2] = rank;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            sb.append(list[i][2]);
            sb.append(' ');
        }

        System.out.println(sb.toString());

    }
}