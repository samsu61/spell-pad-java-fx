package spellpad.eventhandlers.textmodifying;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import spellpad.swing.SpellPadEditorPane;

/**
 *
 * @author Jesse Allen
 */
public class BoldActionListener extends BasicModificationActionListener {

    public BoldActionListener(SpellPadEditorPane editor) {
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
