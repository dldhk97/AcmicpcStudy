import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2477
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int K = Integer.parseInt(br.readLine());

    LinkedList<int[]> edges = new LinkedList<>();

    for(int i = 0 ; i < 6 ; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      int dir = Integer.parseInt(st.nextToken());
      int length = Integer.parseInt(st.nextToken());

      edges.add(new int[]{dir, length});
    }

    for(int i = 0 ; i < 6 ; i++)
      edges.add(edges.get(i));

    for(int i = 0 ; i < edges.size() - 3 ; i++){
      if(edges.get(i)[0] == edges.get(i + 2)[0] && edges.get(i + 1)[0] == edges.get(i + 3)[0]){
        int width = edges.get(i)[1] + edges.get(i + 2)[1];
        int height = edges.get(i + 1)[1] + edges.get(i + 3)[1];

        int miniWidth = edges.get(i + 2)[1];
        int miniHeight = edges.get(i + 1)[1];

        int result = (width * height - (miniWidth * miniHeight)) * K;
        System.out.println(result);
        break;
      }
    }

  }

}