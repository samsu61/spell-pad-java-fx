package spellpad.eventhandlers.textmodifying;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import spellpad.eventhandlers.ClipboardHandler;
import spellpad.eventhandlers.mouse.RightClickPopupMenu;

/**
 * TODO: FIX THIS CLASS!
 */
/**
 *
 * @author Jesse Allen
 */
public class PasteActionListener implements ActionListener {

    JEditorPane textArea;
    ClipboardHandler handler;

    public PasteActionListener(ClipboardHandler clipboard, JEditorPane textArea) {
        this.textArea = textArea;
        handler = clipboard;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            paste();
        } catch (BadLocationException ex) {
            Logger.getLogger(RightClickPopupMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void paste() throws BadLocationException {
        Caret cursor = textArea.getCaret();
        int location = cursor.getMark();
        int prevLoc = cursor.getDot();
        if (location == prevLoc) {
            noSelectionPaste(cursor);
        } else {
            pasteIntoSelection(location, prevLoc, cursor);
        }
    }

    private void pasteIntoSelection(int location, int prevLoc, Caret cursor) throws BadLocationException {
        String text = textArea.getDocument().getText(0, textArea.getDocument().getLength());
        StringBuilder stringEditted = new StringBuilder(text);
        boolean a_before_b = location > prevLoc;
        stringEditted = a_before_b
                ? stringEditted.replace(prevLoc, location, "")
                : stringEditted.replace(location, prevLoc, "");
        stringEditted.insert(
                a_before_b
                ? prevLoc
                : location,
                handler.getClipboardContents());
        textArea.setText(stringEditted.toString());
        cursor.setDot(0);
    }

    private void noSelectionPaste(Caret cursor) {
        StringBuilder stringEdited = new StringBuilder(textArea.getText());
        stringEdited.insert(cursor.getMark(), handler.getClipboardContents());
        textArea.setText(stringEdited.toString());
    }
}
