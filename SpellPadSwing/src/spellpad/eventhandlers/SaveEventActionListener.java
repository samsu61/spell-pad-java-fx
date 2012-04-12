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
        if (chosenFile == null) {
            System.out.print("null file");
            return;
        }
        String textInArea = textArea.getText();
        System.out.println(textInArea);
        textInArea = SpellpadParser.restorePlainText(textInArea);
        System.out.println(textInArea);
        try {
            FileWriter fileWriter = new FileWriter(new File(chosenFile.getAbsoluteFile() + ".txt"));
            try (PrintWriter printer = new PrintWriter(fileWriter)) {
                printer.print(textInArea);
                printer.flush();
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
