package NewDataStructures;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class BrowserHistory {
  
  Stack<String> browserHistoryStack;
  
  public BrowserHistory() {
    browserHistoryStack = new Stack<String>();
  }
  
  public String[] getHistory(int n) {
    ArrayList<String> browsingHistory = new ArrayList<String>();
    Stack<String> tempBrowserHistoryStack = browserHistoryStack;
    HashMap<String, Integer> trackDuplicates = new HashMap<String, Integer>();
    int i = 0;    
    while(!tempBrowserHistoryStack.isEmpty() && i < n) {
      String visitedPage = tempBrowserHistoryStack.pop();
      if (!trackDuplicates.containsKey(visitedPage)) {
        browsingHistory.add(visitedPage);
        trackDuplicates.put(visitedPage, 1);
        i++;    
      }
    }
    String[] output = new String[browsingHistory.size()];
    for (int j = 0; j < browsingHistory.size(); j++)
      output[j] = browsingHistory.get(j);
    return output;
  }
  
  public void visit(String s) {
    browserHistoryStack.add(s);
  }
}
