import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = new_id.toLowerCase();
        answer = answer.replaceAll("[^-_\\.a-z0-9]", "");
        answer = answer.replaceAll("[\\\\.]+", ".");
        answer = answer.replaceAll("^\\.|\\.$", "");
        answer = answer.isEmpty() ? "a" : answer;
        answer = answer.length() >= 16 ? answer.substring(0, 15) : answer;
        answer = answer.replaceAll("\\.$", "");

        char lastChar = answer.charAt(answer.length() - 1);
        while(answer.length() <= 2){
            answer += lastChar;
        }

        return answer;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Solution solution = new Solution();

        String res = solution.solution(br.readLine());
        System.out.println(res);


    }
}