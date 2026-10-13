import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    private static int res = 0;

    public static int largestSubsequence(int[] arr) {
        backtrack(arr, -1, 0, 0);
        return res;
    }

    private static void backtrack(int[] nums, int curr, int index, int len) {
        if (index == nums.length) {
            if (len > 0 && curr > 0) {
                res = Math.max(res, len);
            }
            return;
        }
        backtrack(nums, curr & nums[index], index + 1, len + 1);
        backtrack(nums, curr, index + 1, len);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null)
            return;
        int n = Integer.parseInt(line.trim());
        int[] arr = new int[n];
        line = br.readLine();
        if (line != null) {
            StringTokenizer st = new StringTokenizer(line);
            for (int i = 0; i < n; i++) {
                if (st.hasMoreTokens()) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }
            }
        }
        System.out.println(largestSubsequence(arr));
    }
}
