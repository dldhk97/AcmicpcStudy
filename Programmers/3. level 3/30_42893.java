import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://programmers.co.kr/learn/courses/30/lessons/42893
class Solution {

  Map<String, Page> urlPageMap = new HashMap<>();

  public int solution(String word, String[] pages) {
    int index = 0;
    for(String pageStr : pages){
      Page page = new Page(index++, pageStr, word.toLowerCase());
      urlPageMap.put(page.url, page);
    }

    for(Page page : urlPageMap.values())
      page.updateMatchScore(urlPageMap);

    return urlPageMap.values().stream().sorted().findFirst().get().index;
  }
}

class Page implements Comparable<Page>{
  int index;
  String url;
  StringBuilder body = new StringBuilder();
  Set<String> referencingPageUrls = new HashSet<>();
  private final Pattern keywordPattern;

  double linkScore = 0;
  double defaultScore;
  double matchScore;

  public Page(int index, String pageStr, String keyword) {
    this.index = index;
    this.keywordPattern = Pattern.compile(keyword);
    parse(pageStr);
    this.defaultScore = calcDefaultScore();
  }

  private final static Pattern contentPattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(\\S*)\"");
  private final static Pattern bodyStartPattern = Pattern.compile("<body>");
  private final static Pattern bodyEndPattern = Pattern.compile("</body>");
  private final static Pattern hrefPattern = Pattern.compile("<a href=\"https://(\\S*)\"");
  private final static Pattern hrefEndPattern = Pattern.compile("</a>");

  private void parse(String str){
    boolean isHeader = true;
    StringTokenizer st = new StringTokenizer(str, "<");

    while(st.hasMoreTokens()){
      String line = "<" + st.nextToken().trim();

      if(isHeader){
        Matcher contentMatcher = contentPattern.matcher(line);
        if(contentMatcher.find()){
          this.url = line.substring(contentMatcher.start() + 33, contentMatcher.end() - 1);
          continue;
        }

        Matcher bodyMatcher = bodyStartPattern.matcher(line);
        if(bodyMatcher.find()){
          if(line.length() > bodyMatcher.end())
            body.append(line.substring(bodyMatcher.end()).toLowerCase());
          isHeader = false;
        }
        continue;
      }

      Matcher bodyEndMatcher = bodyEndPattern.matcher(line);
      if(bodyEndMatcher.find()) {
        if(bodyEndMatcher.start() > 0)
          body.append(line.substring(0, bodyEndMatcher.start()).toLowerCase());
        return;
      }
      else{
        // body
        Matcher hrefMatcher = hrefPattern.matcher(line);

        // has href
        if(hrefMatcher.find()){
          this.referencingPageUrls.add(line.substring(hrefMatcher.start() + 9, hrefMatcher.end() - 1));

          // find close
          int hrefHeadCloseIdx = 0;
          for(int i = hrefMatcher.start() ; i < line.length() ; i++) {
            if (line.charAt(i) == '>') {
              hrefHeadCloseIdx = i;
              break;
            }
          }

          // insert head
          body.append(line.substring(0, hrefMatcher.start()).toLowerCase());
          body.append(line.substring(hrefHeadCloseIdx + 1).toLowerCase());
          continue;
        }

        Matcher hrefEndMatcher = hrefEndPattern.matcher(line);
        if(hrefEndMatcher.find()){
          // insert tail
          body.append(line.substring(hrefEndMatcher.end()).toLowerCase());
        }

      }
    }
  }

  private int calcDefaultScore(){
    int cnt = 0;
    int findIdx = 0;
    Matcher matcher = keywordPattern.matcher(body);

    while(matcher.find(findIdx)){
      int startIdx = matcher.start();
      findIdx = matcher.end();

      if(startIdx > 0){
        if(Character.isAlphabetic(body.charAt(startIdx - 1)))
          continue;
      }
      if(findIdx < body.length()){
        if(Character.isAlphabetic(body.charAt(findIdx)))
          continue;
      }

      cnt++;
    }

    return cnt;
  }

  public void updateMatchScore(Map<String, Page> urlPageMap){
    for(Page page : urlPageMap.values()) {
      if(page == this)
        continue;
      if (page.referencingPageUrls.contains(this.url)) {
        this.linkScore += page.defaultScore / page.referencingPageUrls.size();
      }
    }

    this.matchScore = this.defaultScore + this.linkScore;
  }

  @Override
  public int compareTo(Page o) {
    if(this.matchScore == o.matchScore)
      return this.index - o.index;
    return Double.compare(o.matchScore, this.matchScore);
  }
}