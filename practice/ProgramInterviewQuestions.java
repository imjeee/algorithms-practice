import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.lang.StringBuffer;

class ProgramInterviewQuestions {
  public static void main(String[] args) {
    System.out.println("programming interview questions practice!\n");

    groupStrings();
    isSubString();
    maxSubArray();
    allUniqueCharacters();
    anagram();
    switchem();
    findBitPosition();
    reverseString();
    mergeSortedIntArrays();
    // misc();
    playWithLinkedList();
    playWithBinarySearchTree();
    playWithQueue();
    findHowMuchWaterCanAccumulate();
    printPascalPyramid();
    printWhatYouSeeInNumbers();
    findLongestAnagramInString();
    searchNumberInSortedButRotatedArray();
    encodeStringByCharAndNumber();
    findMinimumWindowInStringOneWhichContainsAllCharsInStringTwo();
    givenNReturnAllNumbersInRange();
    traceBackRouteUsingPlaneTickets();
    playWithChar();
    playWithHash();
    findNumPairsEqualOrderUnderK();
    browserHistorySimulation();
    findMaxSubArrayWithMaximumSums();
    findAllCombinationsOfNumbersAddUpToN();
    findNLargestNumbersInArray();
    findMinSumOfTwoIntegerMadeFromDigitsOfArray();
    findLongestContinuingPairs();
    voilateConsecutiveABCCharRequirement();
    queensOnChessBoardProblem();
    convertIntToBinary();
    convertBinaryStringToInt();
  }
  
  private static void convertBinaryStringToInt() {
    String binary = "100";
    System.out.println(binary + " converted to int: " + Algorithms.convertBinaryStringToInt(binary));
    binary = "1011";
    System.out.println(binary + " converted to int: " + Algorithms.convertBinaryStringToInt(binary));
    binary = "101010";
    System.out.println(binary + " converted to int: " + Algorithms.convertBinaryStringToInt(binary));
    binary = "1001110";
    System.out.println(binary + " converted to int: " + Algorithms.convertBinaryStringToInt(binary));
  }

  private static void convertIntToBinary() {
    int num = 6;
    System.out.println(num + " converted to binary: " + Algorithms.convertIntToBinary(num));
    num = 44;
    System.out.println(num + " converted to binary: " + Algorithms.convertIntToBinary(num));
    num = 72;
    System.out.println(num + " converted to binary: " + Algorithms.convertIntToBinary(num));
    num = 131;
    System.out.println(num + " converted to binary: " + Algorithms.convertIntToBinary(num));
  }
  

  
  private static void queensOnChessBoardProblem() {
    int boardSize = 8;
    int numQueensToPlace = 8;
    boolean[][] board = new boolean[boardSize][boardSize];
    ArrayList<int[]> queensPositions = Algorithms.queensOnChessBoardProblem(
        numQueensToPlace, board);
    if (queensPositions != null) {
      System.out.print("on a board size: " + boardSize + "x" + boardSize
          + ", we can place all " + numQueensToPlace
          + " queens on board by placing them at positions: ");
      for (int[] pos : queensPositions)
        System.out.print("{" + pos[0] + pos[1] + "} ");
    } else {
      System.out.print("cannot print " + numQueensToPlace
          + " in board size of " + boardSize + "x" + boardSize);
    }
    System.out.println();
  }

  private static void voilateConsecutiveABCCharRequirement() {
    char[] chars = { 'a', 'b', 'c' };
    String testString = "abc";
    testString = "bbaaaaaaaacb";
    System.out.println("String: " + testString
        + " voilate consecutive abc characters requirement: "
        + voilateConsecutiveABCCharRequirement(chars, testString));
  }

  public static boolean voilateConsecutiveABCCharRequirement(char[] chars,
      String s) {
    int[] checker = new int[256];
    for (int i = 0; i < s.length(); i++) {
      if (i > chars.length - 1)
        checker[s.charAt(i - chars.length)] -= 1;
      checker[s.charAt(i)] += 1;
      boolean containEachAndEveryCharInLastSequence = true;
      for (int j = 0; j < chars.length; j++)
        containEachAndEveryCharInLastSequence &= checker[chars[j]] > 0;
      if (containEachAndEveryCharInLastSequence)
        return true;
    }
    return false;
  }

