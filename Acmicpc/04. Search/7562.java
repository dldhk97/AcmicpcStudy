import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] dX = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] dY = {-2, -1, 1, 2, 2, 1, -1, -2};

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int l = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int sourceX = Integer.parseInt(st.nextToken());
            int sourceY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int destinationX = Integer.parseInt(st.nextToken());
            int destinationY = Integer.parseInt(st.nextToken());

            int[][] distances = new int[l][l];
            boolean[][] visited = new boolean[l][l];

            for(int i = 0 ; i < l ; i++)
                Arrays.fill(distances[i], Integer.MAX_VALUE);
            distances[sourceX][sourceY] = 0;

            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[] {sourceX, sourceY});

            while(!queue.isEmpty()){
                int[] polled = queue.poll();
                int x = polled[0]; int y = polled[1];

                if(visited[x][y]) continue;

                visited[x][y] = true;

                for(int i = 0 ; i < 8 ; i++){
                    int newX = x + dX[i];
                    int newY = y + dY[i];

                    if((0 <= newX && newX < l) && (0 <= newY && newY < l)){
                        distances[newX][newY] = Math.min(distances[newX][newY], distances[x][y] + 1);
                        queue.add(new int[] {newX, newY});
                    }
                }
            }

            sb.append(distances[destinationX][destinationY]).append("\n");
        }
        System.out.print(sb.toString());
    }
}