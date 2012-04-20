package spellpad.swing.autocomplete;

import java.util.Collections;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author Jesse Allen
 */
public class AutocompleteSuggestor implements Runnable {

    DocumentEvent event;
    JEditorPane textArea;
    WordCountCache countCache;

    public AutocompleteSuggestor(JEditorPane editPane, DocumentEvent ev, WordCountCache countCache) {
        textArea = editPane;
        event = ev;
        this.countCache = countCache;
    }

    @Override
    public void run() {
        if (event.getLength() != 1) {
            return;
        }
        int position = event.getOffset();
        String content = null;
        try {
            Document doc = textArea.getDocument();
            content = doc.getText(0, doc.getLength());
        } catch (BadLocationException ex) {
        }
        int wordStart;
        for (wordStart = position; wordStart >= 0; wordStart--) {
            if (!Character.isLetter(content.charAt(wordStart))) {
                break;
            }
        }
        if(position - wordStart < 2){
            return;
        }
        String prefix = content.substring(wordStart + 1).toLowerCase();
        
    }
}
