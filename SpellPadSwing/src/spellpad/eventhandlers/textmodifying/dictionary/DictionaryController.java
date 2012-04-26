package spellpad.eventhandlers.textmodifying.dictionary;

import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import spellpad.swing.SpellCheckWindow;

/**
 *
 * @author Jesse Allen
 */
public class DictionaryController implements Runnable{

    private LinkedList<MisspellingEntry> misspellings;
    private LinkedList<String> ignorance;
    private SpellCheckWindow window;
    
    public DictionaryController(JFrame relativeTo, JTextPane checkOn) {
    }

    public void ignoreWord(String ignored) {
    }

    public void ingoreAllInstancesOfWord(String ignoredCompletely) {
    }

    public void changeWord(String replaceWith) {
    }

    public void changeAllWord(String replaceWith) {
    }

    @Override
    public void run() {
        spellCheck();
    }
    
    private void spellCheck(){
        
    }
}
