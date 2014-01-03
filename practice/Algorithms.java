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
import java.util.Set;
import java.util.Stack;

import NewDataStructures.BinarySearchTreeNode;
import NewDataStructures.BinaryTreeNode;
import NewDataStructures.LinkedListNode;
import NewDataStructures.MyQueue;
import NewDataStructures.Person;
import NewDataStructures.TwoListsAndTheirSumDiff;
  
public class Algorithms {

  /**
   * 
   * You are given an array of n integers which can contain integers from 1 to n only.
   * Some elements can be repeated multiple times and some other elements can be absent from the array.
   * Write a running code on paper which takes O(1) space apart from the input array and O(n) time to
   * print which elements are not present in the array and the count of every element which is there in
   * the array along with the element number. 
   * NOTE: The array isn't necessarily sorted.
   * 
   * http://www.careercup.com/question?id=21263687
   * 
   * @param arr
   */
  public static void printAllDuplicatesAndFindCountInIntArray(int[] arr) {
    for (int i = 0; i < arr.length;) {
      int thisNumPos = i;
      int thisNum = arr[thisNumPos];
      if (thisNum > 0) {
        int nextNumPos = thisNum - 1;
        int nextNum = arr[nextNumPos];          
        if (nextNum > 0) {
          arr[thisNumPos] = arr[nextNumPos];
          arr[nextNumPos] = -1;
        } else {
          arr[i] = 0;
          arr[nextNumPos]--;
          i++;
        }
      } else {
        i++;
      }
    }
  }
  
  /**
   * Given an equation in the form 2^i * 3^j * 5^k * 7^l where i,j,k,l >=0 are integers.
   * write a program to generate numbers from that equation in sorted order efficiently. 
   * for example numbers from that equation will be in the order 2,3,5,6,7,8,9.....and so on..
   * 
   * http://www.careercup.com/question?id=23823662
   * 
   * @param x
   * @return
   */
  public static int[] generateNumbersInOrder(int x) {
    int[] result = new int[x];
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    pq.add(1);
    for (int i = 0; i < x;) {
      int min = pq.poll();
      if (i == 0 || min != result[i-1]) {
        result[i] = min;
        i++;
      }
      pq.add(min*2);
      pq.add(min*3);
      pq.add(min*5);
      pq.add(min*7);
    }
    return result;
  }
  
  /**
   * we will name a number "aggregated number" if this number has the following attribute: 
   * just like the Fibonacci numbers 
   * 1,1,2,3,5,8,13..... 
   * 
   * the digits in the number can divided into several parts, and the later part is the sum of the former parts. 
   * like 112358, because 1+1=2, 1+2=3, 2+3=5, 3+5=8 
   * 122436, because 12+24=36 
   * 1299111210, because 12+99=111, 99+111=210 
   * 112112224, because 112+112=224 
   * 
   * http://www.careercup.com/question?id=14948278
   * 
   * @param x
   * @return
   */
  public static boolean findIfNumIsAggregateOfNumbers(int x) {
    String tmp = Integer.toString(x);
    for (int i = 1; i < tmp.length(); i++) {
      int lastNum = Integer.parseInt(tmp.substring(0, i));
      for (int j = i + 1; j < tmp.length(); j++) {
        int thisNum = Integer.parseInt(tmp.substring(i, j));
        if (findIfNumIsAggregateOfNumbersHelper(lastNum, thisNum, tmp.substring(j, tmp.length())))
          return true;
      }
    }
    return false;
  }
  
  public static boolean findIfNumIsAggregateOfNumbersHelper(int lastNum, int thisNum, String stringLeft) {
    if (stringLeft.equals(""))
      return true;
    for (int i = 1; i < stringLeft.length() + 1; i++) {
      int nextNum = Integer.parseInt(stringLeft.substring(0, i));
      if (lastNum + thisNum == nextNum)
        return findIfNumIsAggregateOfNumbersHelper(thisNum, nextNum, stringLeft.substring(i, stringLeft.length()));
    }
    return false;
  }
  
