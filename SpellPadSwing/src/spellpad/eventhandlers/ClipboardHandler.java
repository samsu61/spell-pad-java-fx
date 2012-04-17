package spellpad.eventhandlers;

import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;

/**
 *
 * @author Jesse Allen
 */
public class ClipboardHandler implements ClipboardOwner {
    
    JEditorPane textArea;
    
    public ClipboardHandler(JEditorPane editor){
        textArea = editor;
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //Nothing
    }
    
    public void setClipboardContents(String selected){
        StringSelection selection = new StringSelection(selected);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, this);
    }
    
    public String getClipboardContents(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemSelection();
        Transferable contents = clipboard.getContents(null);
        boolean transferIsText = (contents != null) 
                && (contents.isDataFlavorSupported(DataFlavor.stringFlavor));
        String result = "";
        if(transferIsText){
            try {
                result = (String)contents.getTransferData(DataFlavor.stringFlavor);
            } catch (    UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(ClipboardHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    
}
