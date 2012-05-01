package spellpad.eventhandlers;

import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jesse Allen
 */
public class ClipboardHandler implements ClipboardOwner {

    public ClipboardHandler() {
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //Nothing
    }

    public void setClipboardContents(String selected) {
        StringSelection selection = new StringSelection(selected);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, this);
    }

    public String getClipboardContents() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(this);
        boolean transferIsText = (contents != null)
                && (contents.isDataFlavorSupported(DataFlavor.stringFlavor));
        String result = "";
        if (transferIsText) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(ClipboardHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
