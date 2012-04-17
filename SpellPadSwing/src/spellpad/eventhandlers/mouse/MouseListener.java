package spellpad.eventhandlers.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jesse Allen
 */
public class MouseListener extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            doPop(e);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            doPop(e);
        }
    }
    
    private void doPop(MouseEvent e) {
        RightClickPopupMenu popup = new RightClickPopupMenu();
        popup.show(e.getComponent(), e.getX(), e.getY());
    }
}
