import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

  // Complete the freqQuery function below.
  static List<Integer> freqQuery(List<List<Integer>> queries) {
    List<Integer> result = new LinkedList<>();
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Integer> valueMap = new HashMap<>();

    for(List<Integer> query : queries){
      int operation = query.get(0);
      int key = query.get(1);

      if(operation == 1){
        putValue(map, valueMap, key);
        continue;
      }

      if(operation == 2){
        if(!map.containsKey(key))
          continue;

        pollValue(map, valueMap, key);
        continue;
      }
      result.add(valueMap.containsKey(key) ? 1 : 0);
    }

    return result;
  }

  private static void putValue(Map<Integer, Integer> map, Map<Integer, Integer> valueMap, int key){
    int nextValue = map.getOrDefault(key, 0) + 1;
    map.put(key, nextValue);
    updateValueMap(valueMap, nextValue - 1, nextValue);
  }

  private static void pollValue(Map<Integer, Integer> map, Map<Integer, Integer> valueMap, int key){
    int value = map.get(key);
    if(value == 0)
      return;

    int nextValue = value - 1;

    if(nextValue > 0)
      map.put(key, nextValue);
    else
      map.remove(key);

    updateValueMap(valueMap, value, nextValue);
  }

  private static void updateValueMap(Map<Integer, Integer> valueMap, int prevKey, int key){
    if(valueMap.containsKey(prevKey)){
      int prev = valueMap.get(prevKey);
      if(prev - 1 <= 0)
        valueMap.remove(prevKey);
      else
        valueMap.put(prevKey, prev - 1);
    }

    if(key > 0)
      valueMap.put(key, valueMap.getOrDefault(key , 0) + 1);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = Integer.parseInt(bufferedReader.readLine().trim());

    List<List<Integer>> queries = new ArrayList<>();

    IntStream.range(0, q).forEach(i -> {
      try {
        queries.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
        );
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    List<Integer> ans = freqQuery(queries);

    bufferedWriter.write(
        ans.stream()
            .map(Object::toString)
            .collect(joining("\n"))
            + "\n"
    );

    bufferedReader.close();
    bufferedWriter.close();
  }
}