  private static void findLongestContinuingPairs() {
    int[] pair1 = { 1, 3 };
    int[] pair2 = { 2, 6 };
    int[] pair3 = { 3, 8 };
    int[] pair4 = { 4, 5 };
    int[] pair5 = { 7, 9 };
    int[] pair6 = { 4, 6 };
    int[] pair7 = { 6, 7 };
    int[] pair8 = { 8, 3 };
    int[] pair9 = { 5, 4 };
    int[][] pairs = { pair1, pair2, pair3, pair4, pair5, pair6, pair7, pair8,
        pair9 };
    ArrayList<int[]> continuousPairs = Algorithms
        .findLongestContinuingPairs(pairs);
    System.out.println("find longest continuing pairs: "
        + printPairs(continuousPairs));
  }

  public static String printPairs(ArrayList<int[]> pairs) {
    if (pairs == null || pairs.size() == 0)
      return "";
    StringBuffer output = new StringBuffer();
    for (int i = 0; i < pairs.size(); i++) {
      output.append("{" + pairs.get(i)[0] + "," + pairs.get(i)[1] + "} ");
    }
    return output.toString();
  }

  private static void findMinSumOfTwoIntegerMadeFromDigitsOfArray() {
    ArrayList<Integer> arr = new ArrayList<Integer>();
    arr.add(1);
    arr.add(2);
    arr.add(8);
    arr.add(7);
    arr.add(9);
    System.out.println("Given arr: " + Algorithms.printIntArray(arr)
        + ", the mind sum of two integers made from the digits of the array: "
        + Algorithms.findMinSumOfTwoIntegerMadeFromDigitsOfArray(arr));
  }

  private static void findNLargestNumbersInArray() {
    int n = 5;
    int[] list = { 3, 6, 4, 2, 6, 12, 4, 8, 9, 34, 4, 7, 23, 9, 20, 10 };
    int[] result = Algorithms.findNLargestNumbersInArray(n, list);
    System.out.println("the largest " + n + " integers in the list: "
        + Algorithms.printIntArray(result));
  }

  private static void findAllCombinationsOfNumbersAddUpToN() {
    int n = 5;
    ArrayList<String> result = Algorithms
        .findAllCombinationsOfNumbersAddUpToN(n);
    System.out.println("find all combinations of numbers add up to " + n + ": "
        + Algorithms.printStringArray(result));
  }

  private static void findMaxSubArrayWithMaximumSums() {
    int[] one = { 1, 4 };
    int[] two = { 2, 2 };
    int[] three = { 9, 3 };
    int[] four = { 5, 2 };
    int[] five = { 9, 6 };
    int[] six = { 10, 1 };
    int[] seven = { 7, 4 };
    int[] eight = { 8, 1 };
    int[] nine = { 10, 10 };

    int[][] input = { one, two, three, four, five, six, seven, eight, nine };
    int result = Algorithms.findMaxSubArrayWithMaximumSum(input);
    System.out.println("find max sub array with max sum: " + result);
  }

  private static void browserHistorySimulation() {
    String[] list = { "a", "g", "g", "g", "g", "w", "a", "t", "e", "v" };
    BrowserHistory history = new BrowserHistory();
    for (String s : list)
      history.visit(s);
    String[] returnedHistory = history.getHistory(10);
    System.out.println("browserHistorySimulation: "
        + Algorithms.printStringArray(returnedHistory));
  }

  private static void findNumPairsEqualOrderUnderK() {
    int[] list = { 1, 2, 5, 7, 8, 12 };
    int k = 15;
    System.out
        .print("given list {1,2,5,7,8,12} and k = 15. the number of pairs thats equal to or less than k: ");
    System.out.println(Algorithms.findNumPairsEqualOrderUnderK(list, k));
  }

  private static void playWithHash() {
    System.out.print("playing with hash values: ");
    int h = 10;
    h ^= h;// >>> 2;
    System.out.print(h);
    int e = 8;
    System.out.println(e >> 1);
  }

