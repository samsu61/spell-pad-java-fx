package spellpad.eventhandlers.textmodifying;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JEditorPane;
import spellpad.eventhandlers.ClipboardHandler;

/**
 *
 * @author Jesse Allen
 */
public class CopyActionListener implements ActionListener {

    ClipboardHandler handler;
    JEditorPane textArea;

    public CopyActionListener(ClipboardHandler clipBoard, JEditorPane textArea) {
        handler = clipBoard;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        copy();
    }

    private void copy() {
        handler.setClipboardContents(textArea.getSelectedText());
    }
}
