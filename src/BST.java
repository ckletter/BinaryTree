import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Your Name Here
 * @version: Date
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        // Calls recursive method tryNode and returns true or false
        return tryNode(val, root);
    }

    /**
     * Recursively searches for a value in the tree
     * Each recursive call to the method tries a new node and checks if its value is equal to the search value
     * @param target
     * @param currentNode
     * @return true if val is in tree, false ortherwise
     */
    public boolean tryNode(int target, BSTNode currentNode) {
        // Gets val of the current node
        int nodeVal = currentNode.getVal();
        // Returns true if val is found
        if (target == nodeVal) {
            return true;
        }
        BSTNode nodeLeft = currentNode.getLeft();
        BSTNode nodeRight = currentNode.getRight();
        // If the current node has no descendants and the value isn't found, returns false
        if (nodeLeft == null && nodeRight == null) {
            return false;
        }
        // Makes the new current node the node to the left if the value is less than the value of the current node
        if (target < nodeVal) {
            return tryNode(target, nodeLeft);
        }
        // Makes the new current node the node to the right if the value is greater than the value of the current node
        return tryNode(target, nodeRight);
    }
    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        // Makes an array list for the order the nodes are traversed
        ArrayList<BSTNode> nodeOrder = new ArrayList<BSTNode>();
        // Calls the recursive method tryInorder to traverse through the node tree
        return tryInorder(root, nodeOrder);
    }

    /**
     * Recursive method to iterate through each node in tree via inorder
     * @param currentNode
     * @param nodeList
     * @returnan ArrayList of BSTNodes in same order iterated
     */
    public ArrayList<BSTNode> tryInorder(BSTNode currentNode, ArrayList<BSTNode> nodeList) {
        BSTNode nodeLeft = currentNode.getLeft();
        BSTNode nodeRight = currentNode.getRight();
        // Keeps going left until there are no more nodes left anymore
        if (nodeLeft != null) {
            tryInorder(nodeLeft, nodeList);
        }
        // Adds the root of the branch once fully traversed left
        nodeList.add(currentNode);
        // After going left, goes right
        if (nodeRight != null) {
            tryInorder(nodeRight, nodeList);
        }
        return nodeList;
    }
    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() {
        // Makes an array list for the order the nodes are traversed
        ArrayList<BSTNode> nodeOrder = new ArrayList<BSTNode>();
        // Calls the recursive method tryPreorder to traverse through the node tree
        return tryPreorder(root, nodeOrder);
    }
    /**
     * Recursive method to iterate through each node in tree via preorder
     * @param currentNode
     * @param nodeList
     * @returnan ArrayList of BSTNodes in same order iterated
     */
    public ArrayList<BSTNode> tryPreorder(BSTNode currentNode, ArrayList<BSTNode> nodeList) {
        BSTNode nodeLeft = currentNode.getLeft();
        BSTNode nodeRight = currentNode.getRight();
        // Adds each root node first as it traverses down
        nodeList.add(currentNode);
        // Keeps going left until there are no more nodes left anymore
        if (nodeLeft != null) {
            tryPreorder(nodeLeft, nodeList);
        }
        // After going left, goes right
        if (nodeRight != null) {
            tryPreorder(nodeRight, nodeList);
        }
        return nodeList;
    }
    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        // Makes an array list for the order the nodes are traversed
        ArrayList<BSTNode> nodeOrder = new ArrayList<BSTNode>();
        // Calls the recursive method tryPostorder to traverse through the node tree
        return tryPostorder(root, nodeOrder);
    }
    /**
     * Recursive method to iterate through each node in tree via postorder
     * @param currentNode
     * @param nodeList
     * @returnan ArrayList of BSTNodes in same order iterated
     */
    public ArrayList<BSTNode> tryPostorder(BSTNode currentNode, ArrayList<BSTNode> nodeList) {
        BSTNode nodeLeft = currentNode.getLeft();
        BSTNode nodeRight = currentNode.getRight();
        // Keeps going left until there are no more nodes left anymore
        if (nodeLeft != null) {
            tryPostorder(nodeLeft, nodeList);
        }
        // Once it is no longer possible to go left, goes right
        if (nodeRight != null) {
            tryPostorder(nodeRight, nodeList);
        }
        // Finally adds the root once all to the left and right nodes of a branch have been added
        nodeList.add(currentNode);
        return nodeList;
    }
    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        // Call to recursive method
        tryBranch(root, val);
    }
    /**
     * Recursive method to iterate down a node tree until insertion spot is reached
     * Inserts node either left or right depending on value inputted
     * @param currentNode
     * @param val
     * @return void
     */
    public void tryBranch(BSTNode currentNode, int val) {
        BSTNode nodeLeft = currentNode.getLeft();
        BSTNode nodeRight = currentNode.getRight();
        int currentVal = currentNode.getVal();
        if (val < currentVal) {
            // If the value being inserted is less than the node value and there are no more descendants left, inserts
            if (nodeLeft == null) {
                BSTNode newNode = new BSTNode(val);
                currentNode.setLeft(newNode);
                return;
            }
            // If more descendants below, keeps recursively iterating down tree in correct path
            tryBranch(nodeLeft, val);
        }
        // If the value being inserted is greater than the node value and there are no more descendants right, inserts
        if (val > currentVal) {
            if (nodeRight == null) {
                BSTNode newNode = new BSTNode(val);
                currentNode.setRight(newNode);
                return;
            }
            // If more descendants below, keeps recursively iterating down tree in correct path
            tryBranch(nodeRight, val);
        }
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // TODO: Optional Challenge!
        return false;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
