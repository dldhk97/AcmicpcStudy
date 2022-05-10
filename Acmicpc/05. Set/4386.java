import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    List<Point> points;
    int[] parents;


    public Graph(int size){
        this.size = size;
        points = new ArrayList<>();
        parents = new int[size];

        for(int i = 0 ; i < size ; i++){
            parents[i] = i;
        }
    }

    public void addPoint(int index, double x, double y){
        points.add(new Point(index, x, y));
    }

    private int find(int x){
        if(parents[x] == x)
            return x;
        return parents[x] = find(parents[x]);
    }

    private void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot)
            return;

        parents[aRoot] = bRoot;
    }

    public void createMST(){
        List<Edge> edges = new ArrayList<>();
        for(int i = 0 ; i < points.size() ; i++){
            for(int j = i + 1 ; j < points.size() ; j++){
                edges.add(new Edge(points.get(i), points.get(j)));
            }
        }

        edges.sort(Edge::compareTo);

        double sum = 0;

        for(Edge e : edges){
            int aRoot = find(e.from.index);
            int bRoot = find(e.to.index);

            if(aRoot == bRoot)
                continue;

            union(aRoot, bRoot);
            sum += e.distance;
        }

        System.out.println(String.format("%.2f", sum));
    }
}

class Point{
    int index;
    double x, y;

    public Point(int index, double x, double y){
        this.index = index;
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge>{
    Point from, to;
    double distance;

    public Edge(Point from, Point to){
        this.from = from;
        this.to = to;

        double xDiff = Math.abs(from.x - to.x);
        double yDiff = Math.abs(from.y - to.y);
        distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
    }

    @Override
    public int compareTo(Edge e) {
        return (int)(this.distance - e.distance);
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Graph graph = new Graph(n);

        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            graph.addPoint(i, x, y);
        }

        graph.createMST();
    }
}