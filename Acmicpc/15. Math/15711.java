import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/15711
public class Main {

  private static final long MAX = 4000000000000L;
  private static final int MAX_SQRT = (int)Math.sqrt(MAX);

  private static boolean[] primes;
  private static List<Integer> primeList = new ArrayList<>();

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();

    calcPrime();

    for(int i = 0 ; i < T ; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());

      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());

      if(solve(a, b))
        sb.append("YES").append("\n");
      else
        sb.append("NO").append("\n");
    }

    System.out.println(sb.toString().trim());
  }

  private static boolean solve(long a, long b){
    long sum = a + b;

    // 4보다 작은 수는 두 소수로 표현할 수 없음.
    if(sum <= 3)
      return false;

    // 골드바흐의 추측, 2보다 큰 짝수는 두 소수의 합으로 표현 가능하다.
    if(sum % 2 == 0)
      return true;

    if(sum <= MAX_SQRT){
      // 홀수는 짝수 + 홀수로 만들어진다.
      // 이 문제에서는 소수 + 소수인 경우를 원함.
      // 두 소수 중 하나는 짝수가 되어야하는데, 짝수인 소수는 2밖에 없음.
      // 즉, "sum - 2 = 소수"이면 소수의 합으로 표현 가능함.
      return primes[(int)sum - 2];
    }

    // 에라토스테네스의 채로 찾았던 소수로 나눠떨어지면, 그 수는 소수가 아님.
    for(int i : primeList){
      if(primes[i]){
        if((sum - 2) % i == 0)
          return false;
      }
    }

    return true;
  }

  private static void calcPrime(){
    primes = new boolean[MAX_SQRT + 1];
    Arrays.fill(primes, true);

    primes[1] = false;

    for(int i = 2 ; i <= MAX_SQRT ; i++){
      if(!primes[i])
        continue;
      primeList.add(i);

      for(int j = i * 2 ; j <= MAX_SQRT ; j += i){
        primes[j] = false;
      }
    }
  }

}
