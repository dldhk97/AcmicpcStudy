import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Node {
  int value;
  Node left;
  Node right;

  public Node(int value) {
    this.value = value;
  }
}

class Result {

  /*
   * Complete the 'swapNodes' function below.
   *
   * The function is expected to return a 2D_INTEGER_ARRAY.
   * The function accepts following parameters:
   *  1. 2D_INTEGER_ARRAY indexes
   *  2. INTEGER_ARRAY queries
   */

  public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {

    Node root = new Node(1);
    createTree(root, indexes);

    List<List<Integer>> result = new ArrayList<>();
    for(int query : queries){
      swapNode(root, query, 1);

      List<Integer> tracker = new ArrayList<>();
      inorderTraversal(root, tracker);
      result.add(tracker);
    }

    return result;
  }

  private static void createTree(Node root, List<List<Integer>> indexes){
    Queue<Node> queue = new LinkedList<>();

    Node parent = root;
    queue.offer(parent);

    for(List<Integer> children : indexes){
      parent = queue.poll();

      parent.left = children.get(0) == -1 ? null : new Node(children.get(0));
      parent.right = children.get(1) == -1 ? null : new Node(children.get(1));

      if(parent.left != null)
        queue.add(parent.left);

      if(parent.right != null)
        queue.add(parent.right);
    }
  }

  private static void swapNode(Node node, int targetDepth, int depth){
    if(node == null)
      return;

    if(depth % targetDepth == 0){
      Node temp = node.left;
      node.left = node.right;
      node.right = temp;
    }

    swapNode(node.left, targetDepth, depth + 1);
    swapNode(node.right, targetDepth, depth + 1);
  }

  private static void inorderTraversal(Node node, List<Integer> tracker){
    if(node.left != null)
      inorderTraversal(node.left, tracker);

    tracker.add(node.value);

    if(node.right != null)
      inorderTraversal(node.right, tracker);
  }
}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<List<Integer>> indexes = new ArrayList<>();

    IntStream.range(0, n).forEach(i -> {
      try {
        indexes.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
        );
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
          try {
            return bufferedReader.readLine().replaceAll("\\s+$", "");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        })
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(toList());

    List<List<Integer>> result = Result.swapNodes(indexes, queries);

    result.stream()
        .map(
            r -> r.stream()
                .map(Object::toString)
                .collect(joining(" "))
        )
        .map(r -> r + "\n")
        .collect(toList())
        .forEach(e -> {
          try {
            bufferedWriter.write(e);
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });

    bufferedReader.close();
    bufferedWriter.close();
  }
}
