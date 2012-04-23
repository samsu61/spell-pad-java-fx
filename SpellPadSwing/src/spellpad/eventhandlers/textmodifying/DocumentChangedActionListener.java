package spellpad.eventhandlers.textmodifying;

import java.util.Timer;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import spellpad.swing.autocomplete.AutocompleteSuggestor;
import spellpad.swing.autocomplete.RecountMethod;
import spellpad.swing.autocomplete.WordCountCache;

/**
 *
 * @author Jesse
 */
public class DocumentChangedActionListener implements DocumentListener {

    WordCountCache cache;
    JTextPane textArea;
    AutocompleteSuggestor suggestor;
    Timer timer = new Timer("Recount Timer");

    public DocumentChangedActionListener(JTextPane area, WordCountCache cache) {
        textArea = area;
        this.cache = cache;
        suggestor = new AutocompleteSuggestor(textArea, cache);
        timer.scheduleAtFixedRate(new RecountMethod(this.cache), 1000, 250);
    }

    @Override
    public void insertUpdate(DocumentEvent de) {
        suggestor.invoke(de);
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        System.out.println("changed");
    }
}