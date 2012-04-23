package spellpad.swing.autocomplete;

import java.util.Objects;

/**
 *
 * @author Jesse Allen
 */
public class TernarySearchTree implements Resetable {

    protected Node root = null;

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
        //make new variable instead. assigning new values to input variables is bad form.
        if (node == null) {
            node = new Node(s.charAt(position), false);
            if (root == null) {
                root = node;
            } else if (direction > 0) {
                old.setRightChild(node);
            } else if (direction < 0) {
                old.setLeftChild(node);
            } else {
                old.setMiddleChild(node);
            }
        }
        if (s.length() <= position) {
            return;
        }
        if (s.charAt(position) > node.getMyChar()) {
            node.increasePopularity();
            add(s, position, 1, node.getRightChild(), node);
        } else if (s.charAt(position) < node.getMyChar()) {
            node.increasePopularity();
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
        boolean isInTree = false;
        while (node != null) {
            int comparison = s.charAt(position) - node.getMyChar();
            if (comparison < 0) {
                node = node.getLeftChild();
            } else if (comparison > 0) {
                node = node.getRightChild();
            } else if (++position == s.length()) {
                isInTree = node.isWordEnd();
                node = null;
            } else {
                node = node.getMiddleChild();
            }
        }
        return isInTree;
    }

    public String search(String s) {

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
                    return findNearestWord(node);
                }
                node = node.getMiddleChild();
            }
        }
        return "";
    }

    private String findNearestWord(Node node) {
        StringBuilder suffix = new StringBuilder();
        while (node != null) {
            suffix.append(node.getMyChar());
            if (node.isWordEnd()) {
                return suffix.toString().substring(1);
            }
            node = node.getMiddleChild();
        }
        return "";
    }

    @Override
    public void reset() {
        root = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TernarySearchTree other = (TernarySearchTree) obj;
        if (!Objects.equals(this.root, other.root)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.root);
        return hash;


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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (this.myChar != other.myChar) {
            return false;
        }
        if (!Objects.equals(this.leftChild, other.leftChild)) {
            return false;
        }
        if (!Objects.equals(this.middleChild, other.middleChild)) {
            return false;
        }
        if (!Objects.equals(this.rightChild, other.rightChild)) {
            return false;
        }
        if (this.wordEnd != other.wordEnd) {
            return false;
        }
        if (this.popularity != other.popularity) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.myChar;
        hash = 53 * hash + Objects.hashCode(this.leftChild);
        hash = 53 * hash + Objects.hashCode(this.middleChild);
        hash = 53 * hash + Objects.hashCode(this.rightChild);
        hash = 53 * hash + (this.wordEnd ? 1 : 0);
        hash = 53 * hash + this.popularity;
        return hash;
    }
}
