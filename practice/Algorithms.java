import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Algorithms {

  public static int convertBinaryStringToInt(String binary) {
    int result = 0;
    int binaryPos = 1;
    while (!binary.equals("")) {
      int lastElementPos = binary.length() - 1;
      char binaryChar = binary.charAt(lastElementPos);
      binary = binary.substring(0, lastElementPos);
      try {
        result += Integer.parseInt(binaryChar + "") * binaryPos;
        binaryPos *= 2;
      } catch (Exception e) {
        System.out.print("binary input has invalid character: " + binaryChar);
        break;
      }
    }
    return result;
  }
  
  /**
   * convert int to binary representation of it in string format
   * 
   * @param num
   * @return
   */
  public static String convertIntToBinary(int num) {
    String binary = "";
    boolean isOneOrZero = false;
    while (!isOneOrZero) {
      isOneOrZero = num == 0 || num == 1;
      String nextBinary = num % 2 == 0 ? "0" : "1";
      num /= 2;
      binary = nextBinary + binary;
    }
    return binary.toString();
  }
  
  /**
   * 8 queens problem
   * 
   * @param queensLeftToPlace
   * @param board
   * @return
   */
  public static ArrayList<int[]> queensOnChessBoardProblem(
      int queensLeftToPlace, boolean[][] board) {
    board = queensOnChessBoardProblemHelper(0, queensLeftToPlace, board);
    ArrayList<int[]> queensPositions = new ArrayList<int[]>();
    if (board != null) {
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
          if (board[i][j]) {
            int[] queenPos = { i, j };
            queensPositions.add(queenPos);
          }
        }
      }
      return queensPositions;
    }
    return null;
  }

  private static boolean[][] queensOnChessBoardProblemHelper(int currentRowPos,
      int queensLeftToPlace, boolean[][] board) {
    if (queensLeftToPlace == 0) {
      return board;
    } else if (currentRowPos >= board.length) {
      return null;
    } else {
      for (int i = 0; i < board[currentRowPos].length; i++) {
        if (queenCanBePlaced(currentRowPos, i, board)) {
          board[currentRowPos][i] = true;
          boolean[][] newBoard = queensOnChessBoardProblemHelper(
              currentRowPos + 1, queensLeftToPlace - 1, board);
          if (newBoard != null)
            return newBoard;
          else
            board[currentRowPos][i] = false;
        }
      }
    }
    return null;
  }

  private static boolean queenCanBePlaced(int row, int col, boolean[][] board) {
    boolean result = noQueenInSameRow(row, col, board);
    result &= noQueenInSameCol(row, col, board);
    result &= noQueenInDiagnalTopLeftToBottomRight(row, col, board);
    result &= noQueenInDiagnalBottomLeftToTopRight(row, col, board);
    return result;
  }

  private static boolean noQueenInSameRow(int row, int col, boolean[][] board) {
    for (int i = 0; i < board[row].length; i++) {
      if (board[row][i] && i != col)
        return false;
    }
    return true;
  }

  private static boolean noQueenInSameCol(int row, int col, boolean[][] board) {
    for (int i = 0; i < board.length; i++) {
      if (board[i][col] && i != row)
        return false;
    }
    return true;
  }

  private static boolean noQueenInDiagnalTopLeftToBottomRight(int row, int col,
      boolean[][] board) {
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
      if (board[i][j])
        return false;
    for (int i = row + 1, j = col + 1; i < board.length
        && j < board[row].length; i++, j++)
      if (board[i][j])
        return false;
    return true;
  }

  private static boolean noQueenInDiagnalBottomLeftToTopRight(int row, int col,
      boolean[][] board) {
    for (int i = row - 1, j = col + 1; i >= 0 && j < board[row].length; i--, j++)
      if (board[i][j])
        return false;
    for (int i = row + 1, j = col - 1; i < board.length && j >= 0; i++, j--)
      if (board[i][j])
        return false;
    return true;
  }

  /**
   * You are given pairs of numbers. In a pair the first number is smaller with
   * respect to the second number. Suppose you have two sets (a, b) and (c, d),
   * the second set can follow the first set if b<c. So you can form a long
   * chain in the similar fashion. Find the longest chain which can be formed
   * 
   * @param pairs
   * @return
   */
  public static ArrayList<int[]> findLongestContinuingPairs(int[][] pairs) {
    HashMap<Integer, Integer> visitedPairs = new HashMap<Integer, Integer>();
    return findLongestContinuingPairsHelper(-1, pairs, visitedPairs);
  }

  private static ArrayList<int[]> findLongestContinuingPairsHelper(
      int lastPairTrailingNum, int[][] pairs,
      HashMap<Integer, Integer> visitedPairs) {
    ArrayList<int[]> longestSoFar = new ArrayList<int[]>();
    for (int i = 0; i < pairs.length; i++) {
      ArrayList<int[]> tmpLongest = new ArrayList<int[]>();
      if (lastPairTrailingNum == -1
          || (lastPairTrailingNum <= pairs[i][0] && !visitedPairs
              .containsKey(i))) {
        tmpLongest.add(pairs[i]);
        visitedPairs.put(i, 1);
        tmpLongest.addAll(findLongestContinuingPairsHelper(pairs[i][1], pairs,
            visitedPairs));
        if (tmpLongest.size() > longestSoFar.size())
          longestSoFar = tmpLongest;
        visitedPairs.remove(i);
      }
    }
    return longestSoFar;
  }

  /**
   * Given the array of digits (0 is also allowed), what is the minimal sum of
   * two integers that are made of the digits contained in the array. For
   * example, array: 1 2 7 8 9. The min sum (129 + 78) should be 207
   * 
   * 
   * 
   **/
  public static int findMinSumOfTwoIntegerMadeFromDigitsOfArray(
      ArrayList<Integer> arr) {
    ArrayList<Integer> sortedArr = sortArray(arr);
    String firstNum = "";
    String secondNum = "";
    int startingPoint = 0;
    if (sortedArr.size() % 2 == 1) {
      firstNum += sortedArr.get(0);
      startingPoint = 1;
    }
    for (int i = startingPoint; i < sortedArr.size(); i++) {
      if (i % 2 == 0)
        firstNum += sortedArr.get(i);
      else
        secondNum += sortedArr.get(i);
    }

    return Integer.parseInt(firstNum) + Integer.parseInt(secondNum);
  }

  /**
   * 
   * quicksort implementation
   * 
   **/
  public static ArrayList<Integer> sortArray(ArrayList<Integer> arr) {
    if (arr.size() <= 1)
      return arr;

    ArrayList<Integer> smallerThanOrEqual = new ArrayList<Integer>();
    ArrayList<Integer> biggerThan = new ArrayList<Integer>();
    int mid = arr.get(0);
    for (int i = 1; i < arr.size(); i++) {
      if (arr.get(i) <= mid)
        smallerThanOrEqual.add(arr.get(i));
      else
        biggerThan.add(arr.get(i));
    }
    smallerThanOrEqual = sortArray(smallerThanOrEqual);
    biggerThan = sortArray(biggerThan);

    ArrayList<Integer> result = new ArrayList<Integer>();
    result.addAll(smallerThanOrEqual);
    result.add(mid);
    result.addAll(biggerThan);
    return result;

  }

  /**
   * find largest n number in int array
   * 
   * @param n
   * @param list
   * @return
   */
  public static int[] findNLargestNumbersInArray(int n, int[] list) {
    if (list == null || list.length == 0)
      return null;
    int[] result = new int[n];
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    for (int i = 0; i < n; i++) {
      result[i] = list[0];
      pq.add(i);
    }

    for (int i = 1; i < list.length; i++) {
      if (pq.peek() < list[i]) {
        pq.remove();
        pq.add(list[i]);
      }
    }
    Object[] objArray = pq.toArray();
    for (int i = 0; i < objArray.length; i++)
      result[i] = (Integer) objArray[i];
    return result;
  }

  /**
   * Given a number N, write a program that returns all possible combinations of
   * numbers that add up to N, as lists. (Exclude the N+0=N) For example, if N=4
   * return {{1,1,1,1},{1,1,2},{2,2},{1,3}}
   * 
   * 
   * @param target
   * @return
   */
  public static ArrayList<String> findAllCombinationsOfNumbersAddUpToN(
      int target) {
    ArrayList<String> result = new ArrayList<String>();

    result.add(target + "");
    for (int i = 1; i < target / 2 + 1; i++) {
      ArrayList<String> tmpResult = findAllCombinationsOfNumbersAddUpToN(target
          - i);
      result.addAll(addNumToListOfString(i, tmpResult));
    }

    return result;
  }

  private static ArrayList<String> addNumToListOfString(int n,
      ArrayList<String> input) {
    ArrayList<String> output = new ArrayList<String>();
    for (String s : input)
      output.add(n + s);
    return output;
  }

  /**
   * Given an array of pairs of the form <a, b>. We have to find a sub-array
   * such that the 1st element in the pairs are in increasing order and the sum
   * of 2nd element of the pairs in the sub-array is maximum possible
   * 
   * @param input
   * @return
   */
  public static int findMaxSubArrayWithMaximumSum(int[][] input) {
    if (input.length == 0)
      return 0;
    int lastSeenNum = input[0][0];
    int maxSumInCurrentArray = input[0][1];
    int maxSoFar = maxSumInCurrentArray;
    for (int i = 1; i < input.length; i++) {
      int currentNum = input[i][0];
      int currentNumValue = input[i][1];
      maxSumInCurrentArray = currentNum >= lastSeenNum ? maxSumInCurrentArray
          + currentNumValue : currentNumValue;
      maxSoFar = maxSumInCurrentArray > maxSoFar ? maxSumInCurrentArray
          : maxSoFar;
      lastSeenNum = currentNum;
    }
    return maxSoFar;
  }

  /**
   * given a list and an integer k, find how many pairs of numbers in list can
   * add up to smaller than or equals to k value, ie. given {1,2,5,7,8,12} and k
   * = 15, the combinations are {1,2},{1,5},{1,7} ... {7,8}, total of 12 of
   * them, so the method should return 12
   * 
   * 
   * @param list
   * @param k
   * @return
   */
  public static int findNumPairsEqualOrderUnderK(int[] list, int k) {
    int frontPointer = 0;
    int backPointer = list.length - 1;
    int output = 0;
    while (frontPointer < backPointer) {
      if (list[frontPointer] + list[backPointer] <= k) {
        output += backPointer - frontPointer;
        frontPointer++;
      } else {
        backPointer--;
      }
    }
    return output;
  }

  /**
   * You visited a list of places recently, but you do not remember the order in
   * which you visited them. You have with you the airplane tickets that you
   * used for travelling. Each ticket contains just the start location and the
   * end location. Can you reconstruct your journey?
   * 
   * this algorithm is O(n) space and time
   * 
   * @param tickets
   * @return
   */
  public static String traceBackRouteUsingPlaneTickets(String[][] tickets) {
    HashMap<String, String> route = new HashMap<String, String>();
    HashMap<String, String> reverseRoute = new HashMap<String, String>();
    for (int i = 0; i < tickets.length; i++) {
      reverseRoute.put(tickets[i][1], tickets[i][0]);
      route.put(tickets[i][0], tickets[i][1]);
    }
    // find starting point
    String startingPoint = "";
    for (String key : reverseRoute.keySet()) {
      if (!reverseRoute.containsKey(reverseRoute.get(key))) {
        startingPoint = reverseRoute.get(key);
        break;
      }
    }
    StringBuffer output = new StringBuffer();
    String city = startingPoint;
    String delimiter = " > ";
    while (city != null) {
      output.append(city + delimiter);
      city = route.get(city);
    }
    String sOutput = output.toString();
    return sOutput.substring(0, sOutput.length() - delimiter.length());
  }

  /**
   * Given a positive int "N". and an array of numbers ranging from 0-9 (say
   * array name is arr). print all numbers from 0 to N which include any number
   * from "arr".
   * 
   * for example, given i/p: N=33 arr= {3,6,8}
   * 
   * return 3,6,8,13,16,18,23,26,28,30,31,32,33
   * 
   * @param n
   * @param arr
   * @return
   */
  public static String givenNReturnAllNumbersInRange(int n, int[] arr) {
    StringBuffer output = new StringBuffer();
    for (int i = arr[0]; i <= n; i++) {
      if (iContainsDigitFromList(i, arr))
        output.append(i + ",");
    }
    return output.toString();
  }

  private static boolean iContainsDigitFromList(int i, int[] arr) {
    for (int j = 0; j < arr.length; j++) {
      if (iContainDigitInJ(i, arr[j]))
        return true;
    }
    return false;
  }

  private static boolean iContainDigitInJ(int j, int singleDigit) {
    while (j != 0) {
      if ((j - singleDigit) % 10 == 0)
        return true;
      j /= 10;
    }
    return false;
  }

  /**
   * convert int[] to string, ie. {1,2,3} becomes "{1,2,3}" O(n) time and space
   * 
   * @param <E>
   * 
   * @param input
   * @return
   */
  public static String printIntArray(int[] input) {
    StringBuffer output = new StringBuffer();
    output.append("{");
    for (int i : input)
      output.append(i + ",");
    String sOutput = output.substring(0, output.length() - 2) + "}";
    return sOutput;
  }

  public static String printIntArray(ArrayList<Integer> input) {
    StringBuffer output = new StringBuffer();
    output.append("{");
    for (Integer i : input)
      output.append(i + ",");
    String sOutput = output.substring(0, output.length() - 2) + "}";
    return sOutput;
  }

  /**
   * 
   * @param input
   * @return
   */
  public static String printStringArray(List<String> input) {
    StringBuffer output = new StringBuffer();
    output.append("{");
    for (String i : input)
      output.append(i + ",");
    String sOutput = output.substring(0, output.length() - 2) + "}";
    return sOutput;
  }

  public static String printStringArray(String[] input) {
    StringBuffer output = new StringBuffer();
    output.append("{");
    for (String i : input)
      output.append(i + ",");
    String sOutput = output.substring(0, output.length() - 2) + "}";
    return sOutput;
  }

  /**
   * You are given two string named str1 and str2. Your task is to find the
   * minimum window in str1 which contains all characters from string str2. this
   * is O(n) in time and space
   * 
   * for example, given: ADOBECODEBANC, ABC return:
   * 
   * 
   * @param one
   * @param two
   * @return
   */
  public static String findMinimumWindowInStringOneWhichContainsAllCharsInStringTwo(
      String one, String two) {
    HashMap<String, Integer> wordsAndNumTheyAppear = new HashMap<String, Integer>();
    ArrayList<int[]> pairs = new ArrayList<int[]>();
    for (int i = 0; i < two.length(); i++) {
      wordsAndNumTheyAppear.put(two.charAt(i) + "", 0);
    }
    int frontRunner = 0;
    int backRunner = 0;

    while (frontRunner + 1 < one.length()) {
      while (missingAtLeastOneKey(wordsAndNumTheyAppear)
          && frontRunner + 1 < one.length()) {
        frontRunner++;
        String key = one.charAt(frontRunner) + "";
        if (wordsAndNumTheyAppear.containsKey(key)) {
          int occurance = wordsAndNumTheyAppear.get(key) + 1;
          wordsAndNumTheyAppear.put(key, occurance);
        }
      }
      int[] pair = { backRunner, frontRunner };
      pairs.add(pair);
      while (!missingAtLeastOneKey(wordsAndNumTheyAppear)
          && backRunner + 1 < one.length()) {
        backRunner++;
        String key = one.charAt(backRunner) + "";
        if (wordsAndNumTheyAppear.containsKey(key)) {
          int occurance = wordsAndNumTheyAppear.get(key) - 1;
          wordsAndNumTheyAppear.put(key, occurance);
        }
      }
      int[] pair2 = { backRunner, frontRunner };
      pairs.add(pair2);
    }
    int shortest = Integer.MAX_VALUE;
    int[] resultPair = new int[2];
    for (int[] pair : pairs) {
      // System.out.println("pairs: " + pair[0] + " " + pair[1] + ", ");
      if (pair[1] - pair[0] < shortest) {
        shortest = pair[1] - pair[0];
        resultPair[0] = pair[0];
        resultPair[1] = pair[1];
      }
    }

    return one.substring(0, resultPair[0]) + "|"
        + one.substring(resultPair[0], resultPair[1] + 1) + "|"
        + one.substring(resultPair[1] + 1, one.length());
  }

  /**
   * given an array of strings, and a begining string, and a end string, return
   * all strings in array that belongs in between them. like in a dictionary.
   * for example, given {aa,b,c,dd,ff}, aa, d, the result should be {aa,b,c}, dd
   * comes later than d
   * 
   * 
   * @param source
   * @param beginning
   * @param end
   * @return
   */
  public static List<String> getStringsInBetweenTwoWordsLikeInDictionary(
      String[] source, String beginning, String end) {
    List<String> result = new ArrayList<String>();
    for (int i = 0; i < source.length; i++) {
      if (isBiggerThanOrEqualTo(source[i], beginning)
          && isSmallerThanOrEqualTo(source[i], end)) {
        result.add(source[i]);
        System.out.println(source[i]);
      }
    }
    return result;
  }

  private static boolean missingAtLeastOneKey(
      HashMap<String, Integer> wordsAndNumTheyAppear) {
    Set<String> set = wordsAndNumTheyAppear.keySet();
    for (String key : set) {
      if (wordsAndNumTheyAppear.get(key) == 0)
        return true;
    }
    return false;
  }

  /**
   * Write a method that takes a string, in this format "aabbaadddc". Encode the
   * string by counting the consecutive letters. Ex: "a2b2a2d3c1" O(n) time and
   * space;
   * 
   * 
   * @param s
   * @return
   */
  public static String encodeString(String s) {
    if (s == null || s.length() == 0)
      return "";
    StringBuffer output = new StringBuffer();
    char currentChar = s.charAt(0);
    int currentCount = 1;

    for (int i = 1; i < s.length(); i++) {
      if (currentChar == s.charAt(i)) {
        currentCount++;
      } else {
        output.append(currentChar + "" + currentCount);
        currentChar = s.charAt(i);
        currentCount = 1;
      }
    }

    output.append(currentChar + "" + currentCount);
    return output.toString();
  }

  /**
   * given an rotated int array, find the position which the target occurs,
   * O(log(n)) time, O(1) space
   * 
   * @param rotatedArray
   * @param target
   * @return
   */
  public static int searchNumberPosInSortedButRotatedArray(int[] rotatedArray,
      int target) {
    int startPosOfOriginalArray = findStartingPosInRotatedArray(rotatedArray);
    if (target >= rotatedArray[startPosOfOriginalArray]
        && target <= rotatedArray[rotatedArray.length - 1])
      return seachTargetBetweenRangeInArray(target, startPosOfOriginalArray,
          rotatedArray.length - 1, rotatedArray);
    else
      return seachTargetBetweenRangeInArray(target, 0, startPosOfOriginalArray,
          rotatedArray);
  }

  private static int seachTargetBetweenRangeInArray(int target, int start,
      int end, int[] array) {
    int mid = (end - start) / 2 + start;
    while (array[mid] != target && target >= array[start]
        && target <= array[end]) {
      if (target <= array[mid]) {
        end = mid;
        mid = (end - start) / 2 + start;
      } else {
        start = mid;
        mid = (end - start) / 2 + mid;
      }
    }
    return array[mid] == target ? mid : -1;
  }

  private static int findStartingPosInRotatedArray(int[] rotatedArray) {
    int start = 0;
    int end = rotatedArray.length - 1;
    int pointer = end / 2;
    while (pointer != 0 && rotatedArray[pointer] > rotatedArray[pointer - 1]) {
      if (rotatedArray[pointer] > rotatedArray[end]) {
        start = pointer;
        pointer = (end - start) / 2 + pointer;
      } else {
        end = pointer;
        pointer = (end - start) / 2;
      }
    }
    return pointer;
  }

  /**
   * 
   * find longest anagram in string using almost brute force way. given banana,
   * return anana, since anana is the longest anagram in the string banana
   * O(n^2) time and O(n) space
   * 
   * @param s
   * @return
   */
  public static String findLongestAnagramInStringBruteForceWay(String s) {
    String longestAnagramSoFar = "";
    for (int i = 0; i < s.length(); i++) {
      String anagramCenteredAtI = findAnagramCenteredAtI(s, i);
      if (anagramCenteredAtI.length() > longestAnagramSoFar.length())
        longestAnagramSoFar = anagramCenteredAtI;
    }
    return longestAnagramSoFar;
  }

  private static String findAnagramCenteredAtI(String s, int center) {
    if (center < 0 || center >= s.length())
      return null;
    String anagram = s.charAt(center) + "";
    int leftPointer = center - 1;
    int rightPointer = center + 1;
    while (leftPointer > 0 && rightPointer < s.length()) {
      if (s.charAt(leftPointer) == s.charAt(rightPointer)) {
        anagram = s.charAt(leftPointer) + anagram + s.charAt(rightPointer);
        leftPointer--;
        rightPointer++;
      } else {
        return anagram;
      }
    }
    return anagram;
  }

  /**
   * given agha, return *a*g*h*a*, O(n) time and space
   * 
   * @param s
   * @return
   */
  public static String insertStarsInString(String s) {
    StringBuffer output = new StringBuffer();
    output.append("*");
    for (int i = 0; i < s.length(); i++)
      output.append(s.charAt(i) + "*");
    return output.toString();
  }

  /**
   * given *a*bb*ee*cda*, return abbeecda, O(n) time and space
   * 
   * @param s
   * @return
   */
  public static String stripStarsFromString(String s) {
    StringBuffer output = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
      String nextChar = s.charAt(i) == '*' ? "" : s.charAt(i) + "";
      output.append(nextChar);
    }
    return output.toString();
  }

  /**
   * 
   * given number of levels you want to see, print what you see starting at 1,
   * so in this case, if input is 5, you see
   * 
   * 1 11 21 1211 111221
   * 
   * @param numOfLevels
   * @return
   */
  public static String printWhatYouSeeGivenNumOfLevels(int numOfLevels) {
    int[] level = { 1 };
    return printWhatYouSeeGivenCurrentLevelAndNumOfLevels(level, numOfLevels);
  }

  private static String printWhatYouSeeGivenCurrentLevelAndNumOfLevels(
      int[] level, int numOfLevels) {
    StringBuffer output = new StringBuffer();
    for (int i = 0; i < numOfLevels; i++) {
      output.append(convertIntArrayToString(level));
      level = getNextLevelOfWhatYouSee(level);
    }
    return output.toString();
  }

  private static String convertIntArrayToString(int[] level) {
    StringBuffer output = new StringBuffer();
    for (int i : level)
      output.append(i);
    output.append("\n");
    return output.toString();
  }

  private static int[] getNextLevelOfWhatYouSee(int[] level) {
    if (level == null || level.length == 0)
      return null;

    ArrayList<Integer> newLevel = new ArrayList<Integer>();
    int currentNum = level[0];
    int currentNumCount = 1;
    for (int i = 1; i <= level.length; i++) {
      if (i == level.length) {
        newLevel.add(currentNumCount);
        newLevel.add(currentNum);
      } else if (currentNum == level[i]) {
        currentNumCount++;
      } else {
        newLevel.add(currentNumCount);
        newLevel.add(currentNum);
        currentNum = level[i];
        currentNumCount = 1;
      }
    }
    return convertArrayListToIntArray(newLevel);
  }

  private static int[] convertArrayListToIntArray(ArrayList<Integer> arraylist) {
    int[] result = new int[arraylist.size()];
    for (int i = 0; i < result.length; i++) {
      result[i] = arraylist.get(i);
    }
    return result;
  }

  /**
   * given a int representing levels user wants, this method returns the levels
   * of pascal triangle int string form, comma separated, where \n is appended
   * when occurs. it runs O(n) time and space for example, when the input is 3,
   * you'll get
   * 
   * 1 1,1 1,2,1
   * 
   * @param level
   * @return
   */
  public static String getPascalPyramid(int level) {
    StringBuffer pyramid = new StringBuffer();
    int[] nextLevel = { 1 };
    for (int i = 0; i < level; i++) {
      for (int j = 0; j < nextLevel.length; j++) {
        pyramid.append(nextLevel[j]);
        if (j != nextLevel.length - 1)
          pyramid.append(",");
      }
      pyramid.append("\n");
      nextLevel = getPascalPyramidBySingleLevelGivenPreLevel(nextLevel);
    }
    return pyramid.toString();
  }

  private static int[] getPascalPyramidBySingleLevelGivenPreLevel(
      int[] prevLevel) {
    int[] thisLevel = new int[prevLevel.length + 1];
    thisLevel[0] = 1;
    thisLevel[prevLevel.length] = 1;
    for (int i = 1; i < prevLevel.length; i++) {
      thisLevel[i] = prevLevel[i - 1] + prevLevel[i];
    }
    return thisLevel;
  }

  /**
   * 
   * given an int array representing a pond, find the water that can be
   * accumulated, O(n) in time and space it pre process the array twice first,
   * for each position, find out the highest wall to its left and right. then we
   * simply calculate it by looking at the lower of the either side wall and
   * take the different between the walls and that spot _ 4 | | _ 3 | |_| | _ _
   * 2 | |_| | _| | 1 |_________|_|___| height: 4 2 3 1 2 0 1 2 pos: 0 1 2 3 4 5
   * 6 7
   * 
   * in this case, the water accumulates at pos 1, 3, 5, 6 water accumualted at
   * each pos are 1,1,2,1 result should be 1+1+2+1 = 5
   * 
   * @param pond
   * @return
   */
  public static int findHowMuchWaterCanAccumulate(int[] pond) {
    int[] highestWallFromLeft = new int[pond.length];
    int[] highestWallFromRight = new int[pond.length];

    int highestWallSoFar = 0;
    for (int i = 0; i < pond.length; i++) {
      int highestWallFromLeftSoFar = Math.max(highestWallSoFar, pond[i]);
      if (highestWallFromLeftSoFar > highestWallSoFar)
        highestWallSoFar = highestWallFromLeftSoFar;
      highestWallFromLeft[i] = highestWallFromLeftSoFar;
    }

    highestWallSoFar = 0;
    for (int i = pond.length - 1; i >= 0; i--) {
      int highestWallFromRightSoFar = Math.max(highestWallSoFar, pond[i]);
      if (highestWallFromRightSoFar > highestWallSoFar)
        highestWallSoFar = highestWallFromRightSoFar;
      highestWallFromRight[i] = highestWallFromRightSoFar;
    }

    int waterAccumulated = 0;
    for (int i = 0; i < pond.length; i++) {
      int lowerOfBothSidesWall = Math.min(highestWallFromLeft[i],
          highestWallFromRight[i]);
      if (pond[i] < lowerOfBothSidesWall) {
        waterAccumulated += lowerOfBothSidesWall - pond[i];
      }
    }
    return waterAccumulated;
  }

  /**
   * take 2 sorted integer arrays, return a third one that combines the 2,
   * remove duplicates
   * 
   * @param int[] a
   * @param int[] b
   * @return
   */
  public static int[] mergeSortedIntArrays(int[] a, int[] b) {
    int[] result = new int[a.length + b.length];
    int aPointer = 0;
    int bPointer = 0;
    int resultPointer = 0;

    for (int i = 0; i < result.length; i++) {
      if (aPointer >= a.length) {
        result[resultPointer++] = b[bPointer++];
      } else if (bPointer >= b.length) {
        result[resultPointer++] = a[aPointer];
      } else if (a[aPointer] <= b[bPointer]) {
        if (resultPointer == 0 || result[resultPointer - 1] != a[aPointer])
          result[resultPointer++] = a[aPointer];
        aPointer++;
      } else {
        if (resultPointer == 0 || result[resultPointer - 1] != b[bPointer])
          result[resultPointer++] = b[bPointer];
        bPointer++;
      }
    }

    return result;
  }

  /**
   * print binary tree (kinda) like a tree
   * 
   * @param node
   */
  public static void printBinaryTreeByLayer(BinaryTreeNode node) {
    MyQueue<BinaryTreeNode> q = new MyQueue<BinaryTreeNode>();
    q.enqueue(node);
    q.enqueue(null);
    while (!q.isEmpty() && q.size() != 1) {
      if (q.peek() != null) {
        BinaryTreeNode tmpNode = q.dequeue();
        System.out.print(tmpNode.value() + ", ");
        if (tmpNode.left() != null)
          q.enqueue(tmpNode.left());
        if (tmpNode.right() != null)
          q.enqueue(tmpNode.right());
      } else {
        q.enqueue(null);
        System.out.println();
        q.dequeue();
      }
    }
    System.out.println();
  }

  /**
   * print tree in-order using stack, O(n)
   * 
   * @param node
   */
  public static void printTreeInOrderUsingStack(BinaryTreeNode node) {
    System.out.print("print tree in order: ");
    if (node != null) {
      Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
      stack.add(node);
      node = node.left();

      while (node != null || !stack.isEmpty()) {
        while (node != null) {
          stack.add(node);
          node = node.left();
        }
        if (node == null && !stack.isEmpty()) {
          node = stack.pop();
          System.out.print(node.value() + ", ");
          node = node.right();
        }
      }
    }
    System.out.println();
  }

  /**
   * find and return middle of the linked list, O(n)
   * 
   * @param head
   * @return
   */
  public static LinkedListNode<Integer> returnMiddleNode(
      LinkedListNode<Integer> head) {
    LinkedListNode<Integer> runner = head;
    LinkedListNode<Integer> middleNode = head;

    while (runner.next() != null && runner.next().next() != null) {
      middleNode = middleNode.next();
      runner = runner.next().next();
    }

    return middleNode;
  }

  /**
   * find if a linked list has cycle, O(n)
   * 
   * @param head
   * @return
   */
  public static boolean ifLinkedListHasCycle(LinkedListNode<Integer> head) {
    LinkedListNode<Integer> trailer = head;
    LinkedListNode<Integer> runner = head;
    while (trailer != null && runner.next() != null
        && runner.next().next() != null) {
      trailer = trailer.next();
      runner = runner.next().next();
      if (trailer == runner) {
        return true;
      }
    }
    return false;
  }

  /**
   * insert to beginning of a linked list, O(1)
   * 
   * @param head
   * @param newNode
   * @return
   */
  public static LinkedListNode<Integer> insertToBeginning(
      LinkedListNode<Integer> head, LinkedListNode<Integer> newNode) {
    newNode.next(head);
    return newNode;
  }

  /**
   * insert to end of a linked list, O(n)
   * 
   * @param head
   * @param newNode
   * @return
   */
  public static LinkedListNode<Integer> insertToEnd(
      LinkedListNode<Integer> head, LinkedListNode<Integer> newNode) {
    LinkedListNode<Integer> tmp = head;
    while (tmp.next() != null)
      tmp = tmp.next();
    tmp.next(newNode);
    return head;
  }

  /**
   * print linked list, O(n)
   * 
   * @param node
   */
  public static void printLinkedList(LinkedListNode<Integer> node) {
    System.out.print("print linkedList: ");
    while (node != null) {
      System.out.print("node: " + node.value() + ", ");
      node = node.next();
    }
    System.out.println();
  }

  /**
   * find max sub array in a int array
   * 
   * @param array
   * @return
   */
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

    System.out.print("max subarray: ");
    for (int i = maxBeginning; i <= maxEnd; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
    return maxSumSoFar;
  }

  /**
   * find if target is a substring of source
   * 
   * @param source
   * @param target
   * @return
   */
  public static boolean isSubString(String source, String target) {
    if (target.length() > source.length()) {
      return false;
    }

    for (int i = source.length(); i >= target.length(); i--) {
      if (source.substring(i - target.length(), i).equals(target))
        return true;
    }
    return false;
  }

  public static int[] switchem(int[] input) {

    int front = 0;
    int back = 0;

    while (true) {
      // back go find a positive #
      while (back < input.length && input[back] < 0)
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

  public static int findBitPosition(int i) {
    int result = 0;
    while (i != 0) {
      if ((1 & i) == 1)
        return result;
      System.out.println(result + " " + i);
      result++;
      i >>= 1;
    }
    return result;
  }

  public static String reverseWordsByLetter(String s) {
    String reversedString = reverseString(s);
    int startOfWord = 0;
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < reversedString.length(); i++) {
      if (i + 1 >= reversedString.length()
          || reversedString.charAt(i + 1) == ' ') {
        resultSb.append(reverseString(reversedString.substring(startOfWord,
            i + 1)) + " ");
        startOfWord = i + 2;
      }
    }
    return resultSb.toString().substring(0, resultSb.length() - 1);
  }

  public static String reverseWords(String s) {
    String[] tokens = s.split(" ");
    StringBuffer buffer = new StringBuffer();
    for (int i = tokens.length - 1; i > 0; i--) {
      buffer.append(tokens[i] + " ");
    }
    buffer.append(tokens[0]);
    return buffer.toString();
  }

  public static void fill(int x, int y) {
    int width = 5;
    int height = 5;

    int[] board = new int[width * height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        board[i * width + j] = 0;
      }
    }

    int pos = width * x + y;
    board[pos] = 7;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        System.out.print(board[i * width + j]);
      }
      System.out.println();
    }
  }

  /**
   * find if string contains all unique characters by putting in hashmap, O(n)
   * 
   * @param s
   * @return
   */
  public static boolean containsAllUniqueChars(String s) {
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    for (int i = 0; i < s.length(); i++) {
      char target = s.charAt(i);
      if (map.containsKey(target + ""))
        return false;
      map.put(target + "", 1);
    }
    return true;
  }

  /**
   * find if string contains all unique characters, double for loop, O(n^2)
   * complexity
   * 
   * @param s
   * @return
   */
  public static boolean allUniqueChars2(String s) {
    for (int i = 0; i < s.length(); i++) {
      char tchar = s.charAt(i);
      for (int j = i + 1; j < s.length(); j++) {
        if (tchar == s.charAt(j))
          return false;
      }
    }
    return true;
  }

  /**
   * find if 2 strings are anagrams of each other, 2 NON nested for loops, O(n)
   * 
   * @param one
   * @param two
   * @return
   */
  public static boolean areAnagram(String one, String two) {
    if (one.length() != two.length())
      return false;

    int[] oneCounter = new int[256];
    int[] twoCounter = new int[256];

    for (int i = 0; i < one.length(); i++) {
      ++oneCounter[one.charAt(i)];
      ++twoCounter[two.charAt(i)];
    }

    for (int i = 0; i < oneCounter.length; i++) {
      if (oneCounter[i] != twoCounter[i])
        return false;
    }
    return true;
  }

  /**
   * replace all spaces in string s with %20, return new string
   * 
   * @param s
   * @return
   */
  public static String replaceSpaceWith20(String s) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ')
        sb.append("%20");
      else
        sb.append(s.charAt(i));
    }
    return sb.toString();
  }

  /**
   * reverse string, given "abcd", return "dcba" O(n) in time and space
   * 
   * @param s
   * @return
   */
  public static String reverseString(String s) {
    StringBuffer sb = new StringBuffer();
    for (int i = s.length() - 1; i >= 0; i--)
      sb.append(s.charAt(i));
    return sb.toString();
  }

  private static boolean isBiggerThanOrEqualTo(String source, String beginning) {
    int checkLength = source.length() < beginning.length() ? source.length()
        : beginning.length();
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
    int checkLength = source.length() < end.length() ? source.length() : end
        .length();
    for (int i = 0; i < checkLength; i++) {
      if (source.charAt(i) > end.charAt(i)) {
        return false;
      } else if (source.charAt(i) < end.charAt(i)) {
        return true;
      }
    }
    return source.length() <= end.length();
  }
}
