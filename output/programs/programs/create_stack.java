import java.util.List;

public class Stack {
    // LIFO stack implemented using an ArrayList
    private List<Integer> items;
    private List<Integer> minStack;

    public Stack() {
        // Initialize the stack and minStack
        items = new ArrayList<>();
        minStack = new ArrayList<>();
    }

    @Override
    public String toString() {
        // Return a string representation of the stack
        if (items.isEmpty()) {
            return "<Stack (empty)>";
        } else {
            return "<Stack tail=" + items.get(items.size() - 1) + " length=" + items.size() + ">";
        }
    }

    public void push(int item) {
        // Add item to the end of the stack
        items.add(item);

        if (minStack.isEmpty() || minStack.get(minStack.size() - 1) > item) {
            minStack.add(item);
        } else {
            minStack.add(minStack.get(minStack.size() - 1));
        }
    }

    public Integer pop() {
        // Remove item from the end of the stack and return it
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("pop from empty list");
        }

        minStack.remove(minStack.size() - 1);
        return items.remove(items.size() - 1);
    }

    public int length() {
        // Return the length of the stack
        return items.size();
    }

    public void empty() {
        // Empty the stack
        items.clear();
        minStack.clear();
    }

    public boolean isEmpty() {
        // Check if the stack is empty
        return items.isEmpty();
    }

    public Integer findMin() {
        // Return the minimum value of the stack
        if (!isEmpty()) {
            return minStack.get(minStack.size() - 1);
        }
        return null;
    }

    public static void main(String[] args) {
        // Simple test cases
        Stack s = new Stack();
        s.push(2);
        s.push(1);
        s.push(3);
        s.push(-1);
        System.out.println(s.findMin()); // Should print -1

        Stack s2 = new Stack();
        s2.push(2);
        s2.push(1);
        s2.push(3);
        System.out.println(s2.findMin()); // Should print 1

        Stack s3 = new Stack();
        s3.push(2);
        s3.push(1);
        s3.push(3);
        s3.push(3);
        s3.push(1);
        System.out.println(s3.findMin()); // Should print 1
        s3.pop();
        System.out.println(s3.findMin()); // Should print 1
        s3.pop();
        s3.pop();
        s3.pop();
        System.out.println(s3.findMin()); // Should print 2
    }