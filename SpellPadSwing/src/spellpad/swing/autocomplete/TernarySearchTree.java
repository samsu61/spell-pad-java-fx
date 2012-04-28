package spellpad.swing.autocomplete;

import java.util.Objects;

/**
 *
 * @author Jesse Allen
 */
public class TernarySearchTree implements Resetable {

    private Node root = null;

    private void balance() {
        balance(root);
    }

    void balance(Node amIBalanced) {
        rotate(amIBalanced);
    }

    private void rotate(Node unbalanced) {
        int balance = unbalanced.getBalance();
        if (balance < -1) {
            rotateLeft(unbalanced);
        } else if (balance > 1) {
            rotateRight(unbalanced);
        }
        if (unbalanced.getMiddleChild() != null) {
            balance(unbalanced.getMiddleChild());
        }

    }

    private void rotateRight(Node unbalanced) {
        int balance = unbalanced.getLeftChild().getBalance();
        if (balance < 0) {
            //rotateleft left child
        }
        Node child = unbalanced.getLeftChild();
        Node children = child.getRightChild();
        Node parent = unbalanced.getParent();
        child.setParent(parent);
        if (parent != null) {
            if (parent.getRightChild() == unbalanced) {
                parent.setRightChild(child);
            } else {
                parent.setLeftChild(child);
            }
        }
        unbalanced.setLeftChild(children);
        if (children != null) {
            children.setParent(unbalanced);
        }
        child.setRightChild(unbalanced);
        unbalanced.setParent(child);
        if (root == unbalanced) {
            root = child;
        }
    }

    private void rotateLeft(Node unbalanced) {
        int balance = unbalanced.getRightChild().getBalance();
        if (balance > 0) {
            rotateRight(unbalanced.getRightChild());
        }
        Node child = unbalanced.getRightChild();
        Node children = child.getLeftChild();
        Node parent = unbalanced.getParent();
        child.setParent(parent);
        if (parent != null) {
            if (parent.getRightChild() == unbalanced) {
                parent.setRightChild(child);
            } else {
                parent.setLeftChild(child);
            }
        }
        unbalanced.setRightChild(children);
        if (children != null) {
            children.setParent(unbalanced);
        }
        child.setLeftChild(unbalanced);
        unbalanced.setParent(child);
        if (root == unbalanced) {
            root = child;
        }
    }

    public void add(String s) throws IllegalArgumentException {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("String is null or empty");
        }
//        if (s.length() < 4) {
//            throw new IllegalArgumentException("String is shorter than length 4");
//        }
        add(s, 0, 0, root, null);
        //balance();
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
            node.setParent(old);
        }
        if (s.length() <= position) {
            return;
        }
        node.increasePopularity();
        if (s.charAt(position) > node.getMyChar()) {
            add(s, position, 1, node.getRightChild(), node);
        } else if (s.charAt(position) < node.getMyChar()) {
            add(s, position, -1, node.getLeftChild(), node);
        } else {
            if (position + 1 == s.length()) {
                node.setWordEnd(true);
            } else {
                add(s, position + 1, 0, node.getMiddleChild(), node);
            }
        }
    }

    private Node noneAreNull(Node right, Node left, Node center, Node node, StringBuilder suffix) {
        //None are null
        int r = right.getPopularity();
        int l = left.getPopularity();
        int c = center.getPopularity();
        if (r > l) {
            if (r > c) {
                node = right;
            } else {
                node = center;
            }
        } else if (l > r) {
            if (l > c) {
                node = left;
            } else {
                node = center;
            }
        }
        return node;
    }

    private Node oneIsNull(Node center, Node left, Node node, Node right, StringBuilder suffix) {
        if (center == null && right == null && left == null) {
            node = null;
        } else if (center == null) {
            if (left == null) {
                node = right;
            } else if (right == null) {
                node = left;
            } else {
                int r = right.getPopularity();
                int l = left.getPopularity();
                if (r > l) {
                    node = right;
                } else {
                    node = left;
                }
            }
        } else {
            if (right == null) {
                if (left == null) {
                    node = center;
                } else {
                    int l = left.getPopularity();
                    int c = center.getPopularity();
                    if (l > c) {
                        node = left;
                    } else {
                        node = center;
                    }
                }
            } else/*
             * left is null
             */ {
                if (right == null) {
                    node = center;
                } else {
                    int r = right.getPopularity();
                    int c = center.getPopularity();
                    if (r > c) {
                        node = right;
                    } else {
                        node = center;
                    }
                }
            }
        }
        return node;
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
                    if (node.isWordEnd()) {
                        return "";
                    }
                    return findNearestWord(node);
                }
                node = node.getMiddleChild();
            }
        }
        return "";
    }

    private String findNearestWord(Node n) {
        StringBuilder suffix = new StringBuilder();
        Node right;
        Node left;
        Node center;
        Node node = n.getMiddleChild();
        while (node != null) {
            if (node.isWordEnd()) {
                suffix.append(node.getMyChar());
                node = null;
                continue;
            }
            suffix.append(node.getMyChar());
            right = node.getRightChild();
            left = node.getLeftChild();
            center = node.getMiddleChild();
            if (right == null || left == null || center == null) {
                node = oneIsNull(center, left, node, right, suffix);
            } else {
                node = noneAreNull(right, left, center, node, suffix);
            }
            if (node != center) {
                suffix.setCharAt(suffix.length() - 1, node.getMyChar());
                suffix.setLength(suffix.length() - 1);
            }
            System.out.println(suffix.length());
        }
        if (suffix.length() == 0) {
            node = n.getMiddleChild();
            while (node != null) {
                if (node.isWordEnd()) {
                    suffix.append(node.getMyChar());
                    node = null;
                } else {
                    suffix.append(node.getMyChar());
                    node = node.getMiddleChild();
                }

            }
        }
        System.out.println("-" + suffix.length());
        return suffix.toString();
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
    private Node leftChild, middleChild, rightChild, parent;
    private boolean wordEnd;
    private int popularity;
    int height;

    public Node(char myChar, boolean wordEnd) {
        this.myChar = myChar;
        this.wordEnd = wordEnd;
        height = 0;
    }

    int updateHeight(Node n) {
        int height;
        if (n == null) {
            height = -1;
        } else {
            int heightLeft = updateHeight(n.getLeftChild());
            int heightRight = updateHeight(n.getRightChild());
            height = Math.max(heightLeft, heightRight) + 1;
            n.height = height;
        }
        return height;
    }

    int getBalance() {
        return updateHeight(leftChild) - updateHeight(rightChild);
    }

    boolean hasLeft() {
        return leftChild != null;
    }

    boolean hasRight() {
        return rightChild != null;
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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
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
