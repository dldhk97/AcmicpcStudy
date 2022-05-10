import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void print(int w, int h, int[][] map){
        StringBuilder sb = new StringBuilder();
        for(int y = 0 ; y < h ; y++){
            for(int x = 0 ; x < w ; x++){
                sb.append(map[x][y]);
                sb.append("\t");
            }
            sb.append("\n");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] directionX = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] directionY = {-1, -1, -1, 0, 0, 1, 1, 1};

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0){
                break;
            }

            // read map
            int[][] map = new int[w][h];
            for(int y = 0 ; y < h ; y++){
                st = new StringTokenizer(br.readLine());
                for(int x = 0 ; x < w ; x++){
                    map[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            int islandCnt = 0;

            // BFS
            boolean[][] visited = new boolean[w][h];
            Queue<int[]> queue = new ArrayDeque<>();

            for(int y = 0 ; y < h ; y++){
                for(int x = 0 ; x < w ; x++){
                    if(map[x][y] == 0 || visited[x][y]) continue;

                    int[] point = {x, y};
                    queue.add(point);

                    while(!queue.isEmpty()){
                        int[] polledPoint = queue.poll();
                        int polledX = polledPoint[0];
                        int polledY = polledPoint[1];
                        if(map[polledX][polledY] == 0 || visited[polledX][polledY]) continue;

                        visited[polledX][polledY] = true;

                        for(int i = 0; i < 8 ; i++){
                            int adjX = polledX + directionX[i];
                            int adjY = polledY + directionY[i];
                            if((adjX >= 0 && adjX <= w - 1) && (adjY >= 0 && adjY <= h - 1)){
                                int[] adjPoint = {adjX, adjY};
                                queue.add(adjPoint);
                            }
                        }
                    }

                    islandCnt++;
                }
            }
            System.out.println(islandCnt);

        }


    }
}