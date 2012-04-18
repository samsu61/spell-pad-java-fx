package spellpad.eventhandlers.textmodifying;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Jesse Allen
 */
public class UndoActionListener implements ActionListener {

    UndoManager manager;

    public UndoActionListener(UndoManager undoManager) {
        manager = undoManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (manager.canUndo()) {
            manager.undo();
        }
    }
}
