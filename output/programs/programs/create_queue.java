import java.util.List;

public class Queue {
    // List to store queue items
    private List<String> items;

    // Constructor to initialize the queue
    public Queue() {
        this.items = new ArrayList<>();
    }

    // Method to represent the queue as a string
    @Override
    public String toString() {
        if (this.length() == 0) {
            return "<Queue (empty)>";
        } else {
            return "<Queue " + this.items + ">";
        }
    }

    // Method to get the length of the queue
    public int length() {
        return this.items.size();
    }

    // Method to remove an item from the front of the queue
    public String dequeue() {
        if (this.length() > 0) {
            return this.items.remove(0);
        } else {
            throw new IndexOutOfBoundsException("queue is empty.");
        }
    }

    // Method to add an item to the end of the queue
    public void enqueue(String item) {
        this.items.add(item);
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    // Method to peek at the first item in the queue without removing it
    public String peek() {
        return this.items.get(0);
    }

    // Main method to run tests
    public static void main(String[] args) {
        Queue q = new Queue();
        q.enqueue("buy flight");
        q.enqueue("pack");
        q.enqueue("enjoy vacation");

        assert q.toString().equals("<Queue [buy flight, pack, enjoy vacation]>");
        assert q.length() == 3;
        assert q.peek().equals("buy flight");
        assert q.dequeue().equals("buy flight");
        assert q.length() == 2;
        assert !q.isEmpty();

        System.out.println("ALL TESTS PASSED!");
    }