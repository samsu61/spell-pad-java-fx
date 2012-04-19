package spellpad.swing.autocomplete;

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

    private HashMap<String, Integer> wordCounts = new HashMap<>();
    private JTextPane textPane;

    public WordCountCache(JTextPane textPane) {
        this.textPane = textPane;
    }

    public HashMap<String, Integer> getWordCount() {
        return wordCounts;
    }

    public void reset() {
        wordCounts.clear();
    }

    @Override
    public void run() {
        try {
            Document document = textPane.getDocument();
            String content = document.getText(0, document.getLength());
            Scanner scanner = new Scanner(content);
            scanner.useDelimiter(Pattern.compile("\\s"));

            while (scanner.hasNext()) {
                String key = scanner.next();
                int value = 0;
                if (wordCounts.containsKey(key)) {
                    value = wordCounts.get(key);
                }
                wordCounts.put(key, ++value);
            }
            for (String key : wordCounts.keySet()) {
                System.out.print(key);
                System.out.print(":");
                System.out.println(wordCounts.get(key));
            }

        } catch (BadLocationException ex) {
            Logger.getLogger(WordCountCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
