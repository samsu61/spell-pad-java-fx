package spellpad.swing.autocomplete;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author Jesse Allen
 */
public class AutocompleteSuggestor implements Runnable {

    DocumentEvent event;
    JTextPane textArea;
    WordCountCache countCache;

    public AutocompleteSuggestor(JTextPane editPane, DocumentEvent ev, WordCountCache countCache) {
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
        if(position - wordStart < 3){
            return;
        }
        String prefix = content.substring(wordStart + 1, position).toLowerCase();
        String suffix = countCache.getAutocompleteTree().search(prefix);
        suffix = suffix.substring(1);
        if(!suffix.equals("")){
            try {
                //A suffix was found
                textArea.getDocument().insertString(wordStart, suffix, null);
            } catch (BadLocationException ex) {
                Logger.getLogger(AutocompleteSuggestor.class.getName()).log(Level.SEVERE, null, ex);
            }
            textArea.setCaretPosition(position + suffix.length());
            textArea.moveCaretPosition(position);
        }
        //Nothing
    }
}
