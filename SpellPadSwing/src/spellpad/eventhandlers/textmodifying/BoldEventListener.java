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
public class BoldEventListener extends BasicModificationEventListener {


    public BoldEventListener(SpellPadEditorPane editor) {
        super(editor);
    }

    @Override
    SimpleAttributeSet doSpecific() {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBold(attr, true);
        if (textArea.getCharacterAttributes().containsAttributes(attr)) {
            attr = new SimpleAttributeSet();
            StyleConstants.setBold(attr, false);
        }
        return attr;
    }
}
