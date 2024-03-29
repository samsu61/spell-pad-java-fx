package spellpad.eventhandlers.textmodifying;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import spellpad.swing.SpellPadEditorPane;

/**
 *
 * @author Jesse Allen
 */
public class UnderlineActionListener extends BasicModificationActionListener {

    public UnderlineActionListener(SpellPadEditorPane textPane) {
        super(textPane);
    }

    @Override
    SimpleAttributeSet doSpecific() {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setUnderline(attr, true);
        if (textArea.getCharacterAttributes().containsAttributes(attr)) {
            attr = new SimpleAttributeSet();
            StyleConstants.setUnderline(attr, false);
        }
        return attr;
    }
}
