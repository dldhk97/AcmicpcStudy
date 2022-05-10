import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            StringTokenizer nmSt = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(nmSt.nextToken());
            int m = Integer.parseInt(nmSt.nextToken());

            Queue<Integer> queue = new ArrayDeque<>();
            StringTokenizer importanceSt = new StringTokenizer(br.readLine());

            while(importanceSt.hasMoreTokens()){
                queue.add(Integer.parseInt(importanceSt.nextToken()));
            }

            int markerIndex = m;
            int printCnt = 0;

            while(!queue.isEmpty()){
                boolean isPostponed = false;
                int front = queue.poll();

                for(int elem : queue){
                    if(front < elem){
                        queue.add(front);
                        isPostponed = true;
                        if(markerIndex == 0){
                            markerIndex = queue.size() - 1;
                        }
                        else{
                            markerIndex--;
                        }

                        break;
                    }
                }
                if(!isPostponed){
                    printCnt++;
                    if(markerIndex-- == 0){
                        System.out.println(printCnt);
                        break;
                    }

                }
            }
        }

    }
}