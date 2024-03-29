package spellpad.eventhandlers.textmodifying.dictionary;

import java.awt.Color;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.apache.commons.codec.language.RefinedSoundex;
import spellpad.swing.SpellCheckWindow;
import spellpad.swing.autocomplete.TernarySearchTree;
import spellpad.swing.autocomplete.WordCountCache;

/**
 *
 * @author Jesse Allen
 */
public class DictionaryController {

    private LinkedList<MisspellingEntry> misspellings;
    public static LinkedList<String> ignorance = new LinkedList<>();
    private static LinkedList<String> temporaryIgnorance = new LinkedList<>();
    private SpellCheckWindow checkWindow;
    private JTextPane textArea;
    private JFrame mainWindow;
    private WordCountCache cache;

    public DictionaryController(JTextPane checkOn, WordCountCache dictionaryOwner, JFrame dispatcher) {
        textArea = checkOn;
        cache = dictionaryOwner;
        mainWindow = dispatcher;
        findMisspellings();
    }

    public MisspellingEntry getNext() {
        return misspellings.peek();
    }

    public List<String> getSuggestions(MisspellingEntry entry) {
        List<String> strings = new ArrayList<>();
        String word = entry.getText();
        TernarySearchTree dictionary = cache.getDictionary();
        RefinedSoundex soundex = RefinedSoundex.US_ENGLISH;
        List<String> soundexSuggestions = cache.getSoundex().get(soundex.encode(word));
        if (soundexSuggestions != null) {
            strings.addAll(soundexSuggestions);
        }
        String suffix = dictionary.search(word);
        String test = dictionary.getFakeWordPointer(word);
        if (test != null) {
            strings.add(test);
        }
        if (!suffix.isEmpty()) {
            strings.add(word + suffix);
        }
        for (int i = 0; i < word.length() - 1; i++) {
            checkProximateKeys(i, word, dictionary, strings);
            removeLetters(i, word, dictionary, strings);
            switchLetters(i, word, dictionary, strings);
            shortenedPrefix(word, i, dictionary, strings);
        }
        //Collections.sort(strings);
        return strings;
    }

    private void checkProximateKeys(int i, String word, TernarySearchTree dictionary, List<String> strings) {
        String suffix;
        ProximateKeys keys = ProximateKeys.getInstance();
        char[] chars = keys.getProximate(word.charAt(i));
        StringBuilder sb = new StringBuilder(word);
        for (char c : chars) {
            sb.replace(i, i + 1, Character.toString(c));
            if (dictionary.contains(sb.toString()) && !strings.contains(sb.toString())) {
                strings.add(sb.toString());
            }
            suffix = dictionary.search(sb.toString());
            if (!suffix.isEmpty()) {
                String completion = sb + suffix;
                if (!completion.equals(sb) && !strings.contains(completion)) {
                    strings.add(completion);
                }
            }
        }
    }

    private void removeLetters(int i, String word, TernarySearchTree dictionary, List<String> strings) {
        StringBuilder sb = new StringBuilder(word);
        sb.replace(i, i + 1, "");
        if (dictionary.contains(sb.toString()) && !strings.contains(sb.toString())) {
            strings.add(sb.toString());
        }
    }

    private void switchLetters(int i, String word, TernarySearchTree dictionary, List<String> strings) {
        String suffix;
        //switch letters exhaustively
        for (int j = i + 1; j < word.length(); j++) {
            StringBuilder newWord = new StringBuilder(word);
            char first = newWord.charAt(i);
            char second = newWord.charAt(j);
            newWord.setCharAt(i, second);
            newWord.setCharAt(j, first);
            if (dictionary.contains(newWord.toString()) && !strings.contains(newWord.toString())) {
                strings.add(newWord.toString());
            }
            suffix = dictionary.search(newWord.toString());
            if (!suffix.isEmpty()) {
                String completion = newWord + suffix;
                if (!completion.equals(word) && !strings.contains(completion)) {
                    strings.add(completion);
                }
            }
        }
    }

    private void shortenedPrefix(String word, int i, TernarySearchTree dictionary, List<String> strings) {
        String suffix;
        //try shortening the prefix
        String suggestion = word.substring(0, word.length() - 1 - i);
        suffix = dictionary.search(suggestion);
        if (!suffix.isEmpty()) {
            String completion = suggestion + suffix;
            if (!completion.equals(word) && !strings.contains(completion)) {
                strings.add(completion);
            }
        }
        if (dictionary.contains(suggestion) && !strings.contains(suggestion)) {
            strings.add(suggestion);
        }
    }

    public void addfake(String fake, String correction) {
        cache.getDictionary().addFake(fake, correction);
    }

    public void spellCheckInvoked() {
        mainWindow.setEnabled(false);
        if (!misspellings.isEmpty()) {
            checkWindow = new SpellCheckWindow(this);
            checkWindow.checkSpelling();
            checkWindow.setLocationRelativeTo(textArea.getParent());
            checkWindow.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No words are misspelled.", "Spell Check", JOptionPane.INFORMATION_MESSAGE);
            cancel();
        }
    }

    private void findMisspellings() {
        misspellings = new LinkedList<>();
//        SimpleAttributeSet attr = new SimpleAttributeSet();
//        StyleConstants.setForeground(attr, Color.black);
        StyledDocument textDocument = (StyledDocument) textArea.getDocument();
//        textDocument.setCharacterAttributes(0, textDocument.getLength(), attr, false);
//        StyleConstants.setForeground(attr, Color.red);
        try {
            String content = textDocument.getText(0, textArea.getDocument().getLength()).toLowerCase();
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
                if (word.length() > 1 && !cache.getDictionary().contains(word) && !ignorance.contains(word)) {
                    misspellings.add(new MisspellingEntry(i, word));
//                    textDocument.setCharacterAttributes(i, wordEnd, attr, false);
                }
                i = wordEnd + 1;
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(DictionaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addWordToDictionary(final String word) {
        cache.getDictionary().add(word);
        misspellings.poll();
        new Thread(new SaveWorker(word)).start();
    }

    public void ignoreWord(String ignored) {
        MisspellingEntry entry = misspellings.peek();
        if (entry.text.equals(ignored)) {
            temporaryIgnorance.add(ignored);
            misspellings.pollFirst();
        }
    }

    public void ingoreAllInstancesOfWord(String ignoredCompletely) {
        ignorance.add(ignoredCompletely);
        int i = misspellings.size();
        while (i > 0) {
            if (misspellings.get(--i).text.equals(ignoredCompletely)) {
                ignorance.add(misspellings.get(i).text);
                misspellings.remove(i);
                //i--;
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
        findMisspellings();
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
                    //i--;
                }
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(DictionaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        findMisspellings();
    }

    public void cancel() {
        temporaryIgnorance.clear();
        mainWindow.setEnabled(true);
        textArea.requestFocusInWindow();
        mainWindow.requestFocus();
    }

    static class SaveWorker implements Runnable {

        private final String word;

        public SaveWorker(String word) {
            this.word = word;
        }

        @Override
        public void run() {
            try (Scanner scanner = new Scanner(new FileInputStream(new File("dictionary.dat")))) {
                ArrayList<String> strings = new ArrayList<>();
                while (scanner.hasNext()) {
                    strings.add(scanner.nextLine());
                }
                strings.add(word);
                Collections.sort(strings);
                try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File("dictionary.dat")))) {
                    for (String s : strings) {
                        pw.println(s);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(DictionaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
