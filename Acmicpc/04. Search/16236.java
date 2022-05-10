import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main{

    static int N;
    static int[][] map;

    static boolean[][] feedVisited;
    static int[][] distanceMap;

    static int sharkX, sharkY;
    static int sharkSize = 2;
    static int eatCnt = 0;
    static int time = 0;

    static int[] directionX = {0, 0, -1, 1};
    static int[] directionY = {-1, 1, 0, 0};

    static int[] comparePoint(int[] p1, int[] p2){
        // compare Distance
        int d1 = distanceMap[p1[0]][p1[1]];
        int d2 = distanceMap[p2[0]][p2[1]];

        if(d1 < d2){
            return p1;
        }
        else if(d1 > d2){
            return p2;
        }

        if(p1[1] == p2[1]){
            if(p1[0] <= p2[0]){
                return p1;
            }
            else{
                return p2;
            }
        }
        else if(p1[1] <= p2[1])
            return p1;
        return p2;
    }

    static int[] findFeeds(int[] startPoint){
        int[] feed = null;

        feedVisited = new boolean[N][N];
        distanceMap = new int[N][N];

        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(startPoint);

        while(!queue.isEmpty()){
            int[] polled = queue.poll();
            int x = polled[0];
            int y = polled[1];

            if(feedVisited[x][y])
                continue;

            if(map[x][y] > sharkSize)
                continue;

            // feed found
            if(0 < map[x][y] && map[x][y] < sharkSize){
                int[] currentFeed = new int[]{x, y};
                if(feed == null){
                    feed = currentFeed;
                }
                else{
                    feed = comparePoint(feed, currentFeed);
                }
            }

            feedVisited[x][y] = true;

            for(int i = 0 ; i < 4 ; i++){
                int newX = x + directionX[i];
                int newY = y + directionY[i];

                if((0 <= newX && newX < N) && (0 <= newY && newY < N)){
                    if(feedVisited[newX][newY])
                        continue;

                    int[] newPoint = {newX, newY};
                    queue.add(newPoint);

                    if(distanceMap[newX][newY] > 0){
                        distanceMap[newX][newY] = Math.min(distanceMap[x][y] + 1, distanceMap[newX][newY]);
                    }
                    else{
                        distanceMap[newX][newY] = distanceMap[x][y] + 1;
                    }
                }
            }
        }

        return feed;
    }

    static void solve(){
        while(true){
            int[] startPoint = {sharkX, sharkY};

            int[] feed = findFeeds(startPoint);

            if(feed == null)
                break;

            int x = feed[0]; int y = feed[1];

            eatCnt++;
            if(eatCnt >= sharkSize){
                sharkSize++;
                eatCnt = 0;
            }

            time += distanceMap[x][y];

            map[x][y] = 0;

            sharkX = x;
            sharkY = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int y = 0 ; y < N ; y++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int x = 0 ; x < N ; x++){
                int value = Integer.parseInt(st.nextToken());

                if(value == 9){
                    sharkX = x; sharkY = y;
                }
                else{
                    map[x][y] = value;
                }
            }
        }

        solve();

        System.out.println(time);
    }
}