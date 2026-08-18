import java.util.*;

/*
 * Problem: Smallest Range Covering Elements from K Lists
 *
 * You are given k lists of sorted integers in non-decreasing order. Find the
 * smallest range [a, b] such that at least one number from each of the k
 * lists is included in that range.
 *
 * We define range [a, b] to be smaller than range [c, d] if b - a < d - c,
 * or if b - a == d - c and a < c.
 *
 * Example 1:
 *   Input:  nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 *   Output: [20, 24]
 *   Explanation:
 *     List 1: [4, 10, 15, 24, 26], 24 is in range [20,24].
 *     List 2: [0, 9, 12, 20], 20 is in range [20,24].
 *     List 3: [5, 18, 22, 30], 22 is in range [20,24].
 *
 * Example 2:
 *   Input:  nums = [[1,2,3], [1,2,3], [1,2,3]]
 *   Output: [1, 1]
 */

public class smallestRangeSolution {

    public int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];
        int minRange = Integer.MAX_VALUE;
        // [value, value_index, list_index]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int currMax = 0;
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            pq.offer(new int[] { val, 0, i });
            currMax = Math.max(currMax, val);
        }

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currVal = curr[0], valueIndex = curr[1], listIndex = curr[2];
            if (currMax - currVal < minRange) {
                minRange = currMax - currVal;
                res[0] = currVal;
                res[1] = currMax;
            }

            if (valueIndex + 1 == nums.get(listIndex).size()) {
                return res;
            }

            int newVal = nums.get(listIndex).get(valueIndex + 1);
            currMax = Math.max(currMax, newVal);
            pq.offer(new int[] { newVal, valueIndex + 1, listIndex });
        }

        return res;
    }

    public static void main(String[] args) {
        smallestRangeSolution sol = new smallestRangeSolution();

        // Test case 1
        List<List<Integer>> input1 = List.of(
                List.of(4, 10, 15, 24, 26),
                List.of(0, 9, 12, 20),
                List.of(5, 18, 22, 30));
        int[] expected1 = { 20, 24 };
        int[] actual1 = sol.smallestRange(input1);
        System.out.println("test case 1: " + (Arrays.equals(expected1, actual1) ? "PASS" : "FAIL"));

        // Test case 2
        List<List<Integer>> input2 = List.of(
                List.of(1, 2, 3),
                List.of(1, 2, 3),
                List.of(1, 2, 3));
        int[] expected2 = { 1, 1 };
        int[] actual2 = sol.smallestRange(input2);
        System.out.println("test case 2: " + (Arrays.equals(expected2, actual2) ? "PASS" : "FAIL"));
    }
}
