package spellpad.dictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import spellpad.swing.autocomplete.TernarySearchTree;
import spellpad.swing.autocomplete.WordCountCache;

/**
 *
 * @author Jesse Allen
 */
public class DictionaryLoader implements Runnable {

    private File dictionary = new File("dictionary.dat");
    private TernarySearchTree tree = new TernarySearchTree();
    private boolean executionComplete = false;

    @Override
    public void run() {
        try(Scanner input = new Scanner(new FileInputStream(dictionary))){
            while(input.hasNext()){
                tree.add(input.nextLine().toLowerCase());
            }
        }catch(IOException ex){
            Logger.getLogger(DictionaryLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        executionComplete = true;
    }

    public boolean isExecutionComplete() {
        return executionComplete;
    }

    public TernarySearchTree getTree() {
        return tree;
    }
    
    
}
