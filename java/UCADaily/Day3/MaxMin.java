import java.io.*;

public class MaxMin {
    public static int maximizeMinimum(int R, int C, int[][] matrix) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            for (int j = i + 1; j < R; j++) {
                int[] row1 = matrix[i];
                int[] row2 = matrix[j];

                int temp = Integer.MAX_VALUE;
                for (int k = 0; k < C; k++) {
                    int max = Math.max(row1[k], row2[k]);
                    temp = Math.min(temp, max);
                }
                res = Math.max(temp, res);
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null)
            return;
        String[] parts = line.trim().split("\\s+");
        int R = Integer.parseInt(parts[0]);
        int C = Integer.parseInt(parts[1]);
        int[][] matrix = new int[R][C];
        for (int i = 0; i < R; i++) {
            line = br.readLine();
            if (line == null)
                break;
            parts = line.trim().split("\\s+");
            for (int j = 0; j < C; j++) {
                matrix[i][j] = Integer.parseInt(parts[j]);
            }
        }
        System.out.println(maximizeMinimum(R, C, matrix));
    }
}
