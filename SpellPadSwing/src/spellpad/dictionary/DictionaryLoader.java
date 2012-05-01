package spellpad.dictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.RefinedSoundex;
import spellpad.swing.autocomplete.TernarySearchTree;

/**
 *
 * @author Jesse Allen
 */
public class DictionaryLoader implements Runnable {

    private File dictionary = new File("dictionary.dat");
    private TernarySearchTree tree = new TernarySearchTree();
    private HashMap<String, List<String>> soundex = new HashMap<>();
    private boolean executionComplete = false;

    @Override
    public void run() {
        try (Scanner input = new Scanner(new FileInputStream(dictionary))) {
            DoubleMetaphone metaphone = new DoubleMetaphone();
            metaphone.setMaxCodeLen(6);
            while (input.hasNext()) {
                String text = input.nextLine().toLowerCase();
                String encoding = metaphone.encode(text);
                if (soundex.get(encoding) == null) {
                    List<String> words = new LinkedList<>();
                    words.add(text);
                    soundex.put(encoding, words);
                } else {
                    List<String> words = soundex.get(encoding);
                    words.add(text);
                }
                tree.add(text);
            }
        } catch (IOException ex) {
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

    public HashMap<String, List<String>> getSoundex() {
        return soundex;
    }
}
