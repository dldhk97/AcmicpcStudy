import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    final static long INF = 200000000;
    int size;

    List<int[]> points;

    public Graph(int size){
        this.size = size;

        points = new ArrayList<>();
    }

    public void addPoint(int x, int y){
        points.add(new int[]{x, y});
    }

    private long calcWeight(int[] p1, int[] p2){
        int p1x = p1[0]; int p1y = p1[1];
        int p2x = p2[0]; int p2y = p2[1];

        return Math.abs(p1x - p2x) + Math.abs(p1y - p2y);
    }

    private boolean[][] createAvailables(){
        boolean[][] availables = new boolean[size][];
        for(int i = 0 ; i < size ; i++){
            availables[i] = new boolean[size];
        }

        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                long weight = calcWeight(points.get(i), points.get(j));
                if(weight <= 1000){
                    availables[i][j] = true;
                    availables[j][i] = true;
                }
            }
        }

        return availables;
    }

    public boolean floydWarshall(){
        boolean[][] availables = createAvailables();

        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                for(int k = 0 ; k < size ; k++){
                    if(availables[j][i] && availables[i][k])
                        availables[j][k] = true;
                }
            }
        }

        return availables[0][size - 1];
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());

            Graph graph = new Graph(n + 2);

            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken()); int startY = Integer.parseInt(st.nextToken());
            graph.addPoint(startX, startY);

            for(int i = 0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()); int y = Integer.parseInt(st.nextToken());
                graph.addPoint(x, y);
            }

            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken()); int endY = Integer.parseInt(st.nextToken());
            graph.addPoint(endX, endY);

            boolean result = graph.floydWarshall();
            sb.append(result ? "happy" : "sad").append("\n");
        }
        System.out.print(sb.toString());
    }
}