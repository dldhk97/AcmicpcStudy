import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/17686
class Solution {
  public String[] solution(String[] files) {
    List<File> list = new ArrayList<>();

    int index = 0;
    for(String fileName : files)
      list.add(new File(index++, fileName));

    list.sort(File::compareTo);

    return list.stream().map(file -> file.orgFileName).toArray(String[]::new);
  }
}
class File implements Comparable<File>{
  int index;
  String orgFileName;
  String head;
  int number;

  public File(int index, String orgFileName) {
    this.index = index;
    this.orgFileName = orgFileName;
    fillField(orgFileName);
  }

  private void fillField(String fileName){
    boolean isDigitFound = false;

    StringBuilder sb = new StringBuilder();
    for(int i = 0 ; i < fileName.length() ; i++){
      char c = fileName.charAt(i);

      if(Character.isDigit(c)){
        if(!isDigitFound){
          this.head = sb.toString().toLowerCase();
          sb = new StringBuilder();
        }
        isDigitFound = true;
        sb.append(c);
        continue;
      }

      if(isDigitFound)
        break;

      sb.append(c);
    }
    this.number = Integer.parseInt(sb.toString());
  }

  @Override
  public int compareTo(File f){
    if(this.head.equals(f.head)){
      if(this.number == f.number){
        return this.index - f.index;
      }
      return this.number - f.number;
    }
    return this.head.compareTo(f.head);
  }
}