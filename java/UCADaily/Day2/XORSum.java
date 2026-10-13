public class XORSum {
    private long MOD = 1000000007;
    private int res = 0;

    public int solve(int n, int[] a) {
        backtrack(a, 0, 0);
        return res;
    }

    private void backtrack(int[] nums, int curr, int index) {
        if (index == nums.length) {
            res = (int) (((long) res + curr) % MOD);
            return;
        }

        curr ^= nums[index];
        backtrack(nums, curr, index + 1);
        curr ^= nums[index];
        backtrack(nums, curr, index + 1);
    }
}
