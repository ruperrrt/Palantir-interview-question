package palantir;
import java.io.*;
import java.util.*;

/*
 * use DP to solve this problem. Start from the right corner and calculate the minimum accumulated time.
 * Store it into a matrix
 * The time complexity is O(mn). The space complexity is O(mn).
 */
public class Solution {
	public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
		
		//read the input data into a 2D matrix
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = reader.readLine();
		String [] parts = line.split(" ");
		int m = Integer.parseInt(parts[0]);
		int n = Integer.parseInt(parts[1]);
		int [][] input = new int [m][n];
		int [][] DP = new int [m][n];
		
		for (int i = 0; i < m; i++){
			line = reader.readLine();
			String[] data = line.split(" ");
			for (int j = 0; j < n; j++){
				input[i][j] = Integer.parseInt(data[j]);
			}
		}
		
		findMinDP(input,DP);
		
		//read location data
		line = reader.readLine();
		int num = Integer.parseInt(line);
		int [][] locations = new int[num][2];
		for (int i = 0; i < num; i++){
			line = reader.readLine();
			String[] data = line.split(" ");
			locations[i][0] = Integer.parseInt(data[0]);
			locations[i][1] = Integer.parseInt(data[1]);
		}
		
		//output
		for (int i = 0; i < num; i++){
			int x = locations[i][0];
			int y = locations[i][1];
			System.out.println(DP[x][y]);
		}
    }
	/*
	 * DP function. Calculate the DP matrix based on the input. Start from the right corner. 
	 */
	public static void findMinDP (int [][] input, int[][] DP){
		int rows = input.length;
		int cols = input[0].length;
		DP[rows - 1][cols - 1] = input[rows - 1][cols - 1];
		for (int i = rows -1; i >= 0; i--){
			for (int j = cols - 1; j >= 0; j--){
				//handle the boundary situation
				if (i == rows - 1 && j < cols - 1){
					DP[i][j] = DP[i][j + 1] + input[i][j];
				}else if (j == cols -1 && i < rows - 1){
					DP[i][j] = DP[i + 1][j] + input[i][j];
				}else if (i < rows - 1 && j < cols - 1){
					DP[i][j] = input[i][j] + Math.min(DP[i + 1][j], DP[i][j + 1]);
				}
			}
		}
	}
	
    public List<Integer> postorderTraversal(TreeNode root) {
    	if (root == null) return null;
    	Stack<TreeNode> stack = new Stack<TreeNode> ();
    	List<Integer> result = new ArrayList<Integer> ();
        while (root != null ) {
        	result.add(root.val);
        	stack.push(root);
        	root = root.right;
        }
        while (!stack.isEmpty()) {
        	TreeNode node = stack.peek().left;
        	stack.pop();
        	while (node != null) {
        		result.add(node.val);
        		stack.push(node);
        		node = node.right;
        	}
        }
        Collections.reverse(result);
        return result;
    }
    
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
        	fast = fast.next.next;
        	slow = slow.next;
        	if (slow.equals(fast)) {
        		return true;
        	}
        }
        return false;
    }
    
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (!fast.equals(slow)) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
    
    public int ladderLength(String start, String end, Set<String> dict) {
        if (dict.size() == 0) return 0;
        LinkedList<String> word_queue = new LinkedList<String> ();
        LinkedList<Integer> step_queue = new LinkedList<Integer> ();
        word_queue.add(start);
        step_queue.add(1);
        while (!word_queue.isEmpty()) {
            String node = word_queue.poll();
            int distance = step_queue.poll();
            if (node.equals(end)) {
                return distance;
            }

            for (int i = 0; i < node.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    String temp = node.substring(0, i) + c + node.substring(i+1);
                    if (dict.contains(temp)) {
                        word_queue.add(temp);
                        step_queue.add(distance + 1);
                        dict.remove(temp);
                    }
                }
            }
            
        }
        return 0;
    }
    
    private boolean isConnected (String a, Set<String> dict) {
        for (int i = 0; i < a.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String temp = a.substring(0, i) + c + a.substring(i+1);
                if (dict.contains(temp)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int [s.length()];
        for (int i = 0; i < s.length(); i++) {
        	
        }
    }

      
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
}