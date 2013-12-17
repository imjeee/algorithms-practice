import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
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
    findNumOnesInBinaryRepresentationOfInt();
    coupleMatchingProblem();
    composeAllPossibleParenthasis();
    SortIntNegativesToLeftOfPositiveOrderDoesntMatter();
    findContinuousSubsetInUnsortedArray();
    givenArrayFindAllElementsAppearMoreThanNOverMTimes();
    findBiggestNumDifferencesInList();
    generateAllValidAlphabetForArray();
    findIfTwoStringsAreInterleaved();
    concatenateListForLargestNumber();
    plusPlusOperatorForIntArray();
    findMaxSplitInArray();
    removeEveryKthNodeInCircularLinkedListUntilOnlyOneNodeIsLeft();
    pushAllZerosToEndOfArray(); 
    findTheImmediateBiggerNumUsingSameDigit();
    swapPosNumWithSmallestNumBiggerThanPos();
    findLargestTripleProductInIncreasingOrderInArray();
    removeDupsFromSortedSingleyListedNode();
    findLongestContinuousZerosInBinaryRepresentationOfInt();
    removeAllBsAndACsInCharArray();
    findSequenceToProduceMostAs();
    CanIOrderExactNumberOfNuggets();
    partitionSetInToTwoSuchThatTheDifferenceIsMinimized();
  }
  
  private static void partitionSetInToTwoSuchThatTheDifferenceIsMinimized() {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(1);
    list.add(4);
    list.add(9);
    list.add(16);
    
    TwoListsAndTheirSumDiff result = Algorithms.partitionSetInToTwoSuchThatTheDifferenceIsMinimized(list);
    System.out.println(result.getSumDiff());
  }

  private static void CanIOrderExactNumberOfNuggets() {
    int n = 15;
    boolean canOrder = CanIOrderExactNumberOfNuggets(n);
    System.out.println("Can you order exactly " + n + " number of nuggets, if you can only order in quantities of 6, 9, and 20? " + canOrder);
  }
  
  public static boolean CanIOrderExactNumberOfNuggets(int n) {
    if (n < 6)
      return false;
    if (n == 6 || n == 9 || n == 20)
      return true;
    boolean result = CanIOrderExactNumberOfNuggets(n - 20);
    result |= CanIOrderExactNumberOfNuggets(n - 9);
    result |= CanIOrderExactNumberOfNuggets(n - 6);
    return result;
  }
  
  private static void findSequenceToProduceMostAs() {
    int n = 171;
    System.out.println("If you can only press A or Ctr-A or Ctr-C or Ctr-V " + n + " times, ");
    System.out.print("you can press in sequence: ");
    int maxAsLength = Algorithms.findMaxAsCanBeProducedByKeySequence(n);
    System.out.println();
    System.out.println("the maximum number of As can be produced is " + maxAsLength + " As.");
  }

  
  private static void removeAllBsAndACsInCharArray() {
    char[] array = {'a','b','c','a','c'};
    String originalArray = Algorithms.printCharArray(array);
    System.out.print("Given char array " + originalArray);
    Algorithms.removeAllBsAndACsInCharArray(array);
    originalArray = Algorithms.printCharArray(array);
    System.out.println(" after removing all the Bs and ACs, we get " + originalArray);
  }

  
  private static void findLongestContinuousZerosInBinaryRepresentationOfInt() {
    int num = 133;
    String numInBinaryString = Algorithms.convertIntToBinaryString(num);
    System.out.print("Given " + num + ", the binary representation is " + numInBinaryString);
    System.out.println(" the longest continuous 0s is " + Algorithms.findLongestContinuousZerosInBinaryRepresentationOfInt(num));
  }
  
  private static void removeDupsFromSortedSingleyListedNode() {
    LinkedListNode<Integer> one = new LinkedListNode<Integer>(1);
    LinkedListNode<Integer> two = new LinkedListNode<Integer>(2);
    LinkedListNode<Integer> three = new LinkedListNode<Integer>(2);
    LinkedListNode<Integer> four = new LinkedListNode<Integer>(2);
    LinkedListNode<Integer> five = new LinkedListNode<Integer>(3);
    LinkedListNode<Integer> six = new LinkedListNode<Integer>(3);
    LinkedListNode<Integer> seven = new LinkedListNode<Integer>(4);
    LinkedListNode<Integer> eight = new LinkedListNode<Integer>(4);
    one.next(two);
    two.next(three);
    three.next(four);
    four.next(five);
    five.next(six);
    six.next(seven);
    seven.next(eight);
    
    LinkedListNode<Integer> start = Algorithms.removeDupsFromSortedSingleyListedNode(one);
    System.out.print("given sorted singley linked list, remove all duplicates: ");
    while (start != null) {
      System.out.print(start.value() + ", ");
      start = start.next();
    }
    System.out.println();
  }
 
  
  private static void findLargestTripleProductInIncreasingOrderInArray() {
    //int[] array = {6,7,8,1,2,3,9,10};
    //int[] array = {1, 11, 12, 7, 8, 9};
    int[] array = {1,11,8,9 ,10,14};
    int[] answer = Algorithms.findLargestTripleProductInIncreasingOrderInArray(array);
    System.out.print("largest triple product in of num in increasign order in array " + Algorithms.printIntArray(array));
    System.out.println(" is " + Algorithms.printIntArray(answer));
  }

  private static void swapPosNumWithSmallestNumBiggerThanPos() {
    int pos = 0;
    int[] list = {1,4,3,4};
    System.out.print("Given " + Algorithms.printIntArray(list) + ", swap position " + pos);
    int newPos = pos + 1;
    System.out.print(" with the next biggest number at " + newPos + " or more, you get ");
    Algorithms.swapPosNumWithSmallestNumBiggerThanPos(0, list);
    System.out.println(Algorithms.printIntArray(list));
  }
  
  private static void findTheImmediateBiggerNumUsingSameDigit() {
    int num = 776549;
    int result = Algorithms.findTheImmediateBiggerNumUsingSameDigit(num);
    System.out.println("Given " + num + ", the next biggest number using the same digits is " + result);
  }

  private static void pushAllZerosToEndOfArray() {
    int[] list = {1,2,0,4,0,0,8};
    System.out.print("push all 0s in list " + Algorithms.printIntArray(list) + " to ");
    Algorithms.pushAllZerosToEndOfArray(list);
    System.out.println(Algorithms.printIntArray(list));
  }

  private static void removeEveryKthNodeInCircularLinkedListUntilOnlyOneNodeIsLeft() {
    LinkedListNode<Integer> one = new LinkedListNode<Integer>(1);
    LinkedListNode<Integer> two = new LinkedListNode<Integer>(2);
    LinkedListNode<Integer> three = new LinkedListNode<Integer>(3);
    LinkedListNode<Integer> four = new LinkedListNode<Integer>(4);
    LinkedListNode<Integer> five = new LinkedListNode<Integer>(5);
    //LinkedListNode<Integer> six = new LinkedListNode<Integer>(6);
    //LinkedListNode<Integer> seven = new LinkedListNode<Integer>(7);
    //LinkedListNode<Integer> eight = new LinkedListNode<Integer>(8);
    one.next(two);
    two.next(three);
    three.next(four);
    four.next(five);
    five.next(one);
    //six.next(seven);
    //seven.next(eight);
    //eight.next(one);
    
    int k = 3;

    int lastNodeValue = Algorithms.removeEveryKthNodeInCircularLinkedListUntilOnlyOneNodeIsLeft(one, k);
    System.out.println("in list {1,2,3,4,5}, remove every 3rd element in the linkedlist get you " + lastNodeValue);
  }  
  
  private static void findMaxSplitInArray() {
    int[] list = {2,-1,-2,1,-4,2,8};
    int maxDiff = Algorithms.findMaxSplitInArray(list);
    System.out.println(Algorithms.printIntArray(list) + "'s max diff array: " + maxDiff);
  }
  
  private static void plusPlusOperatorForIntArray() {
    int[] list = {9,9,9,9};
    int[] output = Algorithms.plusPlusOperatorForIntArray(list);
    System.out.println(Algorithms.printIntArray(list) + "++ is " + Algorithms.printIntArray(output));
  }

  private static void concatenateListForLargestNumber() {
    int[] list = {9009, 664, 64, 19, 7, 9, 24, 964, 99 };
    int[] newList = Algorithms.sortListSuchThatConcatenateListForLargestNumber(list);
    System.out.println("Given list: " + Algorithms.printIntArray(list) + ", the largest number can be produced is made in this order: " + Algorithms.printIntArray(newList));
  }
  
  private static void findIfTwoStringsAreInterleaved() {
    String a = "abcd";
    String b = "xyz";
    String c = "axybczd";
    System.out.println("String " + a + " and string " + b + " are interleaved in String " + c + " : " + Algorithms.findIfTwoStringsAreInterleaved(a,b,c));
  }
  
  private static void generateAllValidAlphabetForArray() {
    // a = 97
    String input = "1123";
    ArrayList<String> result = Algorithms.generateAllValidAlphabetForArray(input);
    System.out.println(Algorithms.printStringArray(result));
  }
  
  private static void findBiggestNumDifferencesInList() {
    int[] list = {3,6,23,6,7,3,7,34,56,12,-4,55};
    System.out.println("biggest difference in list " + Algorithms.printIntArray(list) + " : " + Algorithms.findBiggestNumDifferencesInList(list));
  }
  
  private static void givenArrayFindAllElementsAppearMoreThanNOverMTimes() {
    int[] list = {4, 3, 3, 2, 1, 2, 3, 4, 4, 7};
    int m = 5;
    int[] result = Algorithms.givenArrayFindAllElementsAppearMoreThanNOverMTimes(list, m);
    System.out.print("all elements appear more than n/m or " + list.length + "/" + m + " or " + list.length/m + " times in list ");
    System.out.println(Algorithms.printIntArray(list) + " : " + Algorithms.printIntArray(result));
  }

  private static void findContinuousSubsetInUnsortedArray() {
    int[] list = {1,6,10,4,7,9,5,5};
    System.out.println("given list: " + Algorithms.printIntArray(list));
    list = Algorithms.findContinuousSubsetInUnsortedArray(list);
    System.out.println("longest continous subset: " + Algorithms.printIntArray(list));
  }
  
  private static void SortIntNegativesToLeftOfPositiveOrderDoesntMatter() {
    int[] list = {4,5,-3,-6,6,-2,9,-3,6,7,9,34,76,-33,75,-34,65};
    //int[] list = {5,-3};
    System.out.println("Unsorted list: " + Algorithms.printIntArray(list));
    
    Algorithms.SortIntNegativesToLeftOfPositiveOrderDoesntMatter(list);
    System.out.println("Sorted list: " + Algorithms.printIntArray(list));
  }
  
  private static void composeAllPossibleParenthasis() {
    int n = 3;
    System.out.println("print all possible paranthasis size " + n + " ");
    ArrayList<String> results = Algorithms.composeAllPossibleParenthasis(n);
    for (String s : results)
      System.out.println(s);
  }

  private static void coupleMatchingProblem() {
    Set<Person> guys = new HashSet<Person>();
    Set<Person> girls = new HashSet<Person>();

    String JIMMY = "Jimmy";
    String ERIC = "Eric";
    String NIKHIl = "Nikhil";
    String JIE = "Jie";
    String JARED = "Jared";

    String JENNIFER = "Jennifer";
    String KATE = "Kate";
    String KELLY = "Kelly";
    String JHARNA = "Jharna";
    String MONIQUE = "Monique";

    String[] guysNames = { JIMMY, ERIC, NIKHIl, JIE, JARED };
    String[] girlsNames = { JENNIFER, KATE, KELLY, JHARNA, MONIQUE };
    
    List<String> guysNamesAsList = Arrays.asList(guysNames);
    List<String> girlsNamesAsList = Arrays.asList(girlsNames);

    for (int i = 0; i < guysNames.length; i++) {
      Collections.shuffle(girlsNamesAsList);    
      List<String> guyPreferences = new ArrayList<String>(girlsNamesAsList);
      guys.add(new Person(guysNames[i], true, guyPreferences));
    }
    
    for (int i = 0; i < girlsNames.length; i++) {
      Collections.shuffle(guysNamesAsList);    
      List<String> girlPreferences = new ArrayList<String>(guysNamesAsList);
      girls.add(new Person(girlsNames[i], true, girlPreferences));
    }

    ArrayList<String[]> couples = Algorithms.coupleMatchingProblem(guys, girls);
    System.out.println("finding couples for everyone in this group: ");
    System.out.println("guys preferences: ");
    for (Person guy : guys) {
      System.out.print(guy.getName() + "\t: ");
      for (int i = 0; i < guy.getPreferences().size(); i++)
        System.out.print(guy.getPreferences().get(i) + " ");
      System.out.println();
    }
    System.out.println("girls preferences: ");
    for (Person girl : girls) {
      System.out.print(girl.getName() + "\t: ");
      for (int i = 0; i < girl.getPreferences().size(); i++)
        System.out.print(girl.getPreferences().get(i) + " ");
      System.out.println();
    }
    for (String[] couple : couples)
      System.out.print("{" + couple[0] + "," + couple[1] + "} ");
    System.out.println();
  }

  private static void findNumOnesInBinaryRepresentationOfInt() {
    int num = 6;
    System.out.println("The number of 1s in the binary representation of " + num + " is " + Algorithms.findNumOnesInBinaryRepresentationOfInt(num));
    num = 0;
    System.out.println("The number of 1s in the binary representation of " + num + " is " + Algorithms.findNumOnesInBinaryRepresentationOfInt(num));
    num = 44;
    System.out.println("The number of 1s in the binary representation of " + num + " is " + Algorithms.findNumOnesInBinaryRepresentationOfInt(num));
    num = 72;
    System.out.println("The number of 1s in the binary representation of " + num + " is " + Algorithms.findNumOnesInBinaryRepresentationOfInt(num));
    num = 131;
    System.out.println("The number of 1s in the binary representation of " + num + " is " + Algorithms.findNumOnesInBinaryRepresentationOfInt(num));
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
    num = 0;
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
    ArrayList<int[]> queensPositions = Algorithms.queensOnChessBoardProblem(numQueensToPlace, board);
    if (queensPositions != null) {
      System.out.print("on a board size: " + boardSize + "x" + boardSize + ", we can place all " + numQueensToPlace
          + " queens on board by placing them at positions: ");
      for (int[] pos : queensPositions)
        System.out.print("{" + pos[0] + pos[1] + "} ");
    } else {
      System.out.print("cannot print " + numQueensToPlace + " in board size of " + boardSize + "x" + boardSize);
    }
    System.out.println();
  }

  private static void voilateConsecutiveABCCharRequirement() {
    char[] chars = { 'a', 'b', 'c' };
    String testString = "abc";
    testString = "bbaaaaaaaacb";
    System.out.println("String: " + testString + " voilate consecutive abc characters requirement: "
        + Algorithms.voilateConsecutiveABCCharRequirement(chars, testString));
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
    int[][] pairs = { pair1, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9 };
    ArrayList<int[]> continuousPairs = Algorithms.findLongestContinuingPairs(pairs);
    System.out.println("find longest continuing pairs: " + Algorithms.printPairs(continuousPairs));
  }

  private static void findMinSumOfTwoIntegerMadeFromDigitsOfArray() {
    ArrayList<Integer> arr = new ArrayList<Integer>();
    arr.add(1);
    arr.add(2);
    arr.add(8);
    arr.add(7);
    arr.add(9);
    System.out.println("Given arr: " + Algorithms.printIntArray(arr) + ", the mind sum of two integers made from the digits of the array: "
        + Algorithms.findMinSumOfTwoIntegerMadeFromDigitsOfArray(arr));
  }

  private static void findNLargestNumbersInArray() {
    int n = 5;
    int[] list = { 3, 6, 4, 2, 6, 12, 4, 8, 9, 34, 4, 7, 23, 9, 20, 10 };
    int[] result = Algorithms.findNLargestNumbersInArray(n, list);
    System.out.println("the largest " + n + " integers in the list: " + Algorithms.printIntArray(result));
  }

  private static void findAllCombinationsOfNumbersAddUpToN() {
    int n = 5;
    ArrayList<String> result = Algorithms.findAllCombinationsOfNumbersAddUpToN(n);
    System.out.println("find all combinations of numbers add up to " + n + ": " + Algorithms.printStringArray(result));
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
    System.out.println("browserHistorySimulation: " + Algorithms.printStringArray(returnedHistory));
  }

  private static void findNumPairsEqualOrderUnderK() {
    int[] list = { 1, 2, 5, 7, 8, 12 };
    int k = 15;
    System.out.print("given list {1,2,5,7,8,12} and k = 15. the number of pairs thats equal to or less than k: ");
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
    System.out.println("given a bunch of tickets, trace back the route: " + route);
  }

  private static void givenNReturnAllNumbersInRange() {
    int n = 33;
    int[] arr = { 3, 6, 8 };
    String output = Algorithms.givenNReturnAllNumbersInRange(n, arr);
    System.out.println("print all numbers in range: " + n + ", " + Algorithms.printIntArray(arr) + ": " + output);
  }

  private static void findMinimumWindowInStringOneWhichContainsAllCharsInStringTwo() {
    String one = "ADOBECODEBANC";
    String two = "ABC";
    System.out.print("given string: " + one + ", and target string: " + two + ", the minimum window: ");
    System.out.println(Algorithms.findMinimumWindowInStringOneWhichContainsAllCharsInStringTwo(one, two));
  }

  private static void encodeStringByCharAndNumber() {
    String s = "aabbaadddc";
    System.out.println("encode string: " + s + " to " + Algorithms.encodeString(s));
  }

  private static void searchNumberInSortedButRotatedArray() {
    int[] rotatedArray = { 8, 9, 1, 2, 3, 4, 5, 6, 7 };
    int target = 10;
    System.out.println("find " + target + " in rotated array: " + Algorithms.printIntArray(rotatedArray));
    System.out.println("target position: " + Algorithms.searchNumberPosInSortedButRotatedArray(rotatedArray, target));
  }

  private static void findLongestAnagramInString() {
    String s = "banana";
    System.out.print("find longest anagram in string: " + s + " is ");
    System.out.println(findLongestAnagramInString(s));
  }

  private static String findLongestAnagramInString(String s) {
    String preprocessedString = Algorithms.insertStarsInString(s);
    String longestAnagramWithStars = Algorithms.findLongestAnagramInStringBruteForceWay(preprocessedString);
    String longestAnagram = Algorithms.stripStarsFromString(longestAnagramWithStars);
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
    List<String> result = Algorithms.getStringsInBetweenTwoWordsLikeInDictionary(source, beginning, end);
    System.out.println("getStringsInBetweenTwoWordsLikeInDictionary: " + Algorithms.printStringArray(result));
  }

  private static void findHowMuchWaterCanAccumulate() {
    // int[] pond = {4,3,5,2,1,4,5,7,3,5,8};
    int[] pond2 = { 4, 2, 3, 1, 2, 0, 1, 2 };
    System.out.println("water accumulated total is: " + Algorithms.findHowMuchWaterCanAccumulate(pond2));
  }

  private static void reverseString() {
    String s = "hello how are you doing?";
    System.out.println("reverse \"" + s + "\": \"" + Algorithms.reverseWords(s) + "\"");
    System.out.println("reverse \"" + s + "\": \"" + Algorithms.reverseWordsByLetter(s) + "\"");
  }

  private static void findBitPosition() {
    int num = 33;
    System.out.println("first bit position of " + num + " is " + Algorithms.findBitPosition(num));
  }

  private static void switchem() {
    int[] input = { -1, 5, -2, 3, 9, -10, -5, 3, -10, 9, 8, 7, -30, -100 };
    int[] output = Algorithms.switchem(input);
    System.out.println(Algorithms.printIntArray(output));
  }

  private static void anagram() {
    String one = "banana";
    String two = "anaban";
    System.out.println(one + " and " + two + " are anagram of eachother: " + Algorithms.areAnagram(one, two));
  }

  private static void allUniqueCharacters() {
    String source = "what is up my friend";
    System.out.println(source + " - contain all unique characters: " + Algorithms.containsAllUniqueChars(source));
  }

  private static void maxSubArray() {
    int[] array = { 10, 1, -100, 20, 3, -300, -50, 100, 4, -20, 30 };
    System.out.println("max array length is " + Algorithms.maxSubArray(array));
  }

  private static void isSubString() {
    String source = "why hello there";
    String target = "hello2";

    System.out.println(target + " is substring of " + source + ": " + Algorithms.isSubString(source, target));
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
    System.out.println("middle node is: " + Algorithms.returnMiddleNode(one).value());

    Algorithms.insertToEnd(one, two);
    Algorithms.insertToEnd(two, one);
    /*
     * this linked list now has a cycle, so don't print it like a singly linked
     * list with no cycle
     */
    System.out.println("does this linked list have cycle: " + Algorithms.ifLinkedListHasCycle(one));
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
    System.out.println("insert binary tree node 20 successfull: " + node.insert(20));
    System.out.println("insert binary tree node 25 successfull: " + node.insert(26));
    System.out.println("insert binary tree node 30 successfull: " + node.insert(30));
    System.out.println("insert binary tree node -30 successfull: " + node.insert(-30));
    System.out.println("insert binary tree node 21 successfull: " + node.insert(21));
    System.out.println("insert binary tree node 2 successfull: " + node.insert(2));

    Algorithms.printBinaryTreeByLayer(node);
    Algorithms.printTreeInOrderUsingStack(node);
  }

}
