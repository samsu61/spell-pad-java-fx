package spellpad.eventhandlers.textmodifying.dictionary;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import spellpad.swing.SpellCheckWindow;
import spellpad.swing.autocomplete.WordCountCache;

/**
 *
 * @author Jesse Allen
 */
public class DictionaryController {

    private LinkedList<MisspellingEntry> misspellings;
    private LinkedList<String> ignorance;
    private SpellCheckWindow checkWindow;
    private JTextPane textArea;
    private WordCountCache cache;

    public DictionaryController(JTextPane checkOn, WordCountCache dictionaryOwner) {
        ignorance = new LinkedList<>();
        textArea = checkOn;
        cache = dictionaryOwner;
        findMisspellings();
    }

    public void spellCheckInvoked() {
        for (int i = 0; i < misspellings.size(); i++) {
            List<String> strings = new LinkedList<>();
            strings.add("fumble");
            strings.add("fumple");
            strings.add("frumpy");
            SpellCheckWindow spellWindow = new SpellCheckWindow(this, misspellings.get(i).text, strings);
            spellWindow.setLocationRelativeTo(textArea.getParent());
            spellWindow.setVisible(true);
            while(spellWindow.isBusy()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DictionaryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
            spellWindow.dispose();
        }
    }

    private void findMisspellings() {
        misspellings = new LinkedList<>();
        try {
            String content = textArea.getDocument().getText(0, textArea.getDocument().getLength());
            for (int i = 0; i < content.length();) {
                if (!Character.isLetter(content.charAt(i))) {
                    i++;
                    continue;
                }
                int wordEnd;
                for (wordEnd = i; wordEnd < content.length();) {
                    if (Character.isLetter(content.charAt(wordEnd))) {
                        wordEnd++;
                    } else {
                        break;
                    }
                }
                String word = content.substring(i, wordEnd);
                if (word.length() > 1 && !cache.getDictionary().contains(word)) {
                    misspellings.add(new MisspellingEntry(i, word));
                }
                i = wordEnd + 1;


            }
        } catch (BadLocationException ex) {
            Logger.getLogger(DictionaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addWordToDictionary(String word) {
        //start thread to modify dictionary
        //perhaps not start thread, run in this thred, block until done.
        //reload dictionary
        //remove all instances of misspelling
    }

    public void ignoreWord(String ignored) {
        MisspellingEntry entry = misspellings.peek();
        if (entry.text.equals(ignored)) {
            misspellings.pollFirst();
        }
    }

    public void ingoreAllInstancesOfWord(String ignoredCompletely) {
        ignorance.add(ignoredCompletely);
        int i = misspellings.size();
        while (i > 0) {
            if (misspellings.get(--i).text.equals(ignoredCompletely)) {
                misspellings.remove(i);
                i--;
            }
        }

    }

    public void changeWord(String replaceWith) {
        try {
            MisspellingEntry entry = misspellings.poll();
            textArea.getDocument().remove(entry.begins, entry.text.length());
            textArea.getDocument().insertString(entry.begins, replaceWith, null);


        } catch (BadLocationException ex) {
            Logger.getLogger(DictionaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeAllWord(String replaceWith) {
        String original = misspellings.peek().text;
        try {
            int i = misspellings.size();
            while (i > 0) {
                if (misspellings.get(--i).text.equals(original)) {
                    MisspellingEntry entry = misspellings.remove(i);
                    textArea.getDocument().remove(entry.begins, entry.text.length());
                    textArea.getDocument().insertString(entry.begins, replaceWith, null);
                    i--;


                }
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(DictionaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancel() {
        //do something here.......
    }
}
