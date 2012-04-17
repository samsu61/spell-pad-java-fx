package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JEditorPane;
import spellpad.filetype.parsing.SpellpadParser;

/**
 *
 * @author Jesse
 */
public class OpenEventActionListener implements ActionListener {

    private JEditorPane textDocument;

    public OpenEventActionListener(JEditorPane text) {
        textDocument = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == null || textDocument == null) {
            return;
        }
        FileChooserDetails details = FileFilterFactory.getFileFromPopupDialogue(FileAction.OPEN);
        File chosenFile = details.getFile();
        if (chosenFile == null) {
            return;
        }
        if (!chosenFile.isFile()) {
            return;
        }
        openFile(chosenFile);
        //Reposition caret to top of document.
        textDocument.getCaret().setDot(0);
    }

    private void openFile(File chosenFile) {
        StringBuilder fileContents = new StringBuilder();
        try {
            FileReader reader = new FileReader(chosenFile);
            char[] fileCharacters = new char[(int) chosenFile.length()];
            reader.read(fileCharacters);
            fileContents.append(fileCharacters);
            String preppedText = SpellpadParser.prepPlainText(fileContents.toString());
            textDocument.setText(preppedText.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
