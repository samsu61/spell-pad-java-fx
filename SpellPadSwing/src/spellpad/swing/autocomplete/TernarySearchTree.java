package spellpad.swing.autocomplete;

import java.util.Objects;

/**
 *
 * @author Jesse Allen
 */
public class TernarySearchTree implements Resetable {

    private Node root = null;
    
    public void balance(){
        balance(root);
    }
    
    void balance(Node amIBalanced){
        root.updateHeight(root);
        rotate(amIBalanced);
    }
    
    private void rotate(Node unbalanced){
        int balance = unbalanced.getBalance();
        if(balance < -1){
            rotateLeft(unbalanced);
        }else if(balance > 1){
            rotateRight(unbalanced);
        }
    }
    
    private void rotateRight(Node unbalanced){
        int balance = unbalanced.getLeftChild().getBalance();
        if(balance < 0){
            //rotateleft left child
        }
        Node child = unbalanced.getLeftChild();
        Node children = child.getRightChild();
        Node parent = unbalanced.getParent();
        child.setParent(parent);
        if(parent != null){
            if(parent.getRightChild() == unbalanced){
                parent.setRightChild(child);
            }else{
                parent.setLeftChild(child);
            }
        }
        unbalanced.setLeftChild(children);
        if(children != null){
            children.setParent(unbalanced);
        }
        child.setRightChild(unbalanced);
        unbalanced.setParent(child);
        if(root == unbalanced)root = child;
    }
    
    private void rotateLeft(Node unbalanced){
        int balance = unbalanced.getRightChild().getBalance();
        if(balance > 0){
            rotateRight(unbalanced.getRightChild());
        }
        Node child = unbalanced.getRightChild();
        Node children = child.getLeftChild();
        Node parent = unbalanced.getParent();
        child.setParent(parent);
        if(parent != null){
            if(parent.getRightChild() == unbalanced) parent.setRightChild(child);
            else parent.setLeftChild(child);
        }
        unbalanced.setRightChild(children);
        if(children != null){
            children.setParent(unbalanced);
        }
        child.setLeftChild(unbalanced);
        unbalanced.setParent(child);
        if(root == unbalanced) root = child;
    }
    

    public void add(String s) throws IllegalArgumentException {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("String is null or empty");
        }
        if (s.length() < 4) {
            throw new IllegalArgumentException("String is shorter than length 4");
        }
        add(s, 0, 0, root, null);
        balance(root);
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
        if (n == null) {
            return -1;
        } else {
            return Math.max(updateHeight(n.getLeftChild()), updateHeight(n.getRightChild())) + 1;
        }
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
