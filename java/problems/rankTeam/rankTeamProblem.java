
public class rankTeamProblem {
    public String rankTeams(String[] votes) {
        int n = votes[0].length();
        int[][] freq = new int[26][n];

        for (String vote : votes) {
            for (int i = 0; i < n; i++) {
                freq[vote.charAt(i) - 'A'][i]++;
            }
        }

        Character[] teams = new Character[n];
        for (int i = 0; i < n; i++) {
            teams[i] = votes[0].charAt(i);
        }

        mergeSort(teams, 0, teams.length - 1, freq, n);

        StringBuilder sb = new StringBuilder();
        for (char ch : teams)
            sb.append(ch);
        return sb.toString();
    }

    private void mergeSort(Character[] teams, int left, int right, int[][] freq, int n) {
        if (left >= right)
            return;

        int mid = (left + right) / 2;
        mergeSort(teams, left, mid, freq, n);
        mergeSort(teams, mid + 1, right, freq, n);

        merge(teams, left, mid, right, freq, n);
    }

    private void merge(Character[] teams, int left, int mid, int right, int[][] freq, int n) {
        char[] temp = new char[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            int indexA = teams[i] - 'A';
            int indexB = teams[j] - 'A';

            boolean flag = false;
            for (int idx = 0; idx < n; idx++) {
                if (freq[indexA][idx] != freq[indexB][idx]) {
                    flag = freq[indexA][idx] > freq[indexB][idx];
                    break;
                }
                if (idx == n - 1) {
                    flag = teams[i] < teams[j];
                }
            }

            if (flag) {
                temp[k++] = teams[i++];
            } else {
                temp[k++] = teams[j++];
            }
        }

        while (i <= mid)
            temp[k++] = teams[i++];
        while (j <= right)
            temp[k++] = teams[j++];

        for (int idx = 0; idx < right - left + 1; idx++) {
            teams[left + idx] = temp[idx];
        }
    }

    public void main() {
        rankTeamProblem sol = new rankTeamProblem();

        String[] test_case_1 = new String[] { "ABC", "ACB", "ABC", "ACB", "ACB" };
        String expected_1 = "ACB";
        System.out.println("test case 1: " + (expected_1.equals(sol.rankTeams(test_case_1)) ? "PASS" : "FAIL"));

        String[] test_case_2 = new String[] { "WXYZ", "XYZW" };
        String expected_2 = "XWYZ";
        System.out.println("test case 2: " + (expected_2.equals(sol.rankTeams(test_case_2)) ? "PASS" : "FAIL"));
    }
}
