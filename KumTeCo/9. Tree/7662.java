import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class DualPriorityQueue{

    boolean[] isRemoved;

    PriorityQueue<long[]> minHeap = new PriorityQueue<>((o1, o2) -> {
        long o1Value = o1[1];
        long o2Value = o2[1];
        return Long.compare(o1Value, o2Value);
    });

    PriorityQueue<long[]> maxHeap = new PriorityQueue<>((o1, o2) -> {
        long o1Value = o1[1];
        long o2Value = o2[1];
        return Long.compare(o2Value, o1Value);
    });

    public DualPriorityQueue(int N){
        isRemoved = new boolean[N + 1];
    }

    public void insert(int index, long value){
        maxHeap.add(new long[] {index, value});
        minHeap.add(new long[] {index, value});
    }

    public void delete(boolean isMaxHeap){
        if(isMaxHeap){
            while(!maxHeap.isEmpty()){
                long[] polled = maxHeap.poll();
                int index = (int)polled[0];
                if(!isRemoved[index]){
                    isRemoved[index] = true;
                    return;
                }
            }
        }
        else{
            while(!minHeap.isEmpty()){
                long[] polled = minHeap.poll();
                int index = (int)polled[0];
                if(!isRemoved[index]){
                    isRemoved[index] = true;
                    return;
                }
            }
        }
    }

    public long pollMax(){
        while(!maxHeap.isEmpty()){
            long[] polled = maxHeap.poll();
            int index = (int)polled[0] ; long value = polled[1];

            if(isRemoved[index]) continue;
            return value;
        }
        return Long.MIN_VALUE;
    }

    public long pollMin(){
        while(!minHeap.isEmpty()){

            long[] polled = minHeap.poll();
            int index = (int)polled[0] ; long value = polled[1];

            if(isRemoved[index]) continue;
            return value;
        }
        return Long.MAX_VALUE;
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());

            DualPriorityQueue pq = new DualPriorityQueue(N);

            for(int i = 0 ; i < N ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                char operator = st.nextToken().charAt(0);
                long value = Long.parseLong(st.nextToken());

                if(operator == 'I'){
                    pq.insert(i, value);
                }
                else if(operator == 'D'){
                    pq.delete(value == 1);
                }
            }

            long max = pq.pollMax();
            long min = pq.pollMin();

            if(max == Long.MIN_VALUE || min == Long.MAX_VALUE)
                sb.append("EMPTY\n");
            else
                sb.append(max).append(" ").append(min).append("\n");
        }

        System.out.print(sb.toString());
    }
}