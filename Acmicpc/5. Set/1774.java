import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

class Graph{
    int size;
    ArrayList<Point> points;

    int[] parents;

    public Graph(int size){
        this.size = size;

        points = new ArrayList<>();

        parents = new int[size];
        for(int i = 0 ; i < size ; i++){
            parents[i] = i;
        }

    }

    public void addPoint(int x, int y, int index){
        Point point1 = new Point(x, y, index);
        points.add(point1);
    }

    // 두 점 사이의 거리 계산
    public double calcDistance(Point a, Point b){
        int xDiff = a.x - b.x;
        int yDiff = a.y - b.y;

        return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
    }

    public int findRoot(int x){
        if(x == parents[x])
            return x;
        return parents[x] = findRoot(parents[x]);
    }

    public void union(int a, int b){
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);

        if(aRoot == bRoot)
            return;

        parents[aRoot] = bRoot;
    }

    public double solve(){
        LinkedList<Edge> totalEdges = new LinkedList<>();

        // 모든 점과 점사이를 계산해서 거리 계산.
        for(int i = 1 ; i < size ; i++){
            for(int j = i + 1 ; j < size ; j++){
                double distance = calcDistance(points.get(i - 1), points.get(j - 1));       // 점들의 인덱스는 0부터 시작함.
                totalEdges.add(new Edge(i, j, distance));
            }
        }

        totalEdges.sort(Edge::compareTo);

        double sum = 0;

        for(Edge e : totalEdges){

            int fromRoot = findRoot(e.from);
            int toRoot = findRoot(e.to);

            if(fromRoot == toRoot){
                continue;
            }

            union(fromRoot, toRoot);

            sum += e.distance;
        }

        return sum;
    }
}

class Point{
    int x, y, index;

    public Point(int x, int y, int index){
        this.x = x;
        this.y = y;
        this.index = index;
    }
}

class Edge implements Comparable<Edge>{
    int from, to;
    double distance;

    public Edge(int from, int to, double distance){
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge e){
        if(this.distance < e.distance)
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

        Graph graph = new Graph(N + 1);

        for(int i = 1 ; i < N + 1 ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.addPoint(x, y, i);
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.union(a, b);
        }

        double result = graph.solve();

        System.out.println(String.format("%.2f", result));

    }
}