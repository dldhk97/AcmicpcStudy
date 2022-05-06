import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/60060
class Solution {
  public int[] solution(String[] words, String[] queries) {
    Tree suffixTree = new Tree();
    Tree prefixTree = new Tree();

    for(String word : words){
      suffixTree.addWord(word);
      prefixTree.addWord(reverseString(word));
    }

    int[] result = new int[queries.length];
    int idx = 0;

    for(String query : queries){
      if(query.charAt(0) != '?') {
        result[idx++] = suffixTree.find(query);
        continue;
      }

      if(query.charAt(query.length() - 1) == '?')
        result[idx++] = suffixTree.findAllWildCard(query);
      else
        result[idx++] = prefixTree.find(reverseString(query));
    }

    return result;
  }

  private String reverseString(String word){
    StringBuilder sb = new StringBuilder();
    for(char c : word.toCharArray())
      sb.insert(0, c);
    return sb.toString();
  }

}

class Tree{
  Node root = new Node('-');

  public void addWord(String word){
    Node prev = root;

    for(char c : word.toCharArray()){
      if(!prev.wordSetMap.containsKey(word.length()))
        prev.wordSetMap.put(word.length(), new HashSet<>());
      prev.wordSetMap.get(word.length()).add(word);

      if(prev.children.isEmpty() || !prev.children.containsKey(c)){
        Node newNode = new Node(c);
        prev.children.put(c, newNode);
        prev = newNode;
        continue;
      }

      prev = prev.children.get(c);
    }
  }

  public int findAllWildCard(String query){
    if(!root.wordSetMap.containsKey(query.length()))
      return 0;
    return root.wordSetMap.get(query.length()).size();
  }

  public int find(String query){
    Node prev = root;

    for(char c : query.toCharArray()){
      if(c == '?') {
        if(!prev.wordSetMap.containsKey(query.length()))
          return 0;
        return prev.wordSetMap.get(query.length()).size();
      }

      if(!prev.children.containsKey(c))
        break;

      prev = prev.children.get(c);
    }
    return 0;
  }
}

class Node{
  char character;
  Map<Character, Node> children = new HashMap<>();
  Map<Integer, Set<String>> wordSetMap = new HashMap<>();    // key: word length, value: words

  public Node(char character) {
    this.character = character;
  }
}
