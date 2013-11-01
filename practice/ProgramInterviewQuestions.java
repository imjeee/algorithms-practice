import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.lang.StringBuffer;

class ProgramInterviewQuestions {
  public static void main(String[] args){
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
    //misc();
    playWithLinkedList();
    playWithBinarySearchTree();
    playWithQueue();
    findHowMuchWaterCanAccumulate();
  }
  
  private static void findHowMuchWaterCanAccumulate() {
    int[] pond = {4,3,5,2,1,4,5,7,3,5,8};
    System.out.println("water accumulated total is: " + findHowMuchWaterCanAccumulate(pond));
  }
  
  /**
   * 
   * given an int array representing a pond, find the water that can be accumulated
   *         _
   *      4 | |  _
   *      3 | |_| |  _     _
   *      2 |     |_| |  _| |
   *      1 |_________|_|___|
   * height: 4 2 3 1 2 0 1 2
   * pos:    0 1 2 3 4 5 6 7
   * 
   * in this case, the water accumulates at pos 1, 3, 5, 6
   * water accumualted at each pos are 1,1,2,1
   * result should be 1+1+2+1 = 5 
   * 
   * @param pond
   * @return
   */
  public static int findHowMuchWaterCanAccumulate(int[] pond) {
    if (pond.length < 3)
      return 0;
    int leftWallHeight = pond[0];
    int leftWallPos = 0;
    int rightWallHeight = 0;
    int rightWallPos = 0;
    int waterAccumulated = 0;
    for (int i = 1; i < pond.length; i++) {
      if (pond[i] >= leftWallHeight) {
        rightWallHeight = pond[i];
        rightWallPos = i;
        waterAccumulated += getWaterAccumulatedGivenRange(pond, Math.min(leftWallHeight, rightWallHeight), rightWallPos, leftWallPos);
        leftWallHeight = rightWallHeight;
        leftWallPos = i;
        rightWallHeight = 0;
        rightWallPos = 0;
      }
    }
    return waterAccumulated;
  }
  
  /**
   * 
   * given the wall height and the left and right wall position, 
   * find how much water can accumulate in the pond inbetween those walls
   * 
   * @param pond
   * @param wallHeight
   * @param rightWallPos
   * @param leftWallPos
   * @return
   */
  public static int getWaterAccumulatedGivenRange(int[] pond, int wallHeight, int rightWallPos, int leftWallPos) {
    if (rightWallPos == leftWallPos || rightWallPos == leftWallPos + 1) {
      return 0;
    } else {
      int result = 0;
      for (int i = leftWallPos; i < rightWallPos; i++) {
        result += wallHeight - pond[i];
      }
      return result;
    }
  }
  
  private static void mergeSortedIntArrays() {
    int[] a = {1,4,6,8,9};
    int[] b = {4,7,8, 10,12};
    int[] result = mergeSortedIntArrays(a, b);
    System.out.print("Merged int array: ");
    for (int i = 0; i < result.length; i++)
      System.out.print(result[i] + ", ");
    System.out.println();
  }
  
