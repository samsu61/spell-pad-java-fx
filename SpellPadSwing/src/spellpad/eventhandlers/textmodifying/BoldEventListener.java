package spellpad.eventhandlers.textmodifying;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import javax.swing.text.*;
import spellpad.swing.SpellPadSwing;

/**
 *
 * @author Jesse Allen
 */
public class BoldEventListener implements ActionListener{
    
    JEditorPane textArea;
    public BoldEventListener(JEditorPane editor){
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

//    private void bold(JToggleButton ourSource) throws BadLocationException {
//        StyledDocument textDocument = (StyledDocument) textArea.getDocument();
//        Caret cursor = textArea.getCaret();
//        int mark = cursor.getMark();
//        int dot = cursor.getDot();
//        SimpleAttributeSet attr = new SimpleAttributeSet();
//        StyleConstants.setBold(attr, ourSource.isSelected());
//        boolean mark_before_dot = mark < dot;
//        if(mark_before_dot){
//           textDocument.setCharacterAttributes(mark, dot-mark, attr, false);
//        }else{
//            textDocument.setCharacterAttributes(dot, mark-dot, attr, false); 
//        }
//        textArea.requestFocus();
//    }
    
    private void bold(JToggleButton ourSource) throws BadLocationException{
        Document theText = textArea.getDocument();
        String selection = textArea.getSelectedText();
        System.out.println(selection);
        selection.replace("<strong>", "").replace("</strong>", "");
        selection = "<strong>" + selection + "</strong>";
        Caret cursor = textArea.getCaret();
        int dot = cursor.getDot();
        int mark = cursor.getMark();
        StringBuilder content = new StringBuilder(textArea.getDocument().getText(0, textArea.getDocument().getLength()));
        content = dot < mark?content.replace(dot, mark, selection):content.replace(mark, dot, selection);
        System.out.println(textArea.getText());
        textArea.setContentType(SpellPadSwing.CONTENT_TYPE);
        textArea.setText(content.toString());
        System.out.println(textArea.getText());
        
    }
    
    
    
    
}
