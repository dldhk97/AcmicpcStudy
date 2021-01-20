import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class PlanetGraph {
    int size;
    ArrayList<Planet> planets;
    ArrayList<Tunnel> tunnels;
    int[] parents;

    public PlanetGraph(int size){
        this.size = size;

        planets = new ArrayList<>();
        tunnels = new ArrayList<>();

        parents = new int[size];
        for(int i = 0 ; i < size ; i++){
            parents[i] = i;
        }
    }

    public void insertPlanet(Planet newPlanet){
        planets.add(newPlanet);
    }

    public int findParent(int planetIndex){
        if(parents[planetIndex] == planetIndex)
            return planetIndex;
        return parents[planetIndex] = findParent(parents[planetIndex]);
    }

    public void union(int planetAIndex, int planetBIndex){
        int planetARoot = findParent(planetAIndex);
        int planetBRoot = findParent(planetBIndex);

        if(planetARoot != planetBRoot){
            parents[planetARoot] = planetBRoot;
        }
    }

    public void sortByDistanceOf(char axis){
        planets.sort((nowPlanet, nextPlanet) -> {
            int nowPlanetValue = nowPlanet.getAxisValue(axis);
            int nextPlanetValue = nextPlanet.getAxisValue(axis);

            return nowPlanetValue - nextPlanetValue;
        });
    }

    public void addTunnelByAxis(char axis){
        sortByDistanceOf(axis);

        for(int i = 0 ; i < size - 1 ; i++){
            Planet startPlanet = planets.get(i);
            Planet destinationPlanet = planets.get(i + 1);

            int startPlanetAxisValue = startPlanet.getAxisValue(axis);
            int destinationPlanetAxisValue = destinationPlanet.getAxisValue(axis);
            int weight = Math.abs(destinationPlanetAxisValue - startPlanetAxisValue);

            tunnels.add(new Tunnel(startPlanet.index, destinationPlanet.index, weight));
        }
    }

    private long calculateMinimumCost(){
        tunnels.sort(Tunnel::compareTo);

        long weightSum = 0;
        for(Tunnel t : tunnels){
            int startPlanetRoot = findParent(t.startPlanetIndex);
            int destinationPlanetRoot = findParent(t.destinationPlanetIndex);

            if(startPlanetRoot != destinationPlanetRoot){
                union(startPlanetRoot, destinationPlanetRoot);
                weightSum += t.weight;
            }
        }
        return weightSum;
    }

    public long getMinimumCost(){
        addTunnelByAxis('x');
        addTunnelByAxis('y');
        addTunnelByAxis('z');

        return calculateMinimumCost();
    }
}

class Planet{
    int x, y, z, index;

    public Planet(int x, int y, int z, int index){
        this.x = x;
        this.y = y;
        this.z = z;
        this.index = index;
    }

    public int getAxisValue(char axis){
        switch(axis){
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
            default:
                return -1;
        }
    }
}

class Tunnel implements Comparable<Tunnel>{
    int startPlanetIndex, destinationPlanetIndex, weight;

    public Tunnel(int startPlanetIndex, int destinationPlanetIndex, int weight){
        this.startPlanetIndex = startPlanetIndex;
        this.destinationPlanetIndex = destinationPlanetIndex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Tunnel e) {
        if(this.weight < e.weight)
            return -1;
        return 1;
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PlanetGraph planetGraph = new PlanetGraph(N);

        for(int i = 0 ; i < N ; i++){
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            int z = Integer.parseInt(stringTokenizer.nextToken());

            planetGraph.insertPlanet(new Planet(x, y, z, i));
        }

        long cost = planetGraph.getMinimumCost();

        System.out.println(cost);
    }
}