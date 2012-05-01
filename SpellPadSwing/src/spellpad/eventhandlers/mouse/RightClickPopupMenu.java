package spellpad.eventhandlers.mouse;

import javax.swing.JEditorPane;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import spellpad.eventhandlers.ClipboardHandler;
import spellpad.eventhandlers.textmodifying.CopyActionListener;
import spellpad.eventhandlers.textmodifying.PasteActionListener;

/**
 *
 * @author Jesse Allen
 */
public class RightClickPopupMenu extends JPopupMenu {

    JMenuItem copy;
    JMenuItem paste;
    ClipboardHandler handler;
    JEditorPane textArea;

    public RightClickPopupMenu(JEditorPane editor) {
        textArea = editor;
        handler = new ClipboardHandler();
        copy = new JMenuItem("Copy");
        copy.addActionListener(new CopyActionListener(handler, textArea));
        paste = new JMenuItem("Paste");
        paste.addActionListener(new PasteActionListener(handler, textArea));

        add(copy);
        add(paste);
    }
}