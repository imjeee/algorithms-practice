import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.StringBuffer;

class ProgramInterviewQuestions {
  public static void main(String[] args){
    //System.out.println("programming interview questions practice!\n");
    //System.out.println(allUniqueChars("whatis up?"));
    //System.out.println(allUniqueChars2("whatis up?"));
    //System.out.println(anagram("banana", "anaban"));
    //System.out.println(replaceSpaceWith20("hello how are you doing?"));
    //System.out.println(82/10);
    //fill(2, 2);
    //System.out.println(reverseString2("hello how are you doing?"));
    //System.out.println(findBitPosition(33));
    //int[] input = {-1, 5, -2, 3, 9, -10, -5, 3};
    int[] input = {-1, 5, -2, 3, 9, -10, -5, 3, -10, 9, 8, 7, -30, -100};
    String s = "Mhmdsddm Gtmcqdc Tkkqhbg Zud Ztrshm SW";
    //System.out.println(unscramble(s));
    //switchem(input);
    //fixarray(input);
    int[] i = { Integer.MAX_VALUE };
    System.out.println(i[0]);
    i[0]++;
    System.out.println(i[0]);

    groupStrings();
    isSubString();
    maxSubArray();
  }
  
  public static void maxSubArray() {
    int[] array = {10, 1, -100,20,3,-300, 1000};
    System.out.println("max array length is " + maxSubArray(array));
  }
  
  public static int maxSubArray(int[] array) {
    int currentMax = 0;
    int maxSumSoFar = 0;
    int maxBeginning = 0;
    int maxEnd = 0;
    int currentBeginning = 0;
    
    for (int counter = 0; counter < array.length; counter++) {
      if (currentMax + array[counter] > 0) {
        currentMax = currentMax += array[counter];
        if (currentMax > maxSumSoFar) {
          maxSumSoFar = currentMax;
          maxEnd = counter;
          maxBeginning = currentBeginning;
        }
      } else {
        currentMax = 0;
        
        currentBeginning = counter + 1;
      }
    }
    
    for (int i = maxBeginning; i <= maxEnd; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
    return maxSumSoFar;
  }
    

  public static void isSubString() {
    String source = "why hello there";
    String target = "hello2";
    
    System.out.println(isSubString(source, target));
  }
  
  private static boolean isSubString(String source, String target) {
    if (target.length() > source.length()) {
      return false;
    }
    
    for (int i = source.length(); i >= target.length(); i--) {
      if (source.substring(i - target.length(), i).equals(target))
        return true;
    }
    return false;
  }
  
  public static void groupStrings() {
    String[] source = {"a", "ab", "fadeer", "asdwer", "awerd", "acfgdsfgser"};
    String beginning = "ac";
    String end = "afd";
    List<String> result = new ArrayList<String>();
    for (int i = 0; i < source.length; i++) {
      if (isBiggerThanOrEqualTo(source[i], beginning) && isSmallerThanOrEqualTo(source[i], end)) {
        result.add(source[i]);
        System.out.println(source[i]);
      }
    }
  }
    
  private static boolean isBiggerThanOrEqualTo(String source, String beginning) {
    int checkLength = source.length() < beginning.length() ? source.length() : beginning.length();
    for (int i = 0; i < checkLength; i++) {
      if (source.charAt(i) < beginning.charAt(i)) {
        return false;
      } else if (source.charAt(i) > beginning.charAt(i)) {
        return true;
      }
    }
    return source.length() >= beginning.length();
  }
    
  private static boolean isSmallerThanOrEqualTo(String source, String end) {
    int checkLength = source.length() < end.length() ? source.length() : end.length();
    for (int i = 0; i < checkLength; i++) {
      if (source.charAt(i) > end.charAt(i)) {
        return false;
      } else if (source.charAt(i) < end.charAt(i)) {
        return true;
      }
    }
    return source.length() <= end.length();
  }
    
  
  
  public static String unscramble(String s){
    char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};    
    StringBuffer result = new StringBuffer();
    s = s.toLowerCase();
    for (int i = 0; i < s.length(); i++){
      
    }
    return result.toString();
  }

  public static void fixarray(int[] arr)
  {
    int firstPositiveIdx = 0;
    for (int i = 0; i < arr.length; i++)
      {
        if (arr[i] < 0)
          {
            int temp = arr[i];
            arr[i] = arr[firstPositiveIdx];
            arr[firstPositiveIdx] = temp;
                
            firstPositiveIdx++;
          }
      }
    for (int i = 0; i < arr.length; i++){
      System.out.print(arr[i] + " ");
    }
  }

  public static int[] switchem(int[] input){
    
    int front = 0;
    int back = 0;

    while (true){
      // back go find a positive #
      while(back < input.length && input[back] < 0)
        back++;

      front = back;

      // front go find a negative #
      while (front < input.length && input[front] > 0)
        front++;

      if (front >= input.length)
        break;
      

      // swap those numbers
      int tmp = input[back];
      input[back] = input[front];
      input[front] = tmp;
    }
    return input;
  }

  public static int findBitPosition(int i){
    int result = 0;
    while (i != 0){
      if ((1 & i) == 1)
        return result;
      System.out.println(result + " " + i);
      result++;
      i >>= 1;
    }
    return result;
  }

  public static String reverseString(String s){
    String[] tokens = s.split(" ");
    StringBuffer buffer = new StringBuffer();
    for (int i = tokens.length - 1; i > 0; i--){
      buffer.append(tokens[i] + " ");
    }
    buffer.append(tokens[0]);
    return buffer.toString();
  }

  public static void fill(int x, int y){
    int width = 5;
    int height = 5;
    
    int[] board = new int[width*height];

    for (int i = 0; i < width; i++){
      for (int j = 0; j < height; j++){
        board[i*width + j] = 0;
      }
    }

    int pos = width * x + y;
    board[pos] = 7;

    for (int i = 0; i < width; i++){
      for (int j = 0; j < height; j++){
        System.out.print(board[i*width + j]);
      }
      System.out.println();
    }    
  }

  public static boolean allUniqueChars(String s){
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    for (int i = 0; i < s.length(); i++){
      char target = s.charAt(i);
      if (map.containsKey(target + ""))
        return false;
      map.put(target + "", 1);
    }
    return true;
  }

  public static boolean allUniqueChars2(String s){
    for (int i = 0; i < s.length(); i++){
      char tchar = s.charAt(i);
      for (int j = i+1; j < s.length(); j++){
        if (tchar == s.charAt(j))
          return false;
      }
    }
    return true;
  }

  public static boolean anagram(String one, String two){
    if (one.length() != two.length())
      return false;
    
    int[] oneCounter = new int[256];
    int[] twoCounter = new int[256];
    
    for (int i = 0; i < one.length(); i++){
      ++oneCounter[one.charAt(i)];
      ++twoCounter[two.charAt(i)];
    }

    for (int i = 0; i < oneCounter.length; i++){
      if (oneCounter[i] != twoCounter[i])
        return false;
    }
    return true;
  }

  public static String replaceSpaceWith20(String s){
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++){
      if (s.charAt(i) == ' ')
        sb.append("%20");
      else
        sb.append(s.charAt(i));
    }
    return sb.toString();
  }


}
