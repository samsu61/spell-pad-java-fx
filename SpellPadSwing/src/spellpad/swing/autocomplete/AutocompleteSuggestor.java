package spellpad.swing.autocomplete;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author Jesse Allen
 */
public class AutocompleteSuggestor implements Runnable {

    private static enum Mode {

        INSERT, COMPLETION, NUMITEMSINENUM
    };
    private Mode mode = Mode.INSERT;
    private final String COMMIT_ACTION = "commit";
    private final String COMMIT_ACTION2 = "commitNoSpace";
    DocumentEvent event;
    JTextPane textArea;
    WordCountCache countCache;

    public AutocompleteSuggestor(JTextPane editPane, WordCountCache countCache) {
        textArea = editPane;
        bindKeys();
        this.countCache = countCache;
    }

    private void bindKeys() {
        textArea.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), COMMIT_ACTION);
        textArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), COMMIT_ACTION2);
        textArea.getActionMap().put(COMMIT_ACTION, new CommitActionNormal());
        textArea.getActionMap().put(COMMIT_ACTION2, new CommitActionNonNormal());
    }

    public void invoke(DocumentEvent ev) {
        event = ev;
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (event == null) {
            System.err.println("No event was updated to this suggestor");
            return;
        }
        //grab local cache of event
        DocumentEvent event = this.event;
        if (event.getLength() != 1) {
            return;
        }
        int position = event.getOffset();
        String content = getContent();
        int wordStart = getWordStart(position, content);

        if (position - wordStart < 3) {
            //Word is not long enough to trigger autocomplete
            //threshold
            return;
        }
        String prefix = content.substring(wordStart + 1, position + 1).toLowerCase();
        String suffix = countCache.getAutocompleteTree().search(prefix);
        if (!suffix.equals("")) {
            appendSuffix(position, suffix);
        }
        event = null;
    }

    private void appendSuffix(int position, String suffix) {
        try {
            //A suffix was found
            textArea.getDocument().insertString(position + 1, suffix, null);
        } catch (BadLocationException ex) {
            Logger.getLogger(AutocompleteSuggestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        textArea.setCaretPosition(position + suffix.length() + 1);
        textArea.moveCaretPosition(position + 1);
        mode = Mode.COMPLETION;
    }

    private String getContent() {
        try {
            Document doc = textArea.getDocument();
            String content = doc.getText(0, doc.getLength());
            return content;
        } catch (BadLocationException ex) {
            Logger.getLogger(AutocompleteSuggestor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private int getWordStart(int position, String content) {
        int wordStart;
        for (wordStart = position; wordStart >= 0; wordStart--) {
            if (!Character.isLetter(content.charAt(wordStart))) {
                break;
            }
        }
        return wordStart;
    }

    private abstract class CommitAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (mode == Mode.COMPLETION) {
                int position = textArea.getSelectionEnd();
                doimpl(position);
                mode = Mode.INSERT;
            } else {
                doInsert();
            }
        }

        abstract void doimpl(int position);

        abstract void doInsert();
    }

    private class CommitActionNormal extends CommitAction {

        @Override
        void doimpl(int position) {
            try {
                textArea.getDocument().insertString(position, "", null);
                textArea.setCaretPosition(position);
            } catch (BadLocationException ex) {
                Logger.getLogger(AutocompleteSuggestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        void doInsert() {
            //Nothing. Failure behaves as it should because swing does
            //what it does. I believe swing binds the spacebar.
        }
    }

    private class CommitActionNonNormal extends CommitAction {

        @Override
        void doimpl(int position) {
            textArea.setCaretPosition(position);
        }

        @Override
        void doInsert() {
            try {
                textArea.getDocument().insertString(textArea.getCaretPosition(), "\n", null);
            } catch (BadLocationException ex) {
                Logger.getLogger(AutocompleteSuggestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
