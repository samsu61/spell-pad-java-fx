package spellpad.swing.autocomplete;

/**
 *
 * @author Jesse Allen
 */
public class DictionarTree extends TernarySearchTree {

    public void balance() {
        Node node = root;

    }

    protected int height(Node node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1;
        }
    }
}

class DictionarNode extends Node{

    int height = 0;
    int balance = 0;
    
    public DictionarNode(char myChar, boolean wordEnd) {
        super(myChar, wordEnd);
    }
    
    protected void updateHeight(){
        height = height(this);
        updateBalance();
    }
    
    protected int height(Node node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1;
        }
    }
    
    public void updateBalance(){
        balance = height(getLeftChild()) - height(getRightChild());
    }
    
    
    
}
