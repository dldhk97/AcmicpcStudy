import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int N;
    static boolean[][] map;
    static int whiteCnt = 0;
    static int blueCnt = 0;

    static void solve(int startX, int startY, int length){
        if(length == 1){
            if(map[startX][startY])
                blueCnt++;
            else
                whiteCnt++;
            return ;
        }

        int halfLength = length / 2;

        boolean color = map[startX][startY];
        for(int y = startY ; y < startY + length ; y++){
            for(int x = startX ; x < startX + length ; x++){
                if(color != map[x][y]){
                    solve(startX, startY, halfLength);
                    solve(startX + halfLength, startY, halfLength);
                    solve(startX, startY + halfLength, halfLength);
                    solve(startX + halfLength, startY + halfLength, halfLength);
                    return;
                }

            }
        }

        if(map[startX][startY])
            blueCnt++;
        else
            whiteCnt++;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new boolean[N][N];

        for(int y = 0 ; y < N ; y++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int x = 0 ; x < N ; x++){
                int value = Integer.parseInt(st.nextToken());
                map[x][y] = value == 1;
            }
        }

        solve(0, 0, N);

        System.out.println(whiteCnt);
        System.out.println(blueCnt);
    }
}