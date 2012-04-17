package spellpad.eventhandlers.textmodifying;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import javax.swing.text.*;

/**
 *
 * @author Jesse Allen
 */
public class ItalicsEventListener implements ActionListener{
    JEditorPane textArea;
    public ItalicsEventListener(JEditorPane editor){
        textArea = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JToggleButton ourSource = (JToggleButton)e.getSource();
        try {
            bold(ourSource);
        } catch (BadLocationException ex) {
            Logger.getLogger(BoldEventListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void bold(JToggleButton ourSource) throws BadLocationException {
        StyledDocument textDocument = (StyledDocument) textArea.getDocument();
        
        Caret cursor = textArea.getCaret();
        int mark = cursor.getMark();
        int dot = cursor.getDot();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setItalic(attr, ourSource.isSelected());
        boolean mark_before_dot = mark < dot;
        if(mark_before_dot){
           textDocument.setCharacterAttributes(mark, dot-mark, attr, false);
        }else{
            textDocument.setCharacterAttributes(dot, mark-dot, attr, false); 
        }
        textArea.requestFocus();
    }
}
