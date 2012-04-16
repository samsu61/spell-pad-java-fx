package spellpad.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
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
        if (e == null || textArea == null) {
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(FileFilterFactory.getSpellpadFileFilter());
        int response = fileChooser.showSaveDialog(textArea.getParent());
        if (response == JFileChooser.CANCEL_OPTION || response == JFileChooser.ERROR_OPTION) {
            System.out.println("not successed");
            return;
        }
        File chosenFile = fileChooser.getSelectedFile();
        FileNameExtensionFilter chosenFilter = (FileNameExtensionFilter) fileChooser.getFileFilter();
        if (chosenFile == null || chosenFilter == null) {
            System.out.print("null file or filter");
            return;
        }
        String textInArea = textArea.getText();
        //System.out.println(textInArea);
        textInArea =  SpellpadParser.restorePlainText(textInArea);

        System.out.println(textInArea);
        try {
            String chosenDestination = chosenFile.getCanonicalPath();
            if(!chosenDestination.endsWith('.' + chosenFilter.getExtensions()[0])){
                chosenFile = new File(chosenFile.getCanonicalPath() + '.' + chosenFilter.getExtensions()[0]);
                if(!chosenFilter.accept(chosenFile)){
                    throw new IOException("File name does not pass rule");
                }
            }
            FileWriter fileWriter = new FileWriter(chosenFile);
            try (PrintWriter printer = new PrintWriter(fileWriter)) {
                printer.print(textInArea);
                printer.flush();
                printer.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static final class FileFilterFactory {

        static FileFilter getSpellpadFileFilter() {
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text Files", "txt");
            return extensionFilter;
        }
    }
}
