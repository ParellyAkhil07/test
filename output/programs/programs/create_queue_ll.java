class Node {
    Object data; // Data stored in the node
    Node next;   // Reference to the next node

    // Constructor to initialize the node with data
    public Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

// Queue class implemented using a linked list
class Queue {
    private Node head; // Head of the queue
    private Node tail; // Tail of the queue

    // Constructor to initialize an empty queue
    public Queue() {
        this.head = null;
        this.tail = null;
    }

    // Method to get a string representation of the queue
    @Override
    public String toString() {
        if (isEmpty()) {
            return "<Queue (empty)>";
        } else {
            return "<Queue " + head.data + ">";
        }
    }

    // Method to get the length of the queue
    public int length() {
        Node curr = head;
        int length = 0;

        while (curr != null) {
            length++;
            curr = curr.next;
        }

        return length;
    }

    // Method to add an item to the end of the queue
    public void enqueue(Object item) {
        Node newNode = new Node(item);

        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Method to remove and return the item from the front of the queue
    public Object dequeue() {
        if (head == null) {
            return null;
        } else {
            Object dequeued = head.data;
            head = head.next;
            return dequeued;
        }
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Method to return the first item in the queue without removing it
    public Object peek() {
        return head != null ? head.data : null;
    }

    // Method to print the items in the queue as a list
    public void printQueue() {
        Node curr = head;
        System.out.print("[");

        while (curr != null) {
            System.out.print(curr.data);
            curr = curr.next;
            if (curr != null) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }

    // Main method to test the Queue implementation
    public static void main(String[] args) {
        Queue q = new Queue();
        q.enqueue("buy flight");
        q.enqueue("pack");
        q.enqueue("enjoy vacation");

        q.printQueue(); // Output: [buy flight, pack, enjoy vacation]

        System.out.println(q.dequeue()); // Output: buy flight
        q.printQueue(); // Output: [pack, enjoy vacation]

        System.out.println(q.isEmpty()); // Output: false
        System.out.println(q.peek());    // Output: pack

        Queue q2 = new Queue();
        System.out.println(q2.isEmpty()); // Output: true
        q2.printQueue(); // Output: []
    }