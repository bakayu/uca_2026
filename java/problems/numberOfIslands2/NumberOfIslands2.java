import java.util.ArrayList;
import java.util.List;

/*
 * Given n and m, denoting the number of rows and columns in a 2D matrix, and
 * an array A of size k denoting the number of operations, matrix elements are
 * 0 for water and 1 for land. Initially, every element is 0, meaning there is
 * no land in the matrix.
 *
 * The array contains k operators. Each operator has two integers, A[i][0] and
 * A[i][1], meaning that cell matrix[A[i][0]][A[i][1]] changes from sea to
 * island. Return the number of islands in the matrix after each operation.
 *
 * The directions to check for an island are up, down, right, and left. The
 * answer array will have size k.
 *
 * Example 1:
 * Input: n = 4, m = 5, k = 4,
 *        A = [[1,1], [0,1], [3,3], [3,4]]
 * Output: [1, 1, 2, 2]
 * Explanation: The following illustration represents the operations.
 *
 * Example 2:
 * Input: n = 4, m = 5, k = 12,
 *        A = [[0,0], [0,0], [1,1], [1,0], [0,1], [0,3], [1,3], [0,4],
 *             [3,2], [2,2], [1,2], [0,2]]
 * Output: [1, 1, 2, 1, 1, 2, 2, 2, 3, 3, 1, 1]
 * Explanation: Following the process from Example 1 produces this output.
 */

public class NumberOfIslands2 {
    public List<Integer> numOfIslands(int n, int m, int[][] A) {
        List<Integer> res = new ArrayList<>();
        // TODO: complete the code
        return res;
    }

    // TODO: write tests
    public static void main(String[] args) {

    }
}
