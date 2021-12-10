import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<String> guitars = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            guitars.add(br.readLine());
        }

        guitars.sort(new Comparator<String>() {
            private int sums(String guitar){
                int sum = 0;
                for(char c : guitar.toCharArray()){
                    if('0' <= c && c <= '9'){
                        sum += c - '0';
                    }
                }
                return sum;
            }

            @Override
            public int compare(String s1, String s2) {
                if(s1.length() != s2.length()) return s1.length() - s2.length();
                int sum1 = sums(s1);
                int sum2 = sums(s2);
                if(sum1 != sum2) return sum1 - sum2;

                return s1.compareTo(s2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for(String s : guitars){
            sb.append(s);
            sb.append('\n');
        }
        System.out.println(sb.toString());


    }
}