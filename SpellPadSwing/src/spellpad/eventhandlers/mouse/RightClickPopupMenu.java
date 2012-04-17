package spellpad.eventhandlers.mouse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import spellpad.eventhandlers.ClipboardHandler;

/**
 *
 * @author Jesse Allen
 */
public class RightClickPopupMenu extends JPopupMenu {
    
    JMenuItem copy;
    ClipboardHandler handler;
    private JEditorPane textArea;
    
    public RightClickPopupMenu(JEditorPane editor){
        textArea = editor;
        handler = new ClipboardHandler(editor);
        copy = new JMenuItem("Copy");
        copy.addActionListener(new CopyActionListener());
        add(copy);
    }
    
   
    private class CopyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Caret cursor =  textArea.getCaret();
            int a = cursor.getDot();
            int b = cursor.getMark();
            if( a != b){
                try {
                    String text = a > b
                            ? textArea.getText(b, a-b)
                            : textArea.getText(a, b-a);
                    handler.setClipboardContents(text);
                } catch (BadLocationException ex) {
                    Logger.getLogger(RightClickPopupMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
