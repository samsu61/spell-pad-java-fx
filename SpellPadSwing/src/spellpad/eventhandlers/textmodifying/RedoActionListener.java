package spellpad.eventhandlers.textmodifying;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Jesse Allen
 */
public class RedoActionListener implements ActionListener {

    UndoManager manager;

    public RedoActionListener(UndoManager undoManager) {
        manager = undoManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (manager.canRedo()) {
            manager.redo();
        }
    }
}