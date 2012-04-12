package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import spellpad.eventhandlers.SaveEventActionListener.FileFilterFactory;

/**
 *
 * @author Jesse
 */
public class OpenEventActionListener implements ActionListener {

    private HTMLDocument textDocument;

    public OpenEventActionListener(HTMLDocument text) {
        textDocument = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == null) {
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(FileFilterFactory.getSpellpadFileFilter());
        int response = fileChooser.showOpenDialog(null);
        if(response == JFileChooser.CANCEL_OPTION || response == JFileChooser.ERROR_OPTION){
            return;
        }
        File chosenFile = fileChooser.getSelectedFile();
        if (chosenFile == null) return;
        if (!chosenFile.isFile()) return;
        StringBuilder fileContents = new StringBuilder();
        try {
            FileReader reader = new FileReader(chosenFile);
            char[] fileCharacters = new char[(int)chosenFile.length()];
            reader.read(fileCharacters);
            fileContents.append(fileCharacters);
            
            try {
                Element element = textDocument.getElement(textDocument.getDefaultRootElement(), StyleConstants.NameAttribute, HTML.Tag.BODY);
                textDocument.setInnerHTML(element, "");
                textDocument.insertAfterStart(element, fileContents.toString());
            } catch (BadLocationException ex) {
                Logger.getLogger(OpenEventActionListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }  
    }
}
