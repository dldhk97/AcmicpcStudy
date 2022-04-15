import java.util.*;

class Solution {
  Node root;
  List<Node> nodes = new ArrayList<>();
  Set<Integer> leftOrders = new HashSet<>();

  public boolean solution(int n, int[][] path, int[][] order) {
    initNodes(n);
    createTree(path);
    setOrders(order);

    // 0번 방은 첫 방이기 때문에, 잠겨있으면 false
    if(nodes.get(0).isLocked)
      return false;

    bfs();

    return leftOrders.size() <= 0;
  }

  private void initNodes(int n){
    for(int i = 0 ; i < n ; i++)
      nodes.add(new Node(i));
    root = nodes.get(0);
  }

  private void createTree(int[][] paths){
    for(int[] path : paths){
      Node nodeA = nodes.get(path[0]);
      Node nodeB = nodes.get(path[1]);

      nodeA.connected.add(nodeB);
      nodeB.connected.add(nodeA);
    }
  }

  private void setOrders(int[][] order){
    int orderIdx = 0;
    for(int[] o : order){
      Node keyNode = nodes.get(o[0]);
      Node lockNode = nodes.get(o[1]);

      keyNode.pair = lockNode;
      lockNode.pair = keyNode;

      lockNode.isLocked = true;

      keyNode.orderIdx = orderIdx;
      lockNode.orderIdx = orderIdx;

      leftOrders.add(orderIdx++);
    }
  }

  private void bfs(){
    Queue<Node> queue = new ArrayDeque<>();
    queue.add(root);

    boolean[] visited = new boolean[nodes.size()];

    while(!queue.isEmpty()){
      Node polled = queue.poll();

      if(visited[polled.index])
        continue;
      visited[polled.index] = true;

      if(polled.orderIdx != -1){
        if(polled.isLocked){
          polled.isLocked = false;
          leftOrders.remove(polled.orderIdx);
        }
        else{
          if(isVisitedAdjacentExists(polled.pair, visited)) {
            queue.add(polled.pair);
          }
        }
      }

      for(Node adj : polled.connected){
        if(adj == polled)
          continue;
        if(adj.pair == null && visited[adj.index])
          continue;
        if(adj.pair != null){
          if(adj.isLocked && !visited[adj.pair.index]){
            continue;
          }
        }

        queue.add(adj);
      }
    }
  }

  private boolean isVisitedAdjacentExists(Node node, boolean[] visited){
    for(Node adj : node.connected){
      if(visited[adj.index])
        return true;
    }
    return false;
  }
}

class Node{
  int index;
  Set<Node> connected = new HashSet<>();

  boolean isLocked = false;
  Node pair;
  int orderIdx = -1;

  public Node(int index) {
    this.index = index;
  }
}