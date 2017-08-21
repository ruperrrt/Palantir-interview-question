package palantir;

import java.util.*;

/*
 * Time Complexity O(N), Space Complexity O(N)
 * Use an array to record the sum of the left elements to the index.
 * Calculate the total sum of the Array.
 * If we find the pivot, it should meet the condition leftSum[i] * 2 + A[i] = total sum.
 */
public class Solution1 {
	
	public static int findPivot(int[] A){
		if (A == null || A.length <= 1) return -1;
		int [] leftSum = new int [A.length];
		leftSum[0] = 0;
		int totalSum = 0;
		for (int i = 0; i < A.length; i++){
			if (i > 0){
				leftSum[i] = leftSum[i - 1] + A[i - 1];
			}
			totalSum += A[i];
		}
		for (int i = 0; i < A.length; i++){
			if (2 * leftSum[i] + A[i] == totalSum){
				return i;
			}
		}
		return -1;
	}
	public static int findPivot1(int[] A){
		if (A == null || A.length <= 1) return -1;
		int rightSum = 0;
		for (int i = 0; i < A.length; i++){
			rightSum += A[i];
		}
		int leftSum = 0;
		for (int i = 0; i < A.length; i++){
			rightSum -= A[i];
			if (leftSum == rightSum) return i;
			leftSum += A[i];
		}
		return -1;
	}
	public static void main(String[] args){
		int [] A = {1,4,6,3,2};
		System.out.println(findPivot1(A));
	}
}
