package spellpad.eventhandlers.textmodifying;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import javax.swing.text.*;
import spellpad.swing.SpellPadEditorPane;
import spellpad.swing.SpellPadSwing;

/**
 *
 * @author Jesse Allen
 */
public abstract class BasicModificationEventListener implements ActionListener {

    SpellPadEditorPane textArea;

    public BasicModificationEventListener(SpellPadEditorPane editor) {
        textArea = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton ourSource = (JButton) e.getSource();
        try {
            doTextModify(ourSource);
        } catch (BadLocationException ex) {
            Logger.getLogger(BoldEventListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doTextModify(JButton ourSource) throws BadLocationException {
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
