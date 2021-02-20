import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(((o1, o2) -> { return o1 - o2; }));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> { return o2 - o1; }));

        StringBuilder sb = new StringBuilder();

        while(N-- > 0){
            int value = Integer.parseInt(br.readLine());

            if(minHeap.size() == maxHeap.size())
                maxHeap.offer(value);
            else
                minHeap.offer(value);

            if(!minHeap.isEmpty() && !maxHeap.isEmpty()){
                if(minHeap.peek() < maxHeap.peek()){
                    int temp = minHeap.poll();
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(temp);
                }
            }
            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.print(sb.toString());
    }
}