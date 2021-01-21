import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class BridgeMap{
    int width, height;
    int[][] map;
    List<Edge> edges;
    int[] islandParents;

    static int ISLAND_CNT = 1;

    public BridgeMap(int width, int height){
        this.width = width;
        this.height = height;

        map = new int[width][height];
        edges = new ArrayList<>();
    }

    public void addMapData(int x, int y, int data){
        map[x][y] = data;
    }

    public void printArray(int[][] arr){
        StringBuilder sb = new StringBuilder();
        for(int y = 0 ; y < height ; y++){
            for(int x = 0 ; x < width ; x++){
                sb.append(arr[x][y]);
                sb.append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    public void labelUsingBFS(){
        int[][] visited = new int[width][height];

        Queue<Point> queue = new ArrayDeque<>();

        int[] directionX = {0, 0, -1, 1};
        int[] directionY = {-1, 1, 0, 0};

        for(int y = 0 ; y < height ; y++){
            for(int x = 0 ; x < width ; x++){
                if(visited[x][y] == 0 && map[x][y] != 0){
                    queue.add(new Point(x, y));

                    while(!queue.isEmpty()){
                        Point p = queue.poll();

                        if(map[p.x][p.y] == 0)
                            continue;

                        if(visited[p.x][p.y] == 0){
                            visited[p.x][p.y] = ISLAND_CNT;

                            for(int i = 0 ; i < 4 ; i++){
                                int newX = p.x + directionX[i];
                                int newY = p.y + directionY[i];

                                if((0 <= newX && newX < width) && (0 <= newY && newY < height)){
                                    queue.add(new Point(newX, newY));
                                }
                            }
                        }
                    }
                    ISLAND_CNT++;
                }
            }
        }
        map = visited;
    }

    public void makeEdges(){
        int[] directionX = {0, 0, -1, 1};
        int[] directionY = {-1, 1, 0, 0};

        for(int y = 0 ; y < height ; y++){
            for(int x = 0 ; x < width ; x++){
                if(map[x][y] != 0){
                    for(int i = 0 ; i < 4 ; i++){
                        int newX = x + directionX[i];
                        int newY = y + directionY[i];

                        int cost = 0;
                        while(true){
                            if((0 <= newX && newX < width) && (0 <= newY && newY < height)){
                                if(map[newX][newY] == 0){
                                    cost++;
                                    newX += directionX[i];
                                    newY += directionY[i];
                                }
                                else{
                                    if(map[newX][newY] != map[x][y]){
                                        if(cost > 1){
                                            edges.add(new Edge(new Point(x, y), new Point(newX, newY), cost));
                                        }
                                    }

                                    break;
                                }
                            }
                            else{
                                break;
                            }
                        }
                    }
                }
            }
        }

        edges.sort(Edge::compareTo);
    }

    public int findIslandParent(int islandIdx){
        if(islandParents[islandIdx] == islandIdx)
            return islandIdx;
        return islandParents[islandIdx] = findIslandParent(islandParents[islandIdx]);
    }

    public void unionIsland(int aIslandIdx, int bIslandIdx){
        int aIslandParentIdx = findIslandParent(aIslandIdx);
        int bIslandParentIdx = findIslandParent(bIslandIdx);

        if(aIslandParentIdx != bIslandParentIdx){
            islandParents[aIslandParentIdx] = bIslandIdx;
        }
    }

    public int getMinimumCost(){
        islandParents = new int[ISLAND_CNT];
        for(int i = 0 ; i < ISLAND_CNT ; i++){
            islandParents[i] = i;
        }

        int totalCost = 0;
        int connectedEdgeCnt = 0;
        for(Edge e : edges){
            Point p1 = e.p1;
            Point p2 = e.p2;

            int p1IslandIndex = findIslandParent(map[p1.x][p1.y]);
            int p2IslandIndex = findIslandParent(map[p2.x][p2.y]);

            if(p1IslandIndex != p2IslandIndex){
                unionIsland(p1IslandIndex, p2IslandIndex);
                totalCost += e.cost;
                connectedEdgeCnt++;
            }
        }
        if(totalCost == 0)
            return -1;
        if(ISLAND_CNT - 2 != connectedEdgeCnt)
            return -1;
        return totalCost;
    }
}

class Point{
    int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge>{
    Point p1, p2;
    int cost;

    public Edge(Point p1, Point p2, int cost){
        this.p1 = p1;
        this.p2 = p2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        if(this.cost < e.cost)
            return -1;
        return 1;
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        BridgeMap bridgeMap = new BridgeMap(M, N);
        for(int y = 0 ; y < N ; y++){
            st = new StringTokenizer(br.readLine());
            for(int x = 0 ; x < M ; x++){
                int data = Integer.parseInt(st.nextToken());
                bridgeMap.addMapData(x, y, data);
            }
        }

        bridgeMap.labelUsingBFS();
        bridgeMap.makeEdges();

        System.out.println(bridgeMap.getMinimumCost());

    }
}