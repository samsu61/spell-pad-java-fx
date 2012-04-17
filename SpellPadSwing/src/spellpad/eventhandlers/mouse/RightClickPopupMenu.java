package spellpad.eventhandlers.mouse;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Jesse Allen
 */
public class RightClickPopupMenu extends JPopupMenu {
    
    JMenuItem copy;
    
    RightClickPopupMenu(){
        copy = new JMenuItem("Copy");
        add(copy);
    }
}
