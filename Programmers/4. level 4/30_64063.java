import java.util.*;
import java.util.Map.*;

// https://programmers.co.kr/learn/courses/30/lessons/64063
class Solution {
  public long[] solution(long k, long[] room_number) {

    TreeMap<Long, Long> rangeMap = new TreeMap<>();
    long[] result = new long[room_number.length];
    int resultIdx = 0;

    for(long wanted : room_number){
      long start = wanted;
      long end = wanted;

      Entry<Long, Long> prevEntry = rangeMap.floorEntry(wanted);
      if(prevEntry != null){
        if(wanted <= prevEntry.getValue()){
          rangeMap.remove(prevEntry.getKey());
          start = prevEntry.getKey();
          end = prevEntry.getValue() + 1;
          wanted = end;
        }
        else if(prevEntry.getValue() + 1 == wanted) {
          rangeMap.remove(prevEntry.getKey());
          start = prevEntry.getKey();
        }
      }

      Entry<Long, Long> nextEntry = rangeMap.ceilingEntry(wanted);
      if(nextEntry != null){
        if(nextEntry.getKey() <= wanted){
          rangeMap.remove(nextEntry.getKey());
          start = nextEntry.getKey();
          end = nextEntry.getValue() + 1;
          wanted = end;
        }
        else if(nextEntry.getKey() - 1 == wanted) {
          rangeMap.remove(nextEntry.getKey());
          end = nextEntry.getValue();
        }
      }

      rangeMap.put(start, end);
      result[resultIdx++] = wanted;
    }

    return result;
  }
}