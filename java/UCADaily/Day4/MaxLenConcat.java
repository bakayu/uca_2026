import java.io.*;
import java.util.*;

public class MaxLenConcat {
    public static int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        masks.add(0);
        int res = 0;

        for (String s : arr) {
            int mask = 0;
            boolean flag = false;

            for (char c : s.toCharArray()) {
                int bit = 1 << (c - 'a');
                if ((mask & bit) != 0) {
                    flag = true;
                    break;
                }
                mask |= bit;
            }
            if (flag)
                continue;

            int size = masks.size();
            for (int i = 0; i < size; i++) {
                int prev = masks.get(i);
                if ((prev & mask) == 0) {
                    int combined = prev | mask;
                    masks.add(combined);
                    res = Math.max(res, Integer.bitCount(combined));
                }
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        if (line == null || line.trim().isEmpty())
            return;
        int n = Integer.parseInt(line.trim());
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = reader.readLine();
            if (s != null) {
                arr.add(s.trim());
            }
        }
        System.out.println(maxLength(arr));
    }
}