  private static void playWithChar() {
    char a = 'a';
    char b = 'b';
    char c = 'c';
    char dd = 'D';
    char aa = 'A';
    System.out.print("play with chars, ");
    System.out.print(a + " is " + Integer.valueOf(a) + ", ");
    System.out.print(b + " is " + Integer.valueOf(b) + ", ");
    System.out.print(c + " is " + Integer.valueOf(c) + ", ");
    System.out.print(dd + " is " + Integer.valueOf(dd) + ", ");
    System.out.println(aa + " is " + Integer.valueOf(aa) + ", ");
  }

  private static void traceBackRouteUsingPlaneTickets() {
    String[] one = { "beijing", "tokyo" };
    String[] two = { "paris", "berlin" };
    String[] three = { "london", "beijing" };
    String[] four = { "istanbul", "dubai" };
    String[] five = { "tokyo", "istanbul" };
    String[] six = { "dubai", "paris" };
    String[] seven = { "berlin", "washington" };
    String[] eight = { "washington", "austin" };
    String[][] tickets = { one, two, three, four, five, six, seven, eight };
    String route = Algorithms.traceBackRouteUsingPlaneTickets(tickets);
    System.out.println("given a bunch of tickets, trace back the route: "
        + route);
  }

  private static void givenNReturnAllNumbersInRange() {
    int n = 33;
    int[] arr = { 3, 6, 8 };
    String output = Algorithms.givenNReturnAllNumbersInRange(n, arr);
    System.out.println("print all numbers in range: " + n + ", "
        + Algorithms.printIntArray(arr) + ": " + output);
  }

  private static void findMinimumWindowInStringOneWhichContainsAllCharsInStringTwo() {
    String one = "ADOBECODEBANC";
    String two = "ABC";
    System.out.print("given string: " + one + ", and target string: " + two
        + ", the minimum window: ");
    System.out
        .println(Algorithms
            .findMinimumWindowInStringOneWhichContainsAllCharsInStringTwo(one,
                two));
  }

  private static void encodeStringByCharAndNumber() {
    String s = "aabbaadddc";
    System.out.println("encode string: " + s + " to "
        + Algorithms.encodeString(s));
  }

  private static void searchNumberInSortedButRotatedArray() {
    int[] rotatedArray = { 8, 9, 1, 2, 3, 4, 5, 6, 7 };
    int target = 10;
    System.out.println("find " + target + " in rotated array: "
        + Algorithms.printIntArray(rotatedArray));
    System.out.println("target position: "
        + Algorithms.searchNumberPosInSortedButRotatedArray(rotatedArray,
            target));
  }

  private static void findLongestAnagramInString() {
    String s = "banana";
    System.out.print("find longest anagram in string: " + s + " is ");
    System.out.println(findLongestAnagramInString(s));
  }

  private static String findLongestAnagramInString(String s) {
    String preprocessedString = Algorithms.insertStarsInString(s);
    String longestAnagramWithStars = Algorithms
        .findLongestAnagramInStringBruteForceWay(preprocessedString);
    String longestAnagram = Algorithms
        .stripStarsFromString(longestAnagramWithStars);
    return longestAnagram;
  }

  private static void printWhatYouSeeInNumbers() {
    System.out.println("print what you see:");
    System.out.print(Algorithms.printWhatYouSeeGivenNumOfLevels(10));
  }

  private static void printPascalPyramid() {
    System.out.println("print pascal pyramid:");
    System.out.println(Algorithms.getPascalPyramid(10));
  }

  private static void groupStrings() {
    String[] source = { "a", "ab", "fadeer", "asdwer", "awerd", "acfgdsfgser" };
    String beginning = "ac";
    String end = "afd";
    List<String> result = Algorithms
        .getStringsInBetweenTwoWordsLikeInDictionary(source, beginning, end);
    System.out.println("getStringsInBetweenTwoWordsLikeInDictionary: "
        + Algorithms.printStringArray(result));
  }

  private static void findHowMuchWaterCanAccumulate() {
    // int[] pond = {4,3,5,2,1,4,5,7,3,5,8};
    int[] pond2 = { 4, 2, 3, 1, 2, 0, 1, 2 };
    System.out.println("water accumulated total is: "
        + Algorithms.findHowMuchWaterCanAccumulate(pond2));
  }

  private static void reverseString() {
    String s = "hello how are you doing?";
    System.out.println("reverse \"" + s + "\": \"" + Algorithms.reverseWords(s)
        + "\"");
    System.out.println("reverse \"" + s + "\": \""
        + Algorithms.reverseWordsByLetter(s) + "\"");
  }

