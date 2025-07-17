class Node {

    int data; // Data stored in the node
    Node left; // Reference to the left child
    Node right; // Reference to the right child

    // Constructor to create a node with data and optional left/right nodes
    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    // Overloaded constructor for creating a node with only data
    public Node(int data) {
        this(data, null, null);
    }

    // Method to insert a new node with 'newData' into the BST
    public void insert(int newData) {
        if (newData < this.data) {
            // Insert into the left subtree
            if (this.left == null) {
                this.left = new Node(newData);
            } else {
                this.left.insert(newData);
            }
        } else {
            // Insert into the right subtree
            if (this.right == null) {
                this.right = new Node(newData);
            } else {
                this.right.insert(newData);
            }
        }
    }

    // Main method to test the Node class
    public static void main(String[] args) {
        // Create a sample tree
        Node t = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(7, new Node(5), new Node(8)));

        // Insert new nodes and test the structure
        t.insert(0);
        assert t.left.left.left.data == 0;
        assert t.left.left.right == null;

        t.insert(9);
        assert t.right.right.right.data == 9;
        assert t.right.right.left == null;

        t.insert(6);
        assert t.right.left.right.data == 6;
        assert t.right.left.left == null;

        System.out.println("ALL TESTS PASSED!");
    }