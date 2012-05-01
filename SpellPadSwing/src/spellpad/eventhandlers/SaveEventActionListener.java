package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import spellpad.filetype.parsing.SpellpadParser;

/**
 * @author Jesse
 */
public class SaveEventActionListener implements ActionListener {

    private JEditorPane textArea;

    public SaveEventActionListener(JEditorPane text) {
        textArea = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (textArea == null) {
            return;
        }

        FileChooserDetails details = FileFilterFactory.getFileFromPopupDialogue(FileAction.SAVE);
        File chosenFile = details.getFile();
        FileNameExtensionFilter chosenFilter = details.getExtensionFilter();

        if (chosenFile == null || chosenFilter == null) {
            System.out.print("null file or filter");
            return;
        }

        String textInArea = textArea.getText();
        textInArea = SpellpadParser.restorePlainText(textInArea);
        System.out.println(textInArea);
        writeFile(chosenFile, chosenFilter, textInArea);
    }

    private void writeFile(File chosenFile, FileNameExtensionFilter chosenFilter, String textInArea) {
        try {
            String chosenDestination = chosenFile.getCanonicalPath();
            String comparisonExtension = '.' + chosenFilter.getExtensions()[0];
            if (!chosenDestination.endsWith(comparisonExtension)) {
                chosenFile = new File(chosenFile.getCanonicalPath() + comparisonExtension);
                if (!chosenFilter.accept(chosenFile)) {
                    System.out.println(chosenFile.getCanonicalPath());
                    throw new IOException("File name does not pass rule");
                }
            }
            FileWriter fileWriter = new FileWriter(chosenFile);
            try (final PrintWriter printer = new PrintWriter(fileWriter)) {
                printer.print(textInArea);
                printer.flush();
                printer.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(SaveEventActionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
