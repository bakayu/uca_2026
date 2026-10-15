import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PartitionBasedMin {
    public static long minPartitionScore(String s) {
        int[] lastPos = new int[26];
        int n = s.length();
        long res = 0;
        for (int i = 0; i < n; i++) {
            Character ch = s.charAt(i);
            lastPos[ch - 'a'] = i;
        }

        long start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            Character ch = s.charAt(i);
            end = Math.max(end, lastPos[ch - 'a']);
            if (i == end) {
                res += (long) Math.pow((end - start + 1), 2);
                start = i + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        if (s != null) {
            s = s.trim();
            if (!s.isEmpty()) {
                System.out.println(minPartitionScore(s));
            }
        }
    }
}
