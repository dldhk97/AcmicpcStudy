import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    List<Integer> list = new ArrayList<>(N);
    for(int i = 0 ; i < N ; i++){
      list.add(i + 1);
    }

    StringBuilder sb = new StringBuilder("<");

    int currentIndex = 0;

    while(!list.isEmpty()){
      int delete = currentIndex + (K - 1);

      if(list.size() - 1 < delete){
        delete = delete % list.size();
      }

      sb.append(list.get(delete)).append(", ");

      list.remove(delete);
      currentIndex = delete;
    }

    sb.delete(sb.length() - 2, sb.length()).append(">");
    System.out.println(sb.toString());
  }

}