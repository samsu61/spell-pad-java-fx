package spellpad.swing.autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author Jesse Allen
 */
public class WordCountCache implements Runnable, Resetable {

    //private HashMap<String, Integer> wordCounts = new HashMap<>();
    private ArrayList<Entry> wordCounts = new ArrayList<Entry>();
    private TernarySearchTree tree = new TernarySearchTree();
    private JTextPane textPane;

    public WordCountCache(JTextPane textPane) {
        this.textPane = textPane;
    }

    public ArrayList<Entry> getWordCount() {
        return wordCounts;
    }

    public TernarySearchTree getAutocompleteTree() {
        return tree;
    }

    @Override
    public void reset() {
        wordCounts.clear();
        tree.reset();
    }

    @Override
    public void run() {
        try {
            Document document = textPane.getDocument();
            String content = document.getText(0, document.getLength());
            Scanner scanner = new Scanner(content);
            scanner.useDelimiter(Pattern.compile("[\\s\\W]"));

            scan:
            while (scanner.hasNext()) {
                String key = scanner.next().toLowerCase();
                if (key.length() < 4) {
                    continue;
                }
                tree.add(key);
                for (int i = 0; i < wordCounts.size(); i++) {
                    if (key.equals(wordCounts.get(i).getWord())) {
                        wordCounts.get(i).seenAnother();
                        continue scan;
                    }
                }
                wordCounts.add(new Entry(1, key));
            }
            System.out.println("before sort");
            Collections.sort(wordCounts);
            System.out.println("after sort");
            for (Entry key : wordCounts) {
                System.out.print(key.getWord());
                System.out.print(":");
                System.out.print(key.getCount());
                System.out.print(":");
                System.out.println(tree.contains(key.getWord()));
            }

        } catch (BadLocationException ex) {
            Logger.getLogger(WordCountCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
