import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int N, M, D;

    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, -1, 1, 0, 0};

    static int[] findEnemy(int archerX, int archerY, int[][] tempMap){
        boolean[][] visited = new boolean[M][N];
        int[][] distances = new int[M][N];
        for(int i = 0 ; i < M ; i++)
            Arrays.fill(distances[i], Integer.MAX_VALUE);

        Queue<int[]> queue = new ArrayDeque<>();
        int[] archerPos = {archerX, archerY};
        queue.add(archerPos);

        PriorityQueue<int[]> results = new PriorityQueue<>((o1, o2) -> {
            int dist1 = o1[2]; int dist2 = o2[2];
            if(dist1 == dist2)
                return o1[0] - o2[0];
            return dist1 - dist2;
        });

        while(!queue.isEmpty()){
            int[] polled = queue.poll();
            int x = polled[0]; int y = polled[1];

            if(visited[x][y]) continue;
            visited[x][y] = true;

            for(int i = 0 ; i < 5 ; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];

                if((0 <= newX && newX < M) && (0 <= newY && newY <= archerY)){
                    int dist = Math.abs(archerPos[0] - newX) + Math.abs(archerPos[1] - newY + 1);
                    if(dist <= D){
                        if(tempMap[newX][newY] == 1){
                            distances[newX][newY] = Math.min(distances[newX][newY], dist);
                            results.offer(new int[] {newX, newY, dist});
                        }
                        queue.add(new int[] {newX, newY});
                    }

                }
            }
        }

        if(results.isEmpty())
            return null;

        return results.poll();
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int[][] map = new int[M][N];

        for(int y = 0 ; y < N ; y++){
            st = new StringTokenizer(br.readLine());
            for(int x = 0 ; x < M ; x++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        int max = Integer.MIN_VALUE;
        for(int a = 0 ; a < M ; a++){
            for(int b = a + 1 ; b < M ; b++){
                for(int c = b + 1 ; c < M ; c++){
                    int eliminated = 0;

                    int[][] tempMap = new int[M][N];
                    for(int x = 0 ; x < M ; x++)
                        tempMap[x] = Arrays.copyOf(map[x], N);

                    for(int y = N - 1 ; y >= 0 ; y--){
                        int[] archers = new int[] {a, b, c};

                        List<int[]> enemies = new ArrayList<>();
                        for(int i = 0 ; i < 3 ; i++){
                            int[] enemy = findEnemy(archers[i], y, tempMap);

                            if(enemy == null) continue;

                            enemies.add(enemy);
                        }

                        for(int[] enemy : enemies){
                            int enemyX = enemy[0]; int enemyY = enemy[1];

                            if(tempMap[enemyX][enemyY] == 1){
                                tempMap[enemyX][enemyY] = 0;
                                eliminated++;
                            }
                        }
                    }

                    if(eliminated > max)
                        max = eliminated;

                }
            }
        }

        System.out.println(max);
    }
}