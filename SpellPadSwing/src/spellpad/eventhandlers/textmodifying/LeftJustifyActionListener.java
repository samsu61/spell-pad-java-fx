/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.eventhandlers.textmodifying;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import spellpad.swing.SpellPadEditorPane;

/**
 *
 * @author Jesse Allen
 */
public class LeftJustifyActionListener extends BasicModificationActionListener{

    public LeftJustifyActionListener(SpellPadEditorPane editor) {
        super(editor);
    }
    
    @Override
    SimpleAttributeSet doSpecific() {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr, StyleConstants.ALIGN_LEFT);
//        if (textArea.getCharacterAttributes().containsAttributes(attr)) {
//            attr = new SimpleAttributeSet();
//            StyleConstants.setAlignment(attr, StyleConstants.ALIGN_LEFT);
//        }
        return attr;
    }
    
}
