package problems.minStack;

class MinStack {

    private Node top;

    class Node {

        int data;
        int min_data;
        Node next;

        Node(int data, int min_data) {
            this.data = data;
            this.min_data = min_data;
            this.next = null;
        }
    }

    public MinStack() {
        top = null;
    }

    public void push(int val) {
        if (top == null) {
            top = new Node(val, val);
            return;
        }

        int new_min = Math.min(val, top.min_data);
        Node new_node = new Node(val, new_min);

        new_node.next = top;
        top = new_node;
    }

    public void pop() {
        if (top == null) {
            return;
        }
        top = top.next;
    }

    public int top() {
        if (top == null) {
            throw new IllegalStateException("MinStack is empty.");
        }
        return top.data;
    }

    public int getMin() {
        if (top == null) {
            throw new IllegalStateException("MinStack is empty.");
        }
        return top.min_data;
    }

    public void main() {
        MinStack stack = new MinStack();
        stack.push(0);
        stack.push(5);
        stack.push(2);
        stack.push(-10);
        System.out.println(stack.top());
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }
}
