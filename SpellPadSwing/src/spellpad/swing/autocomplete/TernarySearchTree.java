package spellpad.swing.autocomplete;

/**
 *
 * @author Jesse Allen
 */
public class TernarySearchTree {

    private Node root = null;
    
    public void add(String s) throws IllegalArgumentException {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("String is null or empty");
        }
        if (s.length() < 4) {
            throw new IllegalArgumentException("String is shorter than length 4");
        }
        add(s, 0, 0, root, null);
    }

    private void add(String s, int position, int direction, Node node, Node old) {
        if (node == null) {
            node = new Node(s.charAt(position), false);
            if (root == null) {
                root = node;
            } else
            if(direction == 1){
                old.setRightChild(node);
            } else if(direction == -1){
                old.setLeftChild(node);
            } else {
                old.setMiddleChild(node);
            }
        }
        if(s.length() <= position) return;
        if (s.charAt(position) > node.getMyChar()) {
            
            add(s, position, 1, node.getRightChild(), node);
        } else if (s.charAt(position) < node.getMyChar()) {
            
            add(s, position, -1, node.getLeftChild(), node);
        } else {
            node.increasePopularity();
            if (position + 1 == s.length()) {
                node.setWordEnd(true);
            } else {
                add(s, position + 1, 0, node.getMiddleChild(), node);
            }
        }
    }

    public boolean contains(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("String is null or empty");
        }
        int position = 0;
        Node node = root;
        while (node != null) {
            int comparison = s.charAt(position) - node.getMyChar();
            if (comparison < 0) {
                node = node.getLeftChild();
            } else if (comparison > 0) {
                node = node.getRightChild();
            } else {
                if (++position == s.length()) {
                    return node.isWordEnd();
                }
                node = node.getMiddleChild();
            }
        }
        return false;
    }
}

class Node {

    private char myChar;
    private Node leftChild, middleChild, rightChild;
    private boolean wordEnd;
    private int popularity;

    public Node(char myChar, boolean wordEnd) {
        this.myChar = myChar;
        this.wordEnd = wordEnd;
    }

    void increasePopularity() {
        popularity++;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getMiddleChild() {
        return middleChild;
    }

    public void setMiddleChild(Node middleChild) {
        this.middleChild = middleChild;
    }

    public char getMyChar() {
        return myChar;
    }

    public void setMyChar(char myChar) {
        this.myChar = myChar;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isWordEnd() {
        return wordEnd;
    }

    public void setWordEnd(boolean wordEnd) {
        this.wordEnd = wordEnd;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