  /**
   * 
   * Given a BST and a value x. Find two nodes in the tree whose sum is equal x.
   * Additional space: O(height of the tree). It is not allowed to modify the tree
   * 
   * http://www.careercup.com/question?id=15320677
   * 
   * @param x
   * @param node
   * @return
   */
  public static boolean findIfTwoNumInBSTAddsUpToX(int x, BinarySearchTreeNode node) {
    Stack<BinaryTreeNode> leftStack = new Stack<BinaryTreeNode>();
    Stack<BinaryTreeNode> rightStack = new Stack<BinaryTreeNode>();
    leftStack.add(node);
    rightStack.add(node);
    boolean reachedLeftMostNode = false;
    boolean reachedRightMostNode = false;
    
    while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
      while (!reachedLeftMostNode && leftStack.peek().left() != null)
        leftStack.push(leftStack.peek().left());
      while (!reachedRightMostNode && rightStack.peek().right() != null)
        rightStack.push(rightStack.peek().right());
      
      reachedLeftMostNode = true;
      reachedRightMostNode = true;
      
      int leftNum = leftStack.peek().value();
      int rightNum = rightStack.peek().value();
      
      if (leftNum + rightNum < x) {
        BinaryTreeNode tmpNode = leftStack.pop();
        if (tmpNode.right() != null) {
          leftStack.push(tmpNode.right());
          reachedLeftMostNode = false;
        }
      } else if (leftNum + rightNum > x) {
        BinaryTreeNode tmpNode = rightStack.pop();
        if (tmpNode.left() != null) {
          rightStack.push(tmpNode.left());
          reachedRightMostNode = false;
        }
      } else {
        return true;
      }
    }
    return false;
  }

  /**
   * 
   * Let's say you have a phrase without any spaces - eg. "thisisawesome".
   * Given a dictionary, how would you add spaces in this string?
   * 
   * http://www.careercup.com/question?id=10060840
   * 
   * @param s
   * @param dict
   * @return
   */
  public static String addSpaceToSentence(String s, Set<String> dict) {
    if (s == null || s.length() == 0) {
      return "";
    } else if (dict.contains(s)) {
      return s;
    } else {
      for (int i = 0; i < s.length(); i++) {
        String possibleWord = s.substring(0, i);
        if (dict.contains(possibleWord)) {
          String restOfString = addSpaceToSentence(s.substring(i), dict);
          if (restOfString != null)
            return possibleWord + " " + restOfString; 
        }
      }
      return null;
    }
  }
  
  
  /**
   * Given a string, find whether it has any permutation of another string. 
   * For example, given "abcdefg" and "ba", it shuold return true, because "abcdefg" 
   * has substring "ab", which is a permutation of "ba".
   * 
   * http://www.careercup.com/question?id=15555796
   * 
   * @param s1
   * @param s2
   * @return
   */
  public static boolean findWhetherIthasAnyPermutationOfAnotherString(String s1, String s2) {
    long s2Value = 1;
    for (int i = 0; i < s2.length(); i++) {
      int charValue = s2.charAt(i) - 64;      
      s2Value *= charValue;
    }
    long s1Value = 1;
    int runner = 0;
    int trailer = 0;
    while (trailer < s1.length() && runner < s1.length()) {
      while (s1Value < s2Value) {
        int charValue = s1.charAt(runner) - 64;
        s1Value *= charValue;
        runner++;
      }
      while (s1Value > s2Value) {
        int charValue = s1.charAt(trailer) - 64;
        s1Value /= charValue;
        trailer++;
      }
      if (s1Value == s2Value) {
        String possibleMatch = s1.substring(trailer, runner);
        if (stringsArePermutations(possibleMatch, s2))
          return true;
        else
          runner++;
      }
    }
    return false;
  }
  
  public static boolean stringsArePermutations(String s1, String s2) {
    char[] s1Chars = s1.toCharArray();
    Arrays.sort(s1Chars);
    String sortedS1 = String.valueOf(s1Chars);
    
    char[] s2Chars = s2.toCharArray();
    Arrays.sort(s2Chars);
    String sortedS2 = String.valueOf(s2Chars);
    
    boolean result = sortedS1.equals(sortedS2);
    if (result)
      System.out.println("found match: " + s1 + " : " + s2);
    return result;
  }
  
  /**
   * 
   * Use SIMPLE LOGIC for Converting this string str="aaabbccc" into str="3a2b3c".
   * 
   * http://www.careercup.com/question?id=6074182194429952
   * 
   * @param s
   * @return
   */
  public static String printWhatYouSee(String s) {
    String whatYouSee = "";
    if (s.length() > 0) {
      char lastChar = s.charAt(0);
      String sChar = lastChar + "";
      int charCount = 1;
      for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) != lastChar) {
          whatYouSee += charCount + sChar;
          charCount = 1;
        } else {
          charCount++;
        }
        lastChar = s.charAt(i);
      }
      sChar = lastChar + "";
      whatYouSee += charCount + sChar; 
    }
    return whatYouSee;
  }
  
  /**
   * 
   * Given a source string and a destination string write a program to display sequence of strings 
   * to travel from source to destination. Rules for traversing: 
   * 
   * 1. You can only change one character at a time 
   * 2. Any resulting word has to be a valid word from dictionary 
   * 
   * Example: Given source word CAT and destination word DOG , one of the valid sequence would be 
   * CAT -> COT -> DOT -> DOG 
   * Another valid sequence can be 
   * CAT -> COT - > COG -> DOG 
   * 
   * One character can change at one time and every resulting word has be a valid word from dictionary
   * 
   * http://www.careercup.com/question?id=14947965
   * 
   * @param startWord
   * @param endWord
   * @param dictionary
   * @return
   */
  public static ArrayList<String> morphFromOneWordToAnotherOneLetterAtATime(String startWord, String endWord, Set<String> dictionary) {
    MyQueue<String> wordsStillNeedsToBeSearched = new MyQueue<String>();
    Map<String, ArrayList<String>> wordMap = new HashMap<String, ArrayList<String>>();
    
    wordsStillNeedsToBeSearched.enqueue(startWord);
    wordsStillNeedsToBeSearched.enqueue(endWord);
    
    while (wordsStillNeedsToBeSearched.size() != 0) {
      String currentWord = wordsStillNeedsToBeSearched.dequeue();
      ArrayList<String> neighbors = findAllNeighborsToWord(currentWord, dictionary);
      for (String neighborWord : neighbors) {
        if (!wordMap.containsKey(neighborWord))
          wordsStillNeedsToBeSearched.enqueue(neighborWord);
      }
      wordMap.put(currentWord, neighbors);
    }
    
    ArrayList<String> shortestPath = findShortestPathFromOneWordToAnother(startWord, endWord, wordMap);
    return shortestPath;
  }
    /**
     * 
     * http://www.careercup.com/question?id=22191662
     * 
     * @param amount
     * @param row
     * @param col
     * @return
     */
    public static double pourWaterIntoGlassPyramid(double amount, int row, int col) {
        double[] thisRow = { amount };
        return pourWaterIntoGlassPyramidHelper(thisRow, row, col);
    }
    
    private static double pourWaterIntoGlassPyramidHelper(double[] thisRow, int row, int col) {
        if (thisRow.length - 1 == row && col < thisRow.length) {
            if (thisRow[col] > 1)
                return 1;
            else
                return thisRow[col];
        }
        double[] newRow = new double[thisRow.length + 1];
        for (int i = 0; i < thisRow.length; i++) {
            if (thisRow[i] - 1 > 1) {
                double split = (thisRow[i] - 1) / 2;
                newRow[i] += split;
                newRow[i + 1] += split;
            }
        }
        return pourWaterIntoGlassPyramidHelper(newRow, row, col);
    }
    
    /**
     * Given n. Generate all numbers with number of digits equal to n, such that the digit to the right is greater than
     * the left digit (ai+1 > ai). E.g. if n=3 (123,124,125,...129,234,...789)
     * 
     * http://www.careercup.com/question?id=6715403111759872
     * 
     * @param n
     * @return
     */
    public static ArrayList<Integer> generateNumbersForNDigitsInIncreasingOrder(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (n == 0)
            return result;
        for (int i = 1; i <= 9; i++)
            result.add(i);
        return generateNumbersForNDigitsInIncreasingOrderHelper(result, n - 1);
    }
    
    private static ArrayList<Integer> generateNumbersForNDigitsInIncreasingOrderHelper(ArrayList<Integer> result, int numLeft) {
        ArrayList<Integer> newResult = new ArrayList<Integer>();
        if (numLeft == 0)
            return result;
        for (int i = 0; i < result.size(); i++) {
            int lastDigit = result.get(i) % 10;
            int tmp = result.get(i) * 10;
            for (int j = lastDigit + 1; j <= 9; j++) {
                newResult.add(tmp + j);
            }
        }
        return generateNumbersForNDigitsInIncreasingOrderHelper(newResult, numLeft - 1);
    }

    
    private static ArrayList<String> findShortestPathFromOneWordToAnother(String startWord, String endWord, Map<String, ArrayList<String>> wordMap) {
        ArrayList<String> path = new ArrayList<String>();
        Set<String> visitedWords = new HashSet<String>();
        MyQueue<ArrayList<String>> visitQueues = new MyQueue<ArrayList<String>>();
        
        path.add(startWord);
        visitedWords.add(startWord);
        visitQueues.enqueue(path);
        
        while (!visitQueues.isEmpty()) {
            ArrayList<String> tmpPath = visitQueues.dequeue();
            String lastWord = tmpPath.get(tmpPath.size() - 1);
            ArrayList<String> neighbors = wordMap.get(lastWord);
            for (String word : neighbors) {
                if (!visitedWords.contains(word)) {
                    visitedWords.add(word);
                    ArrayList<String> newPath = new ArrayList<String>(tmpPath);
                    newPath.add(word);
                    if (word.equals(endWord))
                        return newPath;
                    else
                        visitQueues.enqueue(newPath);
                }
            }
        }
        return null;
    }
    
    private static ArrayList<String> findAllNeighborsToWord(String currentWord, Set<String> dictionary) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        ArrayList<String> currenWordsNeighbors = new ArrayList<String>();
        
        for (int i = 0; i < currentWord.length(); i++) {
            char[] currentWordForProcessing = currentWord.toCharArray();
            for (int j = 0; j < alphabet.length; j++) {
                currentWordForProcessing[i] = alphabet[j];
                String possibleNewWord = String.valueOf(currentWordForProcessing);
                if (dictionary.contains(possibleNewWord) && !possibleNewWord.equals(currentWord))
                    currenWordsNeighbors.add(possibleNewWord);
            }
        }
        return currenWordsNeighbors;
    }
    
    /**
     * 
     * Partition a set of numbers into two such that difference between their sum is minimum, and both sets have equal
     * number of elements.
     * 
     * For example: {1, 4, 9, 16} is partitioned as {1,16} and {4,9} with diff = 17-13=4. Does greedy work here? First
     * sorting, and then picking smallest and largest to fall in set 1, and picking 2nd smallest and 2nd largest to fall
     * in set 2.
     * 
     * http://www.careercup.com/question?id=10244832
     * 
     * @param list
     * @return
     */
    public static TwoListsAndTheirSumDiff partitionSetInToTwoSuchThatTheDifferenceIsMinimized(ArrayList<Integer> list) {
        ArrayList<Integer> listClone = new ArrayList<Integer>(list);
        ArrayList<Integer> listOne = new ArrayList<Integer>();
        ArrayList<Integer> listTwo = new ArrayList<Integer>();
        return partitionSetInToTwoSuchThatTheDifferenceIsMinimizedHelper(listClone, listOne, listTwo);
    }
    
    private static TwoListsAndTheirSumDiff partitionSetInToTwoSuchThatTheDifferenceIsMinimizedHelper(ArrayList<Integer> list, ArrayList<Integer> listOne,
            ArrayList<Integer> listTwo) {
        TwoListsAndTheirSumDiff resultDiff = null;
        if (list.size() == 0) {
            resultDiff = new TwoListsAndTheirSumDiff();
            resultDiff.setListOne(listOne);
            resultDiff.setListTwo(listTwo);
        } else {
            int takeOut = list.remove(0);
            listOne.add(takeOut);
            int takeOutPos = listOne.size() - 1;
            TwoListsAndTheirSumDiff ifListOneGetsI = partitionSetInToTwoSuchThatTheDifferenceIsMinimizedHelper(list, listOne, listTwo);
            listOne.remove(takeOutPos);
            listTwo.add(takeOut);
            TwoListsAndTheirSumDiff ifListTwoGetsI = partitionSetInToTwoSuchThatTheDifferenceIsMinimizedHelper(list, listOne, listTwo);
            TwoListsAndTheirSumDiff tmpSumDiff = ifListOneGetsI.getSumDiff() < ifListTwoGetsI.getSumDiff() ? ifListOneGetsI : ifListTwoGetsI;
            resultDiff = resultDiff == null ? tmpSumDiff : resultDiff;
            resultDiff = tmpSumDiff.getSumDiff() < resultDiff.getSumDiff() ? tmpSumDiff : resultDiff;
        }
        return resultDiff;
    }
    
    /**
     * THIS IS A WRONG SOLUTION (12/16/2013)
     * 
     * 1. A 2. Ctrl+A 3. Ctrl+C 4. Ctrl+V
     * 
     * If you can only press the keyboard for N times (with the above four keys), please write a program to produce
     * maximum numbers of A. If possible, please also print out the sequence of keys. So the input parameter is N (No.
     * of keys that you can press), the output is M (No. of As that you can produce).
     * 
     * http://www.careercup.com/question?id=7184083
     * 
     * @param n
     * @return
     */
    public static int findMaxAsCanBeProducedByKeySequence(int n) {
        int currentOutputLength = 0;
        int copyClipLength = 0;
        while (n > 0) {
            if (n < 3) {
                if (copyClipLength > 1) {
                    currentOutputLength += copyClipLength;
                    System.out.print("Ctrl-P ");
                } else {
                    currentOutputLength += 1;
                    System.out.print("A ");
                }
                n--;
            } else {
                int typeThreeAs = currentOutputLength + 3;
                int selectAllCopyPaste = 2 * currentOutputLength;
                int pasteThreeTimes = currentOutputLength + copyClipLength * 3;
                if (pasteThreeTimes > typeThreeAs && pasteThreeTimes > selectAllCopyPaste) {
                    currentOutputLength += copyClipLength * 3;
                    System.out.print("Ctrl-P Ctrl-P Ctrl-P ");
                } else if (typeThreeAs > selectAllCopyPaste) {
                    currentOutputLength += 3;
                    System.out.print("A A A ");
                } else {
                    copyClipLength = currentOutputLength;
                    currentOutputLength *= 2;
                    System.out.print("Ctrl-A Ctrl-C Ctrl-P ");
                }
                n -= 3;
            }
        }
        return currentOutputLength;
    }
    
    /**
     * 
     * Eliminate all ÔbÕ and ÔacÕ in an array of characters, you have to replace them in-place, and you are only allowed
     * to iterate over the char array once.
     * 
     * Examples: abc -> ac ac->''
     * 
     * http://www.careercup.com/question?id=18460667
     * 
     * @param array
     */
    public static void removeAllBsAndACsInCharArray(char[] array) {
        int pointer = 0;
        while (pointer < array.length && array[pointer] != '-') {
            if (array[pointer] == 'b') {
                array[pointer] = '-';
            } else if (array[pointer] == 'a') {
                if (pointer + 1 < array.length && array[pointer + 1] == 'c') {
                    array[pointer] = '-';
                    array[pointer + 1] = '-';
                }
            }
            pointer++;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '-')
                removeCharAtPointerAndPushAllElementsForward(array, i);
        }
    }
    
    private static void removeCharAtPointerAndPushAllElementsForward(char[] array, int pointer) {
        for (int i = pointer; i < array.length - 1; i++) {
            array[pointer] = array[pointer + 1];
        }
        array[array.length - 1] = '-';
    }
    
    /**
     * 
     * Given a binary representation of an integer say 15 as 1111, find the maximum longest continous sequence of 0s.
     * The twist is it needs to be done in log N.
     * 
     * For example. 10000101 the answer should be 4, because there are 4 continouos zeroes.
     * 
     * http://www.careercup.com/question?id=4860021380743168
     * 
     * @param num
     * @return
     */
    public static int findLongestContinuousZerosInBinaryRepresentationOfInt(int num) {
        int result = 0;
        int tmpResult = 0;
        if (num == 0)
            return 1;
        while (num != 0) {
            if ((num & 1) == 0) {
                tmpResult++;
                result = tmpResult > result ? tmpResult : result;
            } else {
                tmpResult = 0;
            }
            num = num >> 1;
        }
        return result;
    }
    
    public static String convertIntToBinaryString(int num) {
        if (num == 0)
            return "0";
        else if (num == 1)
            return "1";
        else {
            if (num % 2 == 0)
                return convertIntToBinaryString(num / 2) + "0";
            else
                return convertIntToBinaryString(num / 2) + "1";
        }
    }
    
    /**
     * Given a sorted linked list, delete all duplicate numbers, leave only distinct numbers from original list. e.g.,
     * given 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3, return 2->3.
     * 
     * http://www.careercup.com/question?id=165053
     * 
     * @param node
     */
    public static LinkedListNode<Integer> removeDupsFromSortedSingleyListedNode(LinkedListNode<Integer> node) {
        
        if (node == null)
            return null;
        else if (node.next() == null)
            return node;
        else {
            LinkedListNode<Integer> startNode = null;
            LinkedListNode<Integer> resultLink = null;
            LinkedListNode<Integer> lastSeenNode = node;
            LinkedListNode<Integer> currentNode = node.next();
            LinkedListNode<Integer> needToRemoveNode = null;
            while (currentNode != null) {
                if (currentNode.value() == lastSeenNode.value()) {
                    needToRemoveNode = currentNode;
                } else {
                    if (needToRemoveNode == null || lastSeenNode.value() != needToRemoveNode.value()) {
                        if (startNode == null) {
                            startNode = new LinkedListNode<Integer>(lastSeenNode.value());
                            resultLink = startNode;
                        } else {
                            LinkedListNode<Integer> dupNode = new LinkedListNode<Integer>(lastSeenNode.value());
                            resultLink.next(dupNode);
                            resultLink = resultLink.next();
                        }
                    }
                }
                lastSeenNode = currentNode;
                currentNode = currentNode.next();
            }
            
            if (lastSeenNode.value() != needToRemoveNode.value())
                resultLink.next(new LinkedListNode<Integer>(lastSeenNode.value()));
            
            return startNode;
        }
    }
    
    /**
     * 
     * Given a sequence of non-negative integers find a subsequence of length 3 having maximum product with the numbers
     * of the subsequence being in ascending order. Example: Input: 6 7 8 1 2 3 9 10 Ouput: 8 9 10
     * 
     * http://www.careercup.com/question?id=16813665
     * 
     * @param array
     * @return
     */
    public static int[] findLargestTripleProductInIncreasingOrderInArray(int[] array) {
        ArrayList<ArrayList<Integer>> answerTable = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < answerTable.size(); j++) {
                ArrayList<Integer> answerTableArrayJ = answerTable.get(j);
                int lastElementPos = answerTableArrayJ.size() - 1;
                if (answerTableArrayJ.get(lastElementPos) < array[i])
                    answerTableArrayJ.add(array[i]);
            }
            ArrayList<Integer> newPossibility = new ArrayList<Integer>();
            newPossibility.add(array[i]);
            answerTable.add(newPossibility);
        }
        int[] output = new int[3];
        int result = 0;
        for (int i = 0; i < answerTable.size(); i++) {
            ArrayList<Integer> answerTableArrayJ = answerTable.get(i);
            if (answerTableArrayJ.size() >= 3) {
                int lastElementPos = answerTableArrayJ.size() - 1;
                int tmpResult = answerTableArrayJ.get(lastElementPos);
                tmpResult *= answerTableArrayJ.get(lastElementPos - 1);
                tmpResult *= answerTableArrayJ.get(lastElementPos - 2);
                if (tmpResult > result) {
                    result = tmpResult;
                    output[0] = answerTableArrayJ.get(lastElementPos - 2);
                    output[1] = answerTableArrayJ.get(lastElementPos - 1);
                    output[2] = answerTableArrayJ.get(lastElementPos);
                }
            }
        }
        return output;
    }
    
    /**
     * 
     * You have given a positive number you have to find a number which is bigger than that by using same digits
     * available in the number
     * 
     * Example :- You have given a number 7585 , your output should be 7855.
     * 
     * http://www.careercup.com/question?id=10676884
     * 
     * @param num
     * @return
     */
    public static int findTheImmediateBiggerNumUsingSameDigit(int num) {
        int[] numInArrayForm = convertIntToIntArray(num);
        int pos = numInArrayForm.length - 2;
        while (pos >= 0 && numInArrayForm[pos] > numInArrayForm[pos + 1])
            pos--;
        swapPosNumWithSmallestNumBiggerThanPos(pos, numInArrayForm);
        int[] newNumInArrayForm = sortArrayBetweenPositions(numInArrayForm, pos + 1, numInArrayForm.length);
        int result = convertIntArrayToInt(newNumInArrayForm);
        return result;
    }
    
    public static void swapPosNumWithSmallestNumBiggerThanPos(int targetNumPos, int[] numInArrayForm) {
        if (targetNumPos < numInArrayForm.length - 1) {
            int targetNum = numInArrayForm[targetNumPos];
            int smallestNumSoFar = numInArrayForm[targetNumPos + 1];
            int smallestNumSoFarPos = targetNumPos + 1;
            for (int i = numInArrayForm[targetNumPos] + 1; i < numInArrayForm.length - 1; i++) {
                if (numInArrayForm[i] < smallestNumSoFar) {
                    smallestNumSoFar = numInArrayForm[i];
                    smallestNumSoFarPos = i;
                }
            }
            int tmp = targetNum;
            numInArrayForm[targetNumPos] = numInArrayForm[smallestNumSoFarPos];
            numInArrayForm[smallestNumSoFarPos] = tmp;
        }
        
    }
    
    private static int findNumDigits(int num) {
        int result = 1;
        while (num > 9) {
            num /= 10;
            result++;
        }
        return result;
    }
    
    private static ArrayList<Integer> getArrayList(int[] numInArrayForm, int front, int back) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = front; i < back; i++)
            result.add(numInArrayForm[i]);
        return result;
    }
    
    private static int[] convertIntToIntArray(int num) {
        int numDigits = findNumDigits(num);
        int[] result = new int[numDigits];
        for (int i = numDigits - 1; i >= 0; i--) {
            result[i] = num % 10;
            num /= 10;
        }
        return result;
    }
    
    public static int[] sortArrayBetweenPositions(int[] numInArrayForm, int front, int back) {
        ArrayList<Integer> frontOfList = getArrayList(numInArrayForm, 0, front);
        ArrayList<Integer> middleOfList = getArrayList(numInArrayForm, front, back);
        Collections.sort(middleOfList);
        ArrayList<Integer> backOfList = getArrayList(numInArrayForm, back, numInArrayForm.length);
        ArrayList<Integer> result = frontOfList;
        result.addAll(middleOfList);
        result.addAll(backOfList);
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < resultArray.length; i++)
            resultArray[i] = result.get(i);
        return resultArray;
    }
    
    public static int convertIntArrayToInt(int[] intArray) {
        int result = 0;
        int multiplier = 1;
        for (int i = intArray.length - 1; i >= 0; i--) {
            result += intArray[i] * multiplier;
            multiplier *= 10;
        }
        return result;
    }
    
    /**
     * 
     * Push all the zero's of a given array to the end of the array. In place only. Ex 1,2,0,4,0,0,8 becomes
     * 1,2,4,8,0,0,0
     * 
     * http://www.careercup.com/question?id=12986664
     * 
     * @param list
     */
    public static void pushAllZerosToEndOfArray(int[] list) {
        int zeroPointer = 0;
        int numFinder = 0;
        while (numFinder < list.length) {
            while (list[zeroPointer] != 0)
                zeroPointer++;
            numFinder = zeroPointer + 1;
            while (numFinder < list.length && list[numFinder] == 0)
                numFinder++;
            if (numFinder != list.length) {
                list[zeroPointer] = list[numFinder];
                list[numFinder] = 0;
            }
        }
    }
    
    /**
     * 
     * Given a circular single linked list.Write a program that deletes every kth node until only one node is left.
     * After kth node is deleted, start the procedure from (k+1)th node. e.g.list is 1->2->3->4->5->1 k=3 1. You are at
     * 1, delete 3. List is: 1->2->4->5->1 2. You are at 4, delete 1 List is: 2->4->5->2 3. You are at 2,delete 5 List
     * is: 2->4->2 4. You are at 2, delete 2 List is: 4 Return 4.
     * 
     * How efficient you can do it? http://www.careercup.com/question?id=14467673
     * 
     * look up Josephus problem
     * 
     * @param currentNode
     * @param k
     * @return
     */
    public static int removeEveryKthNodeInCircularLinkedListUntilOnlyOneNodeIsLeft(LinkedListNode<Integer> currentNode, int k) {
        
        while (currentNode.value() != currentNode.next().value()) {
            for (int i = 0; i < k - 2; i++) {
                currentNode = currentNode.next();
            }
            currentNode.next(currentNode.next().next());
            currentNode = currentNode.next();
        }
        return currentNode.value();
    }
    
    /**
     * 
     * Given an array of integers. Find two disjoint contiguous sub-arrays such that the absolute difference between the
     * sum of two sub-array is maximum. The sub-arrays should not overlap.
     * 
     * eg- [2 -1 -2 1 -4 2 8] ans - (-1 -2 1 -4) (2 8), diff = 16 http://www.careercup.com/question?id=19286747
     * 
     * 
     * @param list
     * @return
     */
    public static int findMaxSplitInArray(int[] list) {
        int[] maxFromLeft = new int[list.length];
        int[] minFromLeft = new int[list.length];
        
        maxFromLeft[0] = list[0];
        minFromLeft[0] = list[0];
        
        int[] maxFromRight = new int[list.length];
        int[] minFromRight = new int[list.length];
        
        maxFromRight[list.length - 1] = list[list.length - 1];
        minFromRight[list.length - 1] = list[list.length - 1];
        
        for (int i = 1; i < list.length; i++) {
            maxFromLeft[i] = maxFromLeft[i - 1] + list[i] > list[i] ? maxFromLeft[i - 1] + list[i] : list[i];
            minFromLeft[i] = minFromLeft[i - 1] + list[i] < list[i] ? minFromLeft[i - 1] + list[i] : list[i];
        }
        
        for (int i = list.length - 2; i >= 0; i--) {
            maxFromRight[i] = maxFromRight[i + 1] + list[i] > list[i] ? maxFromRight[i + 1] + list[i] : list[i];
            minFromRight[i] = minFromRight[i + 1] + list[i] < list[i] ? minFromRight[i + 1] + list[i] : list[i];
        }
        
        // System.out.println(Algorithms.printIntArray(maxFromLeft));
        // System.out.println(Algorithms.printIntArray(minFromLeft));
        // System.out.println(Algorithms.printIntArray(maxFromRight));
        // System.out.println(Algorithms.printIntArray(minFromRight));
        
        int maxDiff = 0;
        
        for (int i = 0; i < list.length - 1; i++) {
            int tmpDiff = absDiff(maxFromRight[i + 1], minFromLeft[i]);
            maxDiff = tmpDiff > maxDiff ? tmpDiff : maxDiff;
            tmpDiff = absDiff(maxFromLeft[i], minFromRight[i + 1]);
            maxDiff = tmpDiff > maxDiff ? tmpDiff : maxDiff;
        }
        return maxDiff;
    }
    
    public static int absDiff(int a, int b) {
        if (a > b)
            return a - b;
        else
            return b - a;
    }
    
    /**
     * 
     * Implement the plusplus operator when we are getting the input as integer array = { 9,9,9,9 }.output will be
     * {1,0,0,0,0}
     * 
     * http://www.careercup.com/question?id=14370695
     * 
     * @param list
     * @return
     */
    public static int[] plusPlusOperatorForIntArray(int[] list) {
        int carry = 1;
        LinkedList<Integer> answer = new LinkedList<Integer>();
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i] + carry == 10)
                answer.addFirst(0);
            else {
                answer.addFirst(list[i] + carry);
                carry = 0;
            }
        }
        if (carry == 1)
            answer.addFirst(carry);
        
        int[] result = new int[answer.size()];
        Iterator<Integer> llIterator = answer.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = llIterator.next();
        }
        return result;
    }
    
    /**
     * 
     * Given an integer array, sort the integer array such that the concatenated integer of the result array is max.
     * e.g. [4, 94, 9, 14, 1] will be sorted to [9,94,4,14,1] where the result integer is 9944141
     * 
     * http://www.careercup.com/question?id=7781671
     * 
     * @param list
     * @return
     */
    public static int[] sortListSuchThatConcatenateListForLargestNumber(int[] list) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < list.length; i++)
            arrayList.add(list[i]);
        arrayList = mergeSort(arrayList);
        int[] result = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            result[i] = arrayList.get(i);
        }
        return result;
    }
    
    public static ArrayList<Integer> mergeSort(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (list != null) {
            if (list.size() <= 1) {
                result = list;
            } else {
                Integer mid = list.get(0);
                ArrayList<Integer> subList = new ArrayList<Integer>();
                for (int i = 1; i < list.size(); i++) {
                    subList.add(list.get(i));
                }
                ArrayList<Integer> front = mergeSort(getListOfIntegersBiggerThanInteger(subList, mid));
                ArrayList<Integer> back = mergeSort(getListOfIntegersSmallerThanOrEqualsToInteger(subList, mid));
                
                result.addAll(front);
                result.add(mid);
                result.addAll(back);
            }
        }
        return result;
    }
    
    private static ArrayList<Integer> getListOfIntegersSmallerThanOrEqualsToInteger(ArrayList<Integer> list, int mid) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Integer i : list) {
            if (customIntegerCompare(i, mid) <= 0)
                result.add(i);
        }
        return result;
    }
    
    private static ArrayList<Integer> getListOfIntegersBiggerThanInteger(ArrayList<Integer> list, int mid) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Integer i : list) {
            if (customIntegerCompare(i, mid) > 0) {
                result.add(i);
            }
        }
        return result;
    }
    
    private static int customIntegerCompare(int x, int y) {
        if (Integer.parseInt(x + "" + y) > Integer.parseInt(y + "" + x)) {
            return 1;
        } else if (Integer.parseInt(x + "" + y) < Integer.parseInt(y + "" + x)) {
            return -1;
        } else {
            return 0;
        }
    }
    
    /**
     * 
     * Three strings say A,B,C are given to you. Check weather 3rd string is interleaved from string A and B. Ex:
     * A="abcd" B="xyz" C="axybczd". answer is yes. o(n)
     * 
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static boolean findIfTwoStringsAreInterleaved(String a, String b, String c) {
        if (a == null || a.length() == 0)
            return c.equals(b);
        else if (b == null || b.length() == 0)
            return c.equals(a);
        else if (c == null)
            return false;
        else {
            int aPointer = 0;
            int bPointer = 0;
            for (int cPointer = 0; cPointer < c.length(); cPointer++) {
                if (aPointer < a.length() && c.charAt(cPointer) == a.charAt(aPointer))
                    aPointer++;
                else if (bPointer < b.length() && c.charAt(cPointer) == b.charAt(bPointer))
                    bPointer++;
                else
                    return false;
            }
            return aPointer == a.length() && bPointer == b.length();
        }
    }
    
    /**
     * 
     * If a=1, b=2, c=3,....z=26. Given a string, find all possible codes that string can generate. Give a count as well
     * as print the strings.
     * 
     * For example: Input: "1123". You need to general all valid alphabet codes from this string.
     * 
     * Output List aabc //a = 1, a = 1, b = 2, c = 3 kbc // since k is 11, b = 2, c= 3 alc // a = 1, l = 12, c = 3 aaw
     * // a= 1, a =1, w= 23 kw // k = 11, w = 23
     * 
     * @param input
     * @return
     */
    public static ArrayList<String> generateAllValidAlphabetForArray(String input) {
        
        ArrayList<String> results = new ArrayList<String>();
        if (input.length() == 0) {
            results.add("");
        } else if (input.length() == 1) {
            results.add(getConvertedString(input));
            ;
        } else {
            for (int i = 1; i <= input.length(); i++) {
                String tmpSubString = input.substring(0, i);
                if (isValidSubString(tmpSubString)) {
                    ArrayList<String> tmpResults = generateAllValidAlphabetForArray(input.substring(i));
                    for (String s : tmpResults)
                        results.add(getConvertedString(tmpSubString) + s);
                }
            }
        }
        return results;
    }
    
    private static String getConvertedString(String tmpSubString) {
        char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        
        int charPosition = Integer.parseInt(tmpSubString) - 1;
        String character = chars[charPosition] + "";
        return character;
    }
    
    private static boolean isValidSubString(String s) {
        return Integer.parseInt(s) <= 26;
    }
    
    /**
     * one unsorted array is given.Find out the index i and j, j> i for which a[j] - a[i] is maximum.perform in linear
     * time complexity
     * 
     * @param list
     * @return
     */
    public static int findBiggestNumDifferencesInList(int[] list) {
        int smallestSoFar = list[0];
        int result = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] < smallestSoFar)
                smallestSoFar = list[i];
            else if (list[i] - smallestSoFar > result)
                result = list[i] - smallestSoFar;
        }
        return result;
    }
    
    /**
     * 
     * Design an algorithm that, given a list of n elements in an array, finds all the elements that appear more than
     * n/3 times in the list. The algorithm should run in linear time ( n >=0 )
     * 
     * You are expected to use comparisons and achieve linear time. No hashing/excessive space/ and don't use standard
     * linear time deterministic selection algo
     * 
     * http://www.careercup.com/question?id=14099679
     * 
     * @param list
     * @param m
     * @return
     */
    public static int[] givenArrayFindAllElementsAppearMoreThanNOverMTimes(int[] list, int m) {
        Map<Integer, Integer> bucket = new HashMap<Integer, Integer>();
        for (int i = 0; i < list.length; i++) {
            if (!bucket.containsKey(list[i])) {
                bucket.put(list[i], 1);
                if (bucket.size() == m) {
                    reduceAllNumberInBucketByOne(bucket);
                }
            } else {
                int total = bucket.get(list[i]);
                total++;
                bucket.put(list[i], total);
            }
        }
        
        setAllKeyTotalsToZero(bucket);
        countTotalForNumInBuckets(bucket, list);
        ArrayList<Integer> result = new ArrayList<Integer>();
        Set<Integer> keys = bucket.keySet();
        for (Integer key : keys) {
            if (bucket.get(key) > list.length / m)
                result.add(key);
        }
        int[] arrayResult = new int[result.size()];
        for (int i = 0; i < result.size(); i++)
            arrayResult[i] = result.get(i);
        return arrayResult;
    }
    
    private static void countTotalForNumInBuckets(Map<Integer, Integer> bucket, int[] list) {
        for (int i : list) {
            if (bucket.containsKey(i)) {
                int total = bucket.get(i) + 1;
                bucket.put(i, total);
            }
        }
    }
    
    private static void setAllKeyTotalsToZero(Map<Integer, Integer> bucket) {
        Set<Integer> keys = bucket.keySet();
        for (Integer key : keys) {
            bucket.put(key, 0);
        }
    }
    
    private static void reduceAllNumberInBucketByOne(Map<Integer, Integer> bucket) {
        Set<Integer> keys = bucket.keySet();
        Set<Integer> dupKeySet = deepCopyKeySet(keys);
        if (dupKeySet != null && dupKeySet.size() != 0) {
            for (Integer key : dupKeySet) {
                int totalNumThisKeyHas = bucket.get(key);
                totalNumThisKeyHas--;
                bucket.put(key, totalNumThisKeyHas);
                if (totalNumThisKeyHas == 0)
                    bucket.remove(key);
            }
        }
    }
    
    private static Set<Integer> deepCopyKeySet(Set<Integer> keys) {
        Set<Integer> newKeys = new HashSet<Integer>();
        for (Integer key : keys) {
            newKeys.add(key);
        }
        return newKeys;
    }
    
    /**
     * Given an int array which might contain duplicates, find the largest subset of it which form a sequence. Eg.
     * {1,6,10,4,7,9,5} then ans is 4,5,6,7
     * 
     * Sorting is an obvious solution. Can this be done in O(n) time
     * 
     * @param unsortedList
     * @return
     */
    public static int[] findContinuousSubsetInUnsortedArray(int[] unsortedList) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i : unsortedList)
            arrayList.add(i);
        Collections.sort(arrayList);
        int[] sortedList = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            sortedList[i] = arrayList.get(i);
        }
        return findContinuousSubsetInSortedArray(sortedList);
    }
    
    /**
     * 
     * @param sortedList
     * @return
     */
    public static int[] findContinuousSubsetInSortedArray(int[] sortedList) {
        ArrayList<Integer> finalResult = new ArrayList<Integer>();
        ArrayList<Integer> tmpResult = new ArrayList<Integer>();
        tmpResult.add(sortedList[0]);
        for (int i = 1; i < sortedList.length; i++) {
            if (sortedList[i] - 1 == tmpResult.get(tmpResult.size() - 1))
                tmpResult.add(sortedList[i]);
            else if (sortedList[i] != tmpResult.get(tmpResult.size() - 1)) {
                if (tmpResult.size() > finalResult.size()) {
                    finalResult = tmpResult;
                    tmpResult = new ArrayList<Integer>();
                    tmpResult.add(sortedList[i]);
                }
            }
        }
        int[] result = new int[finalResult.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = finalResult.get(i);
        return result;
    }
    
    /**
     * Give you an array which has n integers,it has both positive and negative integers. Now you need sort this array
     * in a special way.After that,the negative integers should in the front,and the positive integers should in the
     * back.Also the relative position should not be changed. eg. -1 1 3 -2 2 ans: -1 -2 1 3 2. o(n)time complexity and
     * o(1) space complexity is perfect.
     * 
     * @param list
     */
    public static void SortIntNegativesToLeftOfPositiveOrderDoesntMatter(int[] list) {
        int negPointer = 0;
        int posPointer = 0;
        while (posPointer < list.length && negPointer < list.length) {
            while (posPointer < list.length && list[posPointer] > 0)
                posPointer++;
            if (posPointer < list.length && list[negPointer] > 0 && list[posPointer] < 0 && negPointer < posPointer) {
                int tmp = list[negPointer];
                list[negPointer] = list[posPointer];
                list[posPointer] = tmp;
                posPointer++;
            }
            negPointer++;
        }
    }
    
    /**
     * Given "n", generate all valid parenthesis strings of length "2n". Example: Given n=2 Output: (()) ()()
     * 
     * @param n
     * @return
     */
    public static ArrayList<String> composeAllPossibleParenthasis(int n) {
        ArrayList<String> results = new ArrayList<String>();
        if (n == 0) {
            results.add("");
        } else if (n == 1) {
            results.add("()");
        } else {
            ArrayList<String> tmpResults = composeAllPossibleParenthasis(n - 1);
            for (String s : tmpResults) {
                String firstVariation = s + "()";
                String secondVariation = "()" + s;
                String thirdVariation = "(" + s + ")";
                results.add(firstVariation);
                if (!firstVariation.equals(secondVariation)) {
                    results.add(secondVariation);
                }
                results.add(thirdVariation);
            }
        }
        return results;
    }
    
    /**
     * match making algorithms
     * 
     * @param guysSet
     * @param girlsSet
     * @return
     */
    public static ArrayList<String[]> coupleMatchingProblem(Set<Person> guysSet, Set<Person> girlsSet) {
        HashMap<String, Person> guys = new HashMap<String, Person>();
        HashMap<String, Person> girls = new HashMap<String, Person>();
        
        for (Person p : guysSet)
            guys.put(p.getName(), p);
        for (Person p : girlsSet)
            girls.put(p.getName(), p);
        
        HashMap<String, String> matchesUsingGuyAsKey = new HashMap<String, String>();
        ArrayList<String[]> matchesResult = new ArrayList<String[]>();
        
        while (matchesUsingGuyAsKey.size() != guys.size()) {
            Set<String> guysInArray = guys.keySet();
            for (String guyName : guysInArray) {
                if (guys.get(guyName).isAvailable()) {
                    findTheGuyAGirl(guyName, guys, girls, matchesUsingGuyAsKey);
                }
            }
        }
        for (Entry<String, String> entry : matchesUsingGuyAsKey.entrySet()) {
            String[] pair = { entry.getKey(), entry.getValue() };
            matchesResult.add(pair);
        }
        return matchesResult;
    }
    
    public static void findTheGuyAGirl(String newGuyName, HashMap<String, Person> guys, HashMap<String, Person> girls,
            HashMap<String, String> matchesUsingGuyAsKey) {
        HashMap<String, String> matchesUsingGirlAsKey = new HashMap<String, String>();
        Iterator it = matchesUsingGuyAsKey.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            matchesUsingGirlAsKey.put((String) pairs.getValue(), (String) pairs.getKey());
        }
        boolean matchFound = false;
        for (int i = 0; i < guys.get(newGuyName).getPreferences().size() && !matchFound; i++) {
            String nextGirlName = (String) guys.get(newGuyName).getPreferences().get(i);
            Person nextGirl = girls.get(nextGirlName);
            boolean nextGirlIsAvailable = nextGirl.isAvailable();
            boolean nextGirlPreferNewGuyOverOld = !nextGirlIsAvailable
                    && nextGirlPreferNewGuyOverOldGuy(newGuyName, matchesUsingGirlAsKey.get(nextGirlName), nextGirl);
            if (nextGirlIsAvailable || nextGirlPreferNewGuyOverOld) {
                if (nextGirlPreferNewGuyOverOld) {
                    String oldGuyName = matchesUsingGirlAsKey.get(nextGirlName);
                    matchesUsingGuyAsKey.remove(oldGuyName);
                    matchesUsingGirlAsKey.remove(nextGirlName);
                    Person oldGuy = guys.get(oldGuyName);
                    oldGuy.setAvailable(true);
                }
                matchesUsingGuyAsKey.put(newGuyName, nextGirlName);
                matchesUsingGirlAsKey.put(nextGirlName, newGuyName);
                guys.get(newGuyName).setAvailable(false);
                girls.get(nextGirlName).setAvailable(false);
                matchFound = true;
            } else {
                // nextGirlName prefers the current guy he's with over guy, keep
                // searching :(
            }
        }
        
    }
    
    private static boolean nextGirlPreferNewGuyOverOldGuy(String newGuyName, String oldGuyName, Person girl) {
        for (Object guyName : girl.getPreferences().toArray()) {
            if (guyName.equals(newGuyName))
                return true;
            else if (guyName.equals(oldGuyName))
                return false;
        }
        System.out.println("This guy isn't found in this girl's preference!!! This means bad input!");
        return false;
    }
    
    public static boolean voilateConsecutiveABCCharRequirement(char[] chars, String s) {
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
    
    public static String printPairs(ArrayList<int[]> pairs) {
        if (pairs == null || pairs.size() == 0)
            return "";
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < pairs.size(); i++) {
            output.append("{" + pairs.get(i)[0] + "," + pairs.get(i)[1] + "} ");
        }
        return output.toString();
    }
    
    public static int findNumOnesInBinaryRepresentationOfInt(int num) {
        if (num == 0)
            return 0;
        int result = 1;
        while (num >> 1 != 0) {
            result += num & 1;
            num = num >> 1;
        }
        return result;
    }
    
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
    public static ArrayList<int[]> queensOnChessBoardProblem(int queensLeftToPlace, boolean[][] board) {
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
    
    private static boolean[][] queensOnChessBoardProblemHelper(int currentRowPos, int queensLeftToPlace, boolean[][] board) {
        if (queensLeftToPlace == 0) {
            return board;
        } else if (currentRowPos >= board.length) {
            return null;
        } else {
            for (int i = 0; i < board[currentRowPos].length; i++) {
                if (queenCanBePlaced(currentRowPos, i, board)) {
                    board[currentRowPos][i] = true;
                    boolean[][] newBoard = queensOnChessBoardProblemHelper(currentRowPos + 1, queensLeftToPlace - 1, board);
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
    
    private static boolean noQueenInDiagnalTopLeftToBottomRight(int row, int col, boolean[][] board) {
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j])
                return false;
        for (int i = row + 1, j = col + 1; i < board.length && j < board[row].length; i++, j++)
            if (board[i][j])
                return false;
        return true;
    }
    
    private static boolean noQueenInDiagnalBottomLeftToTopRight(int row, int col, boolean[][] board) {
        for (int i = row - 1, j = col + 1; i >= 0 && j < board[row].length; i--, j++)
            if (board[i][j])
                return false;
        for (int i = row + 1, j = col - 1; i < board.length && j >= 0; i++, j--)
            if (board[i][j])
                return false;
        return true;
    }
    
    /**
     * You are given pairs of numbers. In a pair the first number is smaller with respect to the second number. Suppose
     * you have two sets (a, b) and (c, d), the second set can follow the first set if b<c. So you can form a long chain
     * in the similar fashion. Find the longest chain which can be formed
     * 
     * @param pairs
     * @return
     */
    public static ArrayList<int[]> findLongestContinuingPairs(int[][] pairs) {
        HashMap<Integer, Integer> visitedPairs = new HashMap<Integer, Integer>();
        return findLongestContinuingPairsHelper(-1, pairs, visitedPairs);
    }
    
    private static ArrayList<int[]> findLongestContinuingPairsHelper(int lastPairTrailingNum, int[][] pairs, HashMap<Integer, Integer> visitedPairs) {
        ArrayList<int[]> longestSoFar = new ArrayList<int[]>();
        for (int i = 0; i < pairs.length; i++) {
            ArrayList<int[]> tmpLongest = new ArrayList<int[]>();
            if (lastPairTrailingNum == -1 || (lastPairTrailingNum <= pairs[i][0] && !visitedPairs.containsKey(i))) {
                tmpLongest.add(pairs[i]);
                visitedPairs.put(i, 1);
                tmpLongest.addAll(findLongestContinuingPairsHelper(pairs[i][1], pairs, visitedPairs));
                if (tmpLongest.size() > longestSoFar.size())
                    longestSoFar = tmpLongest;
                visitedPairs.remove(i);
            }
        }
        return longestSoFar;
    }
    
    /**
     * Given the array of digits (0 is also allowed), what is the minimal sum of two integers that are made of the
     * digits contained in the array. For example, array: 1 2 7 8 9. The min sum (129 + 78) should be 207
     * 
     * 
     * 
     **/
    public static int findMinSumOfTwoIntegerMadeFromDigitsOfArray(ArrayList<Integer> arr) {
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
     * Given a number N, write a program that returns all possible combinations of numbers that add up to N, as lists.
     * (Exclude the N+0=N) For example, if N=4 return {{1,1,1,1},{1,1,2},{2,2},{1,3}}
     * 
     * 
     * @param target
     * @return
     */
    public static ArrayList<String> findAllCombinationsOfNumbersAddUpToN(int target) {
        ArrayList<String> result = new ArrayList<String>();
        
        result.add(target + "");
        for (int i = 1; i < target / 2 + 1; i++) {
            ArrayList<String> tmpResult = findAllCombinationsOfNumbersAddUpToN(target - i);
            result.addAll(addNumToListOfString(i, tmpResult));
        }
        
        return result;
    }
    
    private static ArrayList<String> addNumToListOfString(int n, ArrayList<String> input) {
        ArrayList<String> output = new ArrayList<String>();
        for (String s : input)
            output.add(n + s);
        return output;
    }
    
    /**
     * Given an array of pairs of the form <a, b>. We have to find a sub-array such that the 1st element in the pairs
     * are in increasing order and the sum of 2nd element of the pairs in the sub-array is maximum possible
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
            maxSumInCurrentArray = currentNum >= lastSeenNum ? maxSumInCurrentArray + currentNumValue : currentNumValue;
            maxSoFar = maxSumInCurrentArray > maxSoFar ? maxSumInCurrentArray : maxSoFar;
            lastSeenNum = currentNum;
        }
        return maxSoFar;
    }
    
    /**
     * given a list and an integer k, find how many pairs of numbers in list can add up to smaller than or equals to k
     * value, ie. given {1,2,5,7,8,12} and k = 15, the combinations are {1,2},{1,5},{1,7} ... {7,8}, total of 12 of
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
     * You visited a list of places recently, but you do not remember the order in which you visited them. You have with
     * you the airplane tickets that you used for travelling. Each ticket contains just the start location and the end
     * location. Can you reconstruct your journey?
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
     * Given a positive int "N". and an array of numbers ranging from 0-9 (say array name is arr). print all numbers
     * from 0 to N which include any number from "arr".
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
    
    public static String printCharArray(char[] input) {
        StringBuffer output = new StringBuffer();
        output.append("{");
        for (char i : input)
            output.append(i + ",");
        String sOutput = output.substring(0, output.length() - 1) + "}";
        return sOutput;
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
        String sOutput = output.substring(0, output.length() - 1) + "}";
        return sOutput;
    }
    
    public static String printIntArray(ArrayList<Integer> input) {
        StringBuffer output = new StringBuffer();
        output.append("{");
        for (Integer i : input)
            output.append(i + ",");
        String sOutput = output.substring(0, output.length() - 1) + "}";
        return sOutput;
    }
    
    /**
     * 
     * @param input
     * @return
     */
    public static String printStringArray(List<String> input) {
        if (input == null || input.size() == 0)
            return "";
        StringBuffer output = new StringBuffer();
        output.append("{");
        for (String i : input)
            output.append(i + ",");
        return output.substring(0, output.length() - 1) + "}";
    }
    
    public static String printStringArray(String[] input) {
        StringBuffer output = new StringBuffer();
        output.append("{");
        for (String i : input)
            output.append(i + ",");
        return output.substring(0, output.length() - 1) + "}";
    }
    
    /**
     * You are given two string named str1 and str2. Your task is to find the minimum window in str1 which contains all
     * characters from string str2. this is O(n) in time and space
     * 
     * for example, given: ADOBECODEBANC, ABC return:
     * 
     * 
     * @param one
     * @param two
     * @return
     */
    public static String findMinimumWindowInStringOneWhichContainsAllCharsInStringTwo(String one, String two) {
        HashMap<String, Integer> wordsAndNumTheyAppear = new HashMap<String, Integer>();
        ArrayList<int[]> pairs = new ArrayList<int[]>();
        for (int i = 0; i < two.length(); i++) {
            wordsAndNumTheyAppear.put(two.charAt(i) + "", 0);
        }
        int frontRunner = 0;
        int backRunner = 0;
        
        while (frontRunner + 1 < one.length()) {
            while (missingAtLeastOneKey(wordsAndNumTheyAppear) && frontRunner + 1 < one.length()) {
                frontRunner++;
                String key = one.charAt(frontRunner) + "";
                if (wordsAndNumTheyAppear.containsKey(key)) {
                    int occurance = wordsAndNumTheyAppear.get(key) + 1;
                    wordsAndNumTheyAppear.put(key, occurance);
                }
            }
            int[] pair = { backRunner, frontRunner };
            pairs.add(pair);
            while (!missingAtLeastOneKey(wordsAndNumTheyAppear) && backRunner + 1 < one.length()) {
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
        
        return one.substring(0, resultPair[0]) + "|" + one.substring(resultPair[0], resultPair[1] + 1) + "|" + one.substring(resultPair[1] + 1, one.length());
    }
    
    /**
     * given an array of strings, and a begining string, and a end string, return all strings in array that belongs in
     * between them. like in a dictionary. for example, given {aa,b,c,dd,ff}, aa, d, the result should be {aa,b,c}, dd
     * comes later than d
     * 
     * 
     * @param source
     * @param beginning
     * @param end
     * @return
     */
    public static List<String> getStringsInBetweenTwoWordsLikeInDictionary(String[] source, String beginning, String end) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < source.length; i++) {
            if (isBiggerThanOrEqualTo(source[i], beginning) && isSmallerThanOrEqualTo(source[i], end)) {
                result.add(source[i]);
                System.out.println(source[i]);
            }
        }
        return result;
    }
    
    private static boolean missingAtLeastOneKey(HashMap<String, Integer> wordsAndNumTheyAppear) {
        Set<String> set = wordsAndNumTheyAppear.keySet();
        for (String key : set) {
            if (wordsAndNumTheyAppear.get(key) == 0)
                return true;
        }
        return false;
    }
    
    /**
     * Write a method that takes a string, in this format "aabbaadddc". Encode the string by counting the consecutive
     * letters. Ex: "a2b2a2d3c1" O(n) time and space;
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
     * given an rotated int array, find the position which the target occurs, O(log(n)) time, O(1) space
     * 
     * @param rotatedArray
     * @param target
     * @return
     */
    public static int searchNumberPosInSortedButRotatedArray(int[] rotatedArray, int target) {
        int startPosOfOriginalArray = findStartingPosInRotatedArray(rotatedArray);
        if (target >= rotatedArray[startPosOfOriginalArray] && target <= rotatedArray[rotatedArray.length - 1])
            return seachTargetBetweenRangeInArray(target, startPosOfOriginalArray, rotatedArray.length - 1, rotatedArray);
        else
            return seachTargetBetweenRangeInArray(target, 0, startPosOfOriginalArray, rotatedArray);
    }
    
    private static int seachTargetBetweenRangeInArray(int target, int start, int end, int[] array) {
        int mid = (end - start) / 2 + start;
        while (array[mid] != target && target >= array[start] && target <= array[end]) {
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
     * find longest anagram in string using almost brute force way. given banana, return anana, since anana is the
     * longest anagram in the string banana O(n^2) time and O(n) space
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
     * given number of levels you want to see, print what you see starting at 1, so in this case, if input is 5, you see
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
    
    private static String printWhatYouSeeGivenCurrentLevelAndNumOfLevels(int[] level, int numOfLevels) {
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
     * given a int representing levels user wants, this method returns the levels of pascal triangle int string form,
     * comma separated, where \n is appended when occurs. it runs O(n) time and space for example, when the input is 3,
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
    
    private static int[] getPascalPyramidBySingleLevelGivenPreLevel(int[] prevLevel) {
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
     * given an int array representing a pond, find the water that can be accumulated, O(n) in time and space it pre
     * process the array twice first, for each position, find out the highest wall to its left and right. then we simply
     * calculate it by looking at the lower of the either side wall and take the different between the walls and that
     * spot _ 4 | | _ 3 | |_| | _ _ 2 | |_| | _| | 1 |_________|_|___| height: 4 2 3 1 2 0 1 2 pos: 0 1 2 3 4 5 6 7
     * 
     * in this case, the water accumulates at pos 1, 3, 5, 6 water accumualted at each pos are 1,1,2,1 result should be
     * 1+1+2+1 = 5
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
            int lowerOfBothSidesWall = Math.min(highestWallFromLeft[i], highestWallFromRight[i]);
            if (pond[i] < lowerOfBothSidesWall) {
                waterAccumulated += lowerOfBothSidesWall - pond[i];
            }
        }
        return waterAccumulated;
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
    public static LinkedListNode<Integer> returnMiddleNode(LinkedListNode<Integer> head) {
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
        while (trailer != null && runner.next() != null && runner.next().next() != null) {
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
    public static LinkedListNode<Integer> insertToBeginning(LinkedListNode<Integer> head, LinkedListNode<Integer> newNode) {
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
    public static LinkedListNode<Integer> insertToEnd(LinkedListNode<Integer> head, LinkedListNode<Integer> newNode) {
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
            if (i + 1 >= reversedString.length() || reversedString.charAt(i + 1) == ' ') {
                resultSb.append(reverseString(reversedString.substring(startOfWord, i + 1)) + " ");
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
     * find if string contains all unique characters, double for loop, O(n^2) complexity
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
}
