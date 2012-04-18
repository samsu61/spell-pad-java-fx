package spellpad.eventhandlers.textmodifying;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import spellpad.swing.SpellPadEditorPane;

/**
 *
 * @author Jesse Allen
 */
public class ItalicsActionListener extends BasicModificationActionListener {

    public ItalicsActionListener(SpellPadEditorPane editor) {
        super(editor);
    }

    @Override
    SimpleAttributeSet doSpecific() {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setItalic(attr, true);
        if (textArea.getCharacterAttributes().containsAttributes(attr)) {
            attr = new SimpleAttributeSet();
            StyleConstants.setItalic(attr, false);
        }
        return attr;
    }
}
