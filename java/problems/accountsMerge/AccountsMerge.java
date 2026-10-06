import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/*
 * Problem: Accounts Merge
 *
 * Given a list of accounts where each element accounts[i] is a list of
 * strings, where the first element accounts[i][0] is a name, and the rest of
 * the elements are emails representing emails of the account.
 *
 * We would like to merge these accounts. Two accounts definitely belong to
 * the same person if there is some common email to both accounts. Note that
 * even if two accounts have the same name, they may belong to different people
 * as people could have the same name. A person could have any number of
 * accounts initially, but all of their accounts definitely have the same
 * name.
 *
 * After merging the accounts, return the accounts in the following format:
 * the first element of each account is the name, and the rest of the elements
 * are emails in sorted order. The accounts themselves can be returned in any
 * order.
 *
 * Solve it using the DSU data structure only.
 *
 * Sample input:
 * [["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 *  ["John", "johnsmith@mail.com", "john00@mail.com"],
 *  ["Mary", "mary@mail.com"],
 *  ["John", "johnnybravo@mail.com"]]
 * Sample output:
 * [["John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"],
 *  ["Mary", "mary@mail.com"],
 *  ["John", "johnnybravo@mail.com"]]
 * Explanation: The first and second John's accounts merge because they share
 * johnsmith@mail.com. Mary and the third John are separate because none of
 * their emails are used by another account. The output accounts may be in any
 * order.
 */

class DSU {
    private int[] parent, size;

    public DSU(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int node) {
        if (node != parent[node]) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    public boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if (pu == pv)
            return false;
        if (size[pu] >= size[pv]) {
            size[pu] += size[pv];
            parent[pv] = pu;
        } else {
            size[pv] += size[pu];
            parent[pu] = pv;
        }
        return true;
    }
}

public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();
        DSU dsu = new DSU(accounts.size());

        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                Integer prevIndex = map.get(email);

                if (prevIndex == null) {
                    map.put(email, i);
                } else {
                    dsu.union(i, prevIndex);
                }
            }
        }

        Map<Integer, TreeSet<String>> emailsByRoot = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String email = entry.getKey();
            int accountIndex = entry.getValue();
            int root = dsu.find(accountIndex);

            TreeSet<String> rootEmails = emailsByRoot.get(root);
            if (rootEmails == null) {
                rootEmails = new TreeSet<>();
                emailsByRoot.put(root, rootEmails);
            }
            rootEmails.add(email);
        }

        for (Map.Entry<Integer, TreeSet<String>> entry : emailsByRoot.entrySet()) {
            int root = entry.getKey();
            TreeSet<String> sortedEmails = entry.getValue();
            List<String> mergedAccounts = new ArrayList<>();
            mergedAccounts.add(accounts.get(root).get(0));
            mergedAccounts.addAll(sortedEmails);
            res.add(mergedAccounts);
        }

        return res;
    }

    public static void main(String[] args) {
        List<List<String>> sampleAccounts = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com"));

        AccountsMerge solution = new AccountsMerge();
        System.out.println("Sample input:  " + sampleAccounts);
        System.out.println("Sample output: " + solution.accountsMerge(sampleAccounts));
    }

}
