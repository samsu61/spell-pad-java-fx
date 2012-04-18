package spellpad.eventhandlers.textmodifying;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import spellpad.swing.SpellPadEditorPane;

/**
 *
 * @author Jesse Allen
 */
public abstract class BasicModificationActionListener implements ActionListener {

    SpellPadEditorPane textArea;

    public BasicModificationActionListener(SpellPadEditorPane editor) {
        textArea = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            doTextModify();
        } catch (BadLocationException ex) {
            Logger.getLogger(BoldActionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doTextModify() throws BadLocationException {
        StyledDocument textDocument = (StyledDocument) textArea.getDocument();
        Caret cursor = textArea.getCaret();
        int mark = cursor.getMark();
        int dot = cursor.getDot();
        textArea.setCaretPosition(textArea.getSelectionStart());
        SimpleAttributeSet attr = doSpecific();
        boolean mark_before_dot = mark < dot;
        if (mark_before_dot) {
            textDocument.setCharacterAttributes(mark, dot - mark, attr, false);
        } else {
            textDocument.setCharacterAttributes(dot, mark - dot, attr, false);
        }
        System.out.println(textArea.getText());
        textArea.requestFocus();
    }

    abstract SimpleAttributeSet doSpecific();
}