  /**
   * take 2 sorted integer arrays, return a third one that combines the 2, remove duplicates
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
        if (resultPointer == 0 || result[resultPointer-1] != a[aPointer])
          result[resultPointer++] = a[aPointer];
        aPointer++;
      } else {
        if (resultPointer == 0 || result[resultPointer-1] != b[bPointer])
          result[resultPointer++] = b[bPointer];
        bPointer++;
      }
    }
    
    return result;
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
    
    printBinaryTree(node);
  }

  /**
   * print binary tree (kinda) like a tree
   * 
   * @param node
   */
  public static void printBinaryTree(BinaryTreeNode node) {
    MyQueue<BinaryTreeNode> q = new MyQueue<BinaryTreeNode>();
    q.enqueue(node);
    q.enqueue(null);
    while(!q.isEmpty() && q.size() != 1) {
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
  
  private static void playWithLinkedList() {
    LinkedListNode<Integer> node = new LinkedListNode<Integer>(10);
    LinkedListNode<Integer> node2 = new LinkedListNode<Integer>(20);
    node.next(node2);    
    printLinkedList(node);

    LinkedListNode<Integer> node3 = new LinkedListNode<Integer>(25);    
    LinkedListNode<Integer> head = insertToEnd(node, node3);
    printLinkedList(head);
    
    LinkedListNode<Integer> node4 = new LinkedListNode<Integer>(1);
    head = insertToBeginning(node, node4);
    printLinkedList(head);
    
    LinkedListNode<Integer> one = new LinkedListNode<Integer>(1);
    LinkedListNode<Integer> two = new LinkedListNode<Integer>(10);
    one.insert(one, 2);
    one.insert(one, 3);
    one.insert(one, 4);
    one.insert(one, 5);
    one.insert(one, 6);
    one.delete(one, 2);
    one.insert(one, 2);
    printLinkedList(one);
    insertToEnd(one, two);
    insertToEnd(two, one);
    /* this linked list now has a cycle, so don't print it like a singly linked list with no cycle */
    System.out.println("does this linked list have cycle: " + ifLinkedListHasCycle(one));
  }
  
  public static boolean ifLinkedListHasCycle(LinkedListNode<Integer> head) {
    LinkedListNode<Integer> trailer = head;
    LinkedListNode<Integer> runner = head;
    while (trailer != null && runner.next() != null && runner.next().next() != null) {
      trailer = trailer.next();
      runner = runner.next().next();
      if (trailer == runner) {
        return true;
      }
    }
    return false;
  }
  
  public static LinkedListNode<Integer> insertToBeginning(LinkedListNode<Integer> head, LinkedListNode<Integer> newNode) {
    newNode.next(head);
    return newNode;
  }
  
  public static LinkedListNode<Integer> insertToEnd(LinkedListNode<Integer> head, LinkedListNode<Integer> newNode) {
    LinkedListNode<Integer> tmp = head;
    while(tmp.next() != null)
      tmp = tmp.next();
    tmp.next(newNode);
    return head;
  }
  
  /**
   * print linked list
   * 
   * @param node
   */
  public static void printLinkedList(LinkedListNode<Integer> node) {
    System.out.print("print linkedList: ");
    while(node != null) {
      System.out.print("node: " + node.value() + ", ");
      node = node.next();
    }
    System.out.println();
  }
  
  private static void reverseString() {
    String s = "hello how are you doing?";
    System.out.println("reverse \"" + s + "\": \"" + reverseWords(s) + "\"");
    System.out.println("reverse \"" + s + "\": \"" + reverseWordsByLetter(s) + "\"");
  }
  
  private static void findBitPosition() {
    int num = 33;
    System.out.println("first bit position of " + num + " is " + findBitPosition(num));
  }
  
  private static void misc() {
    int[] i = { Integer.MAX_VALUE };
    System.out.println(i[0]);
    i[0]++;
    System.out.println(i[0]);
  }
  
  private static void switchem() {
    int[] input = {-1, 5, -2, 3, 9, -10, -5, 3, -10, 9, 8, 7, -30, -100};
    int[] output = switchem(input);
    
    for (int i : output) {
      System.out.print(i+" ");
    }
    System.out.println();
  }
  
  private static void anagram() {
    String one = "banana";
    String two = "anaban";
    System.out.println(one + " and " + two + " are anagram of eachother: " + anagram(one, two));
  }
  
  private static void allUniqueCharacters() {
    String source = "what is up my friend";
    System.out.println(source + " - contain all unique characters: " + allUniqueChars(source));
  }
  
  private static void maxSubArray() {
    int[] array = {10, 1, -100,20,3,-300, -50, 100, 4, -20, 30};
    System.out.println("max array length is " + maxSubArray(array));
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
    

  private static void isSubString() {
    String source = "why hello there";
    String target = "hello2";
    
    System.out.println(target + " is substring of " + source + ": " + isSubString(source, target));
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

  public static String reverseWordsByLetter(String s) {    
    String reversedString = reverseString(s);
    int startOfWord = 0;
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < reversedString.length(); i++) {
      if (i + 1 >= reversedString.length() || reversedString.charAt(i + 1) == ' ') {
        resultSb.append(reverseString(reversedString.substring(startOfWord, i + 1)) + " ");
        startOfWord = i + 2;
      }
    }
    return resultSb.toString().substring(0, resultSb.length() - 1);
  }
  
  private static String reverseString(String s) {
    StringBuffer sb = new StringBuffer();
    for (int i = s.length() - 1; i >= 0; i--)
      sb.append(s.charAt(i));
    return sb.toString();
  }
  
  public static String reverseWords(String s){
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

  /**
   * find if string contains all unique characters by putting in hashmap, O(n)
   * 
   * @param s
   * @return
   */
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

  /**
   * find if string contains all unique characters, double for loop, O(n^2) complexity
   * 
   * @param s
   * @return
   */
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

  /**
   * find if 2 strings are anagrams of each other, 2 NON nested for loops, O(n)
   * 
   * @param one
   * @param two
   * @return
   */
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

  /**
   * replace all spaces in string s with %20, return new string
   * 
   * @param s
   * @return
   */
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
