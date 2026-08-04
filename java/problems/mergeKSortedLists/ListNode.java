class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return helper(lists, 0, lists.length - 1);
    }

    private ListNode helper(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }

        int mid = left + (right - left) / 2;
        ListNode list1 = helper(lists, left, mid);
        ListNode list2 = helper(lists, mid + 1, right);
        return merge(list1, list2);
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode();
        ListNode res = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                dummy.next = a;
                a = a.next;
            } else {
                dummy.next = b;
                b = b.next;
            }
            dummy = dummy.next;
        }

        if (a != null) {
            dummy.next = a;
        }
        if (b != null) {
            dummy.next = b;
        }

        return res.next;
    }

    public void main(String[] args) {
        ListNode[] lists = new ListNode[3];

        lists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
        lists[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
        lists[2] = new ListNode(2, new ListNode(6));

        ListNode solution = new ListNode();
        ListNode result = solution.mergeKLists(lists);

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
