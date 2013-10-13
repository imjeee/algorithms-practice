import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Tester {
	public static void main(String[] args){
		String[] words = {"aba", "aab", "baa", "ca", "ac"};
		groupThem(words);
		//System.out.println("Hello world!");
	}
	
	public static HashMap<String, ArrayList<String>> groupThem(String[] source){
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		for (String s : source){
			char[] stringArray = s.toCharArray();
			Arrays.sort(stringArray);
			String key = new String(stringArray);
			if (result.containsKey(key)){
				result.get(key).add(s);
			} else {
				ArrayList<String> newVal = new ArrayList<String>();
				newVal.add(s);
				result.put(key, newVal);
			}
		}
		
		// test
		for (ArrayList<String> al : result.values()){
			for (String s : al){
				System.out.print(s + " ");
			}
			System.out.println();
		}
		
		return result;
	}
}