  private static void findBitPosition() {
    int num = 33;
    System.out.println("first bit position of " + num + " is "
        + Algorithms.findBitPosition(num));
  }

  private static void switchem() {
    int[] input = { -1, 5, -2, 3, 9, -10, -5, 3, -10, 9, 8, 7, -30, -100 };
    int[] output = Algorithms.switchem(input);
    System.out.println(Algorithms.printIntArray(output));
  }

  private static void anagram() {
    String one = "banana";
    String two = "anaban";
    System.out.println(one + " and " + two + " are anagram of eachother: "
        + Algorithms.areAnagram(one, two));
  }

  private static void allUniqueCharacters() {
    String source = "what is up my friend";
    System.out.println(source + " - contain all unique characters: "
        + Algorithms.containsAllUniqueChars(source));
  }

  private static void maxSubArray() {
    int[] array = { 10, 1, -100, 20, 3, -300, -50, 100, 4, -20, 30 };
    System.out.println("max array length is " + Algorithms.maxSubArray(array));
  }

  private static void isSubString() {
    String source = "why hello there";
    String target = "hello2";

    System.out.println(target + " is substring of " + source + ": "
        + Algorithms.isSubString(source, target));
  }

  private static void playWithLinkedList() {
    LinkedListNode<Integer> node = new LinkedListNode<Integer>(10);
    LinkedListNode<Integer> node2 = new LinkedListNode<Integer>(20);
    node.next(node2);
    Algorithms.printLinkedList(node);

    LinkedListNode<Integer> node3 = new LinkedListNode<Integer>(25);
    LinkedListNode<Integer> head = Algorithms.insertToEnd(node, node3);
    Algorithms.printLinkedList(head);

    LinkedListNode<Integer> node4 = new LinkedListNode<Integer>(1);
    head = Algorithms.insertToBeginning(node, node4);
    Algorithms.printLinkedList(head);

    LinkedListNode<Integer> one = new LinkedListNode<Integer>(1);
    LinkedListNode<Integer> two = new LinkedListNode<Integer>(10);
    one.insert(one, 2);
    one.insert(one, 3);
    one.insert(one, 4);
    one.insert(one, 5);
    one.insert(one, 6);
    one.delete(one, 2);
    one.insert(one, 2);
    one.insert(one, 13);
    Algorithms.printLinkedList(one);
    System.out.println("middle node is: "
        + Algorithms.returnMiddleNode(one).value());

    Algorithms.insertToEnd(one, two);
    Algorithms.insertToEnd(two, one);
    /*
     * this linked list now has a cycle, so don't print it like a singly linked
     * list with no cycle
     */
    System.out.println("does this linked list have cycle: "
        + Algorithms.ifLinkedListHasCycle(one));
  }

  private static void mergeSortedIntArrays() {
    int[] a = { 1, 4, 6, 8, 9 };
    int[] b = { 4, 7, 8, 10, 12 };
    int[] result = Algorithms.mergeSortedIntArrays(a, b);
    System.out.print("Merged int array: " + Algorithms.printIntArray(result));
  }

  private static void playWithQueue() {
    MyQueue<Integer> myQueue = new MyQueue<Integer>();
    for (int i = 0; i < 10; i++) {
      myQueue.enqueue(i);
    }

    System.out.print("myQueue: ");
    while (myQueue.peek() != null) {
      System.out.print(myQueue.dequeue() + ", ");
    }
    System.out.println(myQueue.dequeue());
  }

  private static void playWithBinarySearchTree() {
    BinaryTreeNode node = new BinarySearchTreeNode(25);
    System.out.println("insert binary tree node 20 successfull: "
        + node.insert(20));
    System.out.println("insert binary tree node 25 successfull: "
        + node.insert(26));
    System.out.println("insert binary tree node 30 successfull: "
        + node.insert(30));
    System.out.println("insert binary tree node -30 successfull: "
        + node.insert(-30));
    System.out.println("insert binary tree node 21 successfull: "
        + node.insert(21));
    System.out.println("insert binary tree node 2 successfull: "
        + node.insert(2));

    Algorithms.printBinaryTreeByLayer(node);
    Algorithms.printTreeInOrderUsingStack(node);
  }

}
