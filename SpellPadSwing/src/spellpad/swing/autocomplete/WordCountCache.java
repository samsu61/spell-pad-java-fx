package spellpad.swing.autocomplete;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import spellpad.eventhandlers.textmodifying.dictionary.DictionaryController;
import spellpad.eventhandlers.textmodifying.dictionary.MisspellingEntry;

/**
 *
 *
 * @author Jesse Allen
 */
public class WordCountCache implements Runnable, Resetable {

    private TernarySearchTree tree = new TernarySearchTree();
    private TernarySearchTree dictionary;
    private HashMap<String, List<String>> soundex;
    private JTextPane textPane;

    public HashMap<String, List<String>> getSoundex() {
        return soundex;
    }

    public void setSoundex(HashMap<String, List<String>> soundex) {
        this.soundex = soundex;
    }

    public void setDictionary(TernarySearchTree dictionary) {
        this.dictionary = dictionary;
    }

    public TernarySearchTree getDictionary() {
        return dictionary;
    }

    public WordCountCache(JTextPane textPane) {
        this.textPane = textPane;
    }

    public TernarySearchTree getAutocompleteTree() {
        return tree;
    }

    @Override
    public void reset() {
        tree.reset();
    }

    @Override
    public void run() {
        try {
            Document document = textPane.getDocument();
            String content = document.getText(0, document.getLength());
            Scanner scanner = new Scanner(content);
            scanner.useDelimiter(Pattern.compile("[\\s\\W]"));

            while (scanner.hasNext()) {
                String key = scanner.next();
                String key2 = key.toLowerCase();
                if (key2.length() < 3) {
                    continue;
                }
                tree.add(key2);
            }



        } catch (BadLocationException ex) {
            Logger.getLogger(WordCountCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
