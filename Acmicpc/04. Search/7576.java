import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] tomatoes = new int[M][N];
        int[][] visited = new int[M][N];

        int zeroCnt = 0;
        int days = -1;

        Queue<int[]> queue1 = new ArrayDeque<>();
        Queue<int[]> queue2 = new ArrayDeque<>();

        for(int y = 0 ; y < N ; y++){
            st = new StringTokenizer(br.readLine());
            int x = 0;
            while(st.hasMoreTokens()){
                int value = Integer.parseInt(st.nextToken());
                tomatoes[x][y] = value;

                if(value == 0){
                    zeroCnt++;
                }
                else if(value == 1){
                    int[] point = {x, y};
                    queue1.add(point);
                }
                else{
                    visited[x][y] = -1;
                }
                x++;
            }
        }

        int remain = 0;
        while(!queue1.isEmpty() || !queue2.isEmpty()){
            if(queue1.isEmpty()){
                queue1.clear();
                queue1 = queue2;
                queue2 = new ArrayDeque<>();
                days++;
                remain = 0;
            }
            int[] point = queue1.poll();
            int x = point[0]; int y = point[1];

            if(visited[x][y] == 1){
                continue;
            }
            if(tomatoes[x][y] == -1){
                continue;
            }

            visited[x][y] = 1;
            if(tomatoes[x][y] == 0){
                zeroCnt--;
            }
            remain++;

            if(x > 0){
                int[] adjPoint = {x - 1, y};
                if(tomatoes[x - 1][y] == 0){
                    queue2.add(adjPoint);
                }

            }
            if(x < M - 1){
                int[] adjPoint = {x + 1, y};
                if(tomatoes[x + 1][y] == 0){
                    queue2.add(adjPoint);
                }
            }
            if(y > 0){
                int[] adjPoint = {x, y - 1};
                if(tomatoes[x][y - 1] == 0){
                    queue2.add(adjPoint);
                }
            }
            if(y < N - 1){
                int[] adjPoint = {x, y + 1};
                if(tomatoes[x][y + 1] == 0){
                    queue2.add(adjPoint);
                }
            }

            if(queue1.isEmpty()){
                queue1.clear();
                queue1 = queue2;
                queue2 = new ArrayDeque<>();

                days++;
                remain = 0;
            }
        }

        if(zeroCnt != 0){
            days = -1;
        }
        else if(remain != 0){
            days += 1;
        }
        System.out.println(days);
    }
}