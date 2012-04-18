package spellpad.eventhandlers;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Jesse
 */
public class DocumentChangedActionListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent de) {
        System.out.println(de);
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        System.out.println(de);
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        System.out.println(de);
    }
}
