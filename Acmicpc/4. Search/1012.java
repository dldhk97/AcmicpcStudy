import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int M, N, K;
    static int[][] CABBAGES = new int[M][N];
    static int COLOR_INDEX;

    public static void search(int x, int y){
        if(CABBAGES[x][y] != 1){
            return;
        }

        CABBAGES[x][y] = COLOR_INDEX;

        if(x > 0){
            search(x - 1, y);
        }
        if(x < M - 1){
            search(x + 1, y);
        }
        if(y > 0){
            search(x, y - 1);
        }
        if(y < N - 1){
            search(x, y + 1);
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            CABBAGES = new int[M][N];
            COLOR_INDEX = 1;

            while(K-- > 0){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                CABBAGES[x][y] = 1;
            }

            for(int y = 0 ; y < N ; y++){
                for(int x = 0; x < M ; x++){
                    if(CABBAGES[x][y] == 1){
                        COLOR_INDEX++;
                    }
                    search(x, y);
                }
            }

            System.out.println(COLOR_INDEX - 1);
        }

    }
}