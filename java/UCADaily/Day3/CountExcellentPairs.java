import java.io.*;
import java.util.*;

public class CountExcellentPairs {
    public static long countExcellentPairs(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        List<Integer> arr = new ArrayList<>(set);
        int n = arr.size();
        long res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num1 = arr.get(i);
                int num2 = arr.get(j);

                if (Integer.bitCount(num1) + Integer.bitCount(num2) >= k) {
                    res++;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null)
            return;
        int n = Integer.parseInt(line.trim());
        int[] nums = new int[n];
        String[] parts = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }
        int k = Integer.parseInt(br.readLine().trim());
        System.out.println(countExcellentPairs(nums, k));
    }
}
