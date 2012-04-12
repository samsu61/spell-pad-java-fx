package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import spellpad.eventhandlers.SaveEventActionListener.FileFilterFactory;
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
        if (e == null) {
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(FileFilterFactory.getSpellpadFileFilter());
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.CANCEL_OPTION || response == JFileChooser.ERROR_OPTION) {
            return;
        }
        File chosenFile = fileChooser.getSelectedFile();
        if (chosenFile == null) {
            return;
        }
        if (!chosenFile.isFile()) {
            return;
        }
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
