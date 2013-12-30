package NewDataStructures;
import java.util.ArrayList;

public class TwoListsAndTheirSumDiff {
    private ArrayList<Integer> listOne;
    private ArrayList<Integer> listTwo;
    private int sumDiff;
    
    public TwoListsAndTheirSumDiff() {
        listOne = new ArrayList<Integer>();
        listTwo = new ArrayList<Integer>();
    }
    
    public int getSumDiff() {
        return this.sumDiff;
    }
    
    private void calculateSumDiff() {
        int listOneSum = 0;
        int listTwoSum = 0;
        for (int i = 0; i < listOne.size(); i++) {
            listOneSum += this.listOne.get(i);
        }
        for (int i = 0; i < listTwo.size(); i++) {
            listTwoSum += this.listTwo.get(i);
        }
        listOneSum = Math.abs(listOneSum);
        listTwoSum = Math.abs(listTwoSum);
        this.sumDiff = Math.abs(listOneSum - listTwoSum);
    }
    
    public ArrayList<Integer> getListOne() {
        return this.listOne;
    }
    
    public void setListOne(ArrayList<Integer> list) {
        this.listOne = new ArrayList<Integer>(list);
        calculateSumDiff();
    }
    
    public ArrayList<Integer> getListTwo() {
        return this.listTwo;
    }
    
    public void setListTwo(ArrayList<Integer> list) {
        this.listTwo = new ArrayList<Integer>(list);
        calculateSumDiff();
    }
}
